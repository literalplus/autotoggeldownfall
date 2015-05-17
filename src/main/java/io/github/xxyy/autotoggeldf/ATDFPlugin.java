package io.github.xxyy.autotoggeldf;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

import io.github.xxyy.common.util.CommandHelper;

/**
 * Main class for AutoToggelDownfall, providing an interface from the plugin logic to the Bukkit API.
 *
 * @author xxyy
 */
public final class ATDFPlugin extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        for (World world : Bukkit.getWorlds()) { //It might be raining on enable
            world.setStorm(false);
            world.setThundering(false);
        }

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onWeatherChange(final WeatherChangeEvent evt) {
        evt.setCancelled(true); //imagine if the server admin had an event or something and wants to keep rain
        evt.getWorld().setWeatherDuration(Integer.MAX_VALUE);
        CommandHelper.broadcast("\u00a77\u00a7o{\u00a7eATDf: \u00a77\u00a7oSilenced da weather (atdf.msg)}", "atdf.msg");
    }
}
