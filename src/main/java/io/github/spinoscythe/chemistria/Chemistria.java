package io.github.spinoscythe.chemistria;

import com.mojang.logging.LogUtils;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.common.NeoForge;
import org.slf4j.Logger;

@Mod(Chemistria.MOD_ID)
public final class Chemistria {
    public static final String MOD_ID = "chemistria";
    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();

    public Chemistria(IEventBus modEventBus) {
        NeoForge.EVENT_BUS.register(this);
    }

    public static ResourceLocation rl(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
