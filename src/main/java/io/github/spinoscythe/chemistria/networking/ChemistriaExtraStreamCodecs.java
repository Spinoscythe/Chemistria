package io.github.spinoscythe.chemistria.networking;

import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.ItemStackHandler;

public class ChemistriaExtraStreamCodecs {
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackHandler> ITEM_STACK_HANDLER =
            ItemStack.STREAM_CODEC.apply(ByteBufCodecs.list()).map(itemStacks -> {
                NonNullList<ItemStack> list = NonNullList.createWithCapacity(itemStacks.size());
                list.addAll(itemStacks);
                return new ItemStackHandler(list);
            }, ish -> {
                var container = new SimpleContainer(ish.getSlots());
                for (int i = 0; i < container.getContainerSize(); i++) {
                    container.setItem(i, ish.getStackInSlot(i));
                }
                return container.getItems();
            });
}
