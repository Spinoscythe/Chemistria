package io.github.spinoscythe.chemistria;

import io.github.spinoscythe.chemistria.block.entity.ChemistriaBlockEntityTypes;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;

@EventBusSubscriber(modid = Chemistria.MOD_ID)
public class ChemistriaEventHandler {
    @SubscribeEvent
    public static void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.registerBlockEntity(Capabilities.ItemHandler.BLOCK, ChemistriaBlockEntityTypes.BOILER_TANK.get(), (object, context) -> object.itemStackHandler);
        event.registerBlockEntity(Capabilities.FluidHandler.BLOCK, ChemistriaBlockEntityTypes.BOILER_TANK.get(), (object, context) -> object.fluidTank);
    }
}
