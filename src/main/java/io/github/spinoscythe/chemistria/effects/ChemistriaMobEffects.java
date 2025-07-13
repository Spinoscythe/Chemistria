package io.github.spinoscythe.chemistria.effects;

import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ChemistriaMobEffects {
    public static final DeferredRegister<MobEffect> MOB_EFFECTS =
            DeferredRegister.create(Registries.MOB_EFFECT, Chemistria.MOD_ID);

    public static final DeferredHolder<MobEffect, MobEffect> WEALTHY = MOB_EFFECTS.register("wealthy",
            () -> new SimpleMobEffect(MobEffectCategory.BENEFICIAL, 0xFAEB1E, (level, entity, amplifier) -> {
                if (entity instanceof Player player) {
                    for (int i = 0; i < 90; i++) {
                        player.getInventory().add(new ItemStack(Items.GOLD_INGOT));
                    }
                }
            }));

    public static final DeferredHolder<MobEffect, MobEffect> BLEEDING = MOB_EFFECTS.register("bleeding",
            () -> new SimpleMobEffect(MobEffectCategory.HARMFUL, 0xc70e0e,
                    (level, entity, amplifier) -> entity.hurtServer(level, level.damageSources().generic(), 1.0F)));

    public static void register(IEventBus eventBus) {
        MOB_EFFECTS.register(eventBus);
    }
}
