package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.tags.TagKey;
import net.minecraft.tags.ItemTags;

public final class LocksItemTags
{
	private LocksItemTags() {}

	public static final TagKey<Item>
		KEYS = bind("keys"),
		LOCKS = bind("locks"),
		LOCK_PICKS = bind("lock_picks");

	public static TagKey<Item> bind(String name)
	{
		return ItemTags.create(new ResourceLocation(Locks.ID + ":" + name));
	}
}