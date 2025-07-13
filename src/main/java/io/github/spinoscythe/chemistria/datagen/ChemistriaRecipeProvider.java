package io.github.spinoscythe.chemistria.datagen;

import com.google.common.collect.ImmutableList;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
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

        this.shaped(RecipeCategory.TOOLS, ChemistriaItems.ONYX_AXE)
                .define('#', Items.STICK)
                .define('X', ChemistriaTags.Items.ONYX_TOOL_MATERIALS)
                .pattern("XX")
                .pattern("X#")
                .pattern(" #")
                .unlockedBy(getHasName(ChemistriaItems.ONYX_INGOT), this.has(ChemistriaTags.Items.ONYX_TOOL_MATERIALS))
                .save(this.output);
        this.nineBlockStorageRecipes(RecipeCategory.MISC, ChemistriaItems.ONYX_INGOT, RecipeCategory.BUILDING_BLOCKS, ChemistriaBlocks.ONYX_BLOCK);
        this.nineBlockStorageRecipes(RecipeCategory.MISC, ChemistriaItems.RAW_ONYX, RecipeCategory.BUILDING_BLOCKS, ChemistriaBlocks.RAW_ONYX_BLOCK);
        this.shaped(RecipeCategory.COMBAT, ChemistriaItems.ONYX_BOOTS)
                .define('X', ChemistriaItems.ONYX_INGOT)
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ChemistriaItems.ONYX_INGOT), this.has(ChemistriaTags.Items.ONYX_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ChemistriaItems.ONYX_CHESTPLATE)
                .define('X', Items.DIAMOND)
                .pattern("X X")
                .pattern("XXX")
                .pattern("XXX")
                .unlockedBy("has_diamond", this.has(Items.DIAMOND))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ChemistriaItems.ONYX_HELMET)
                .define('X', Items.DIAMOND)
                .pattern("XXX")
                .pattern("X X")
                .unlockedBy("has_diamond", this.has(Items.DIAMOND))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ChemistriaItems.ONYX_HOE)
                .define('#', Items.STICK)
                .define('X', ItemTags.DIAMOND_TOOL_MATERIALS)
                .pattern("XX")
                .pattern(" #")
                .pattern(" #")
                .unlockedBy("has_diamond", this.has(ItemTags.DIAMOND_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ChemistriaItems.ONYX_LEGGINGS)
                .define('X', ChemistriaItems.ONYX_INGOT)
                .pattern("XXX")
                .pattern("X X")
                .pattern("X X")
                .unlockedBy(getHasName(ChemistriaItems.ONYX_INGOT), this.has(ChemistriaItems.ONYX_INGOT))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ChemistriaItems.ONYX_PICKAXE)
                .define('#', Items.STICK)
                .define('X', ChemistriaTags.Items.ONYX_TOOL_MATERIALS)
                .pattern("XXX")
                .pattern(" # ")
                .pattern(" # ")
                .unlockedBy(getHasName(ChemistriaItems.ONYX_INGOT), this.has(ChemistriaTags.Items.ONYX_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.TOOLS, ChemistriaItems.ONYX_SHOVEL)
                .define('#', Items.STICK)
                .define('X', ChemistriaTags.Items.ONYX_TOOL_MATERIALS)
                .pattern("X")
                .pattern("#")
                .pattern("#")
                .unlockedBy(getHasName(ChemistriaItems.ONYX_INGOT), this.has(ChemistriaTags.Items.ONYX_TOOL_MATERIALS))
                .save(this.output);
        this.shaped(RecipeCategory.COMBAT, ChemistriaItems.ONYX_SWORD)
                .define('#', Items.STICK)
                .define('X', ChemistriaTags.Items.ONYX_TOOL_MATERIALS)
                .pattern("X")
                .pattern("X")
                .pattern("#")
                .unlockedBy(getHasName(ChemistriaItems.ONYX_INGOT), this.has(ChemistriaTags.Items.ONYX_TOOL_MATERIALS))
                .save(this.output);
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
