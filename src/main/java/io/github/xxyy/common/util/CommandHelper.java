/*
 * This file is part of XYC.
 * It may only be used in my projects
 * and only be distributed with my explicit permission.
 */
package io.github.xxyy.common.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang.StringUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

/**
 * Class that provides several utilities for commands and some unrelated methods.
 *
 * @author xxyy98<xxyy98@gmail.com>
 */
public class CommandHelper {

    /**
     * Alternative to {@link Bukkit#broadcast(String, String)} that works with PermissionsEx.
     *
     * @param msg        Message to send
     * @param permission Players with that permission will receive <code>msg</code>
     *
     * @see Bukkit#broadcast(String, String)
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static void broadcast(String msg, String permission) {
        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.hasPermission(permission)) {
                plr.sendMessage(msg);
            }
        }
        Bukkit.getConsoleSender().sendMessage(msg);
    }

    /**
     * public static void clearInv(Player plr) Clears the Inv of plr.
     *
     * @author xxyy98(xxyy98[at]gmail.com)
     * @param plr Player
     */
    public static void clearInv(Player plr) {
        plr.getInventory().clear();
        plr.getInventory().setArmorContents(new ItemStack[] {null, null, null, null});
    }

    /**
     * Clears a list of inventories.
     *
     * @see clearInv()
     * @param plrs This playsers whose inventorys shall be cleared
     */
    public static void clearInvList(List<Player> plrs) {
        for (int i = 0; i < plrs.size(); i++) {
            CommandHelper.clearInv(plrs.get(i));
        }
    }

    /**
     * CS == Comma Seperate
     *
     * @param col An Iterable to seperate
     *
     * @return Element1,Element2,Element3 OR {empty}
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String CSCollection(Iterable<? extends Object> col) {
        Iterator<? extends Object> i = col.iterator();
        if (!i.hasNext()) {
            return "{empty}";
        }
        String rtrn = i.next().toString() + "";
        while (i.hasNext()) {
            rtrn += ", " + i.next();
        }
        return rtrn;
    }

    /**
     * CS == Comma Seperate
     *
     * @param col        An Iterable to seperate
     * @param defaultVal value to be returned if <code>col</code> is empty.
     *
     * @return Element1,Element2,Element3 OR <code>defaultVal</code>
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String CSCollection(Iterable<? extends Object> col, String defaultVal) {
        Iterator<? extends Object> i = col.iterator();
        if (!i.hasNext()) {
            return defaultVal;
        }
        String rtrn = i.next().toString() + "";
        while (i.hasNext()) {
            rtrn += ", " + i.next();
        }
        return rtrn;
    }

    /**
     * Formats
     * <code>seconds</code> for a human-readable output in german. If
     * <code>seconds >= 60</code>, the output will be formatted like this:
     * <i>x Minuten und y Sekunden</i>
     * <b>Notice:</b> Currently, there is no support for hours.
     *
     * <b>Examples:</b>
     * 1 -> <i>1 Sekunde</i>
     * 46 -> <i>46 Sekunden</i>
     * 60 -> <i>1 Minute</i>
     * 61 -> <i>1 Minute und 1 Sekunde</i>
     * 90 -> <i>1 Minute und 30 Sekunden</i>
     * 120 -> <i>2 Minuten</i>
     * 125 -> <i>2 Minuten und 5 Sekunden</i>
     *
     * @param seconds Time to be formatted, in seconds.
     *
     * @return a human-readable time string in german.
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String formatSeconds(int seconds) {
        if (seconds < 60) {
            return seconds + " Sekunde" + ((seconds == 1) ? "" : "n");
        }
        short minutes = (short) (seconds / 60);
        seconds = seconds - (60 * minutes);
        return minutes + " Minute" + ((minutes == 1) ? "" : "n") + ((seconds == 0) ? "" : " und " + seconds + " Sekunde" + ((seconds == 1) ? "" : "n"));
    }

    /**
     * Returns a string representing the size of each element in
     * <code>values</code>.
     *
     * @param maxLength how long the string shall be.
     * @param values    values.
     * @param max       The highest value in <code>values</code>.
     *
     * @return 1111112222233333
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String getBarOfValues(int maxLength, List<Integer> values, int max) {
        maxLength -= 2;// []
        int i = 0;
        String rtrn = "";
        for (int val : values) {
            float factor = (((float) val) / ((float) max));
            short linesToDraw = (short) (maxLength * factor);
            rtrn += "§" + Integer.toHexString(i) + StringUtils.rightPad("", linesToDraw, (char) ('0' + i));// there should never be more than 16 full items.
            // System.out.println(i+"->"+rtrn+": "+val);
            i++;
        }
        return rtrn;
    }

    /**
     * Returns the names of all online players.
     *
     * @see Bukkit#getOnlinePlayers()
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static List<String> getOnlinePlayerNames() {
        List<String> rtrn = new ArrayList<>();
        for (Player plr : Bukkit.getOnlinePlayers()) {
            rtrn.add(plr.getName());
        }
        return rtrn;
    }

    /**
     * Returns a bar marking progress.
     *
     * @param maxLength lenght in characters of the bar.
     * @param value     the current value.
     * @param max       the maximum value (100%)
     *
     * @return ████▒▒▒ for (6,1,2)
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String getProgressBar(int maxLength, int value, int max) {
        double factor = (((double) value) / ((double) max));
        maxLength -= 5;// "[]xx%".lenght
        short linesToDraw = (short) (maxLength * factor);// factor can not be > 1
        return StringUtils.rightPad(StringUtils.rightPad("[", linesToDraw, '█'), maxLength, '▒') + "]" + StringUtils.leftPad(
                ((short) (factor * 100)) + "%", 3, '0');
    }

    /**
     * public static boolean isInventoryEmpty(Player plr) Chekcs if the inventory of plr is currently empty.
     *
     * @author xxyy98(xxyy98[at]gmail.com)
     * @param plr Player
     *
     * @return boolean
     */
    public static boolean isInventoryEmpty(Player plr) {
        for (ItemStack item : plr.getInventory().getContents()) {
            if (item != null) {
                return false;
            }
        }
        for (ItemStack item : plr.getInventory().getArmorContents()) {
            if (item != null && item.getType() != Material.AIR) {
                return false;
            }
        }
        return true;
    }

