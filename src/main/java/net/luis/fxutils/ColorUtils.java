package net.luis.fxutils;

import javafx.scene.paint.Color;

/**
 *
 * @author Luis-st
 *
 */

public class ColorUtils {
	
	public static Color createColor(String hex) {
		if (hex.startsWith("#")) {
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
	
	public static Color createColor(int r, int g, int b) {
		return createColor(r, g, b, 255);
	}
	
	public static Color createColor(int r, int g, int b, int a) {
		return new Color(r / 255.0, g / 255.0, b / 255.0, a / 255.0);
	}
	
}
