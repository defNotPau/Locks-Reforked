package me.pau.mod.locks.mixin;

import java.util.List;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;

@Mixin(LootTable.class)
public interface LootTableAccessor {
	@Accessor("pools")
	List<LootPool> getPools();
}