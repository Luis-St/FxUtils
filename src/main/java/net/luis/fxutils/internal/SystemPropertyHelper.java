package net.luis.fxutils.internal;

import java.util.Objects;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 *
 * @author Luis-st
 *
 */

public class SystemPropertyHelper {
	
	@NotNull
	public static int getIntProperty(String propertyKey, int defaultValue) {
		String keyValue = System.getProperty(propertyKey);
		try {
			return Integer.valueOf(keyValue.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	@NotNull
	public static double getDoubleProperty(String propertyKey, double defaultValue) {
		String keyValue = System.getProperty(propertyKey);
		try {
			return Double.valueOf(keyValue.trim());
		} catch (Exception e) {
			return defaultValue;
		}
	}
	
	@NotNull
	public static String getStringProperty(String propertyKey, String defaultValue) {
		return System.getProperty(propertyKey, Objects.requireNonNull(defaultValue, "The default value can not be null"));
	}
	
	@NotNull
	public static int[] getIntArrayProperty(String propertyKey) {
		return getIntArrayProperty(propertyKey, null);
	}
	
	@NotNull
	public static int[] getIntArrayProperty(String propertyKey, @Nullable int[] defaultValue) {
		String[] stringValues = getStringArrayProperty(propertyKey, null);
		if (stringValues.length > 0) {
			int[] values = new int[stringValues.length];
			for (int i = 0; i < values.length; i++) {
				try {
					values[i] = Integer.valueOf(stringValues[i]);
				} catch (Exception e) {
					continue;
				}
			}
			return values;
		}
		return defaultValue != null ? defaultValue : new int[0];
	}
	
	@NotNull
	public static double[] getDoubleArrayProperty(String propertyKey) {
		return getDoubleArrayProperty(propertyKey, null);
	}
	
	@NotNull
	public static double[] getDoubleArrayProperty(String propertyKey, @Nullable double[] defaultValue) {
		String[] stringValues = getStringArrayProperty(propertyKey, null);
		if (stringValues.length > 0) {
			double[] values = new double[stringValues.length];
			for (int i = 0; i < values.length; i++) {
				try {
					values[i] = Double.valueOf(stringValues[i]);
				} catch (Exception e) {
					continue;
				}
			}
			return values;
		}
		return defaultValue != null ? defaultValue : new double[0];
	}
	
	@NotNull
	public static String[] getStringArrayProperty(String propertyKey) {
		return getStringArrayProperty(propertyKey, null);
	}
	
	@NotNull
	public static String[] getStringArrayProperty(String propertyKey, @Nullable String[] defaultValue) {
		String keyValue = System.getProperty(propertyKey);
		if (keyValue != null) {
			keyValue = keyValue.trim();
			if (keyValue.startsWith("[") && keyValue.endsWith("]")) {
				String[] values = keyValue.replace("[", "").replace("]", "").trim().split(",");
				for (int i = 0; i < values.length; i++) {
					values[i] = values[i].trim();
				}
				return values;
			}
		}
		return defaultValue != null ? defaultValue : new String[0];
	}
	
}
