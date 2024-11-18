package me.pau.mod.locks.common.capability;

import net.minecraft.nbt.Tag;
import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.INBTSerializable;

public class CapabilityStorage <A extends INBTSerializable> {
	/*
	@Override
	public Tag writeNBT(Capability<A> cap, A inst, Direction side)
	{
		return inst.serializeNBT();
	}

	@Override
	public void readNBT(Capability<A> cap, A inst, Direction side, Tag nbt)
	{
		inst.deserializeNBT(nbt);
	}
	 */
}