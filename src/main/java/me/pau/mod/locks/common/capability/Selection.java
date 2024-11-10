package me.pau.mod.locks.common.capability;

import me.pau.mod.locks.Locks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.BlockPos;
import net.minecraftforge.common.capabilities.AutoRegisterCapability;

@AutoRegisterCapability
public class Selection implements ISelection {
	public static final ResourceLocation ID = new ResourceLocation(Locks.ID, "selection");

	public BlockPos pos;

	@Override
	public BlockPos get() {
		return this.pos;
	}

	@Override
	public void set(BlockPos pos)
	{
		this.pos = pos;
	}
}