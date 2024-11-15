package me.pau.mod.locks;

import com.mojang.logging.LogUtils;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import me.pau.mod.locks.common.config.LocksClientConfig;
import me.pau.mod.locks.common.config.LocksConfig;
import me.pau.mod.locks.common.config.LocksServerConfig;
import me.pau.mod.locks.common.init.LocksContainerTypes;
import me.pau.mod.locks.common.init.LocksEnchantments;
import me.pau.mod.locks.common.init.LocksFeatures;
import me.pau.mod.locks.common.init.LocksItems;
import me.pau.mod.locks.common.init.LocksPlacements;
import me.pau.mod.locks.common.init.LocksRecipeSerializers;
import me.pau.mod.locks.common.init.LocksSoundEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.ModLoadingContext;
import org.spongepowered.asm.mixin.Mixins;

@Mod(Locks.ID)
public final class Locks {
	IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

	public static final String ID = "locks";
	public static final Logger LOGGER = LogUtils.getLogger();

	public Locks() {
		Mixins.addConfiguration("mixin.locks.json");

		ModLoadingContext.get().registerConfig(Type.SERVER, LocksServerConfig.SPEC);
		ModLoadingContext.get().registerConfig(Type.COMMON, LocksConfig.SPEC);
		ModLoadingContext.get().registerConfig(Type.CLIENT, LocksClientConfig.SPEC);

		LocksItems.register(modEventBus);
		LocksEnchantments.register(modEventBus);
		LocksSoundEvents.register(modEventBus);
		LocksPlacements.register(modEventBus);
		LocksFeatures.register(modEventBus);
		LocksContainerTypes.register(modEventBus);
		LocksRecipeSerializers.register(modEventBus);
	}
}