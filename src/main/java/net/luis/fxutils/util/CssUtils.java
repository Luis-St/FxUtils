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
