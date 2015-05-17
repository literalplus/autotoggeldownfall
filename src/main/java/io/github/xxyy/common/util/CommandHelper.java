package io.github.xxyy.common.util;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

/**
 * Class that provides several utilities for commands and some unrelated methods, taken from the private XYC library
 * with permission from the author.
 *
 * @author xxyy
 */
public class CommandHelper {

    /**
     * Alternative to {@link Bukkit#broadcast(String, String)} that works with PermissionsEx.
     *
     * @param msg        the message to send
     * @param permission the permission players need to receive the message
     */
    public static void broadcast(String msg, String permission) {
        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.hasPermission(permission)) {
                plr.sendMessage(msg);
            }
        }
        Bukkit.getConsoleSender().sendMessage(msg);
    }
}
