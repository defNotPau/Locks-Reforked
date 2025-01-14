package me.pau.mod.locks.common.recipe;

import me.pau.mod.locks.common.init.LocksItems;
import me.pau.mod.locks.common.init.LocksRecipeSerializers;
import me.pau.mod.locks.common.item.LockingItem;
import net.minecraft.world.inventory.CraftingContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.crafting.CustomRecipe;

public class KeyRecipe extends CustomRecipe {
	public KeyRecipe(ResourceLocation id)
	{
		super(id);
	}

	@Override
	public RecipeSerializer<?> getSerializer() { return LocksRecipeSerializers.KEY.get(); }

	@Override
	public boolean matches(CraftingContainer inv, Level world) {
		boolean hasLocking = false;
		int blanks = 0;

		for(int a = 0; a < inv.getContainerSize(); ++a) {
			ItemStack stack = inv.getItem(a);
			if(stack.isEmpty())
				continue;
			if(stack.hasTag() && stack.getTag().contains(LockingItem.KEY_ID)) {
				if(hasLocking)
					return false;
				hasLocking = true;
			}
			else if(stack.getItem() == LocksItems.KEY_BLANK.get())
				++blanks;
			else
				return false;
		}
		return hasLocking && blanks >= 1;
	}

	@Override
	public ItemStack assemble(CraftingContainer inv) {
		ItemStack locking = ItemStack.EMPTY;
		int blanks = 0;

		for (int a = 0; a < inv.getContainerSize(); ++a) {
			ItemStack stack = inv.getItem(a);
			if (stack.isEmpty())
				continue;
			if (stack.hasTag() && stack.getTag().contains(LockingItem.KEY_ID)) {
				if (!locking.isEmpty())
					return ItemStack.EMPTY;
				locking = stack;
			}
			else if(stack.getItem() == LocksItems.KEY_BLANK.get())
				++blanks;
			else
				return ItemStack.EMPTY;
		}

		if(!locking.isEmpty() && blanks >= 1)
			return LockingItem.copyId(locking, new ItemStack(LocksItems.KEY.get()));
		return ItemStack.EMPTY;
	}

	@Override
	public NonNullList<ItemStack> getRemainingItems(CraftingContainer inv) {
		NonNullList<ItemStack> list = NonNullList.withSize(inv.getContainerSize(), ItemStack.EMPTY);

		for (int a = 0; a < list.size(); ++a) {
			ItemStack stack = inv.getItem(a);
			if(!stack.hasTag() || !stack.getTag().contains(LockingItem.KEY_ID))
				continue;
			list.set(a, stack.copy());
			break;
		}

		return list;
	}

	@Override
	public boolean canCraftInDimensions(int x, int y)
	{
		return x >= 3 && y >= 3;
	}
}