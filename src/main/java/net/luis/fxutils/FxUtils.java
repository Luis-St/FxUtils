package net.luis.fxutils;

import static net.luis.fxutils.internal.SystemPropertyHelper.getBooleanProperty;
import static net.luis.fxutils.internal.SystemPropertyHelper.getDoubleProperty;
import static net.luis.fxutils.internal.SystemPropertyHelper.getEnumProperty;
import static net.luis.fxutils.internal.SystemPropertyHelper.hasProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jetbrains.annotations.NotNull;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Popup;

/**
 *
 * @author Luis-st
 *
 */

public class FxUtils {
	
	private static final String GRID_POS_KEY = "fxutils.grid.default.pos";
	
	private static final String GRID_PADDING_KEY = "fxutils.grid.default.padding";
	private static final String GRID_PADDING_TOP_KEY = "fxutils.grid.default.padding_top";
	private static final String GRID_PADDING_RIGHT_KEY = "fxutils.grid.default.padding_right";
	private static final String GRID_PADDING_BOTTOM_KEY = "fxutils.grid.default.padding_bottom";
	private static final String GRID_PADDING_LEFT_KEY = "fxutils.grid.default.padding_left";
	private static final String GRID_GAP_KEY = "fxutils.grid.default.gap";
	private static final String GRID_HGAP_KEY = "fxutils.grid.default.hgap";
	private static final String GRID_VGAP_KEY = "fxutils.grid.default.vgap";
	
	private static final String VBOX_POS_KEY = "fxutils.vbox.default.pos";
	private static final String VBOX_PADDING_KEY = "fxutils.vbox.default.padding";
	private static final String VBOX_PADDING_TOP_KEY = "fxutils.vbox.default.padding_top";
	private static final String VBOX_PADDING_RIGHT_KEY = "fxutils.vbox.default.padding_right";
	private static final String VBOX_PADDING_BOTTOM_KEY = "fxutils.vbox.default.padding_bottom";
	private static final String VBOX_PADDING_LEFT_KEY = "fxutils.vbox.default.padding_left";

	private static final String HBOX_POS_KEY = "fxutils.hbox.default.pos";
	private static final String HBOX_PADDING_KEY = "fxutils.hbox.default.padding";
	private static final String HBOX_PADDING_TOP_KEY = "fxutils.hbox.default.padding_top";
	private static final String HBOX_PADDING_RIGHT_KEY = "fxutils.hbox.default.padding_right";
	private static final String HBOX_PADDING_BOTTOM_KEY = "fxutils.hbox.default.padding_bottom";
	private static final String HBOX_PADDING_LEFT_KEY = "fxutils.hbox.default.padding_left";
	
	private static final String POPUP_HIDE_ON_ESCAPE_KEY = "fxutils.popup.default.hide_on_escape";
	
