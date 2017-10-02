package me.jacky1356400.luckybeans.handler;

import com.google.common.collect.Maps;
import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.reward.IReward;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.apache.logging.log4j.Level;

import java.util.Map;
import java.util.Random;

public class RewardHandler {

    public static RewardHandler INSTANCE = new RewardHandler();
    public Map<String, IReward> rewardMap = Maps.newHashMap();
    private static IReward lastReward = null;

    public void randomReward(World world, BlockPos pos, EntityPlayer player, int chance) {
        if(this.rewardMap.size() == 0) {
            LuckyBeans.logger.log(Level.WARN, "There are no rewards being registered!");
            return;
        }

        int lowerIndex = 0;
        int upperIndex = rewardMap.size() - 1;
        int lowerRange = chance - 20 < -100 ? -100 : chance - 20;
        int upperRange = chance + 20 > 100 ? 100 : chance + 20;

        while(rewardMap.get(lowerIndex).getChance() < lowerRange) {
            lowerIndex++;
            if(lowerIndex >= rewardMap.size()) {
                lowerIndex--;
                break;
            }
        }
        while(rewardMap.get(upperIndex).getChance() > upperRange) {
            upperIndex--;
            if(upperIndex < 0) {
                upperIndex++;
                break;
            }
        }
        int range = upperIndex - lowerIndex > 0 ? upperIndex - lowerIndex : 1;
        int pick = new Random().nextInt(range) + lowerIndex;
        IReward pickedReward = rewardMap.get(pick);
        if(lastReward != null) {
            byte atempts = 0;
            while (atempts < 5 && lastReward.getName().equals(pickedReward.getName())) {
                pick = new Random().nextInt(range) + lowerIndex;
                pickedReward = rewardMap.get(pick);
                atempts++;
            }
        }
        LuckyBeans.logger.log(Level.INFO, "Spawned reward: " + pickedReward.getName());
        pickedReward.spawnReward(world, pos, player);
        lastReward = pickedReward;
    }

    public void registerReward(IReward reward) {
        rewardMap.put(reward.getName(), reward);
    }

}
