package me.jacky1356400.luckybeans.worldgen;

import me.jacky1356400.luckybeans.init.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.Random;

public class BeanTreeGen extends WorldGenAbstractTree {

    private final int minTreeHeight;

    public BeanTreeGen(int minTreeHeight) {
        super(true);
        this.minTreeHeight = minTreeHeight;
    }

    @Override
    @ParametersAreNonnullByDefault
    @SuppressWarnings("deprecation")
    public boolean generate(World worldIn, Random rand, BlockPos blockPos) {
        final BlockPos.MutableBlockPos mutableBlockPos = new BlockPos.MutableBlockPos();

        int treeHeight = rand.nextInt(3) + this.minTreeHeight;
        boolean canGenerate = true;

        int treeTopPos = blockPos.getY() + treeHeight;

        if (blockPos.getY() >= 1 && treeTopPos <= 255) {
            for (int iPosY = blockPos.getY(); iPosY <= treeTopPos + 1; ++iPosY) {
                int k = 1;

                if (iPosY == blockPos.getY()) {
                    k = 0;
                }

                if (iPosY >= treeTopPos - 1) {
                    k = 2;
                }

                for (int iPosX = blockPos.getX() - k, halflength = blockPos.getX() + k; iPosX <= halflength + k && canGenerate; ++iPosX) {
                    for (int iPosZ = blockPos.getZ() - k, halfLength = blockPos.getZ() + k; iPosZ <= halfLength + k && canGenerate; ++iPosZ) {
                        if (iPosY >= 0 && iPosY < 256) {
                            if (!this.isReplaceable(worldIn, mutableBlockPos.setPos(iPosX, iPosY, iPosZ))) {
                                canGenerate = false;
                            }
                        } else {
                            canGenerate = false;
                        }
                    }
                }
            }
            if (!canGenerate) {
                return false;
            } else {
                BlockPos down = blockPos.down();
                IBlockState blockState = worldIn.getBlockState(down);
                Block block = blockState.getBlock();
                boolean isSoil = block.canSustainPlant(blockState, worldIn, down, EnumFacing.UP, (BlockSapling) Blocks.SAPLING);

                if (isSoil && treeTopPos + 1 < 256) {
                    block.onPlantGrow(blockState, worldIn, down, blockPos);

                    for (int iPosY = treeTopPos - 3; iPosY <= treeTopPos; ++iPosY) {
                        int distanceToTop = iPosY - treeTopPos;
                        int margin = 1 - distanceToTop / 2;

                        for (int xStart = blockPos.getX() - margin, xEnd = blockPos.getX() + margin; xStart <= xEnd; ++xStart) {
                            int currentMarginX = Math.abs(xStart - blockPos.getX());

                            for (int zStart = blockPos.getZ() - margin, zEnd = blockPos.getZ() + margin; zStart <= zEnd; ++zStart) {
                                int currentMarginZ = Math.abs(zStart - blockPos.getZ());

                                if (currentMarginX != margin || currentMarginZ != margin || rand.nextInt(2) != 0 && distanceToTop != 0) {
                                    BlockPos leavesBlockPos = new BlockPos(xStart, iPosY, zStart);
                                    IBlockState leavesBlockState = worldIn.getBlockState(leavesBlockPos);

                                    Block leavesBlock = worldIn.getBlockState(leavesBlockPos).getBlock();

                                    if (leavesBlock.isAir(leavesBlockState, worldIn, leavesBlockPos) || leavesBlock.isLeaves(leavesBlockState, worldIn, leavesBlockPos)
                                            || leavesBlock.getMaterial(leavesBlockState) == Material.VINE) {
                                        this.setBlockAndNotifyAdequately(worldIn, leavesBlockPos, ModRegistry.BEANLEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, true));

                                        BlockPos fruitBlockPos = new BlockPos(xStart, iPosY - 1, zStart);
                                        BlockPos blockBelowFruitPos = new BlockPos(xStart, iPosY - 2, zStart);
                                        if (worldIn.isAirBlock(fruitBlockPos)) {
                                            if (worldIn.isAirBlock(blockBelowFruitPos) && iPosY > 2) {
                                                if (rand.nextInt(4) == 0) {
                                                    this.setBlockAndNotifyAdequately(worldIn, fruitBlockPos, ModRegistry.MYSTBEANBLOCK.getDefaultState());
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }

                    for (int i = 0; i < treeHeight; ++i) {
                        BlockPos upN = blockPos.up(i);
                        IBlockState blockStateUp = worldIn.getBlockState(upN);
                        Block blockUp = blockStateUp.getBlock();

                        if (blockUp.isAir(blockStateUp, worldIn, upN) || blockUp.isLeaves(blockStateUp, worldIn, upN) ||
                                blockUp == ModRegistry.MYSTBEANBLOCK
                                || blockUp.getMaterial(blockStateUp) == Material.VINE) {
                            this.setBlockAndNotifyAdequately(worldIn, blockPos.up(i), Blocks.LOG.getDefaultState());
                        }
                    }
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

}
