package me.pau.mod.locks;

import com.mojang.logging.LogUtils;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.slf4j.Logger;

import me.pau.mod.locks.common.config.LocksClientConfig;
import me.pau.mod.locks.common.config.LocksConfig;
import me.pau.mod.locks.common.config.LocksServerConfig;
import me.pau.mod.locks.common.init.LocksContainerTypes;
import me.pau.mod.locks.common.init.LocksEnchantments;
import me.pau.mod.locks.common.init.LocksFeatures;
import me.pau.mod.locks.common.init.LocksItems;
import me.pau.mod.locks.common.init.LocksRecipeSerializers;
import me.pau.mod.locks.common.init.LocksSoundEvents;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig.Type;
import net.minecraftforge.fml.ModLoadingContext;
import org.spongepowered.asm.mixin.Mixins;

import java.util.Arrays;

@Mod(Locks.ID)
public final class Locks {
	public static final String ID = "locks";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ID);

	public Locks() {
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		Mixins.addConfiguration("locks.mixin.json");

		ModLoadingContext.get().registerConfig(Type.SERVER, LocksServerConfig.SPEC);
		ModLoadingContext.get().registerConfig(Type.COMMON, LocksConfig.SPEC);
		ModLoadingContext.get().registerConfig(Type.CLIENT, LocksClientConfig.SPEC);

		LocksItems.ITEMS.register(modEventBus);
		LocksEnchantments.ENCHANTMENTS.register(modEventBus);
		LocksSoundEvents.SOUND_EVENTS.register(modEventBus);
		LocksFeatures.FEATURES.register(modEventBus);
		LocksContainerTypes.CONTAINER_TYPES.register(modEventBus);
		LocksRecipeSerializers.RECIPE_SERIALIZERS.register(modEventBus);

		MinecraftForge.EVENT_BUS.register(this);
	}
}