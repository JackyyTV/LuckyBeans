package me.jacky1356400.luckybeans.worldgen;

import me.jacky1356400.luckybeans.Config;
import me.jacky1356400.luckybeans.block.BlockBeanSapling;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.fml.common.IWorldGenerator;

import java.util.Random;

public class BeanTreeWorldGen implements IWorldGenerator {

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
        if (random.nextInt(Config.beanTreesGenChance) == 0) {
            int xChunk = chunkX * 16 + 8;
            int zChunk = chunkZ * 16 + 8;

            final BlockPos blockPos = world.getTopSolidOrLiquidBlock(new BlockPos(xChunk + MathHelper.getInt(random, -4, 4), 0, zChunk + MathHelper.getInt(random, -4, 4)));
            final Biome biome = world.getBiomeForCoordsBody(blockPos);

            if ((BiomeDictionary.hasType(biome, BiomeDictionary.Type.PLAINS))
                    || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.WET))
                    || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.HILLS))
                    || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.MOUNTAIN))
                    || (BiomeDictionary.hasType(biome, BiomeDictionary.Type.FOREST))) {
                if (Config.beanTreesGen) {
                    generateBeanTrees(world, blockPos);
                }
            }
        }
    }

    private void generateBeanTrees(World world, BlockPos pos) {
        BlockBeanSapling.worldGenTrees(world, pos);
    }
}