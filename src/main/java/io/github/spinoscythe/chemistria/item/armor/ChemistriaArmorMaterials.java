package io.github.spinoscythe.chemistria.item.armor;

import com.google.common.collect.Maps;
import io.github.spinoscythe.chemistria.datagen.ChemistriaTags;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.equipment.ArmorMaterial;
import net.minecraft.world.item.equipment.ArmorType;
import net.minecraft.world.item.equipment.EquipmentAssets;

import java.util.Map;

public interface ChemistriaArmorMaterials {
    ArmorMaterial ONYX = new ArmorMaterial(
            33, makeDefense(3, 6, 8, 3, 11), 10, SoundEvents.ARMOR_EQUIP_DIAMOND, 2.0F, 0.0F, ChemistriaTags.Items.ONYX_TOOL_MATERIALS, EquipmentAssets.DIAMOND
    );

    private static Map<ArmorType, Integer> makeDefense(int boots, int leggings, int chestplate, int helmet, int body) {
        return Maps.newEnumMap(
                Map.of(
                        ArmorType.BOOTS,
                        boots,
                        ArmorType.LEGGINGS,
                        leggings,
                        ArmorType.CHESTPLATE,
                        chestplate,
                        ArmorType.HELMET,
                        helmet,
                        ArmorType.BODY,
                        body
                )
        );
    }
}
