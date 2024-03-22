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

package net.luis.fxutils.pane;

import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import net.luis.fxutils.util.FxUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

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
	
	public InputPane(@Nullable String inputText, @NotNull T inputNode) {
		this.inputText = new Text(inputText == null ? "" : inputText);
		this.inputNode = Objects.requireNonNull(inputNode, "Input node must not be null");
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