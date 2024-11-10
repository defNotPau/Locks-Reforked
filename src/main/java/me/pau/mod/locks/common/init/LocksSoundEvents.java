package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksSoundEvents {
	public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, Locks.ID);

	public static final RegistryObject<SoundEvent>
		KEY_RING = add("key_ring"),
		LOCK_CLOSE = add("lock.close"),
		LOCK_OPEN = add("lock.open"),
		LOCK_RATTLE = add("lock.rattle"),
		PIN_FAIL = add("pin.fail"),
		PIN_MATCH = add("pin.match"),
		SHOCK = add("shock");

	private LocksSoundEvents() {}

	public static void register(IEventBus eventBus)
	{
		SOUND_EVENTS.register(eventBus);
	}

	public static RegistryObject<SoundEvent> add(String name) {
		return SOUND_EVENTS.register(name, () -> new SoundEvent(new ResourceLocation(Locks.ID, name)));
	}
}