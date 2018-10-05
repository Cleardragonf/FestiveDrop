package me.cleardragonf.festivedrop;

import com.google.inject.Inject;
import me.cleardragonf.festivedrop.Commands.SetChest;
import me.cleardragonf.festivedrop.Commands.SpawnChest;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.text.Text;

import java.io.File;

@Plugin(
        id = "festivedrop",
        version = "Beta1.0",
        name = "FestiveDrop",
        description = "Testing",
        url = "https://ourminecraftserver.000webhostapp.com",
        authors = {
                "Cleardragonf"
        }
)
public class FestiveDrop {

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;



    @Listener //setting of Commands
    public void allhands(GamePreInitializationEvent event){
        ConfigurationManager.getInstance().ConfigurationManager2(configDir);
        ConfigurationManager.getInstance().enable();
        CommandSpec SetChest;
        SetChest = CommandSpec.builder()
                .description(Text.of("Set the Chest for the Drop"))
                .executor(new SetChest())
                .build();
        Sponge.getCommandManager().register(this,SetChest,"FDset");
        CommandSpec SpawnChest;
        SpawnChest = CommandSpec.builder()
                .description(Text.of("Spawn the Chest from the Configuration File"))
                .executor(new SpawnChest())
                .build();
        Sponge.getCommandManager().register(this, SpawnChest, "FDSpawn");
    }

    @Listener //***
    public void onServerStart(GameStartedServerEvent event) {
        logger.info("Festive Drop Loaded");
    }


/*
    ******-*************May just need to be deleted???********************
    @Listener
    public void onChestInteract(InteractBlockEvent.Primary event, @First Player src) {
    Optional<Location<World>> a = event.getTargetBlock().getLocation();
    ChestInteract chestInteraction = new ChestInteract();
    chestInteraction.setTarget(a);
    Sponge.getServer().getBroadcastChannel().send(Text.of("You clicked on a Chest"));
    }
    */


}
