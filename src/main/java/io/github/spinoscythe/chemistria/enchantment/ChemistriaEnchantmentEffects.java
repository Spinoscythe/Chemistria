package io.github.spinoscythe.chemistria.enchantment;

import com.mojang.serialization.MapCodec;
import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ChemistriaEnchantmentEffects {
    public static final DeferredRegister<MapCodec<? extends EnchantmentEntityEffect>> ENCHANTMENT_EFFECTS =
            DeferredRegister.create(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Chemistria.MOD_ID);

    public static final DeferredHolder<MapCodec<? extends EnchantmentEntityEffect>, MapCodec<GoldenBlessingEnchantmentEntityEffect>> GOLDEN_BLESSING =
            ENCHANTMENT_EFFECTS.register("golden_blessing", () -> GoldenBlessingEnchantmentEntityEffect.CODEC);

    public static void register(IEventBus eventBus) {
        ENCHANTMENT_EFFECTS.register(eventBus);
    }
}
