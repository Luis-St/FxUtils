package net.luis.fxutils;

import javafx.scene.Node;
import javafx.scene.Parent;

/**
 *
 * @author Luis-st
 *
 */

public class CssUtils {
	
	public static void addStyle(Node node, String style) {
		node.setStyle(style);
	}
	
	// id use '#' as indicator
	public static void setId(Node node, String id) {
		node.setId(id);
	}
	
	// class use '.' as indicator
	public static void addStyleClass(Node node, String styleClass) {
		node.getStyleClass().add(styleClass);
	}
	
	public static void addStylesheet(Parent parent, String stylesheet) {
		parent.getStylesheets().add(stylesheet);
	}
	
}
