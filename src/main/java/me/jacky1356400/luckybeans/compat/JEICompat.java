package me.jacky1356400.luckybeans.compat;

import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.Data;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

@JEIPlugin
public class JEICompat implements IModPlugin {

    @Override
    public void register(IModRegistry registry) {
        ItemStack beanleaf = new ItemStack(ModRegistry.BEANLEAF, 1);
        registry.addIngredientInfo(beanleaf, ItemStack.class, Data.MODID + "." + "bean_leaves.jeidesc");

        ItemStack beansap = new ItemStack(ModRegistry.BEANSAP, 1);
        registry.addIngredientInfo(beansap, ItemStack.class, Data.MODID + "." + "bean_sapling.jeidesc");

        ItemStack mystbeanblock = new ItemStack(ModRegistry.MYSTBEANBLOCK, 1);
        registry.addIngredientInfo(mystbeanblock, ItemStack.class, Data.MODID + "." + "mysterious_bean_block.jeidesc");

        ItemStack mystbean = new ItemStack(ModRegistry.MYSTBEAN, 1);
        registry.addIngredientInfo(mystbean, ItemStack.class, Data.MODID + "." + "mysterious_bean.jeidesc");

        ItemStack bean = new ItemStack(ModRegistry.BEAN, 1, OreDictionary.WILDCARD_VALUE);
        registry.addIngredientInfo(bean, ItemStack.class, Data.MODID + "." + "bean.jeidesc");
    }

}
