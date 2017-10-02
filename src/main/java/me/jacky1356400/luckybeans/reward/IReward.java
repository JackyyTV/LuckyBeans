package me.jacky1356400.luckybeans.reward;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public interface IReward {
    /**
     * @param world World that reward is being triggered
     * @param pos BlockPos that reward is being triggered
     * @param player EntityPlayer that ate the bean
     */
    void spawnReward(World world, BlockPos pos, EntityPlayer player);

    /**
     * @return How "lucky" the bean is. Can be from -100 to 100. 0 would be an "average" reward.
     */
    int getChance();

    /**
     * @return Name for the reward.
     */
    String getName();
}
