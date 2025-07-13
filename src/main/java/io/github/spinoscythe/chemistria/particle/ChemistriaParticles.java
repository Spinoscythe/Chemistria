package io.github.spinoscythe.chemistria.particle;

import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.core.particles.ParticleType;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.core.registries.Registries;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ChemistriaParticles {
    public static final DeferredRegister<ParticleType<?>> PARTICLE_TYPES =
            DeferredRegister.create(Registries.PARTICLE_TYPE, Chemistria.MOD_ID);

    public static final DeferredHolder<ParticleType<?>, SimpleParticleType> BLOOD = PARTICLE_TYPES
            .register("blood", () -> new SimpleParticleType(false));

    public static void register(IEventBus eventBus) {
        PARTICLE_TYPES.register(eventBus);
    }
}
