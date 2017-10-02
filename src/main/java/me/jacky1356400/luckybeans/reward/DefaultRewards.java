package me.jacky1356400.luckybeans.reward;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.handler.MobHandler;
import me.jacky1356400.luckybeans.handler.PotionHandler;
import me.jacky1356400.luckybeans.handler.RewardHandler;
import me.jacky1356400.luckybeans.handler.SoundHandler;
import me.jacky1356400.luckybeans.init.ModRegistry;
import me.jacky1356400.luckybeans.util.EnumBeans;
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
import net.minecraft.world.World;

import java.util.Random;

public class DefaultRewards {

    public static void init() {
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_potion_effect_x1", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new PotionHandler().giveRandomPotionEffect(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_potion_effect_x2", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new PotionHandler().giveRandomPotionEffect(player);
                        new PotionHandler().giveRandomPotionEffect(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_potion_effect_x3", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new PotionHandler().giveRandomPotionEffect(player);
                        new PotionHandler().giveRandomPotionEffect(player);
                        new PotionHandler().giveRandomPotionEffect(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_sound_event", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new SoundHandler().giveRandomSoundEvent(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_mob_x1", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new MobHandler(player.getEntityWorld()).giveRandomMob(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_mob_x2", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new MobHandler(player.getEntityWorld()).giveRandomMob(player);
                        new MobHandler(player.getEntityWorld()).giveRandomMob(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_mob_x3", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        new MobHandler(player.getEntityWorld()).giveRandomMob(player);
                        new MobHandler(player.getEntityWorld()).giveRandomMob(player);
                        new MobHandler(player.getEntityWorld()).giveRandomMob(player);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":grass_with_sapling", 0, EnumBeans.GREEN, EnumBeans.LIME) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.BLOCK_GRASS_PLACE,
                                SoundCategory.MASTER, 1.0F, 1.0F);
                        player.world.setBlockState(new BlockPos(player.posX, player.posY - 1, player.posZ), Blocks.GRASS.getDefaultState());
                        player.world.setBlockState(new BlockPos(player.posX, player.posY, player.posZ), Blocks.SAPLING.getDefaultState());
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":spawn_lava", 0, EnumBeans.RED, EnumBeans.ORANGE) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.world.setBlockState(new BlockPos(player.posX, player.posY, player.posZ), Blocks.FLOWING_LAVA.getDefaultState());
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":set_on_fire", 0, EnumBeans.RED, EnumBeans.ORANGE) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.setFire(new Random().nextInt(30));
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":set_random_hp", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.setHealth(player.world.rand.nextInt(19) + 1);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":set_random_arrow", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ARROW_HIT,
                                SoundCategory.MASTER, 0.5F, 1.0F);
                        player.setArrowCountInEntity(new Random().nextInt(50));
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":mini_explosion", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.world.createExplosion(null, player.posX, player.posY, player.posZ,
                                0.25F, false);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":spawn_water", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        if (!player.world.provider.doesWaterVaporize())
                            player.world.setBlockState(new BlockPos(player), Blocks.FLOWING_WATER.getDefaultState());
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":drop_own_head", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        if (player != null) {
                            player.attackEntityFrom(DamageSource.MAGIC, player.getHealth() - 1);
                            ItemStack skull = new ItemStack(Items.SKULL, 1, 3);
                            if(!skull.hasTagCompound())
                                skull.setTagCompound(new NBTTagCompound());
                            skull.getTagCompound().setString("SkullOwner", player.getName());
                            player.entityDropItem(skull, 0);
                        }
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":throw_into_air", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.isAirBorne = true;
                        player.motionY = 20;
                        EntityPlayerMP mp = (EntityPlayerMP) player;
                        mp.connection.sendPacket(new SPacketEntityVelocity(player.getEntityId(),
                                player.motionX, player.motionY, player.motionZ));
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":random_item", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.sendMessage(new TextComponentString("Where did you pick that up from?"));
                        ItemStack item = new ItemStack(Item.getItemById(256 + new Random().nextInt(166)));
                        item.setStackDisplayName(player.getName() + "'s " + item.getDisplayName());
                        player.entityDropItem(item, 0);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":try_again_x1", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.sendMessage(new TextComponentString("That was \"lucky\"... Try again!"));
                        player.entityDropItem(new ItemStack(ModRegistry.MYSTBEAN, 1), 0);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":try_again_x2", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.sendMessage(new TextComponentString("That was \"lucky\"... Try again!"));
                        player.entityDropItem(new ItemStack(ModRegistry.MYSTBEAN, 2), 0);
                    }
                }
        );
        RewardHandler.INSTANCE.registerReward(
                new BasicLuckyBeansReward(LuckyBeans.MODID + ":try_again_x3", 0, EnumBeans.ALL) {
                    @Override
                    public void spawnReward(World world, BlockPos pos, EntityPlayer player) {
                        player.sendMessage(new TextComponentString("That was \"lucky\"... Try again!"));
                        player.entityDropItem(new ItemStack(ModRegistry.MYSTBEAN, 3), 0);
                    }
                }
        );
    }

}
