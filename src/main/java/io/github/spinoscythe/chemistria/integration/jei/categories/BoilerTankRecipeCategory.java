package io.github.spinoscythe.chemistria.integration.jei.categories;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import io.github.spinoscythe.chemistria.recipe.BoilerTankRecipe;
import io.github.spinoscythe.chemistria.screen.BoilerTankScreen;
import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.ingredient.IRecipeSlotsView;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.category.IRecipeCategory;
import mezz.jei.api.recipe.types.IRecipeType;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

public class BoilerTankRecipeCategory implements IRecipeCategory<BoilerTankRecipe> {
    public static final ResourceLocation UID = Chemistria.rl("boiler_tank_category");

    private final IDrawable icon;
    private final IDrawable background;

    public BoilerTankRecipeCategory(IGuiHelper helper) {
        this.icon = helper.createDrawableItemStack(ChemistriaItems.RAW_ONYX.toStack());
        this.background = helper.createDrawable(BoilerTankScreen.TEXTURE, 0, 0, 176, 85);
    }

    @Override
    public IRecipeType<BoilerTankRecipe> getRecipeType() {
        return IRecipeType.create(UID, BoilerTankRecipe.class);
    }

    @Override
    public Component getTitle() {
        return Component.literal("Boiler Tank Recipe Category");
    }

    @Override
    public @Nullable IDrawable getIcon() {
        return this.icon;
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, BoilerTankRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 17, 37).add(recipe.fuel());
        builder.addSlot(RecipeIngredientRole.INPUT, 79, 17).add(recipe.fluidBucket());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 79, 58).add(recipe.gasChamber());
    }

    @Override
    public void draw(BoilerTankRecipe recipe, IRecipeSlotsView recipeSlotsView, GuiGraphics guiGraphics, double mouseX, double mouseY) {
        this.background.draw(guiGraphics);
    }
}
