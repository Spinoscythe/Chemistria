package io.github.spinoscythe.chemistria.block.entity;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public final class ChemistriaBlockEntityTypes {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY_TYPES =
            DeferredRegister.create(Registries.BLOCK_ENTITY_TYPE, Chemistria.MOD_ID);

    public static final DeferredHolder<BlockEntityType<?>, BlockEntityType<BoilerTankBlockEntity>> BOILER_TANK =
            BLOCK_ENTITY_TYPES.register("boiler_tank", () -> new BlockEntityType<>(BoilerTankBlockEntity::new, ChemistriaBlocks.BOILER_TANK.get()));

    public static void register(IEventBus eventBus) {
        BLOCK_ENTITY_TYPES.register(eventBus);
    }
}
