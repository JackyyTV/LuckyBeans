package me.jacky1356400.luckybeans.compat;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.init.ModRegistry;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.IModRegistry;
import mezz.jei.api.JEIPlugin;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import javax.annotation.ParametersAreNonnullByDefault;

@JEIPlugin
public class JEICompat implements IModPlugin {

    @Override @ParametersAreNonnullByDefault
    public void register(IModRegistry registry) {
        //Lucky Bean Leaves
        registry.addIngredientInfo(new ItemStack(ModRegistry.BEANLEAVES, 1), ItemStack.class,
                LuckyBeans.MODID + "." + "bean_leaves.jeidesc");
        //Lucky Bean Sapling
        registry.addIngredientInfo(new ItemStack(ModRegistry.BEANSAP, 1), ItemStack.class,
                LuckyBeans.MODID + "." + "bean_sapling.jeidesc");
        //Mysterious Lucky Bean Block
        registry.addIngredientInfo(new ItemStack(ModRegistry.MYSTBEANBLOCK, 1), ItemStack.class,
                LuckyBeans.MODID + "." + "mysterious_bean_block.jeidesc");
        //Mysterious Lucky Bean
        registry.addIngredientInfo(new ItemStack(ModRegistry.MYSTBEAN, 1), ItemStack.class,
                LuckyBeans.MODID + "." + "mysterious_bean.jeidesc");
        //Lucky Bean
        registry.addIngredientInfo(new ItemStack(ModRegistry.BEAN, 1, OreDictionary.WILDCARD_VALUE), ItemStack.class,
                LuckyBeans.MODID + "." + "bean.jeidesc");
        //Creative Tree Generator
        registry.addIngredientInfo(new ItemStack(ModRegistry.TREEGEN, 1), ItemStack.class,
                LuckyBeans.MODID + "." + "creative_tree_generator.jeidesc");
    }

}