    /**
     * Determines if a number
     * <code>toCheck</code> is between or equal to one the boundaries specified. There is no special order of the boundaries required, they can even
     * be equal.
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static boolean isNumberBetween(int toCheck, int boundary1, int boundary2) {
        if (boundary1 > boundary2) {
            return boundary2 <= toCheck && toCheck <= boundary1;
        } else if (boundary1 < boundary2) {
            return boundary1 <= toCheck && toCheck <= boundary2;
        } else {
            return toCheck == boundary1;
        }
    }

    /**
     * Returns a List with only
     * <code>t</code> in it.
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static <T> List<T> list(T t) {
        List<T> lst = new ArrayList<>();
        lst.add(t);
        return lst;
    }

    /**
     * Sends a message to sender without those ugly spaces on the beginning of each line.
     *
     * @param msg    Message to be sent, preferably multi-line (use /n)
     * @param sender Receiver of the message
     *
     * @author xxyy98<xxyy98@gmail.com @return t rue for use with commands.
     */
    public static boolean msg(String msg, CommandSender sender) {
        for (String str2 : msg.split("\n")) {
            sender.sendMessage(str2);
        }
        return true;
    }

    /**
     * Prints a message to {@link System#out} and the provided logger
     * <code>lgr</code> if it is not
     * <code>null</code>.
     *
     * @param message The message to print
     * @param lgr     The logger to print it to, can be <code>null</code>.
     * @param lvl     the log {@link Level} to use
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static void printAndOrLog(String message, Logger lgr, Level lvl) {
        System.out.println(message);
        if (lgr != null) {
            lgr.log(lvl, message);
        }
    }

    /**
     * public static void sendImportantActionMessage(CommandSender sender, String message) Sends notification of an important action to all Ops and
     * CONSOLE. (/reload-styled) example: §7§m[Notch: §aInvented Minecraft.§7]
     *
     * @param sender  The player who performed the action
     * @param message Short description of the action
     */
    public static void sendImportantActionMessage(CommandSender sender, String message) {
        CommandHelper.sendMessageToOpsAndConsole("§7§o[" + sender.getName() + ": §a§o" + message + "§7§o]");
    }

