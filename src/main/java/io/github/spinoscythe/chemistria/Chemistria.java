package io.github.spinoscythe.chemistria;

import com.mojang.logging.LogUtils;
import io.github.spinoscythe.chemistria.block.ChemistriaBlocks;
import io.github.spinoscythe.chemistria.block.entity.ChemistriaBlockEntityTypes;
import io.github.spinoscythe.chemistria.effects.ChemistriaMobEffects;
import io.github.spinoscythe.chemistria.enchantment.ChemistriaEnchantmentEffects;
import io.github.spinoscythe.chemistria.item.ChemistriaCreativeModeTabs;
import io.github.spinoscythe.chemistria.item.ChemistriaItems;
import io.github.spinoscythe.chemistria.recipe.ChemistriaRecipes;
import io.github.spinoscythe.chemistria.screen.BoilerTankScreen;
import io.github.spinoscythe.chemistria.screen.ChemistriaMenuTypes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.capabilities.Capabilities;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.RegisterMenuScreensEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.server.ServerStartingEvent;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(Chemistria.MOD_ID)
public class Chemistria {
    // Define mod id in a common place for everything to reference
    public static final String MOD_ID = "chemistria";
    // Directly reference a slf4j logger
    public static final Logger LOGGER = LogUtils.getLogger();

    // The constructor for the mod class is the first code that is run when your mod is loaded.
    // FML will recognize some parameter types like IEventBus or ModContainer and pass them in automatically.
    public Chemistria(IEventBus modEventBus, ModContainer modContainer) {

        ChemistriaBlocks.register(modEventBus);
        ChemistriaItems.register(modEventBus);
        ChemistriaCreativeModeTabs.register(modEventBus);
        ChemistriaEnchantmentEffects.register(modEventBus);
        ChemistriaMobEffects.register(modEventBus);
        ChemistriaRecipes.register(modEventBus);
        ChemistriaBlockEntityTypes.register(modEventBus);
        ChemistriaMenuTypes.register(modEventBus);

        // Register ourselves for server and other game events we are interested in.
        // Note that this is necessary if and only if we want *this* class (ExampleMod) to respond directly to events.
        // Do not add this line if there are no @SubscribeEvent-annotated functions in this class, like onServerStarting() below.
        NeoForge.EVENT_BUS.register(this);
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event) {
        // Do something when the server starts
        LOGGER.info("HELLO from server starting");
    }

    public static ResourceLocation rl(String path) {
        return ResourceLocation.fromNamespaceAndPath(MOD_ID, path);
    }

    @EventBusSubscriber(modid = Chemistria.MOD_ID, value = Dist.CLIENT)
    public static class ClientModEvents {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event) {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }

        @SubscribeEvent
        public static void registerMenuScreens(RegisterMenuScreensEvent event) {
            event.register(ChemistriaMenuTypes.BOILER_TANK.get(), BoilerTankScreen::new);
        }
    }
}