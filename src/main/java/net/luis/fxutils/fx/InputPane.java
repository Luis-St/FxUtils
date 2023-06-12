package net.luis.fxutils.fx;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.luis.fxutils.FxUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 *
 * @author Luis-st
 *
 */

public class InputPane<T extends Node> extends GridPane {
	
	private final Text inputText;
	private final T inputNode;
	
	private InputPane(String inputText, T inputNode, double textSize) {
		this.inputText = new Text(inputText);
		this.inputNode = Objects.requireNonNull(inputNode);
		this.init(textSize);
	}
	
	public static @NotNull InputPane<TextField> create(String inputText) {
		return new InputPane<>(inputText, new TextField(), 12.0);
	}
	
	public static @NotNull InputPane<TextField> create(String inputText, double textSize) {
		return new InputPane<>(inputText, new TextField(), textSize);
	}
	
	public static <T extends Node> @NotNull InputPane<T> create(String inputText, T inputNode, double textSize) {
		return new InputPane<>(inputText, inputNode, textSize);
	}
	
	private void init(double textSize) {
		this.inputText.setFont(new Font(Font.getDefault().getName(), textSize));
		this.setAlignment(Pos.CENTER);
		this.setVgap(0.0);
		this.setHgap(0.0);
		this.addColumn(0, FxUtils.makeVBox(Pos.CENTER_LEFT, 0.0, this.inputText), this.inputNode);
	}
	
	@NotNull
	public Text getInputText() {
		return this.inputText;
	}
	
	@NotNull
	public T getInputNode() {
		return this.inputNode;
	}
	
}