    /**
     * public static void sendMessageToOpsAndConsole(String message) Sends a message to all online Ops and CONSOLE.
     *
     * @param message The message to be sended
     */
    public static void sendMessageToOpsAndConsole(String message) {
        Bukkit.getConsoleSender().sendMessage(message);
        if (Bukkit.getOnlinePlayers().length == 0) {
            return;
        }
        for (Player plr : Bukkit.getOnlinePlayers()) {
            if (plr.hasPermission("xyc.adminmsg") || plr.isOp()) {
                plr.sendMessage(message);
            }
        }
    }

    /**
     * returns a set with just
     * <code>t</code> in it.
     *
     * @param t Element to put and type argument
     *
     * @return Set<T> with <code>t</code> in it.
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static <T> Set<T> set(T t) {
        Set<T> set = new HashSet<>();
        set.add(t);
        return set;
    }

    /**
     * Returns a String with maximal length of 16 characters. If
     * <code>colorString</code> does not fit into
     * <code>input</code> without exceeding the limit, it will be returned uncolorized.
     * <b>Notice:</b> colorString is put first; Example: ("xxyy98","§3§l") => "§3§lxxyy98"
     *
     * @return A String with a maximal length of 16 characters. Even if <code>input</code> is longer than that.
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String sixteenCharColorize(String input, String colorString) {
        if (input == null) {
            return "null";
        }
        if (colorString == null) {
            return CommandHelper.sixteenCharLimit(input);
        }
        if ((input.length() + colorString.length()) > 16) {
            return CommandHelper.sixteenCharLimit(input);
        }
        return colorString.concat(input);
    }

    /**
     * Returns
     * <code>input</code>. If it is longer than 16 characters, returns a shortened version (cuts the end off)
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static String sixteenCharLimit(String input) {
        if (input == null) {
            return null;
        }
        if (input.length() > 16) {
            return input.substring(0, 16);
        }
        return input;
    }

    /**
     * Parses a tabComplete return so that all elements not starting with the last element of
     * <code>args</code> are removed. if
     * <code>rtrn</code> is
     * <code>null</code> or empty,
     * <code>null</code> is returned. If
     * <code>args.length == 0</code>, rtrn is returned. if the last arg is empty,
     * <code>rtrn</code> is returned.
     *
     * @param args Arguments already entered by the user.
     * @param rtrn A list of Strings with tabComplete suggestions.
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static List<String> tabCompleteArgs(String[] args, List<String> rtrn) {
        if (rtrn == null || rtrn.isEmpty()) {
            return null;
        }
        if (args.length == 0) {
            return rtrn;
        }
        String lastArg = args[args.length - 1];
        if (lastArg.isEmpty()) {
            return rtrn;
        }
        Iterator<String> it = rtrn.iterator();
        while (it.hasNext()) {
            if (!it.next().startsWith(lastArg)) {
                it.remove();
            }
        }
        return rtrn;
    }

    /**
     * Returns a list of all objects in a Collection, {@link Object#toString()}'d.
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static List<String> toStringAll(Collection<?> coll) {
        List<String> lst = new ArrayList<>();
        for (Object obj : coll) {
            lst.add(obj.toString());
        }
        return lst;
    }

    /**
     * An Array-based version of {@link CommandHelper#toStringAll(Collection)}
     *
     * @author xxyy98<xxyy98@gmail.com>
     */
    public static List<String> toStringAll(Object[] arr) {
        List<String> lst = new ArrayList<>();
        for (Object obj : arr) {
            lst.add(obj.toString());
        }
        return lst;
    }

    /**
     * public static <T extends Object> T writeAndPass(T t) Write t.toString() to Console and passes it as return.
     *
     * @author xxyy98(xxyy98[at]gmail.com)
     * @param t
     *
     * @return
     */
    public static <T extends Object> T writeAndPass(T t) {
        System.out.println("output: " + t.toString());
        return t;
    }
}
