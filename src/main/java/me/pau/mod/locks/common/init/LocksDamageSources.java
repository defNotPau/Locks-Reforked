package me.pau.mod.locks.common.init;

import net.minecraft.world.damagesource.DamageSource;

public final class LocksDamageSources {
	public static final DamageSource SHOCK = new DamageSource("locks.shock").bypassArmor();

	private LocksDamageSources() {}
}