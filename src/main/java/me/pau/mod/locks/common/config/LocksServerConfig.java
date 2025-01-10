package me.pau.mod.locks.common.config;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.regex.Pattern;
import java.lang.System;

import com.google.common.collect.Lists;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.registries.ForgeRegistries;

public class LocksServerConfig {
	public static final ForgeConfigSpec SPEC;

	public static final ForgeConfigSpec.IntValue MAX_LOCKABLE_VOLUME;
	public static final ForgeConfigSpec.ConfigValue<List<? extends String>> LOCKABLE_BLOCKS;
	public static final ForgeConfigSpec.BooleanValue ALLOW_REMOVING_LOCKS;
	public static final ForgeConfigSpec.BooleanValue PROTECT_LOCKABLES;
	public static final ForgeConfigSpec.BooleanValue EASY_LOCK;

	public static Pattern[] lockableBlocks;

	static {
		ForgeConfigSpec.Builder cfg = new ForgeConfigSpec.Builder();

		MAX_LOCKABLE_VOLUME = cfg
			.comment("Maximum amount of blocks that can be locked at once")
			.defineInRange("Max Lockable Volume", 6, 1, Integer.MAX_VALUE);
		LOCKABLE_BLOCKS = cfg
			.comment("Blocks that can be locked. Each entry is the mod domain followed by the block's registry name. Can include regular expressions")
			.defineList("Lockable Blocks", Lists.newArrayList(".*chest", ".*barrel", ".*hopper", ".*door", ".*trapdoor", ".*fence_gate", ".*shulker_box"), e -> e instanceof String);
		ALLOW_REMOVING_LOCKS = cfg
			.comment("Open locks can be removed with an empty hand while sneaking")
			.define("Allow Removing Locks", true);
		PROTECT_LOCKABLES = cfg
			.comment("Locked blocks cannot be destroyed in survival mode")
			.define("Protect Lockables", true);
		EASY_LOCK = cfg
			.comment("Lock blocks with just one click! It's magic! (Will probably fail spectacularly with custom doors, custom double chests, etc)")
			.define("Easy Lock", true);

		SPEC = cfg.build();
	}

	private LocksServerConfig() {}

	public static void init() {
		lockableBlocks = LOCKABLE_BLOCKS.get().stream().map(Pattern::compile).toArray(Pattern[]::new);
	}

	public static boolean canLock(Level world, BlockPos pos) {
		Block block = world.getBlockState(pos).getBlock();
		String name = Objects.requireNonNull(ForgeRegistries.BLOCKS.getKey(block)).toString();
		for(Pattern p : lockableBlocks)
			if (p.matcher(name).matches())
				return true;
		return false;
	}
}