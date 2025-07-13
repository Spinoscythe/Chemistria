package io.github.spinoscythe.chemistria.recipe;

import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ChemistriaRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, Chemistria.MOD_ID);
    public static final DeferredRegister<RecipeType<?>> RECIPE_TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, Chemistria.MOD_ID);

    public static final DeferredHolder<RecipeSerializer<?>, RecipeSerializer<BoilerTankRecipe>> BOILER_TANK =
            RECIPE_SERIALIZERS.register("boiler_tank", () -> new SimpleRecipeSerializer<>(BoilerTankRecipe.CODEC, BoilerTankRecipe.STREAM_CODEC));

    public static final DeferredHolder<RecipeType<?>, RecipeType<BoilerTankRecipe>> BOILER_TANK_TYPE =
            RECIPE_TYPES.register("boiler_tank", SimpleRecipeType::new);

    public static void register(IEventBus eventBus) {
        RECIPE_TYPES.register(eventBus);
        RECIPE_SERIALIZERS.register(eventBus);
    }
}
