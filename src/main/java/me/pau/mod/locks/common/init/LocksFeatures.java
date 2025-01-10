package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksFeatures {
	public static final DeferredRegister<Feature<?>> FEATURES = DeferredRegister.create(ForgeRegistries.FEATURES, Locks.ID);

	public static void register() {
		FEATURES.register(FMLJavaModLoadingContext.get().getModEventBus());
	}
}