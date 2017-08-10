package me.jacky1356400.luckybeans.handler;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.init.ModRegistry;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.play.server.SPacketEntityVelocity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentString;

import java.util.Random;

public class RewardHandler {

    public void rewards(ItemStack stack, EntityLivingBase living, int id) {
        switch(id) {
            case 0 : {
                //Random potion effect x1
                LuckyBeans.logger.info("Spawned reward: Random potion effect x1");
                new PotionHandler().giveRandomPotionEffect(living);
                break;
            }
            case 1 : {
                //Random potion effect x2
                LuckyBeans.logger.info("Spawned reward: Random potion effect x2");
                new PotionHandler().giveRandomPotionEffect(living);
                new PotionHandler().giveRandomPotionEffect(living);
                break;
            }
            case 2 : {
                //Random potion effect x3
                LuckyBeans.logger.info("Spawned reward: Random potion effect x3");
                new PotionHandler().giveRandomPotionEffect(living);
                new PotionHandler().giveRandomPotionEffect(living);
                new PotionHandler().giveRandomPotionEffect(living);
                break;
            }
            case 3 : {
                //Play random sound event
                LuckyBeans.logger.info("Spawned reward: Random sound event");
                new SoundHandler().giveRandomSoundEvent(living);
                break;
            }
            case 4 : {
                //Spawn random mob x1
                LuckyBeans.logger.info("Spawned reward: Random mob x1");
                new MobHandler(living.getEntityWorld()).giveRandomMob(living);
                break;
            }
            case 5 : {
                //Spawn random mob x2
                LuckyBeans.logger.info("Spawned reward: Random mob x2");
                new MobHandler(living.getEntityWorld()).giveRandomMob(living);
                new MobHandler(living.getEntityWorld()).giveRandomMob(living);
                break;
            }
            case 6 : {
                //Spawn random mob x3
                LuckyBeans.logger.info("Spawned reward: Random mob x3");
                new MobHandler(living.getEntityWorld()).giveRandomMob(living);
                new MobHandler(living.getEntityWorld()).giveRandomMob(living);
                new MobHandler(living.getEntityWorld()).giveRandomMob(living);
                break;
            }
            case 7 : {
                //Place down grass block with sapling on top
                LuckyBeans.logger.info("Spawned reward: Spawn grass + sapling");
                living.world.playSound(null, living.posX, living.posY, living.posZ, SoundEvents.BLOCK_GRASS_PLACE,
                        SoundCategory.MASTER, 0.5F, 1.0F);
                living.world.setBlockState(new BlockPos(living.posX, living.posY - 1, living.posZ), Blocks.GRASS.getDefaultState());
                living.world.setBlockState(new BlockPos(living.posX, living.posY, living.posZ), Blocks.SAPLING.getDefaultState());
                break;
            }
            case 8 : {
                //Place down lava
                LuckyBeans.logger.info("Spawned reward: Spawn lava");
                living.world.setBlockState(new BlockPos(living.posX, living.posY, living.posZ), Blocks.FLOWING_LAVA.getDefaultState());
                break;
            }
            case 9 : {
                //Set on fire for random amount of time (up to 30 seconds)
                LuckyBeans.logger.info("Spawned reward: Set player on fire");
                living.setFire(new Random().nextInt(30));
                break;
            }
            case 10 : {
                //Set random HP
                LuckyBeans.logger.info("Spawned reward: Set random HP on player");
                living.setHealth(living.world.rand.nextInt(19) + 1);
                break;
            }
            case 11 : {
                //Set random amount of arrows on player (up to 50)
                LuckyBeans.logger.info("Spawned reward: Set random amount of arrows on player");
                living.world.playSound(null, living.posX, living.posY, living.posZ, SoundEvents.ENTITY_ARROW_HIT,
                        SoundCategory.MASTER, 0.5F, 1.0F);
                living.setArrowCountInEntity(new Random().nextInt(50));
                break;
            }
            case 12 : {
                //Mini explosion
                LuckyBeans.logger.info("Spawned reward: Mini explosion");
                living.world.createExplosion(null, living.posX, living.posY, living.posZ,
                        0.25F, false);
                break;
            }
            case 13 : {
                //Spawn water
                LuckyBeans.logger.info("Spawned reward: Spawn water");
                if (!living.world.provider.doesWaterVaporize())
                    living.world.setBlockState(new BlockPos(living), Blocks.FLOWING_WATER.getDefaultState());
                break;
            }
            case 14 : {
                //Drop player's own head
                LuckyBeans.logger.info("Spawned reward: Drop player head");
                if (living instanceof EntityPlayer) {
                    living.attackEntityFrom(DamageSource.MAGIC, living.getHealth() - 1);
                    ItemStack skull = new ItemStack(Items.SKULL, 1, 3);
                    if(!skull.hasTagCompound())
                        skull.setTagCompound(new NBTTagCompound());
                    skull.getTagCompound().setString("SkullOwner", living.getName());
                    living.entityDropItem(skull, 0);
                }
                break;
            }
            case 15 : {
                //Throw into the air
                LuckyBeans.logger.info("Spawned reward: Throw into the air");
                living.isAirBorne = true;
                living.motionY = 20;
                EntityPlayerMP mp = (EntityPlayerMP) living;
                mp.connection.sendPacket(new SPacketEntityVelocity(living.getEntityId(),
                        living.motionX, living.motionY, living.motionZ));
                break;
            }
            case 16 : {
                //Spawn random item
                LuckyBeans.logger.info("Spawned reward: Spawn random item");
                living.sendMessage(new TextComponentString("Where did you pick that up from?"));
                ItemStack item = new ItemStack(Item.getItemById(256 + new Random().nextInt(166)));
                item.setStackDisplayName(living.getName() + "'s " + item.getDisplayName());
                living.entityDropItem(item, 0);
                break;
            }
            case 17 : {
                //Spawn a Mysterious Lucky Bean - try again!
                LuckyBeans.logger.info("Spawned reward: Try again");
                living.sendMessage(new TextComponentString("Nope, try again!"));
                living.entityDropItem(new ItemStack(ModRegistry.MYSTBEAN), 0);
                break;
            }
            case 18 : {
                //Spawn 2x Mysterious Lucky Bean - try again!
                LuckyBeans.logger.info("Spawned reward: Try again x2");
                living.sendMessage(new TextComponentString("That was \"lucky\"... Try again!"));
                living.entityDropItem(new ItemStack(ModRegistry.MYSTBEAN, 2), 0);
                break;
            }
            case 19 : {
                //Spawn 3x Mysterious Lucky Bean - try again!
                LuckyBeans.logger.info("Spawned reward: Try again x3");
                living.sendMessage(new TextComponentString("That was \"lucky\"... Try again!"));
                living.entityDropItem(new ItemStack(ModRegistry.MYSTBEAN, 3), 0);
                break;
            }
        }
    }

    public void randomReward(EntityLivingBase player, ItemStack stack) {
        rewards(stack, player, new Random().nextInt(20));
    }

}
