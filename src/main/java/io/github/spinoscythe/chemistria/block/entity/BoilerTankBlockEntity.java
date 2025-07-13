package io.github.spinoscythe.chemistria.block.entity;

import io.github.spinoscythe.chemistria.networking.payloads.ItemStackSyncPayload;
import io.github.spinoscythe.chemistria.recipe.BoilerTankRecipe;
import io.github.spinoscythe.chemistria.recipe.ChemistriaRecipes;
import io.github.spinoscythe.chemistria.screen.BoilerTankMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.protocol.Packet;
import net.minecraft.network.protocol.game.ClientGamePacketListener;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerData;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.neoforged.neoforge.fluids.FluidStack;
import net.neoforged.neoforge.fluids.capability.templates.FluidTank;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.PacketDistributor;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class BoilerTankBlockEntity extends BlockEntity implements MenuProvider, FluidMachine, InventoryMachine {
    public static final int FUEL_SLOT = 0;
    public static final int BUCKET_SLOT = 1;
    public static final int GAS_SLOT = 2;

    public final FluidTank fluidTank = new FluidTank(64000) {
        @Override
        protected void onContentsChanged() {
            setChanged();
        }

        @Override
        public int getTanks() {
            return 2;
        }
    };

    public final ItemStackHandler itemStackHandler = new ItemStackHandler(3) {
        @Override
        protected void onContentsChanged(int slot) {
            setChanged();
            PacketDistributor.sendToAllPlayers(new ItemStackSyncPayload(this, worldPosition));
        }
    };

    private int progress;
    private int maxProgress = 72;
    private final ContainerData containerData;

    public BoilerTankBlockEntity(BlockPos pos, BlockState blockState) {
        super(ChemistriaBlockEntityTypes.BOILER_TANK.get(), pos, blockState);

        this.containerData = new ContainerData() {
            @Override
            public int get(int index) {
                return switch (index) {
                    case 0 -> progress;
                    case 1 -> maxProgress;
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                };
            }

            @Override
            public void set(int index, int value) {
                switch (index) {
                    case 0 -> progress = value;
                    case 1 -> maxProgress = value;
                    default -> throw new IllegalStateException("Unexpected value: " + index);
                }
            }

            @Override
            public int getCount() {
                return 2;
            }
        };
    }

    @Override
    protected void loadAdditional(ValueInput input) {
        super.loadAdditional(input);
        fluidTank.deserialize(input);
        itemStackHandler.deserialize(input);
        progress = input.getIntOr("boiler_tank.progress", 0);
        maxProgress = input.getIntOr("boiler_tank.max_progress", 0);
    }

    @Override
    protected void saveAdditional(ValueOutput output) {
        super.saveAdditional(output);
        fluidTank.serialize(output);
        itemStackHandler.serialize(output);
        output.putInt("boiler_tank.progress", progress);
        output.putInt("boiler_tank.max_progress", maxProgress);
    }

    @Override
    public Component getDisplayName() {
        return Component.literal("Boiler Tank");
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int containerId, Inventory playerInventory, Player player) {
        return new BoilerTankMenu(containerId, playerInventory, this, this.containerData);
    }

    @Override
    public FluidStack getFluid() {
        return fluidTank.getFluid();
    }

    @Override
    public void setFluid(FluidStack fluid) {
        fluidTank.setFluid(fluid);
    }

    @Override
    public void setHandler(ItemStackHandler handler) {
        for (int i = 0; i < handler.getSlots(); i++) {
            handler.setStackInSlot(i, this.itemStackHandler.getStackInSlot(i));
        }
    }

    private void resetProgress() {
        progress = 0;
        maxProgress = 72;
    }

    private boolean hasCraftingFinished() {
        return this.progress >= this.maxProgress;
    }

    private void increaseCraftingProgress() {
        progress++;
    }

    public void serverTick() {
        if (hasRecipe()) {
            increaseCraftingProgress();
            setChanged(level, getBlockPos(), getBlockState());

            if (hasCraftingFinished()) {
                craftItem();
                resetProgress();
            }
        }
    }

    @Override
    public CompoundTag getUpdateTag(HolderLookup.Provider registries) {
        return saveWithoutMetadata(registries);
    }

    @Override
    public @Nullable Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    private boolean hasRecipe() {
        Optional<RecipeHolder<BoilerTankRecipe>> recipe = getCurrentRecipe();
        if (recipe.isEmpty()) {
            return false;
        }

        ItemStack output = recipe.get().value().gasChamber();
        return canInsertAmountIntoOutputSlot(output.getCount()) && canInsertItemIntoOutputSlot(output);
    }

    private boolean canInsertItemIntoOutputSlot(ItemStack output) {
        return itemStackHandler.getStackInSlot(GAS_SLOT).isEmpty() || itemStackHandler.getStackInSlot(GAS_SLOT).getItem() == output.getItem();
    }

    private Optional<RecipeHolder<BoilerTankRecipe>> getCurrentRecipe() {
        BoilerTankRecipe.Input input = new BoilerTankRecipe.Input(
                itemStackHandler.getStackInSlot(FUEL_SLOT),
                itemStackHandler.getStackInSlot(BUCKET_SLOT),
                itemStackHandler.getStackInSlot(GAS_SLOT));
        return ((ServerLevel) level).recipeAccess().getRecipeFor(ChemistriaRecipes.BOILER_TANK_TYPE.get(), input, level);
    }

    private boolean canInsertAmountIntoOutputSlot(int count) {
        int maxCount = itemStackHandler.getStackInSlot(GAS_SLOT).isEmpty() ? 64 : itemStackHandler.getStackInSlot(GAS_SLOT).getMaxStackSize();
        int currentCount = itemStackHandler.getStackInSlot(GAS_SLOT).getCount();

        return maxCount >= count + currentCount;
    }

    private void craftItem() {
        Optional<RecipeHolder<BoilerTankRecipe>> recipe = getCurrentRecipe();
        ItemStack output = recipe.get().value().gasChamber();

        itemStackHandler.extractItem(BUCKET_SLOT, 1, false);
        itemStackHandler.setStackInSlot(GAS_SLOT, new ItemStack(output.getItem(),
                itemStackHandler.getStackInSlot(GAS_SLOT).getCount() + output.getCount()));
    }
}
