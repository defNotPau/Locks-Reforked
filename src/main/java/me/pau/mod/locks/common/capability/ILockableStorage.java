package me.pau.mod.locks.common.capability;

import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import me.pau.mod.locks.common.util.Lockable;
import net.minecraft.nbt.ListTag;
import net.minecraftforge.common.util.INBTSerializable;

public interface ILockableStorage extends INBTSerializable<ListTag>
{
	Int2ObjectMap<Lockable> get();

	void add(Lockable lkb);

	void remove(int id);
}