package me.cleardragonf.festivedrop;

import com.google.inject.Inject;
import me.cleardragonf.festivedrop.Commands.SetChest;
import me.cleardragonf.festivedrop.Commands.SpawnChest;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.text.Text;

import javax.annotation.Nonnull;
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

    private static FestiveDrop instance;

    @Nonnull
    private final PluginContainer pluginContainer;

    @Inject
    public FestiveDrop(@Nonnull final PluginContainer pluginContainer) {
        FestiveDrop.instance = this;
        this.pluginContainer = pluginContainer;
    }

    public static FestiveDrop getInstance() {
        return FestiveDrop.instance;
    }

    @Nonnull
    public PluginContainer getPluginContainer() {
        return this.pluginContainer;
    }

    @Inject
    private Logger logger;

    @Inject
    @ConfigDir(sharedRoot = false)
    private File configDir;




    @Listener //setting of Commands
    public void allhands(GamePreInitializationEvent event){
        ConfigurationManager.getInstance().ConfigurationManager2(configDir);
        ConfigurationManager.getInstance().enable();

        CommandSpec FD;
        FD = CommandSpec.builder()
                .child(CommandSpec.builder()
                        .permission("FestiveDrop.command.setLocation")
                        .arguments(
                                GenericArguments.string(Text.of("ID Tag")),
                                GenericArguments.string(Text.of("# of Items")),
                                GenericArguments.string(Text.of("Run on Start?"))
                        )
                        .executor(new SetChest())
                    .build(), "setLocation"
                )
                .child(CommandSpec.builder()
                        .permission("FestiveDrop.command.SpawnDrop")
                        .arguments(
                                GenericArguments.string(Text.of("ID Tag")))
                        .executor(new SpawnChest())
                    .build(), "SpawnDrop"
                )
                .build();
        Sponge.getCommandManager().register(this,FD,"FD");
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
