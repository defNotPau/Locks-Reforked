package me.pau.mod.locks.common.worldgen;

import java.util.Random;

import com.mojang.serialization.Codec;

import me.pau.mod.locks.common.config.LocksConfig;
import me.pau.mod.locks.common.util.Cuboid6i;
import me.pau.mod.locks.common.util.ILockableProvider;
import me.pau.mod.locks.common.util.Lock;
import me.pau.mod.locks.common.util.Lockable;
import me.pau.mod.locks.common.util.Transform;
import net.minecraft.block.BlockState;
import net.minecraft.block.ChestBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.state.properties.ChestType;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class LockChestsFeature extends Feature<NoFeatureConfig>
{
	public LockChestsFeature(Codec<NoFeatureConfig> codec)
	{
		super(codec);
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator gen, Random rng, BlockPos pos, NoFeatureConfig cfg)
	{
		if(!LocksConfig.canGen(rng))
			return false;
		BlockState state = world.getBlockState(pos);
		BlockPos pos1 = state.getValue(ChestBlock.TYPE) == ChestType.SINGLE ? pos : pos.relative(ChestBlock.getConnectedDirection(state));
		ItemStack stack = LocksConfig.getRandomLock(rng);
		Lockable lkb = new Lockable(new Cuboid6i(pos, pos1), Lock.from(stack), Transform.fromDirection(state.getValue(ChestBlock.FACING), Direction.NORTH), stack, world.getLevel());
		lkb.bb.getContainedChunks((x, z) ->
		{
			((ILockableProvider) world.getChunk(x, z)).getLockables().add(lkb);
			return true;
		});
		return true;
	}
}