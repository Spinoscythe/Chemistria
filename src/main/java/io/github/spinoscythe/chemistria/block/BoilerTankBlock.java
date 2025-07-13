package io.github.spinoscythe.chemistria.block;

import com.mojang.serialization.MapCodec;
import io.github.spinoscythe.chemistria.block.entity.BoilerTankBlockEntity;
import io.github.spinoscythe.chemistria.block.entity.ChemistriaBlockEntityTypes;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.EnumProperty;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;

public class BoilerTankBlock extends BaseEntityBlock {
    public static final MapCodec<BoilerTankBlock> CODEC = simpleCodec(BoilerTankBlock::new);
    public static final BooleanProperty LIT = BlockStateProperties.LIT;
    public static final EnumProperty<Direction> FACING = BlockStateProperties.HORIZONTAL_FACING;

    public BoilerTankBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.defaultBlockState().setValue(LIT, false).setValue(FACING, Direction.NORTH));
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0, 0.125, 0, 0.125, 0.9375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.875, 0.125, 0, 1, 0.9375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.125, 0.875, 0.875, 0.9375, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.125, 0.125, 0, 0.875, 0.9375, 0.125), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0, 0, 1, 0.125, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.9375, 0, 1, 1.0625, 0.375), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.9375, 0.5625, 1, 1.0625, 1), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0, 0.9375, 0.375, 0.375, 1.0625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 0.9375, 0.375, 1, 1.0625, 0.5625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.3125, 1.0625, 0.375, 0.375, 1.5625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.625, 1.0625, 0.375, 0.6875, 1.5625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 1.0625, 0.5625, 0.625, 1.5625, 0.625), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.375, 1.0625, 0.375, 0.625, 1.5625, 0.4375), BooleanOp.OR);
        return shape;
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if (!level.isClientSide && level.getBlockEntity(pos) instanceof BoilerTankBlockEntity entity) {
            player.openMenu(entity, pos);
            return InteractionResult.SUCCESS;
        }
        return super.useWithoutItem(state, level, pos, player, hitResult);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(LIT, FACING);
    }

    @Override
    public @Nullable BlockState getStateForPlacement(BlockPlaceContext context) {
        return this.defaultBlockState().setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    @Override
    protected BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    protected MapCodec<? extends BaseEntityBlock> codec() {
        return CODEC;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new BoilerTankBlockEntity(pos, state);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> blockEntityType) {
        return createTickerHelper(blockEntityType, ChemistriaBlockEntityTypes.BOILER_TANK.get(), (level1, pos, state1, blockEntity) -> blockEntity.serverTick());
    }
}
