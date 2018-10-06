package me.cleardragonf.festivedrop.Commands;

import org.spongepowered.api.command.CommandException;
import org.spongepowered.api.command.CommandResult;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.args.CommandContext;
import org.spongepowered.api.command.spec.CommandExecutor;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.inventory.ItemStack;

import java.util.Random;

public class SpawnChest implements CommandExecutor{


    @Override
    public CommandResult execute(CommandSource src, CommandContext args) throws CommandException {
        String id = args.<String>getOne("ID Tag").get();

        if((src instanceof Player)){
            SpawnItem spawnitem = new SpawnItem();
            spawnitem.spawnItem(randomizer(), ((Player) src).getLocation(), id);
        }
        return CommandResult.success();

    }


    public ItemStack randomizer() {

        Random rand = new Random();
        int n = rand.nextInt(20) + 1;
        if (n == 1) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIAMOND_BLOCK).build();
            return Excaliber;
        }
        if (n == 2) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.COAL_BLOCK).build();
            return Excaliber;
        }
        if (n == 3) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.BRICK_BLOCK).build();
            return Excaliber;
        }
        if (n == 4) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.EMERALD_BLOCK).build();
            return Excaliber;
        }
        if (n == 5) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.GOLD_BLOCK).build();
            return Excaliber;
        }
        if (n == 6) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.IRON_BLOCK).build();
            return Excaliber;
        }
        if (n == 7) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.LAPIS_BLOCK).build();
            return Excaliber;
        }
        if (n == 8) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.QUARTZ_BLOCK).build();
            return Excaliber;
        }
        if (n == 9) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.REDSTONE_BLOCK).build();
            return Excaliber;
        }
        if (n == 10) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.CLAY).build();
            return Excaliber;
        }
        if (n == 11) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.LOG).build();
            return Excaliber;
        }
        if (n == 12) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.BLAZE_ROD).build();
            return Excaliber;
        }
        if (n == 13) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.SAND).build();
            return Excaliber;
        }
        if (n == 14) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.STONE).build();
            return Excaliber;
        }
        if (n == 15) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIRT).build();
            return Excaliber;
        }
        if (n == 16) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIRT).build();
            return Excaliber;
        }
        if (n == 17) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIRT).build();
            return Excaliber;
        }
        if (n == 18) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIRT).build();
            return Excaliber;
        }
        if (n == 19) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIRT).build();
            return Excaliber;
        }
        if (n == 20) {
            ItemStack Excaliber = ItemStack.builder()
                    .itemType(ItemTypes.DIRT).build();
            return Excaliber;
        }
        return null;
    }



}
