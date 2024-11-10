package me.pau.mod.locks.client.init;

import me.pau.mod.locks.client.gui.KeyRingScreen;
import me.pau.mod.locks.client.gui.LockPickingScreen;
import me.pau.mod.locks.common.init.LocksContainerTypes;
import net.minecraft.client.gui.ScreenManager;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class LocksScreens
{
	private LocksScreens() {}

	public static void register()
	{
		ScreenManager.register(LocksContainerTypes.LOCK_PICKING.get(), LockPickingScreen::new);
		ScreenManager.register(LocksContainerTypes.KEY_RING.get(), KeyRingScreen::new);
	}
}