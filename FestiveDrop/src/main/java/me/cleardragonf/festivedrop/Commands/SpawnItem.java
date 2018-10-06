package me.cleardragonf.festivedrop.Commands;

import com.flowpowered.math.vector.Vector3d;
import me.cleardragonf.festivedrop.ConfigurationManager;
import me.cleardragonf.festivedrop.FestiveDrop;
import org.spongepowered.api.Game;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.CauseStackManager;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.scheduler.Task;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;

import java.util.concurrent.TimeUnit;
import java.util.function.Consumer;

public class SpawnItem{

    Game game;


    public void spawnItem(ItemStack superMegaAwesomeSword, Location<World> spawnLocation, String id) {
        Task task = Task.builder().execute(new Consumer<Task>() {
            private int trial = 0;
            @Override
            public void accept(Task task) {
                    ConfigurationManager.getInstance().load1();
                    Double x = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "X: ").getDouble();
                    Double y = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Y: ").getDouble();
                    Double z = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Z: ").getDouble();

                    Vector3d location = new Vector3d(x,y,z);
                    Location<World> location2 = spawnLocation.setPosition(location);




                    Extent extent = spawnLocation.getExtent();
                    Entity item = spawnLocation.getExtent().createEntity(EntityTypes.ITEM, location2.getPosition());
                    item.offer(Keys.REPRESENTED_ITEM, superMegaAwesomeSword.createSnapshot());

                    try (CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame()) {
                        frame.addContext(EventContextKeys.SPAWN_TYPE, SpawnTypes.PLACEMENT);
                        extent.spawnEntity(item);
                        trial++;
                    }
                    if(trial==ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Number of Items: ").getInt()){
                        task.cancel();
                    }
            }

        })
                .interval(5, TimeUnit.SECONDS)
                .name("ExamplePlugin - Fetch Stats from Database").submit(FestiveDrop.getInstance());
    }

    public void serverDrop(ItemStack superMegaAwesomeSword, Location<World> spawnLocation, String id) {
        ConfigurationManager.getInstance().load1();
        Double x = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "X: ").getDouble();
        Double y = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Y: ").getDouble();
        Double z = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Z: ").getDouble();
        int number = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location " + id, "Number of Items: ").getInt();

        for (int run = 0; run < number; run++) {
            Vector3d location = new Vector3d(x, y, z);
            Location<World> location2 = spawnLocation.setPosition(location);


            Extent extent = spawnLocation.getExtent();
            Entity item = spawnLocation.getExtent().createEntity(EntityTypes.ITEM, location2.getPosition());
            item.offer(Keys.REPRESENTED_ITEM, superMegaAwesomeSword.createSnapshot());

            try (CauseStackManager.StackFrame frame = Sponge.getCauseStackManager().pushCauseFrame()) {
                frame.addContext(EventContextKeys.SPAWN_TYPE, SpawnTypes.PLACEMENT);
                extent.spawnEntity(item);
            }
        }

    }


}
