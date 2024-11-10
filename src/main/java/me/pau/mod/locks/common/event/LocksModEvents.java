package me.pau.mod.locks.common.event;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.config.LocksConfig;
import me.pau.mod.locks.common.config.LocksServerConfig;
import me.pau.mod.locks.common.init.LocksCapabilities;
import me.pau.mod.locks.common.init.LocksNetwork;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Locks.ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public final class LocksModEvents
{
	private LocksModEvents() {}

	@SubscribeEvent
	public static void onSetup(FMLCommonSetupEvent e)
	{
		LocksCapabilities.register();
		LocksNetwork.register();
	}

	@SubscribeEvent
	public static void onConfigLoad(ModConfig.ModConfigEvent e)
	{
		if(e.getConfig().getSpec() == LocksConfig.SPEC)
			LocksConfig.init();
		if(e.getConfig().getSpec() == LocksServerConfig.SPEC)
			LocksServerConfig.init();
	}
}