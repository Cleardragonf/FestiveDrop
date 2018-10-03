package me.cleardragonf.festivedrop;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.world.Location;
import org.spongepowered.api.world.World;

import java.util.Optional;

public class ChestInteract {

    public void setTarget(Optional<Location<World>> location) {
        Sponge.getGame().getServer().getBroadcastChannel().send(Text.of(location));
    }
}
