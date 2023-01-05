package net.luis.fxutils.fx;

import javafx.geometry.Pos;
import javafx.scene.Node;
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
	private double textSize = 13.0;
	
	public InputPane(String inputText, T inputNode) {
		this.inputText = new Text(inputText);
		this.inputNode = Objects.requireNonNull(inputNode);
		this.init();
	}
	
	private void init() {
		this.update();
		this.setAlignment(Pos.CENTER);
		this.setVgap(0.0);
		this.setHgap(0.0);
		this.addColumn(0, FxUtils.makeVBox(Pos.CENTER_LEFT, 0.0, this.inputText), this.inputNode);
	}
	
	protected void update() {
		this.inputText.setFont(new Font(Font.getDefault().getName(), this.textSize));
	}
	
	public @NotNull Text getInputText() {
		return this.inputText;
	}
	
	public double getTextSize() {
		return this.textSize;
	}
	
	public void setTextSize(double textSize) {
		this.textSize = textSize;
		this.update();
	}
	
	public @NotNull T getInputNode() {
		return this.inputNode;
	}
	
}