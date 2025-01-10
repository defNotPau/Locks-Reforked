package me.pau.mod.locks.common.item;

import java.util.List;
import java.util.Objects;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.container.LockPickingContainer;
import me.pau.mod.locks.common.init.LocksEnchantments;
import me.pau.mod.locks.common.util.Lockable;
import me.pau.mod.locks.common.util.LocksPredicates;
import me.pau.mod.locks.common.util.LocksUtil;
import net.minecraft.network.chat.Component;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Item;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.core.BlockPos;
import net.minecraft.ChatFormatting;
import net.minecraft.world.level.Level;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.network.NetworkHooks;
import org.jetbrains.annotations.NotNull;

public class LockPickItem extends Item {
	public static final Component TOO_COMPLEX_MESSAGE = Component.translatable(Locks.ID + ".status.too_complex");

	public final float strength;

	public LockPickItem(float strength, Properties props) {
		super(props);
		this.strength = strength;
	}

	public static final String KEY_STRENGTH = "Strength";

	// WARNING: EXPECTS LOCKPICKITEM STACK
	public static float getOrSetStrength(ItemStack stack) {
		CompoundTag nbt = stack.getOrCreateTag();
		if(!nbt.contains(KEY_STRENGTH))
			nbt.putFloat(KEY_STRENGTH, ((LockPickItem) stack.getItem()).strength);
		return nbt.getFloat(KEY_STRENGTH);
	}

	public static boolean canPick(ItemStack stack, int cmp)
	{
		return getOrSetStrength(stack) > cmp * 0.25f;
	}

	public static boolean canPick(ItemStack stack, Lockable lkb) {
		return canPick(stack, EnchantmentHelper.getTagEnchantmentLevel(LocksEnchantments.COMPLEXITY.get(), lkb.stack));
	}

	@Override
	public @NotNull InteractionResult useOn(UseOnContext ctx) {
		Player localPlayer = ctx.getPlayer();

		Level world = ctx.getLevel();
		BlockPos pos = ctx.getClickedPos();

		List<Lockable> match = LocksUtil.intersecting(world, pos).filter(LocksPredicates.LOCKED).toList();

		if(match.isEmpty())
			return InteractionResult.PASS;
		Lockable lkb = match.get(0);

		if(!canPick(ctx.getItemInHand(), lkb)) {
			if(world.isClientSide) {
                assert localPlayer != null;
                localPlayer.displayClientMessage(TOO_COMPLEX_MESSAGE, true);
            }
			return InteractionResult.PASS;
		}
		if(world.isClientSide) return InteractionResult.SUCCESS;
		InteractionHand hand = ctx.getHand();

        assert localPlayer != null;
        NetworkHooks.openScreen((ServerPlayer) localPlayer, new LockPickingContainer.Provider(hand, lkb), new LockPickingContainer.Writer(hand, lkb));
		return InteractionResult.SUCCESS;
	}

	@OnlyIn(Dist.CLIENT)
	@Override
	public void appendHoverText(@NotNull ItemStack stack, Level world, @NotNull List<Component> lines, @NotNull TooltipFlag flag) {
		super.appendHoverText(stack, world, lines, flag);
		lines.add(Component.translatable(Locks.ID + ".tooltip.strength", ItemStack.ATTRIBUTE_MODIFIER_FORMAT.format(stack.hasTag() && Objects.requireNonNull(stack.getTag()).contains(KEY_STRENGTH) ? stack.getTag().getFloat(KEY_STRENGTH) : this.strength)).withStyle(ChatFormatting.BLUE));
	}
}