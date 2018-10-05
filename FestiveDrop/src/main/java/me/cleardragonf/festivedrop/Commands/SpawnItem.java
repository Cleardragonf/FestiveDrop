package me.cleardragonf.festivedrop.Commands;

import com.flowpowered.math.vector.Vector3d;
import me.cleardragonf.festivedrop.ConfigurationManager;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityTypes;
import org.spongepowered.api.event.CauseStackManager;
import org.spongepowered.api.event.cause.EventContextKeys;
import org.spongepowered.api.event.cause.entity.spawn.SpawnTypes;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;
import org.spongepowered.api.world.extent.Extent;

public class SpawnItem {


    public void spawnItem(ItemStack superMegaAwesomeSword, Location<World> spawnLocation) {

        for (int i = 0; i < 10; i++) {
            ConfigurationManager.getInstance().load1();
            Double x = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location", "X: ").getDouble();
            Double y = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location", "Y: ").getDouble();
            Double z = ConfigurationManager.getInstance().getConfig1().getNode("Chest Location", "Z: ").getDouble();
            Vector3d location = new Vector3d(x,y,z);

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
