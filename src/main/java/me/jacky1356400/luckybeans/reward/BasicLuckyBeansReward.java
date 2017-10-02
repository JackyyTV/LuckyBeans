package me.jacky1356400.luckybeans.reward;

import me.jacky1356400.luckybeans.util.EnumBeans;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BasicLuckyBeansReward implements IReward {

    private String name;
    private int chance;
    private EnumBeans[] beans;

    public BasicLuckyBeansReward(String name, int chance, EnumBeans... beans) {
        this.name = name;
        this.chance = chance;
        this.beans = beans;
    }

    @Override
    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
        if (!world.isRemote)
            for (EnumBeans bean : beans)
                if (player.getHeldItemMainhand() == bean.getBean())
                    spawnReward(world, pos, player);
    }

    @Override
    public int getChance() {
        return this.chance;
    }

    @Override
    public String getName() {
        return this.name;
    }

}
