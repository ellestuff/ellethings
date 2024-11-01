package ellestuff.ellethings.blocks;

import ellestuff.ellethings.items.RedstoneTransformerItem;
import net.minecraft.block.*;
import net.minecraft.block.enums.WallMountLocation;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.loot.context.LootContextParameterSet;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Collections;
import java.util.List;

public class RedstoneTransformerBlock extends WallMountedBlock {
    public static final IntProperty POWER = Properties.POWER;
    public static final EnumProperty<WallMountLocation> FACE = Properties.WALL_MOUNT_LOCATION;
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;

    public RedstoneTransformerBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(POWER, 0));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return getShape(state);
    }

    public int getWeakRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return state.get(POWER);
    }

    public int getStrongRedstonePower(BlockState state, BlockView world, BlockPos pos, Direction direction) {
        return getDirection(state) == direction ? state.get(POWER) : 0;
    }

    public boolean emitsRedstonePower(BlockState state) {
        return true;
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient()) {
            world.scheduleBlockTick(pos, this, 2);
        }
    }

    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (!state.isOf(newState.getBlock())) {
            this.updateTarget(world, pos, state);

            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    @Override
    public void onPlaced(World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
        this.updateTarget(world, pos, state);
        super.onPlaced(world, pos, state, placer, itemStack);
    }
    
    protected void updateTarget(World world, BlockPos pos, BlockState state) {
        Direction direction = state.get(FACING);

        if (state.get(FACE) == WallMountLocation.CEILING) { direction = Direction.DOWN; }
        else if (state.get(FACE) == WallMountLocation.FLOOR) { direction = Direction.UP; }

        BlockPos blockPos = pos.offset(direction.getOpposite());
        world.updateNeighbor(blockPos, this, pos);
        world.updateNeighborsExcept(blockPos, this, direction);
    }

    @Override
    public @Nullable BlockState getPlacementState(ItemPlacementContext ctx) {
        if (super.getPlacementState(ctx) == null) { return null; }

        ItemStack stack = ctx.getStack();
        int power = RedstoneTransformerItem.getPower(stack);
        return super.getPlacementState(ctx).with(POWER, power);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
        builder.add(FACE);
        builder.add(FACING);
    }

    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        ItemStack stack = ElleBlocks.TRANSFORMER_ITEM.getDefaultStack();

        RedstoneTransformerItem.setPower(stack, state.get(POWER));
        
        return stack;
    }

    private VoxelShape getShape(BlockState state) {
        VoxelShape shape = Block.createCuboidShape(1.0, 0.0, 3.0, 13.0, 4.0, 13.0);

        Direction facing = state.get(FACING);
        WallMountLocation face = state.get(FACE);

        // Swap facing side for ceilings
        if (face == WallMountLocation.CEILING) { facing = facing.getOpposite(); }

        // Actual shapes
        if (face == WallMountLocation.WALL) {
            if (facing == Direction.NORTH || facing == Direction.SOUTH ) { shape = Block.createCuboidShape(3,1,0,13,13,4); }
            else { shape = Block.createCuboidShape(0,1,3,4,13,13); }

            if (facing == Direction.WEST) { shape = shape.offset(0.75,0,0); }
            if (facing == Direction.NORTH) { shape = shape.offset(0,0,0.75); }
        }
        else {
            if (facing == Direction.NORTH || facing == Direction.SOUTH ) { shape = Block.createCuboidShape(3,0,1,13,4,13); }

            if (facing == Direction.WEST) { shape = shape.offset(0.125,0,0); }
            if (facing == Direction.NORTH) { shape = shape.offset(0,0,0.125); }
        }

        // Ceiling offset
        if (face == WallMountLocation.CEILING) {
            shape = shape.offset(0,0.75,0);
        }

        return shape;
    }

    @Override
    public List<ItemStack> getDroppedStacks(BlockState state, LootContextParameterSet.Builder builder) {
        ItemStack stack = ElleBlocks.TRANSFORMER_ITEM.getDefaultStack();
        RedstoneTransformerItem.setPower(stack, state.get(POWER));

        return Collections.singletonList(stack);
    }
}