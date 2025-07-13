package io.github.spinoscythe.chemistria.screen;

import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.inventory.MenuType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.extensions.IMenuTypeExtension;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ChemistriaMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(Registries.MENU, Chemistria.MOD_ID);

    public static final DeferredHolder<MenuType<?>, MenuType<BoilerTankMenu>> BOILER_TANK =
            MENU_TYPES.register("boiler_tank", () -> IMenuTypeExtension.create(BoilerTankMenu::new));

    public static void register(IEventBus eventBus) {
        MENU_TYPES.register(eventBus);
    }
}
