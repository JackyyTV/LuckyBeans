package me.jacky1356400.luckybeans.block;

import java.util.Random;

import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.Data;
import me.jacky1356400.luckybeans.util.IHasModel;
import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;

public class BlockMysteriousBean extends Block implements IHasModel {

	public static final AxisAlignedBB BULB_AABB = new AxisAlignedBB(0.3125D, 0.0D, 0.3125D, 0.6875D, 0.5D, 0.6875D);

	public BlockMysteriousBean() {
		super(Material.WOOD);
		setSoundType(SoundType.WOOD);
		setHardness(0.1f);
		setRegistryName("mysterious_bean_block");
		setUnlocalizedName(Data.MODID + ".mysterious_bean_block");
		setCreativeTab(Data.TAB);
		Data.BLOCKS.add(this);
		Data.ITEMS.add(new ItemBlock(this).setRegistryName(getRegistryName()));
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return ModRegistry.MYSTBEAN;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return false;
	}

	@Override
	public AxisAlignedBB getBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BULB_AABB;
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return BULB_AABB;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return false;
	}

	@Override
	public boolean isNormalCube(IBlockState state) {
		return false;
	}

}