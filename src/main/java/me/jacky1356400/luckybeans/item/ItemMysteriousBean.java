package me.jacky1356400.luckybeans.item;

import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ItemMysteriousBean extends Item implements IHasModel {

    public ItemMysteriousBean(){
        setRegistryName(Data.MODID + ":mysterious_bean");
        setUnlocalizedName(Data.MODID + ".mysterious_bean");
        setCreativeTab(Data.TAB);
        Data.ITEMS.add(this);
    }

    public boolean isFull3D(){
        return true;
    }

    @Override
    public EnumActionResult onItemUse(EntityPlayer player, World world, BlockPos pos, EnumHand hand, EnumFacing facing, float hitX, float hitY, float hitZ) {
        return giveBean(world, pos, player, player.getHeldItem(hand))? EnumActionResult.SUCCESS : EnumActionResult.FAIL;

    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {
        boolean result = giveBean(world, player.getPosition(), player, player.getHeldItem(hand));
        return ActionResult.newResult(result? EnumActionResult.SUCCESS : EnumActionResult.FAIL, player.getHeldItem(hand));
    }

    private static boolean giveBean(World world, BlockPos pos, EntityPlayer player, ItemStack stack) {
        stack.shrink(1);
        return true;
    }

}
