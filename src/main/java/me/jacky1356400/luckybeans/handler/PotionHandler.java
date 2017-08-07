package me.jacky1356400.luckybeans.handler;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.MobEffects;
import net.minecraft.potion.PotionEffect;

import java.util.Random;

public class PotionHandler {

    public PotionEffect[] effects = new PotionEffect[27];

    public PotionHandler() {
        effects[0] = new PotionEffect(MobEffects.SPEED, new Random().nextInt(2400), new Random().nextInt(4));
        effects[1] = new PotionEffect(MobEffects.SLOWNESS, new Random().nextInt(2400), new Random().nextInt(4));
        effects[2] = new PotionEffect(MobEffects.HASTE, new Random().nextInt(2400), new Random().nextInt(4));
        effects[3] = new PotionEffect(MobEffects.MINING_FATIGUE, new Random().nextInt(2400), new Random().nextInt(4));
        effects[4] = new PotionEffect(MobEffects.STRENGTH, new Random().nextInt(2400), new Random().nextInt(4));
        effects[5] = new PotionEffect(MobEffects.INSTANT_HEALTH, 1, new Random().nextInt(4));
        effects[6] = new PotionEffect(MobEffects.INSTANT_DAMAGE, 1, new Random().nextInt(4));
        effects[7] = new PotionEffect(MobEffects.JUMP_BOOST, new Random().nextInt(2400), new Random().nextInt(4));
        effects[8] = new PotionEffect(MobEffects.NAUSEA, new Random().nextInt(2400), new Random().nextInt(4));
        effects[9] = new PotionEffect(MobEffects.REGENERATION, new Random().nextInt(2400), new Random().nextInt(4));
        effects[10] = new PotionEffect(MobEffects.RESISTANCE, new Random().nextInt(2400), new Random().nextInt(4));
        effects[11] = new PotionEffect(MobEffects.FIRE_RESISTANCE, new Random().nextInt(2400), new Random().nextInt(4));
        effects[12] = new PotionEffect(MobEffects.WATER_BREATHING, new Random().nextInt(2400), new Random().nextInt(4));
        effects[13] = new PotionEffect(MobEffects.INVISIBILITY, new Random().nextInt(2400), new Random().nextInt(4));
        effects[14] = new PotionEffect(MobEffects.BLINDNESS, new Random().nextInt(2400), new Random().nextInt(4));
        effects[15] = new PotionEffect(MobEffects.NIGHT_VISION, new Random().nextInt(2400), new Random().nextInt(4));
        effects[16] = new PotionEffect(MobEffects.HUNGER, new Random().nextInt(2400), new Random().nextInt(4));
        effects[17] = new PotionEffect(MobEffects.WEAKNESS, new Random().nextInt(2400), new Random().nextInt(4));
        effects[18] = new PotionEffect(MobEffects.POISON, new Random().nextInt(2400), new Random().nextInt(4));
        effects[19] = new PotionEffect(MobEffects.WITHER, new Random().nextInt(2400), new Random().nextInt(4));
        effects[20] = new PotionEffect(MobEffects.HEALTH_BOOST, new Random().nextInt(2400), new Random().nextInt(4));
        effects[21] = new PotionEffect(MobEffects.ABSORPTION, new Random().nextInt(2400), new Random().nextInt(4));
        effects[22] = new PotionEffect(MobEffects.SATURATION, new Random().nextInt(2400), new Random().nextInt(4));
        effects[23] = new PotionEffect(MobEffects.GLOWING, new Random().nextInt(2400), new Random().nextInt(4));
        effects[24] = new PotionEffect(MobEffects.LEVITATION, new Random().nextInt(2400), new Random().nextInt(4));
        effects[25] = new PotionEffect(MobEffects.LUCK, new Random().nextInt(2400), new Random().nextInt(4));
        effects[26] = new PotionEffect(MobEffects.UNLUCK, new Random().nextInt(2400), new Random().nextInt(4));
    }

    public void giveRandomPotionEffect(EntityLivingBase living) {
        int r = new Random().nextInt(effects.length);
        PotionEffect effect = effects[r];
        living.addPotionEffect(new PotionEffect(effect.getPotion(), effect.getDuration(), effect.getAmplifier()));
    }

}
