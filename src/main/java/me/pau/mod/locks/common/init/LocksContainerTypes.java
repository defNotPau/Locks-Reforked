package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.container.KeyRingContainer;
import me.pau.mod.locks.common.container.LockPickingContainer;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksContainerTypes {
	public static final DeferredRegister<MenuType<?>> CONTAINER_TYPES = DeferredRegister.create(ForgeRegistries.MENU_TYPES, Locks.ID);

	public static final RegistryObject<MenuType<LockPickingContainer>> LOCK_PICKING = CONTAINER_TYPES.register("lock_picking", () -> new MenuType<>(LockPickingContainer.FACTORY));
	public static final RegistryObject<MenuType<KeyRingContainer>> KEY_RING = CONTAINER_TYPES.register("key_ring", () -> new MenuType<>(KeyRingContainer.FACTORY));

	private LocksContainerTypes() {}

	public static void register() { CONTAINER_TYPES.register(FMLJavaModLoadingContext.get().getModEventBus()); }
}