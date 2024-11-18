package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.recipe.KeyRecipe;
import net.minecraft.item.crafting.SpecialRecipeSerializer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Locks.ID);

	public static final RegistryObject<RecipeSerializer<KeyRecipe>> KEY = add("crafting_key", new SpecialRecipeSerializer<>(KeyRecipe::new));

	private LocksRecipeSerializers() {}

	public static void register(IEventBus eventBus) { RECIPE_SERIALIZERS.register(eventBus); }

	public static <T extends Recipe<?>> RegistryObject<RecipeSerializer<T>> add(String name, RecipeSerializer<T> serializer) {
		return RECIPE_SERIALIZERS.register(name, () -> serializer);
	}
}