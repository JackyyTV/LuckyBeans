package me.jacky1356400.luckybeans.util;

import me.jacky1356400.luckybeans.init.ModRegistry;
import net.minecraft.item.ItemStack;

public enum EnumBeans {
    WHITE(new ItemStack(ModRegistry.BEAN, 1, 0)),
    ORANGE(new ItemStack(ModRegistry.BEAN, 1, 1)),
    MAGENTA(new ItemStack(ModRegistry.BEAN, 1, 2)),
    LIGHTBLUE(new ItemStack(ModRegistry.BEAN, 1, 3)),
    YELLOW(new ItemStack(ModRegistry.BEAN, 1, 4)),
    LIME(new ItemStack(ModRegistry.BEAN, 1, 5)),
    PINK(new ItemStack(ModRegistry.BEAN, 1, 6)),
    GRAY(new ItemStack(ModRegistry.BEAN, 1, 7)),
    LIGHTGRAY(new ItemStack(ModRegistry.BEAN, 1, 8)),
    CYAN(new ItemStack(ModRegistry.BEAN, 1, 9)),
    PURPLE(new ItemStack(ModRegistry.BEAN, 1, 10)),
    BLUE(new ItemStack(ModRegistry.BEAN, 1, 11)),
    BROWN(new ItemStack(ModRegistry.BEAN, 1, 12)),
    GREEN(new ItemStack(ModRegistry.BEAN, 1, 13)),
    RED(new ItemStack(ModRegistry.BEAN, 1, 14)),
    BLACK(new ItemStack(ModRegistry.BEAN, 1, 15)),
    ALL(new ItemStack(ModRegistry.BEAN, 1));

    final ItemStack stack;

    EnumBeans(ItemStack itemStack) {
        stack = itemStack;
    }

    public ItemStack getBean() {
        return stack;
    }
}
