package net.luis.fxutils;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Popup;
import org.jetbrains.annotations.NotNull;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
	
	public static @NotNull GridPane makeGrid(Pos pos, double padding, double gap) {
		return makeGrid(pos, padding, gap, gap);
	}
	
	public static @NotNull GridPane makeGrid(Pos pos, double padding, double hGap, double vGap) {
		return makeGrid(pos, new Insets(padding), hGap, vGap);
	}
	
	public static @NotNull GridPane makeGrid(Pos pos, Insets padding, double hGap, double vGap) {
		GridPane pane = new GridPane();
		pane.setAlignment(pos);
		pane.setHgap(hGap);
		pane.setVgap(vGap);
		pane.setPadding(padding);
		return pane;
	}
	
	public static @NotNull VBox makeDefaultVBox(Node... children) {
		return makeVBox(Pos.CENTER, 0.0, children);
	}
	
	public static @NotNull VBox makeVBox(double padding, Node... children) {
		return makeVBox(Pos.CENTER, padding, children);
	}
	
	public static @NotNull VBox makeVBox(Pos pos, double padding, Node... children) {
		return makeVBox(pos, new Insets(padding), children);
	}
	
	public static @NotNull VBox makeVBox(Pos pos, Insets padding, Node... children) {
		VBox box = new VBox();
		box.setAlignment(pos);
		box.setPadding(padding);
		box.getChildren().addAll(children);
		return box;
	}
	
	public static @NotNull HBox makeDefaultHBox(Node... children) {
		return makeHBox(Pos.CENTER, 0.0, children);
	}
	
	public static @NotNull HBox makeHBox(double padding, Node... children) {
		return makeHBox(Pos.CENTER, padding, children);
	}
	
	public static @NotNull HBox makeHBox(Pos pos, double padding, Node... children) {
		return makeHBox(pos, new Insets(padding), children);
	}
	
	public static @NotNull HBox makeHBox(Pos pos, Insets padding, Node... children) {
		HBox box = new HBox();
		box.setAlignment(pos);
		box.setPadding(padding);
		box.getChildren().addAll(children);
		return box;
	}
	
	public static @NotNull Button makeButton(Runnable action) {
		Button button = new Button();
		button.setOnAction((event) -> action.run());
		return button;
	}
	
	public static @NotNull Button makeButton(String name, Runnable action) {
		Button button = new Button(name);
		button.setOnAction((event) -> action.run());
		return button;
	}
	
	public static @NotNull ImageView makeImageView(Path imagePath, double width, double height) {
		return makeImageView(imagePath.toString(), width, height);
	}
	
	public static @NotNull ImageView makeImageView(String imagePath, double width, double height) {
		return makeImageView(imagePath, width, height, true);
	}
	
	public static @NotNull ImageView makeImageView(Path imagePath, double width, double height, boolean smooth) {
		return makeImageView(imagePath.toString(), width, height, smooth);
	}
	
	public static @NotNull ImageView makeImageView(String imagePath, double width, double height, boolean smooth) {
		if (imagePath.contains(".")) {
			return makeImageView(new Image(imagePath), width, height, smooth);
		}
		return makeImageView(new Image(imagePath + ".png"), width, height, smooth);
	}
	
	public static @NotNull ImageView makeImageView(Image image, double width, double height, boolean smooth) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(height);
		imageView.setSmooth(smooth);
		return imageView;
	}
	
	public static @NotNull Popup makePopup(double width, double height, Node... nodes) {
		return makePopup(true, width, height, "-fx-background-color: #F4F4F4; -fx-padding: 0; -fx-border-color: black; -fx-border-width: 1;", nodes);
	}
	
	public static @NotNull Popup makePopup(boolean hideOnEscape, double width, double height, String style, Node... nodes) {
		Popup popup = new Popup();
		popup.setHideOnEscape(hideOnEscape);
		Label backgroundLabel = new Label();
		backgroundLabel.setStyle(style);
		backgroundLabel.setPrefSize(width, height);
		List<Node> children = new ArrayList<>();
		children.add(backgroundLabel);
		children.addAll(Arrays.asList(nodes));
		popup.getContent().add(new StackPane(children.toArray(Node[]::new)));
		return popup;
	}
}
