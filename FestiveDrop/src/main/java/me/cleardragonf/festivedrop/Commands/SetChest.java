package me.cleardragonf.festivedrop.Commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

public class SetChest implements CommandExecutor{

    public ItemStack generate(){
        ItemStack Excaliber = ItemStack.builder()
                .itemType(ItemTypes.DIAMOND_SWORD).build();
        return Excaliber;
    }

    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        if((src instanceof Player)){
            SpawnItem spawnitem = new SpawnItem();
            spawnitem.spawnItem(generate(), ((Player) src).getLocation());

        }
        return CommandResult.success();

    }


}
