package io.github.spinoscythe.chemistria.enchantment;

import io.github.spinoscythe.chemistria.Chemistria;
import net.minecraft.advancements.critereon.DamageSourcePredicate;
import net.minecraft.advancements.critereon.TagPredicate;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.tags.EnchantmentTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentEffectComponents;
import net.minecraft.world.item.enchantment.EnchantmentTarget;
import net.minecraft.world.item.enchantment.LevelBasedValue;
import net.minecraft.world.item.enchantment.effects.AddValue;
import net.minecraft.world.level.storage.loot.predicates.DamageSourceCondition;

public class ChemistriaEnchantments {
    public static final ResourceKey<Enchantment> GOLDEN_BLESSING = createKey("golden_blessing");

    public static void bootstrap(BootstrapContext<Enchantment> context) {
        HolderGetter<Item> itemsGetter = context.lookup(Registries.ITEM);
        HolderGetter<Enchantment> enchantmentsGetter = context.lookup(Registries.ENCHANTMENT);
        register(context, GOLDEN_BLESSING,
                Enchantment.enchantment(
                                Enchantment.definition(
                                        itemsGetter.getOrThrow(ItemTags.ARMOR_ENCHANTABLE),
                                        10,
                                        4,
                                        Enchantment.dynamicCost(1, 11),
                                        Enchantment.dynamicCost(12, 11),
                                        2,
                                        EquipmentSlotGroup.ARMOR
                                )
                        ).exclusiveWith(enchantmentsGetter.getOrThrow(EnchantmentTags.ARMOR_EXCLUSIVE))
                        .withEffect(EnchantmentEffectComponents.POST_ATTACK, EnchantmentTarget.ATTACKER, EnchantmentTarget.VICTIM, new GoldenBlessingEnchantmentEntityEffect())
        );
    }

    private static void register(BootstrapContext<Enchantment> context, ResourceKey<Enchantment> key, Enchantment.Builder builder) {
        context.register(key, builder.build(key.location()));
    }

    private static ResourceKey<Enchantment> createKey(String name) {
        return ResourceKey.create(Registries.ENCHANTMENT, Chemistria.rl(name));
    }
}
