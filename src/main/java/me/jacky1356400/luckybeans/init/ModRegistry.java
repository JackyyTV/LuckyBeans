package me.jacky1356400.luckybeans.init;

import me.jacky1356400.luckybeans.block.BlockBeanLeaves;
import me.jacky1356400.luckybeans.block.BlockBeanSapling;
import me.jacky1356400.luckybeans.block.BlockMysteriousBean;
import me.jacky1356400.luckybeans.item.ItemBean;
import me.jacky1356400.luckybeans.item.ItemCreativeTreeGen;
import me.jacky1356400.luckybeans.item.ItemMysteriousBean;
import me.jacky1356400.luckybeans.util.Data;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.registry.GameRegistry;

import static me.jacky1356400.luckybeans.util.Data.MODID;

public class ModRegistry {

    public static final Block BEANLEAVES = new BlockBeanLeaves();
    public static final Block BEANSAP = new BlockBeanSapling();
    public static final Block MYSTBEANBLOCK = new BlockMysteriousBean();

    public static final Item MYSTBEAN = new ItemMysteriousBean();
    public static final Item BEAN = new ItemBean();
    public static final Item TREEGEN = new ItemCreativeTreeGen();

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
        GameRegistry.addShapelessRecipe(
                new ResourceLocation(MODID, "bean_sapling"),
                new ResourceLocation(MODID, "bean_sapling"),
                new ItemStack(BEANSAP, 1),
                Ingredient.fromStacks(new ItemStack(Blocks.SAPLING, 1)),
                Ingredient.fromStacks(new ItemStack(MYSTBEAN, 1))
        );
    }

}
