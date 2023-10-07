package net.luis.fxutils.util;

import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-st
 *
 */

public class ColorUtils {
	
	public static @NotNull Color createColor(String hex) {
		hex = hex.trim();
		if (!hex.isEmpty() && hex.charAt(0) == '#') {
			hex = hex.replace("#", "");
		}
		hex = hex.trim();
		if (hex.length() > 6) {
			throw new RuntimeException("Can not parse color from hex string: " + hex);
		}
		int r = Integer.valueOf(hex.substring(0, 2), 16);
		int g = Integer.valueOf(hex.substring(2, 4), 16);
		int b = Integer.valueOf(hex.substring(4, 6), 16);
		return createColor(r, g, b);
	}
	
	public static @NotNull Color createColor(int r, int g, int b) {
		return createColor(r, g, b, 255);
	}
	
	public static @NotNull Color createColor(int r, int g, int b, int a) {
		return new Color(r / 255.0, g / 255.0, b / 255.0, a / 255.0);
	}
}
