package me.pau.mod.locks.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.level.storage.loot.entries.LootPoolEntryContainer;
import net.minecraft.world.level.storage.loot.LootPool;

@Mixin(LootPool.class)
public interface LootPoolAccessor {
	@Accessor("entries")  // Ensure "entries" is the correct field name
	List<LootPoolEntryContainer> getEntries();
}