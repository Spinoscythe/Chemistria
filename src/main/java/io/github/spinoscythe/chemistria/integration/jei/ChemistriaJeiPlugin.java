package io.github.spinoscythe.chemistria.integration.jei;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.integration.jei.categories.BoilerTankRecipeCategory;
import io.github.spinoscythe.chemistria.recipe.BoilerTankRecipe;
import io.github.spinoscythe.chemistria.recipe.ChemistriaRecipes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.types.IRecipeType;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeHolder;

import java.util.ArrayList;

@JeiPlugin
public class ChemistriaJeiPlugin implements IModPlugin {
    @Override
    public ResourceLocation getPluginUid() {
        return Chemistria.rl("jei_plugin");
    }

    @Override
    public void registerRecipes(IRecipeRegistration registration) {
        var boilerTankRecipes = new ArrayList<RecipeHolder<BoilerTankRecipe>>();
        Minecraft.getInstance().level.registryAccess().lookup(Registries.RECIPE).stream().flatMap(HolderLookup::listElements).forEach(holder -> {
            if (holder.value() instanceof BoilerTankRecipe recipe) {
                boilerTankRecipes.add(new RecipeHolder<>(ResourceKey.create(Registries.RECIPE, Chemistria.rl("boiler_tank")), recipe));
            }
        });
        registration.addRecipes(IRecipeType.create(ChemistriaRecipes.BOILER_TANK_TYPE.get()), boilerTankRecipes);
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registration) {
        registration.addRecipeCategories(new BoilerTankRecipeCategory(registration.getJeiHelpers().getGuiHelper()));
    }
}
