package me.cleardragonf.festivedrop;

import com.flowpowered.math.vector.Vector3d;
import com.google.inject.Inject;
import me.cleardragonf.festivedrop.Commands.SetChest;
import me.cleardragonf.festivedrop.Commands.SpawnChest;
import me.cleardragonf.festivedrop.Commands.SpawnItem;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.args.GenericArguments;
import org.spongepowered.api.command.spec.CommandSpec;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.event.game.state.GameInitializationEvent;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.item.ItemType;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import javax.annotation.Nonnull;
import java.io.File;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

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
                                GenericArguments.integer(Text.of("# of Items")),
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
        List<ItemType> types = Sponge.getRegistry().getAllOf(ItemType.class).stream()

                .collect(Collectors.toList());

        for (int i = 0; i < 5; i++) {
            Collections.shuffle(types);
            ItemType a = types.get(0);
            Sponge.getServer().getBroadcastChannel().send(Text.of(a));
        }
    }

    @Listener
    public void onGameInit(GameInitializationEvent event) {
        ConfigurationManager.getInstance().load1();
        Task task = Task.builder().execute(new Runnable() {
            @Override
            public void run() {
                Sponge.getServer().getBroadcastChannel().send(Text.of("Drop Party Now Occuring"));
                ConfigurationManager.getInstance().load1();
                Random random = new Random();
                int n = random.nextInt(100) + 1;
                for (int id2 = 0; id2 < 100; id2++) {
                    if(!ConfigurationManager.getInstance().getConfig1().getNode("Chests", "Chest Location " + id2).isVirtual()){
                        if (ConfigurationManager.getInstance().getConfig1().getNode("Chests", "Chest Location " + id2, "Run on Start?").getString().equals("true")) {
                            Double x = ConfigurationManager.getInstance().getConfig1().getNode("Chests", "Chest Location " + id2, "X: ").getDouble();
                            Double y = ConfigurationManager.getInstance().getConfig1().getNode("Chests", "Chest Location " + id2, "Y: ").getDouble();
                            Double z = ConfigurationManager.getInstance().getConfig1().getNode("Chests", "Chest Location " + id2, "Z: ").getDouble();
                            Vector3d location = new Vector3d(x, y, z);
                            World world = Sponge.getServer().getWorld("world").get();
                            Location<World> worldLocation = new Location(world, 0, 0, 0);
                            SpawnItem testing = new SpawnItem();
                            SpawnChest item = new SpawnChest();
                            ItemStack itemStack = null;
                            ItemStack itemStack1 = null;
                            try {

                                    Random random1 = new Random();
                                    int map = random1.nextInt(10) + 1;
                                    itemStack1 = item.randomizer2(map);
                                    itemStack = item.randomizer3();

                            } catch (ObjectMappingException e) {
                                e.printStackTrace();
                            }
                            String id = String.valueOf(id2);
                            if (n <= ConfigurationManager.getInstance().getConfig1().getNode("Server Drop Event", "Special Drops", "Chance: ").getInt()) {
                                String config = ConfigurationManager.getInstance().getConfig1().getNode("Chests").getChildrenMap().keySet().toString();
                                String config1 = config.replace("[", "");
                                String config2 = config1.replace("]", "");
                                String[] test = config2.split(",");
                                List<String> pages = new LinkedList<>();
                                for (String a : test){
                                    pages.add(a);
                                }
                                Collections.shuffle(pages);
                                String finalChest = pages.get(0);
                                String finalChest2 = finalChest.replace("Chest Location ", "");
                                id = finalChest2.replace(" ", "");
                                testing.serverDrop(itemStack1, worldLocation, id);
                                id2 = 500;
                            }else{
                                testing.serverDrop(itemStack, worldLocation, id);
                            }


                        }
                    }
                }
            }
        })
            .interval(ConfigurationManager.getInstance().getConfig1().getNode("Server Drop Event", "Time Between: ").getLong(), TimeUnit.SECONDS)
            .name("Self-Cancelling Timer Task").submit(this);
    }
}
