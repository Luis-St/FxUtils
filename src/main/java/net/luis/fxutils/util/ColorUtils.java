/*
 * FxUtils
 * Copyright (C) 2024 Luis Staudt
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program. If not, see <https://www.gnu.org/licenses/>.
 */

package net.luis.fxutils.util;

import javafx.scene.paint.Color;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 *
 * @author Luis-st
 *
 */

public class ColorUtils {
	
	public static @NotNull Color createColor(@NotNull String hex) {
		Objects.requireNonNull(hex, "Hex string must not be null");
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
