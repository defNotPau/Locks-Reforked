package me.pau.mod.locks.common.item;

import java.util.List;
import java.util.stream.Collectors;

import me.pau.mod.locks.common.capability.CapabilityProvider;
import me.pau.mod.locks.common.capability.KeyRingInventory;
import me.pau.mod.locks.common.container.KeyRingContainer;
import me.pau.mod.locks.common.init.LocksSoundEvents;
import me.pau.mod.locks.common.util.Lockable;
import me.pau.mod.locks.common.util.LocksUtil;
import net.minecraft.world.entity.player.Player;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.ActionResultType;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.network.NetworkHooks;
import net.minecraftforge.items.CapabilityItemHandler;
import net.minecraftforge.items.IItemHandler;

public class KeyRingItem extends Item {
	public final int rows;

	public KeyRingItem(int rows, Properties props) {
		super(props.stacksTo(1));
		this.rows = rows;
	}

	@Override
	public ICapabilityProvider initCapabilities(ItemStack stack, CompoundTag nbt) {
		return new CapabilityProvider(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, new KeyRingInventory(stack, this.rows, 9));
	}

	public static boolean containsId(ItemStack stack, int id) {
		IItemHandler inv = stack.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
		for(int a = 0; a < inv.getSlots(); ++a)
			if(LockingItem.getOrSetId(inv.getStackInSlot(a)) == id)
				return true;
		return false;
	}

	@Override
	public ActionResult<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if(!player.level.isClientSide)
			NetworkHooks.openGui((ServerPlayerEntity) player, new KeyRingContainer.Provider(stack), new KeyRingContainer.Writer(hand));
		return new ActionResult<>(ActionResultType.PASS, stack);
	}

	@Override
	public ActionResultType useOn(UseOnContext ctx) {
		Level world = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();
		IItemHandler inv = ctx.getItemInHand().getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY).orElse(null);
		List<Lockable> intersect = LocksUtil.intersecting(world, pos).collect(Collectors.toList());
		if(intersect.isEmpty())
			return ActionResultType.PASS;
		for(int a = 0; a < inv.getSlots(); ++a) {
			int id = LockingItem.getOrSetId(inv.getStackInSlot(a));
			List<Lockable> match = intersect.stream().filter(lkb -> lkb.lock.id == id).collect(Collectors.toList());
			if(match.isEmpty())
				continue;
			world.playSound(ctx.getPlayer(), pos, LocksSoundEvents.LOCK_OPEN.get(), SoundCategory.BLOCKS, 1f, 1f);
			if(world.isClientSide)
				return ActionResultType.SUCCESS;
			for(Lockable lkb : match)
				lkb.lock.setLocked(!lkb.lock.isLocked());
			return ActionResultType.SUCCESS;
		}
		return ActionResultType.SUCCESS;
	}
}