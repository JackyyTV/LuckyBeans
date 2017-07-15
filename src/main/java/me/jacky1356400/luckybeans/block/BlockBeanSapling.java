package me.jacky1356400.luckybeans.block;

import me.jacky1356400.luckybeans.util.Data;
import net.minecraft.block.*;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class BlockBeanSapling extends BlockBush implements IGrowable {

    public BlockBeanSapling() {
        super();
        setSoundType(SoundType.PLANT);
        setHardness(0.0f);
        setRegistryName(Data.MODID + ":bean_sapling");
        setUnlocalizedName(Data.MODID + ".bean_sapling");
        setCreativeTab(Data.TAB);
        Data.BLOCKS.add(this);
    }

    @Override
    public boolean canPlaceBlockAt(World world, BlockPos pos) {
        Block soilBlock = world.getBlockState(pos.down()).getBlock();

        return this.isSuitableSoilBlock(soilBlock);
    }

    private void validatePosition(World world, BlockPos pos, IBlockState state) {
        if (!this.canPlaceBlockAt(world, pos)) {
            this.dropBlockAsItem(world, pos, state, 0);

            world.setBlockToAir(pos);
        }
    }

    @Override
    public boolean isOpaqueCube(IBlockState state) {
        return false;
    }

    @Override
    public boolean isFullCube(IBlockState state) {
        return false;
    }

    private boolean isSuitableSoilBlock(Block soilBlock) {
        return soilBlock == Blocks.GRASS || soilBlock == Blocks.DIRT || soilBlock == Blocks.FARMLAND;
    }

    @Override
    public void updateTick(World worldIn, BlockPos pos, IBlockState state, Random rand) {
        if (!worldIn.isRemote) {
            super.updateTick(worldIn, pos, state, rand);

            if (worldIn.getLightFromNeighbors(pos.up()) >= 9 && rand.nextInt(7) == 0) {
                this.grow(worldIn, rand, pos, state);
            }
        }
    }


    @Override
    public boolean canGrow(World worldIn, BlockPos pos, IBlockState state, boolean isClient) {
        return true;
    }

    @Override
    public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        return rand.nextFloat() < 0.45D;
    }

    @Override
    public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state) {
        this.grow(worldIn, rand, pos, state);
    }

    @Override
    public BlockRenderLayer getBlockLayer() {
        return BlockRenderLayer.CUTOUT;
    }

    @Override
    public IBlockState getPlant(net.minecraft.world.IBlockAccess world, BlockPos pos) {
        final IBlockState state = world.getBlockState(pos);
        if (state.getBlock() != this) return getDefaultState();
        return state;
    }

}
