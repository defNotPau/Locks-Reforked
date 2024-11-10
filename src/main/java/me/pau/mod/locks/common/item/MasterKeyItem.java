package me.pau.mod.locks.common.item;

import java.util.List;
import java.util.stream.Collectors;

import me.pau.mod.locks.common.init.LocksSoundEvents;
import me.pau.mod.locks.common.util.Lockable;
import me.pau.mod.locks.common.util.LocksUtil;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.InteractionResult;
import net.minecraft.sounds.SoundSource;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;

public class MasterKeyItem extends Item {
	public MasterKeyItem(Properties props) {
		super(props.stacksTo(1));
	}

	// TODO Sound pitch
	@Override
	public InteractionResult useOn(UseOnContext ctx) {
		Level world = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();
		List<Lockable> match = LocksUtil.intersecting(world, pos).collect(Collectors.toList());
		if(match.isEmpty())
			return InteractionResult.PASS;
		world.playSound(ctx.getPlayer(), pos, LocksSoundEvents.LOCK_OPEN.get(), SoundSource.BLOCKS, 1f, 1f);
		if(world.isClientSide)
			return InteractionResult.SUCCESS;
		for(Lockable lkb : match)
			lkb.lock.setLocked(!lkb.lock.isLocked());
		return InteractionResult.SUCCESS;
	}
}