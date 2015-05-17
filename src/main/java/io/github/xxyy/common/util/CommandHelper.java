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
