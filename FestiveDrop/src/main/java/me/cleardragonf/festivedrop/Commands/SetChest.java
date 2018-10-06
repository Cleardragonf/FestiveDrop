package me.cleardragonf.festivedrop.Commands;

import me.cleardragonf.festivedrop.ConfigurationManager;
import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.world.World;

public class SetChest implements CommandExecutor{


    public ItemStack generate(){
        ItemStack Excaliber = ItemStack.builder()
                .itemType(ItemTypes.DIAMOND_SWORD).build();
        return Excaliber;
    }
    World world;
    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if((src instanceof Player)){
            String message = args.<String>getOne("ID Tag").get();
            Integer block = args.<Integer>getOne("# of Items").get();
            String runOnStart = args.<String>getOne("Run on Start?").get();
            String id = message;
            ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "X: ").setValue(((Player) src).getPosition().getX());
            ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Y: ").setValue(((Player) src).getPosition().getY());
            ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Z: ").setValue(((Player) src).getPosition().getZ());
            ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Number of Items: ").setValue(block);
            ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Run on Start?").setValue(runOnStart);
            ConfigurationManager.getInstance().save1();
        }
        return CommandResult.success();

    }


}
