package net.luis.fxutils;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import org.controlsfx.control.decoration.Decorator;
import org.controlsfx.control.decoration.GraphicDecoration;

import java.util.Objects;

/**
 *
 * @author Luis-st
 *
 */

public class Decorators {
	
	public static final ImageView INFO_DECORATION = new ImageView(new Image(Objects.requireNonNull(Decorators.class.getResource("/impl/org/controlsfx/control/validation/decoration-info.png")).toExternalForm()));
	public static final ImageView WARNING_DECORATION = new ImageView(new Image(Objects.requireNonNull(Decorators.class.getResource("/impl/org/controlsfx/control/validation/decoration-warning.png")).toExternalForm()));
	public static final ImageView ERROR_DECORATION = new ImageView(new Image(Objects.requireNonNull(Decorators.class.getResource("/impl/org/controlsfx/control/validation/decoration-error.png")).toExternalForm()));
	public static final ImageView REQUIRED_DECORATION = new ImageView(new Image(Objects.requireNonNull(Decorators.class.getResource("/impl/org/controlsfx/control/validation/required-indicator.png")).toExternalForm()));
	
	public static void addInfoDecoration(Node node, Pos pos) {
		Decorator.addDecoration(node, new GraphicDecoration(INFO_DECORATION, pos));
	}
	
	public static void addInfoDecoration(Node node, Pos pos, double xOffset, double yOffset) {
		Decorator.addDecoration(node, new GraphicDecoration(INFO_DECORATION, pos, xOffset, yOffset));
	}
	
	public static void addWarningDecoration(Node node, Pos pos) {
		Decorator.addDecoration(node, new GraphicDecoration(WARNING_DECORATION, pos));
	}
	
	public static void addWarningDecoration(Node node, Pos pos, double xOffset, double yOffset) {
		Decorator.addDecoration(node, new GraphicDecoration(WARNING_DECORATION, pos, xOffset, yOffset));
	}
	
	public static void addErrorDecoration(Node node, Pos pos) {
		Decorator.addDecoration(node, new GraphicDecoration(ERROR_DECORATION, pos));
	}
	
	public static void addErrorDecoration(Node node, Pos pos, double xOffset, double yOffset) {
		Decorator.addDecoration(node, new GraphicDecoration(ERROR_DECORATION, pos, xOffset, yOffset));
	}
	
	public static void addRequiredDecoration(Node node, Pos pos) {
		Decorator.addDecoration(node, new GraphicDecoration(REQUIRED_DECORATION, pos));
	}
	
	public static void addRequiredDecoration(Node node, Pos pos, double xOffset, double yOffset) {
		Decorator.addDecoration(node, new GraphicDecoration(REQUIRED_DECORATION, pos, xOffset, yOffset));
	}
	
}
