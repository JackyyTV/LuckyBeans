package me.jacky1356400.luckybeans.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.*;
import net.minecraft.world.World;

import java.util.Random;

public class MobHandler {

    public Entity[] mobs = new Entity[17];

    public MobHandler(World world) {
        mobs[0] = new EntityZombie(world);
        mobs[1] = new EntitySkeleton(world);
        mobs[2] = new EntitySpider(world);
        mobs[3] = new EntityCreeper(world);
        mobs[4] = new EntityCaveSpider(world);
        mobs[5] = new EntityBlaze(world);
        mobs[6] = new EntitySilverfish(world);
        mobs[7] = new EntityEndermite(world);
        mobs[8] = new EntityWitch(world);
        mobs[9] = new EntitySlime(world);
        mobs[10] = new EntityHusk(world);
        mobs[11] = new EntityZombieVillager(world);
        mobs[12] = new EntityWitherSkeleton(world);
        mobs[13] = new EntityShulker(world);
        mobs[14] = new EntityMagmaCube(world);
        mobs[15] = new EntityGuardian(world);
        mobs[16] = new EntityEnderman(world);
    }

    public void giveRandomMob(EntityLivingBase living) {
        int r = new Random().nextInt(mobs.length);
        Entity mob = mobs[r];
        mob.setPosition(living.posX, living.posY, living.posZ);
        living.world.spawnEntity(mob);
    }

}
