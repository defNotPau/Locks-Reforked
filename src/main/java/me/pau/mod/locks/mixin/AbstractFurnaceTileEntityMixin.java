package me.pau.mod.locks.mixin;

import net.minecraft.core.Direction;
import net.minecraft.world.level.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import me.pau.mod.locks.common.util.LocksUtil;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;

@Mixin(AbstractFurnaceBlockEntity.class)
public class AbstractFurnaceTileEntityMixin {
	@Inject(at = @At("HEAD"), method = "getCapability", cancellable = true, remap = false)
	private void getCapability(Capability cap, Direction side, CallbackInfoReturnable<LazyOptional> cir) {
		BlockEntity te = (BlockEntity) (Object) this;
		if(!te.isRemoved() && cap == ForgeCapabilities.ITEM_HANDLER && te.hasLevel() && LocksUtil.locked(te.getLevel(), te.getBlockPos()))
			cir.setReturnValue(LazyOptional.empty());
	}
}