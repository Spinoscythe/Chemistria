package io.github.spinoscythe.chemistria.networking.payloads;

import io.github.spinoscythe.chemistria.Chemistria;
import io.github.spinoscythe.chemistria.block.entity.InventoryMachine;
import io.github.spinoscythe.chemistria.networking.ChemistriaExtraStreamCodecs;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.items.ItemStackHandler;
import net.neoforged.neoforge.network.handling.IPayloadContext;

public record ItemStackSyncPayload(ItemStackHandler ish, BlockPos blockPos) implements CustomPacketPayload {
    public static final Type<ItemStackSyncPayload> TYPE = new Type<>(Chemistria.rl("item_stack_sync"));
    public static final StreamCodec<RegistryFriendlyByteBuf, ItemStackSyncPayload> STREAM_CODEC = StreamCodec.composite(
            ChemistriaExtraStreamCodecs.ITEM_STACK_HANDLER, ItemStackSyncPayload::ish,
            BlockPos.STREAM_CODEC, ItemStackSyncPayload::blockPos,
            ItemStackSyncPayload::new
    );

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }

    public void handle(IPayloadContext context) {
        context.enqueueWork(() -> {
           if (Minecraft.getInstance().level.getBlockEntity(blockPos) instanceof InventoryMachine machine) {
               machine.setHandler(ish);
           }
        });
    }
}
