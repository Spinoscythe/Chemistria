package io.github.spinoscythe.chemistria.networking;

import io.github.spinoscythe.chemistria.Chemistria;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;

@EventBusSubscriber(modid = Chemistria.MOD_ID)
public class ChemistriaNetworking {

    @SubscribeEvent
    public static void registerPayloads(RegisterPayloadHandlersEvent event) {

    }
}
