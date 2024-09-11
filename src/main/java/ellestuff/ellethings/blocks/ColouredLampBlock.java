package ellestuff.ellethings.blocks;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.function.ToIntFunction;

public class ColouredLampBlock extends PillarBlock {
    public static final IntProperty POWER = Properties.POWER;
    public static final EnumProperty<Direction.Axis> AXIS = Properties.AXIS;

    public ColouredLampBlock(AbstractBlock.Settings settings) {
        super(settings);
        this.setDefaultState((BlockState)this.getDefaultState().with(AXIS, Direction.Axis.Y));
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return this.getDefaultState().with(POWER, ctx.getWorld().getReceivedRedstonePower(ctx.getBlockPos())).with(AXIS, ctx.getSide().getAxis());
    }

    public void neighborUpdate(BlockState state, World world, BlockPos pos, Block sourceBlock, BlockPos sourcePos, boolean notify) {
        if (!world.isClient) {
            int pwr = state.get(POWER);
            if (pwr != world.getReceivedRedstonePower(pos)) {
                world.scheduleBlockTick(pos, this, 1);
            }

        }
    }

    public void scheduledTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (state.get(POWER) != world.getReceivedRedstonePower(pos)) {
            world.setBlockState(pos, state.with(POWER, world.getReceivedRedstonePower(pos)), 2);
        }
    }

    public static final ToIntFunction<BlockState> STATE_TO_LUMINANCE = state -> Math.min(15, state.get(POWER) * 5);

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(POWER);
        builder.add(AXIS);
    }
}