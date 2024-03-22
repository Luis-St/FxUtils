package net.luis.fxutils.util;

import javafx.css.PseudoClass;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

/**
 *
 * @author Luis-st
 *
 */

public class CssUtils {
	
	public static <T extends Node> @NotNull T addStyle(@NotNull T node, @Nullable String style) {
		Objects.requireNonNull(node, "Node must not be null");
		if (style != null) {
			node.setStyle(style);
		}
		return node;
	}
	
	public static <T extends Node> @NotNull T setId(@NotNull T node, @Nullable String id) {
		Objects.requireNonNull(node, "Node must not be null");
		if (id != null) {
			node.setId(id);
		}
		return node;
	}
	
	public static <T extends Node> @NotNull T addStyleClass(@NotNull T node, @Nullable String styleClass) {
		Objects.requireNonNull(node, "Node must not be null");
		if (styleClass != null) {
			node.getStyleClass().add(styleClass);
		}
		return node;
	}
	
	public static <T extends Parent> @NotNull T addStylesheet(@NotNull T parent, @Nullable String stylesheet) {
		Objects.requireNonNull(parent, "Parent must not be null");
		if (stylesheet != null) {
			parent.getStylesheets().add(stylesheet);
		}
		return parent;
	}
	
	public static <T extends Stage> @NotNull T addStylesheet(@NotNull T stage, @Nullable String stylesheet) {
		Objects.requireNonNull(stage, "Stage must not be null");
		if (stylesheet != null) {
			stage.getScene().getStylesheets().add(stylesheet);
		}
		return stage;
	}
	
	public static <T extends Node> @NotNull T setPseudoClassValue(@NotNull T node, @Nullable String clazz, boolean value) {
		Objects.requireNonNull(node, "Node must not be null");
		if (clazz != null) {
			node.pseudoClassStateChanged(PseudoClass.getPseudoClass(clazz), value);
		}
		return node;
	}
	
	public static void setPseudoClassValues(@Nullable String clazz, boolean value, Node @NotNull ... nodes) {
		Objects.requireNonNull(nodes, "Nodes must not be null");
		for (Node node : nodes) {
			setPseudoClassValue(node, clazz, value);
		}
	}
}
