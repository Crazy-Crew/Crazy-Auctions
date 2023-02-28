package us.crazycrew.crazyauctions.utils.utilities.misc;

import us.crazycrew.crazyauctions.api.enums.ServerVersion;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ColorUtils {
    private static final Pattern HEX_PATTERN = Pattern.compile("#[a-fA-F\\d]{6}");

    public static String color(String message) {
        if (ServerVersion.isAtLeast(ServerVersion.v1_15)) {
            Matcher matcher = HEX_PATTERN.matcher(message);
            StringBuilder buffer = new StringBuilder();

            while (matcher.find()) {
                matcher.appendReplacement(buffer, ChatColor.valueOf(matcher.group()).toString());
            }

            return ChatColor.translateAlternateColorCodes('&', matcher.appendTail(buffer).toString());
        }

        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static void color(List<Color> colors, String colorString) {
        if (colorString.contains(", ")) {
            for (String color : colorString.split(", ")) {
                Color c = getColor(color);

                if (c != null) colors.add(c);
            }
        } else {
            Color c = getColor(colorString);

            if (c != null) colors.add(c);
        }
    }

    public static Color getColor(String color) {
        return switch (color.toUpperCase()) {
            case "AQUA" -> Color.AQUA;
            case "BLACK" -> Color.BLACK;
            case "BLUE" -> Color.BLUE;
            case "FUCHSIA" -> Color.FUCHSIA;
            case "GRAY" -> Color.GRAY;
            case "GREEN" -> Color.GREEN;
            case "LIME" -> Color.LIME;
            case "MAROON" -> Color.MAROON;
            case "NAVY" -> Color.NAVY;
            case "OLIVE" -> Color.OLIVE;
            case "ORANGE" -> Color.ORANGE;
            case "PURPLE" -> Color.PURPLE;
            case "RED" -> Color.RED;
            case "SILVER" -> Color.SILVER;
            case "TEAL" -> Color.TEAL;
            case "YELLOW" -> Color.YELLOW;
            default -> Color.WHITE;
        };
    }

    public static String removeColor(String msg) {
        return ChatColor.stripColor(msg);
    }

    public static String getPrefix(String string) {
        return "";
    }

    public static String getPrefix() {
        return getPrefix("");
    }
}