	@NotNull
	public static GridPane makeDefaultGrid() {
		if (hasProperties(GRID_PADDING_TOP_KEY, GRID_PADDING_RIGHT_KEY, GRID_PADDING_BOTTOM_KEY, GRID_PADDING_LEFT_KEY) && hasProperties(GRID_HGAP_KEY, GRID_VGAP_KEY)) {
			Insets padding = new Insets(getDoubleProperty(GRID_PADDING_TOP_KEY, 20.0), getDoubleProperty(GRID_PADDING_RIGHT_KEY, 20.0), getDoubleProperty(GRID_PADDING_BOTTOM_KEY, 20.0), getDoubleProperty(GRID_PADDING_LEFT_KEY, 20.0));
			return makeGrid(getEnumProperty(GRID_POS_KEY, Pos.values(), Pos.CENTER), padding, getDoubleProperty(GRID_HGAP_KEY, 10.0), getDoubleProperty(GRID_VGAP_KEY, 10.0));
		} else if (hasProperties(GRID_PADDING_TOP_KEY, GRID_PADDING_RIGHT_KEY, GRID_PADDING_BOTTOM_KEY, GRID_PADDING_LEFT_KEY)) {
			Insets padding = new Insets(getDoubleProperty(GRID_PADDING_TOP_KEY, 20.0), getDoubleProperty(GRID_PADDING_RIGHT_KEY, 20.0), getDoubleProperty(GRID_PADDING_BOTTOM_KEY, 20.0), getDoubleProperty(GRID_PADDING_LEFT_KEY, 20.0));
			return makeGrid(getEnumProperty(GRID_POS_KEY, Pos.values(), Pos.CENTER), padding, getDoubleProperty(GRID_GAP_KEY, 10.0), getDoubleProperty(GRID_GAP_KEY, 10.0));
		} else if (hasProperties(GRID_HGAP_KEY, GRID_VGAP_KEY)) {
			return makeGrid(getEnumProperty(GRID_POS_KEY, Pos.values(), Pos.CENTER), getDoubleProperty(GRID_PADDING_KEY, 20.0), getDoubleProperty(GRID_HGAP_KEY, 10.0), getDoubleProperty(GRID_VGAP_KEY, 10.0));
		}
		return makeGrid(getEnumProperty(GRID_POS_KEY, Pos.values(), Pos.CENTER), getDoubleProperty(GRID_PADDING_KEY, 20.0), getDoubleProperty(GRID_GAP_KEY, 10.0));
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
		if (hasProperties(VBOX_PADDING_TOP_KEY, VBOX_PADDING_RIGHT_KEY, VBOX_PADDING_BOTTOM_KEY, VBOX_PADDING_LEFT_KEY)) {
			Insets padding = new Insets(getDoubleProperty(VBOX_PADDING_TOP_KEY, 0.0), getDoubleProperty(VBOX_PADDING_RIGHT_KEY, 0.0), getDoubleProperty(VBOX_PADDING_BOTTOM_KEY, 0.0), getDoubleProperty(VBOX_PADDING_LEFT_KEY, 0.0));
			return makeVBox(getEnumProperty(VBOX_POS_KEY, Pos.values(), Pos.CENTER), padding, children);
		}
		return makeVBox(getEnumProperty(VBOX_POS_KEY, Pos.values(), Pos.CENTER), getDoubleProperty(VBOX_PADDING_KEY, 0.0), children);
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
		if (hasProperties(HBOX_PADDING_TOP_KEY, HBOX_PADDING_RIGHT_KEY, HBOX_PADDING_BOTTOM_KEY, HBOX_PADDING_LEFT_KEY)) {
			Insets padding = new Insets(getDoubleProperty(HBOX_PADDING_TOP_KEY, 0.0), getDoubleProperty(HBOX_PADDING_RIGHT_KEY, 0.0), getDoubleProperty(HBOX_PADDING_BOTTOM_KEY, 0.0), getDoubleProperty(HBOX_PADDING_LEFT_KEY, 0.0));
			return makeHBox(getEnumProperty(HBOX_POS_KEY, Pos.values(), Pos.CENTER), padding, children);
		}
		return makeHBox(getEnumProperty(HBOX_POS_KEY, Pos.values(), Pos.CENTER), getDoubleProperty(HBOX_PADDING_KEY, 0.0), children);
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
	public static ImageView makeImageView(Image image, double width, double heigh) {
		return makeImageView(image, width, heigh, true);
	}
	
	@NotNull
	public static ImageView makeImageView(String imagePath, double width, double heigh, boolean smooth) {
		return makeImageView(new Image(imagePath), width, heigh, smooth);
	}
	
	@NotNull
	public static ImageView makeImageView(Image image, double width, double heigh, boolean smooth) {
		ImageView imageView = new ImageView(image);
		imageView.setFitWidth(width);
		imageView.setFitHeight(heigh);
		imageView.setSmooth(smooth);
		return imageView;
	}
	
	@NotNull
	public static Popup makeDefaultPopup(double width, double height, Node... nodes) {
		return makePopup(getBooleanProperty(POPUP_HIDE_ON_ESCAPE_KEY, true), width, height, "-fx-background-color: #F4F4F4; -fx-padding: 0; -fx-border-color: black; -fx-border-width: 1;", nodes);
	}
	
	@NotNull
	public static Popup makePopup(double width, double height, Node... nodes) {
		return makePopup(true, width, height, "-fx-background-color: #F4F4F4; -fx-padding: 0; -fx-border-color: black; -fx-border-width: 1;", nodes);
	}
	
	@NotNull
	public static Popup makePopup(boolean hideOnEscape, double width, double height, String style, Node... nodes) {
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
	
	@NotNull
	public static Parent addHighlighting(Node node, Color highlightColor, Color defaultColor, double highlightPadding) {
		return addHighlighting(node, highlightColor, defaultColor, 5.0, highlightPadding);
	}
	
	@NotNull
	public static Parent addHighlighting(Node node, Color highlightColor, Color defaultColor, double highlightRadius, double highlightPadding) {
		return addHighlighting(node, new Background(new BackgroundFill(highlightColor, new CornerRadii(highlightRadius), null)), new Background(new BackgroundFill(defaultColor, new CornerRadii(highlightRadius), null)), highlightPadding);
	}
	
	@NotNull
	public static Parent addHighlighting(Node node, Background highlightBackground, Background defaultBackground, double highlightPadding) {
		return addHighlighting(node, highlightBackground, defaultBackground, new Insets(highlightPadding));
	}
	
	@NotNull
	public static Parent addHighlighting(Node node, Color highlightColor, Color defaultColor, Insets highlightPadding) {
		return addHighlighting(node, highlightColor, defaultColor, 5.0, highlightPadding);
	}
	
	@NotNull
	public static Parent addHighlighting(Node node, Color highlightColor, Color defaultColor, double highlightRadius, Insets highlightPadding) {
		return addHighlighting(node, new Background(new BackgroundFill(highlightColor, new CornerRadii(highlightRadius), null)), new Background(new BackgroundFill(defaultColor, new CornerRadii(highlightRadius), null)), highlightPadding);
	}
	
	@NotNull
	public static Parent addHighlighting(Node node, Background highlightBackground, Background defaultBackground, Insets highlightPadding) {
		GridPane pane = makeGrid(Pos.CENTER, highlightPadding, 0.0, 0.0);
		pane.add(node, 0, 0);
		pane.setBackground(defaultBackground);
		pane.hoverProperty().addListener(PropertyListeners.create((oldValue, newValue) -> {
			if (newValue) {
				pane.setBackground(highlightBackground);
			} else {
				pane.setBackground(defaultBackground);
			}
		}));
		return pane;
	}
	
}
