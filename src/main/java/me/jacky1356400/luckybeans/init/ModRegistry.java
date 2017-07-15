package me.jacky1356400.luckybeans.init;

import me.jacky1356400.luckybeans.block.BlockBeanLeaf;
import me.jacky1356400.luckybeans.block.BlockBeanSapling;
import me.jacky1356400.luckybeans.block.BlockMysteriousBean;
import me.jacky1356400.luckybeans.item.ItemBean;
import me.jacky1356400.luckybeans.item.ItemMysteriousBean;
import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.RecipeHelper;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipe;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModRegistry {

	public static final Block BEANLEAF = new BlockBeanLeaf();
	public static final Block BEANSAP = new BlockBeanSapling();
	public static final Block MYSTBEANBLOCK = new BlockMysteriousBean();

	public static final Item MYSTBEAN = new ItemMysteriousBean();
	public static final Item BEAN = new ItemBean();

	private static void initRecipes() {
		RecipeHelper.addShapeless(BEANSAP, ModRegistry.MYSTBEAN, Blocks.SAPLING);
	}

	@SubscribeEvent
	public void onBlockRegistry(RegistryEvent.Register<Block> e) {
		e.getRegistry().registerAll(Data.BLOCKS.toArray(new Block[0]));
	}

	@SubscribeEvent
	public void onItemRegistry(RegistryEvent.Register<Item> e) {
		e.getRegistry().registerAll(Data.ITEMS.toArray(new Item[0]));
	}

	@SubscribeEvent
	public void onRecipeRegistry(RegistryEvent.Register<IRecipe> e) {
		initRecipes();
		e.getRegistry().registerAll(Data.RECIPES.toArray(new IRecipe[0]));
	}

}
