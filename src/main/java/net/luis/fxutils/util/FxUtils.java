package net.luis.fxutils.util;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.stage.Popup;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.nio.file.Path;
import java.util.*;

/**
 *
 * @author Luis-st
 *
 */

public class FxUtils {
	
	public static @NotNull GridPane makeDefaultGrid() {
		return makeGrid(Pos.CENTER, 20.0, 10.0);
	}
	
	public static @NotNull GridPane makeGrid(double padding, double gap) {
		return makeGrid(Pos.CENTER, padding, gap);
	}
	
	public static @NotNull GridPane makeGrid(@NotNull Pos pos, double padding, double gap) {
		return makeGrid(pos, padding, gap, gap);
	}
	
	public static @NotNull GridPane makeGrid(@NotNull Pos pos, double padding, double hGap, double vGap) {
		return makeGrid(pos, new Insets(padding), hGap, vGap);
	}
	
	public static @NotNull GridPane makeGrid(@NotNull Pos pos, @NotNull Insets padding, double hGap, double vGap) {
		GridPane pane = new GridPane();
		pane.setAlignment(Objects.requireNonNull(pos, "Pos must not be null"));
		pane.setHgap(hGap);
		pane.setVgap(vGap);
		pane.setPadding(Objects.requireNonNull(padding, "Padding must not be null"));
		return pane;
	}
	
	public static @NotNull VBox makeDefaultVBox(Node @Nullable ... children) {
		return makeVBox(Pos.CENTER, 0.0, children);
	}
	
	public static @NotNull VBox makeVBox(double padding, Node @Nullable ... children) {
		return makeVBox(Pos.CENTER, padding, children);
	}
	
	public static @NotNull VBox makeVBox(@NotNull Pos pos, double padding, Node @Nullable ... children) {
		return makeVBox(pos, new Insets(padding), children);
	}
	
	public static @NotNull VBox makeVBox(@NotNull Pos pos, @NotNull Insets padding, Node @Nullable ... children) {
		VBox box = new VBox();
		box.setAlignment(Objects.requireNonNull(pos, "Pos must not be null"));
		box.setPadding(Objects.requireNonNull(padding, "Padding must not be null"));
		if (children != null) {
			box.getChildren().addAll(children);
		}
		return box;
	}
	
	public static @NotNull HBox makeDefaultHBox(Node @Nullable ... children) {
		return makeHBox(Pos.CENTER, 0.0, children);
	}
	
	public static @NotNull HBox makeHBox(double padding, Node @Nullable ... children) {
		return makeHBox(Pos.CENTER, padding, children);
	}
	
	public static @NotNull HBox makeHBox(@NotNull Pos pos, double padding, Node @Nullable ... children) {
		return makeHBox(pos, new Insets(padding), children);
	}
	
	public static @NotNull HBox makeHBox(@NotNull Pos pos, @NotNull Insets padding, Node @Nullable ... children) {
		HBox box = new HBox();
		box.setAlignment(Objects.requireNonNull(pos, "Pos must not be null"));
		box.setPadding(Objects.requireNonNull(padding, "Padding must not be null"));
		if (children != null) {
			box.getChildren().addAll(children);
		}
		return box;
	}
	
	public static @NotNull Button makeButton(@NotNull Runnable action) {
		Button button = new Button();
		Objects.requireNonNull(action, "Action must not be null");
		button.setOnAction((event) -> action.run());
		return button;
	}
	
	public static @NotNull Button makeButton(@Nullable String name, @NotNull Runnable action) {
		Button button = new Button(name);
		Objects.requireNonNull(action, "Action must not be null");
		button.setOnAction((event) -> action.run());
		return button;
	}
	
	public static @NotNull ImageView makeImageView(@NotNull Path imagePath, double width, double height) {
		Objects.requireNonNull(imagePath, "Image path must not be null");
		return makeImageView(imagePath.toString(), width, height);
	}
	
	public static @NotNull ImageView makeImageView(@NotNull String imagePath, double width, double height) {
		return makeImageView(imagePath, width, height, true);
	}
	
	public static @NotNull ImageView makeImageView(@NotNull Path imagePath, double width, double height, boolean smooth) {
		Objects.requireNonNull(imagePath, "Image path must not be null");
		return makeImageView(imagePath.toString(), width, height, smooth);
	}
	
	public static @NotNull ImageView makeImageView(@NotNull String imagePath, double width, double height, boolean smooth) {
		Objects.requireNonNull(imagePath, "Image path must not be null");
		if (imagePath.contains(".") && !imagePath.startsWith(".")) {
			return makeImageView(new Image(imagePath), width, height, smooth);
		}
		return makeImageView(new Image(imagePath + ".png"), width, height, smooth);
	}
	
	public static @NotNull ImageView makeImageView(@NotNull Image image, double width, double height, boolean smooth) {
		Objects.requireNonNull(image, "Image must not be null");
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setSmooth(smooth);
		return imageView;
	}
	
	public static @NotNull Popup makePopup(double width, double height, Node @Nullable ... nodes) {
		return makePopup(true, width, height, "-fx-background-color: #F4F4F4; -fx-padding: 0; -fx-border-color: black; -fx-border-width: 1;", nodes);
	}
	
	public static @NotNull Popup makePopup(boolean hideOnEscape, double width, double height, @NotNull String style, Node @Nullable ... nodes) {
		Popup popup = new Popup();
		popup.setHideOnEscape(hideOnEscape);
		Label backgroundLabel = new Label();
		backgroundLabel.setStyle(Objects.requireNonNull(style, "Style must not be null"));
		backgroundLabel.setPrefSize(width, height);
		List<Node> children = new ArrayList<>();
		children.add(backgroundLabel);
		if (nodes != null) {
			children.addAll(Arrays.asList(nodes));
		}
		popup.getContent().add(new StackPane(children.toArray(Node[]::new)));
		return popup;
	}
}
