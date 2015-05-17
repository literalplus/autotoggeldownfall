/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2013-2015 Philipp Nowak<devnull@nowak-at.net>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

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
