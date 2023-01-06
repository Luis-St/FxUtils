package net.luis.fxutils;

import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Parent;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-st
 *
 */

public class CssUtils {
	
	public static <T extends Node> @NotNull T addStyle(T node, String style) {
		node.setStyle(style);
		return node;
	}
	
	// id use '#' as indicator
	public static <T extends Node> @NotNull T setId(T node, String id) {
		node.setId(id);
		return node;
	}
	
	// class use '.' as indicator
	public static <T extends Node> @NotNull T addStyleClass(T node, String styleClass) {
		node.getStyleClass().add(styleClass);
		return node;
	}
	
	public static <T extends Parent> @NotNull T addStylesheet(T parent, String stylesheet) {
		parent.getStylesheets().add(stylesheet);
		return parent;
	}
	
	public static <T extends Node> @NotNull T setPseudoClassValue(T node, String clazz, boolean value) {
		node.pseudoClassStateChanged(PseudoClass.getPseudoClass(clazz), value);
		return node;
	}
	
	public static void setPseudoClassValues(String clazz, boolean value, Node... nodes) {
		for (Node node : nodes) {
			setPseudoClassValue(node, clazz, value);
		}
	}
	
}
