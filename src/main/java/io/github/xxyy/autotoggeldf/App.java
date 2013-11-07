package io.github.xxyy.autotoggeldf;

import io.github.xxyy.common.util.CommandHelper;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Main class for AutoToggelDownfall. Does stuff.
 *
 */
public final class ATDFPlugin extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        for (World wrld : Bukkit.getWorlds()) { //It might be raining on enable
            wrld.setStorm(false); 
            wrld.setThundering(false);
        }

        Bukkit.getPluginManager().registerEvents(this, this); //Register afterwards - setStorm(boolean) triggers the event.
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onWeatherChange(final WeatherChangeEvent evt) {
        evt.setCancelled(true); //Let's do this instead of just setting the weather to sun. Why? I donÄt know.
        evt.getWorld().setWeatherDuration(Integer.MAX_VALUE); //Duration of the CURRENT weather
        CommandHelper.broadcast("\u00a77\u00a7o{\u00a7eATDf: \u00a77\u00a7oSilenced da weather (atdf.msg)}", "atdf.msg");
    }
}
