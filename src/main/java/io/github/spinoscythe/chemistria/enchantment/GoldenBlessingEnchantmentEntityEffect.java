package io.github.spinoscythe.chemistria.enchantment;

import com.mojang.serialization.MapCodec;
import io.github.spinoscythe.chemistria.effects.ChemistriaMobEffects;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.enchantment.EnchantedItemInUse;
import net.minecraft.world.item.enchantment.effects.EnchantmentEntityEffect;
import net.minecraft.world.phys.Vec3;

public class GoldenBlessingEnchantmentEntityEffect implements EnchantmentEntityEffect {
    public static final MapCodec<GoldenBlessingEnchantmentEntityEffect> CODEC =
            MapCodec.unit(new GoldenBlessingEnchantmentEntityEffect());

    @Override
    public void apply(ServerLevel level, int enchantmentLevel, EnchantedItemInUse item, Entity entity, Vec3 origin) {
        if (entity instanceof Player player) {
            player.addEffect(new MobEffectInstance(ChemistriaMobEffects.WEALTHY, 90));
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> codec() {
        return CODEC;
    }
}
