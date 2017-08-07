package me.jacky1356400.luckybeans.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.SoundEvents;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.math.MathHelper;

import java.util.Random;

public class SoundHandler {

    public SoundEvent[] soundEvents = new SoundEvent[8];

    public SoundHandler() {
        soundEvents[0] = SoundEvents.ENTITY_GHAST_SCREAM;
        soundEvents[1] = SoundEvents.ENTITY_GHAST_WARN;
        soundEvents[2] = SoundEvents.ENTITY_ENDERMEN_SCREAM;
        soundEvents[3] = SoundEvents.ENTITY_CREEPER_PRIMED;
        soundEvents[4] = SoundEvents.ENTITY_GENERIC_EXPLODE;
        soundEvents[5] = SoundEvents.AMBIENT_CAVE;
        soundEvents[6] = SoundEvents.ENTITY_ENDERDRAGON_DEATH;
        soundEvents[7] = SoundEvents.ENTITY_WITHER_SPAWN;
    }

    public void giveRandomSoundEvent(EntityLivingBase living) {
        int r = new Random().nextInt(soundEvents.length);
        SoundEvent soundEvent = soundEvents[r];
        living.world.playSound(null, living.posX, living.posY, living.posZ, soundEvent, SoundCategory.MASTER,
                MathHelper.nextFloat(new Random(), 0.01F, 1.0F), new Random().nextFloat());
    }

}
