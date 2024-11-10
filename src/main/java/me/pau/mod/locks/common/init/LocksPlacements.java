package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.worldgen.ChestPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.NoPlacementConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksPlacements
{
	public static final DeferredRegister<Placement<?>> PLACEMENTS = DeferredRegister.create(ForgeRegistries.DECORATORS, Locks.ID);

	public static final RegistryObject<Placement<NoPlacementConfig>>
		CHEST = add("chest", new ChestPlacement(NoPlacementConfig.CODEC));

	private LocksPlacements() {}

	public static void register(IEventBus eventBus) { PLACEMENTS.register(eventBus); }

	public static <T extends IPlacementConfig> RegistryObject<Placement<T>> add(String name, Placement<T> pl)
	{
		return PLACEMENTS.register(name, () -> pl);
	}
}