package me.pau.mod.locks.mixin;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.level.chunk.ChunkAccess;
import org.spongepowered.asm.mixin.Mixin;

import me.pau.mod.locks.common.util.ILockableProvider;
import me.pau.mod.locks.common.util.Lockable;

@Mixin(ChunkAccess.class)
public class ChunkPrimerMixin implements ILockableProvider {
	private final List<Lockable> lockableList = new ArrayList<>();

	@Override
	public List<Lockable> getLockables()
	{
		return this.lockableList;
	}
}