package io.github.spinoscythe.chemistria.item;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.item.armor.ChemistriaArmorMaterials;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.equipment.ArmorType;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.UnaryOperator;

public final class ChemistriaItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(Chemistria.MOD_ID);

    public static final DeferredItem<Item> RAW_ONYX = ITEMS.registerSimpleItem("raw_onyx");
    public static final DeferredItem<Item> ONYX_INGOT = ITEMS.registerSimpleItem("onyx_ingot");

    public static final DeferredItem<Item> PEAT = ITEMS.registerSimpleItem("peat");

    // @todo enable once textures are created
//    // Tools
    public static final DeferredItem<Item> ONYX_PICKAXE = ITEMS.registerItem("onyx_pickaxe", properties -> new Item(properties.pickaxe(ChemistriaToolMaterials.ONYX, 12.0F, 6.0F)));
    public static final DeferredItem<Item> ONYX_AXE = ITEMS.registerItem("onyx_axe", properties -> new AxeItem(ChemistriaToolMaterials.ONYX, 12.0F, 6.0F, properties));
    public static final DeferredItem<Item> ONYX_SWORD = ITEMS.registerItem("onyx_sword", properties -> new Item(properties.sword(ChemistriaToolMaterials.ONYX, 14.0F, 4.0F)));
    public static final DeferredItem<Item> ONYX_SHOVEL = ITEMS.registerItem("onyx_shovel", properties -> new ShovelItem(ChemistriaToolMaterials.ONYX, 14.0F, 5.0F, properties));
    public static final DeferredItem<Item> ONYX_HOE = ITEMS.registerItem("onyx_hoe", properties -> new HoeItem(ChemistriaToolMaterials.ONYX, 14.0F, 5.0F, properties));

    public static final DeferredItem<Item> ONYX_HELMET = ITEMS.registerItem("onyx_helmet", properties -> new Item(properties.humanoidArmor(ChemistriaArmorMaterials.ONYX, ArmorType.HELMET)));
    public static final DeferredItem<Item> ONYX_CHESTPLATE = ITEMS.registerItem("onyx_chestplate", properties -> new Item(properties.humanoidArmor(ChemistriaArmorMaterials.ONYX, ArmorType.CHESTPLATE)));
    public static final DeferredItem<Item> ONYX_LEGGINGS = ITEMS.registerItem("onyx_leggings", properties -> new Item(properties.humanoidArmor(ChemistriaArmorMaterials.ONYX, ArmorType.LEGGINGS)));
    public static final DeferredItem<Item> ONYX_BOOTS = ITEMS.registerItem("onyx_boots", properties -> new Item(properties.humanoidArmor(ChemistriaArmorMaterials.ONYX, ArmorType.BOOTS)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
