package io.github.spinoscythe.chemistria.effects;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.LivingEntity;

public class SimpleMobEffect extends MobEffect {
    private final EffectApplier applier;

    public SimpleMobEffect(MobEffectCategory category, int color, EffectApplier applier) {
        super(category, color);
        this.applier = applier;
    }

    @Override
    public boolean applyEffectTick(ServerLevel level, LivingEntity entity, int amplifier) {
        applier.apply(level, entity, amplifier);
        return super.applyEffectTick(level, entity, amplifier);
    }

    @FunctionalInterface
    public interface EffectApplier {
        void apply(ServerLevel level, LivingEntity entity, int amplifier);
    }
}
