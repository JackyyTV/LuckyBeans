package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.util.IHasModel;
import me.jacky1356400.luckybeans.worldgen.BeanTreeGen;
import net.minecraft.client.resources.I18n;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Loader;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.Random;

public class ItemCreativeTreeGen extends Item implements IHasModel {

    public ItemCreativeTreeGen() {
        setRegistryName(LuckyBeans.MODID + ":creative_tree_generator");
        setUnlocalizedName(LuckyBeans.MODID + ".creative_tree_generator");
        setMaxStackSize(1);
        setCreativeTab(LuckyBeans.TAB);
        LuckyBeans.ITEMS.add(this);
    }

    @Override
    public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
        super.addInformation(stack, world, tooltip, flag);
        tooltip.add(I18n.format(LuckyBeans.MODID + ".tooltip.creative"));
        if (!Loader.isModLoaded("jei")) {
            tooltip.add(I18n.format(LuckyBeans.MODID + ".tooltip.creative_tree_generator1"));
            tooltip.add(I18n.format(LuckyBeans.MODID + ".tooltip.creative_tree_generator2"));
        }
    }

    @Override @Nonnull
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        if (!world.isRemote && !player.isSneaking()) {
            pos = pos.offset(facing);
            if (!player.canPlayerEdit(pos, facing, player.getHeldItem(hand))) {
                return EnumActionResult.FAIL;
            }
            else {
                if (world.isAirBlock(pos)) {
                    new BeanTreeGen(5).generate(world, new Random(), pos);
                }
                return EnumActionResult.SUCCESS;
            }
        }
        return EnumActionResult.FAIL;
    }

    @Override @Nonnull
    public EnumAction getItemUseAction(ItemStack stack) {
        return EnumAction.BLOCK;
    }

}
