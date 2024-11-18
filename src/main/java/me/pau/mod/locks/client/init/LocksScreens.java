package me.pau.mod.locks.client.init;

import me.pau.mod.locks.client.gui.KeyRingScreen;
import me.pau.mod.locks.client.gui.LockPickingScreen;
import me.pau.mod.locks.common.init.LocksContainerTypes;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public final class LocksScreens {
	private LocksScreens() {}

	public static void register() {
		MenuScreens.register(LocksContainerTypes.LOCK_PICKING.get(), LockPickingScreen::new);
		MenuScreens.register(LocksContainerTypes.KEY_RING.get(), KeyRingScreen::new);
	}
}