package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.enchantment.ComplexityEnchantment;
import me.pau.mod.locks.common.enchantment.ShockingEnchantment;
import me.pau.mod.locks.common.enchantment.SturdyEnchantment;
import me.pau.mod.locks.common.item.LockItem;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksEnchantments {
	public static final EnchantmentCategory LOCK_TYPE = EnchantmentCategory.create("LOCK", item -> item instanceof LockItem); // FIXME check if is in tag instead?

	public static final DeferredRegister<Enchantment> ENCHANTMENTS = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, Locks.ID);

	public static final RegistryObject<Enchantment>
		SHOCKING = add("shocking", new ShockingEnchantment()),
		STURDY = add("sturdy", new SturdyEnchantment()),
		COMPLEXITY = add("complexity", new ComplexityEnchantment());

	private LocksEnchantments() {}

	public static void register(IEventBus modEventBus)
	{
		ENCHANTMENTS.register(modEventBus);
	}

	public static RegistryObject<Enchantment> add(String name, Enchantment ench) {
		return ENCHANTMENTS.register(name, () -> ench);
	}
}