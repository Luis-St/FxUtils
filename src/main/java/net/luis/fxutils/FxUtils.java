package net.luis.fxutils;

import static net.luis.fxutils.internal.SystemPropertyHelper.getDoubleProperty;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

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

/**
 *
 * @author Luis-st
 *
 */

public class FxUtils {
	
	/**
	 * TODO:
	 *  - Add property key for Pos
	 *  - Add property key for padding array
	 *  - Add property key for hGap and vGap
	 */
	
	private static final String GRID_PADDING_KEY = "fxutils.grid.default.padding";
	private static final String GRID_GAP_KEY = "fxutils.grid.default.gap";
	private static final String VBOX_PADDING_KEY = "fxutils.vbox.default.padding";
	private static final String HBOX_PADDING_KEY = "fxutils.hbox.default.padding";
	
	@NotNull
	public static GridPane makeDefaultGrid() {
		return makeGrid(getDoubleProperty(GRID_PADDING_KEY, 20.0), getDoubleProperty(GRID_GAP_KEY, 10.0));
	}
	
	@NotNull
	public static GridPane makeGrid(double padding, double gap) {
		return makeGrid(Pos.CENTER, padding, gap);
	}
	
	@NotNull
	public static GridPane makeGrid(Pos pos, double padding, double gap) {
		return makeGrid(pos, padding, gap, gap);
	}
	
	@NotNull
	public static GridPane makeGrid(Pos pos, double[] padding, double gap) {
		return makeGrid(Pos.CENTER, padding, gap, gap);
	}
	
	@NotNull
	public static GridPane makeGrid(Pos pos, double padding, double hGap, double vGap) {
		return makeGrid(pos, new Insets(padding), hGap, vGap);
	}
	
	@NotNull
	public static GridPane makeGrid(Pos pos, double[] padding, double hGap, double vGap) {
		if (padding.length == 1) {
			return makeGrid(pos, new Insets(padding[0]), hGap, vGap);
		} else if (padding.length == 4) {
			return makeGrid(pos, new Insets(padding[0], padding[1], padding[2], padding[3]), hGap, vGap);
		}
		throw new IllegalArgumentException("Can not create the padding of a grid pane for a double array with size " + padding.length);
	}
	
	@NotNull
	public static GridPane makeGrid(Pos pos, Insets padding, double hGap, double vGap) {
		GridPane pane = new GridPane();
		pane.setAlignment(pos);
		pane.setHgap(hGap);
		pane.setVgap(vGap);
		pane.setPadding(padding);
		return pane;
	}
	
	@NotNull
	public static VBox makeDefaultVBox(Node... children) {
		return makeVBox(getDoubleProperty(VBOX_PADDING_KEY, 0.0), children);
	}
	
	@NotNull
	public static VBox makeVBox(double padding, Node... children) {
		return makeVBox(Pos.CENTER, padding, children);
	}
	
	@NotNull
	public static VBox makeVBox(double[] padding, Node... children) {
		return makeVBox(Pos.CENTER, padding, children);
	}
	
	@NotNull
	public static VBox makeVBox(Pos pos, double padding, Node... children) {
		return makeVBox(pos, new Insets(padding), children);
	}
	
	@NotNull
	public static VBox makeVBox(Pos pos, double[] padding, Node... children) {
		if (padding.length == 1) {
			return makeVBox(pos, new Insets(padding[0]), children);
		} else if (padding.length == 4) {
			return makeVBox(pos, new Insets(padding[0], padding[1], padding[2], padding[3]), children);
		}
		throw new IllegalArgumentException("Can not create the padding of a vbox for a double array with size " + padding.length);
	}
	
	@NotNull
	public static VBox makeVBox(Pos pos, Insets padding, Node... children) {
		VBox box = new VBox();
		box.setAlignment(pos);
		box.setPadding(padding);
		box.getChildren().addAll(children);
		return box;
	}
	
	@NotNull
	public static HBox makeDefaultHBox(Node... children) {
		return makeHBox(getDoubleProperty(HBOX_PADDING_KEY, 0.0), children);
	}
	
	@NotNull
	public static HBox makeHBox(double padding, Node... children) {
		return makeHBox(Pos.CENTER, padding, children);
	}
	
	@NotNull
	public static HBox makeHBox(double[] padding, Node... children) {
		return makeHBox(Pos.CENTER, padding, children);
	}
	
	@NotNull
	public static HBox makeHBox(Pos pos, double padding, Node... children) {
		return makeHBox(pos, new Insets(padding), children);
	}
	
	@NotNull
	public static HBox makeHBox(Pos pos, double[] padding, Node... children) {
		if (padding.length == 1) {
			return makeHBox(pos, new Insets(padding[0]), children);
		} else if (padding.length == 4) {
			return makeHBox(pos, new Insets(padding[0], padding[1], padding[2], padding[3]), children);
		}
		throw new IllegalArgumentException("Can not create the padding of a hbox for a double array with size " + padding.length);
	}
	
	@NotNull
	public static HBox makeHBox(Pos pos, Insets padding, Node... children) {
		HBox box = new HBox();
		box.setAlignment(pos);
		box.setPadding(padding);
		box.getChildren().addAll(children);
		return box;
	}
	
	@NotNull
	public static Button makeButton(Runnable action) {
		Button button = new Button();
		button.setOnAction((event) -> {
			action.run();
		});
		return button;
	}
	
	@NotNull
	public static Button makeButton(String name, Runnable action) {
		Button button = new Button(name);
		button.setOnAction((event) -> {
			action.run();
		});
		return button;
	}
	
	@NotNull
	public static ImageView makeImageView(String path, double width, double heigh) {
		return makeImageView(path, width, heigh, true);
	}
	
	@NotNull
	public static ImageView makeImageView(String path, double width, double heigh, boolean smooth) {
		ImageView imageView = new ImageView(new Image(path));
		imageView.setFitWidth(width);
		imageView.setFitHeight(heigh);
		imageView.setSmooth(smooth);
		return imageView;
	}
	
	@NotNull
	public static Popup makePopup(boolean hideOnEscape, double width, double height, String style, Node... nodes) {
		Popup popup = new Popup();
		popup.setHideOnEscape(hideOnEscape);
		Label backgroundLabel = new Label();
		backgroundLabel.setStyle("-fx-background-color: #F4F4F4; -fx-padding: 0; -fx-border-color: black; -fx-border-width: 1;");
		backgroundLabel.setPrefSize(width, height);
		List<Node> children = new ArrayList<>();
		children.add(backgroundLabel);
		children.addAll(Arrays.asList(nodes));
		popup.getContent().add(new StackPane(children.toArray(Node[]::new)));
		return popup;
		
	}
	
}
