package me.jacky1356400.luckybeans.command;

import me.jacky1356400.luckybeans.LuckyBeans;
import me.jacky1356400.luckybeans.init.ModRegistry;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommand;
import net.minecraft.command.ICommandSender;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;
import java.util.ArrayList;
import java.util.List;

public class CommandGiveBean extends CommandBase implements ICommand {

    private final List<String> aliases;

    public CommandGiveBean() {
        aliases = new ArrayList<>();
        aliases.add("give_bean");
        aliases.add("give_lucky_bean");
    }

    @Override @Nonnull
    public String getName() {
        return "givebean";
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public String getUsage(ICommandSender sender) {
        return "/givebean <amount>";
    }

    @Override @Nonnull
    public List<String> getAliases() {
        return this.aliases;
    }

    @Override @ParametersAreNonnullByDefault
    public void execute(MinecraftServer server, ICommandSender sender, String[] args) throws CommandException {
        World world = sender.getEntityWorld();
        EntityPlayer player = getPlayer(server, sender, sender.getName());
        int i = args.length == 1 ? parseInt(args[0], 1, 64) : 1;
        if (!world.isRemote) {
            if (args.length == 0) {
                player.addItemStackToInventory(new ItemStack(ModRegistry.MYSTBEAN, i));
                player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                LuckyBeans.logger.info("Successfully given " + i + " * Mysterious Lucky Bean to " + sender.getName());
            } else {
                player.addItemStackToInventory(new ItemStack(ModRegistry.MYSTBEAN, i));
                player.world.playSound(null, player.posX, player.posY, player.posZ, SoundEvents.ENTITY_ITEM_PICKUP, SoundCategory.PLAYERS, 0.2F, ((player.getRNG().nextFloat() - player.getRNG().nextFloat()) * 0.7F + 1.0F) * 2.0F);
                LuckyBeans.logger.info("Successfully given " + args[0] + " * Mysterious Lucky Bean to " + sender.getName());
            }
        }
    }

    @Override @ParametersAreNonnullByDefault
    public boolean checkPermission(MinecraftServer server, ICommandSender sender) {
        return true;
    }

    @Override @Nonnull @ParametersAreNonnullByDefault
    public List<String> getTabCompletions(MinecraftServer server, ICommandSender sender, String[] args, @Nullable BlockPos targetPos) {
        return null;
    }

    @Override @ParametersAreNonnullByDefault
    public boolean isUsernameIndex(String[] args, int index) {
        return false;
    }

    @Override @ParametersAreNonnullByDefault
    public int compareTo(ICommand o) {
        return 0;
    }

}
