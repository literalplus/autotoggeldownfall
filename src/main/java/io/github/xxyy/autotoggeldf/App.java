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
 * Hai wurld!!1
 *
 */
public final class App extends JavaPlugin implements Listener {
    @Override
    public void onEnable() {
        for (World wrld : Bukkit.getWorlds()) {
            wrld.setStorm(false);
            wrld.setThundering(false);
        }

        Bukkit.getPluginManager().registerEvents(this, this);
    }

    @EventHandler(priority = EventPriority.LOW)
    public void onWeatherChange(final WeatherChangeEvent evt) {
        evt.setCancelled(true);
        evt.getWorld().setWeatherDuration(Integer.MAX_VALUE); //Duration of the CURRENT weather
        CommandHelper.broadcast("\u00a77\u00a7o{\u00a7eATDf: \u00a77\u00a7oSilenced da weather (atdf.msg)}", "atdf.msg");
    }
}
