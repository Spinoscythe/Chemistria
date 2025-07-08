package io.github.spinoscythe.chemistria.datagen;

import com.google.common.collect.ImmutableList;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.level.ItemLike;

import java.util.concurrent.CompletableFuture;

public class ChemistriaRecipeProvider extends RecipeProvider {
    public static final ImmutableList<ItemLike> ONYX_SMELTABLES = ImmutableList.of(ChemistriaBlocks.ONYX_ORE, ChemistriaBlocks.DEEPSLATE_ONYX_ORE, ChemistriaItems.RAW_ONYX);

    public ChemistriaRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        super(registries, output);
    }

    @Override
    protected void buildRecipes() {
        this.oreSmelting(ONYX_SMELTABLES, RecipeCategory.MISC, ChemistriaItems.ONYX_INGOT, 0.7F, 200, "onyx_ingot");
        this.oreBlasting(ONYX_SMELTABLES, RecipeCategory.MISC, ChemistriaItems.ONYX_INGOT, 0.7F, 200, "onyx_ingot");
    }

    public static class Runner extends RecipeProvider.Runner {
        protected Runner(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> registries) {
            super(packOutput, registries);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
            return new ChemistriaRecipeProvider(registries, output);
        }

        @Override
        public String getName() {
            return "Chemistria Recipes";
        }
    }
}
