package me.pau.mod.locks.common.init;

import me.pau.mod.locks.Locks;
import me.pau.mod.locks.common.recipe.KeyRecipe;
import net.minecraft.world.item.crafting.SimpleRecipeSerializer;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public final class LocksRecipeSerializers {
	public static final DeferredRegister<RecipeSerializer<?>> RECIPE_SERIALIZERS = DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, Locks.ID);

	public static final RegistryObject<RecipeSerializer<KeyRecipe>> KEY = RECIPE_SERIALIZERS.register("crafting_key", () -> new SimpleRecipeSerializer<>(KeyRecipe::new));

	private LocksRecipeSerializers() {}

	public static void register() { RECIPE_SERIALIZERS.register(FMLJavaModLoadingContext.get().getModEventBus()); }
}