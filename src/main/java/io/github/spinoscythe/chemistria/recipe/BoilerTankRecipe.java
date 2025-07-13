package io.github.spinoscythe.chemistria.recipe;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import io.github.spinoscythe.chemistria.block.entity.BoilerTankBlockEntity;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public record BoilerTankRecipe(String group, ItemStack fuel, Ingredient fluidBucket, ItemStack gasChamber, float xp) implements Recipe<BoilerTankRecipe.Input> {
    public static final MapCodec<BoilerTankRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
            Codec.STRING.optionalFieldOf("group", "").forGetter(BoilerTankRecipe::group),
            ItemStack.CODEC.fieldOf("fuel").forGetter(BoilerTankRecipe::fuel),
            Ingredient.CODEC.fieldOf("fluidBucket").forGetter(BoilerTankRecipe::fluidBucket),
            ItemStack.CODEC.fieldOf("gas").forGetter(BoilerTankRecipe::gasChamber),
            Codec.FLOAT.fieldOf("experience").orElse(0.0F).forGetter(BoilerTankRecipe::xp)
    ).apply(instance, BoilerTankRecipe::new));

    public static final StreamCodec<RegistryFriendlyByteBuf, BoilerTankRecipe> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8, BoilerTankRecipe::group,
            ItemStack.STREAM_CODEC, BoilerTankRecipe::fuel,
            Ingredient.CONTENTS_STREAM_CODEC, BoilerTankRecipe::fluidBucket,
            ItemStack.STREAM_CODEC, BoilerTankRecipe::gasChamber,
            ByteBufCodecs.FLOAT, BoilerTankRecipe::xp,
            BoilerTankRecipe::new
    );

    @Override
    public boolean matches(BoilerTankRecipe.Input input, Level level) {
        return fluidBucket.test(input.getItem(BoilerTankBlockEntity.BUCKET_SLOT));
    }

    @Override
    public ItemStack assemble(BoilerTankRecipe.Input input, HolderLookup.Provider registries) {
        return this.gasChamber.copy();
    }

    @Override
    public RecipeSerializer<? extends Recipe<BoilerTankRecipe.Input>> getSerializer() {
        return ChemistriaRecipes.BOILER_TANK.get();
    }

    @Override
    public RecipeType<? extends Recipe<BoilerTankRecipe.Input>> getType() {
        return ChemistriaRecipes.BOILER_TANK_TYPE.get();
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return new RecipeBookCategory();
    }

    public record Input(ItemStack fuel, ItemStack bucket, ItemStack gasChamber) implements RecipeInput {
        @Override
        public @NotNull ItemStack getItem(int index) {
            return switch (index) {
                case 0 -> fuel;
                case 1 -> bucket;
                case 2 -> gasChamber;
                default -> throw new IllegalStateException("Unexpected value: " + index);
            };
        }

        @Override
        public int size() {
            return 3;
        }
    }
}
