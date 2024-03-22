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

import javafx.scene.Node;
import javafx.scene.control.TextInputControl;
import net.luis.fxutils.helper.EventHandlers;
import net.luis.fxutils.util.CssUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.*;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * @author Luis-st
 *
 */

public class InputValidationPane<T extends Node> extends InputPane<T> {
	
	private final List<Node> childNodes = new ArrayList<>();
	private Runnable inputValidation = () -> {};
	private Predicate<T> validationPredicate = (node) -> {
		if (this.getInputNode() instanceof TextInputControl control) {
			return control.isEditable();
		} else {
			return true;
		}
	};
	
	public InputValidationPane(@Nullable String inputText, @NotNull T inputNode) {
		super(inputText, inputNode);
	}
	
	public InputValidationPane(@Nullable String inputText, @NotNull T inputNode, @NotNull Function<T, ValidationState> inputValidation) {
		super(inputText, inputNode);
		this.setInputValidation(inputValidation);
	}
	
	@Override
	protected void update() {
		super.update();
		this.getInputNode().setOnKeyTyped(EventHandlers.create(this.inputValidation));
		this.getInputNode().setOnMouseClicked(EventHandlers.create(this.inputValidation));
	}
	
	public void setInputValidation(@NotNull Function<T, InputValidationPane.ValidationState> inputValidation) {
		Objects.requireNonNull(inputValidation, "Input validation must not be null");
		this.inputValidation = () -> {
			if (this.isValidationActive()) {
				this.setPseudoClassValue("valid", false);
				this.setPseudoClassValue("invalid", false);
				ValidationState state = inputValidation.apply(this.getInputNode());
				if (state == ValidationState.VALID) {
					this.setPseudoClassValue("valid", true);
				} else if (state == ValidationState.INVALID) {
					this.setPseudoClassValue("invalid", true);
				}
			}
		};
		this.update();
	}
	
	private void setPseudoClassValue(@NotNull String clazz, boolean value) {
		Objects.requireNonNull(clazz, "Pseudo class must not be null");
		CssUtils.setPseudoClassValue(this.getInputNode(), clazz, value);
		for (Node node : this.childNodes) {
			CssUtils.setPseudoClassValue(node, clazz, value);
		}
	}
	
	public void validateInput() {
		this.inputValidation.run();
	}
	
	public void setValidationPredicate(Predicate<T> validationPredicate) {
		this.validationPredicate = Objects.requireNonNull(validationPredicate, "Validation predicate must not be null");
	}
	
	public boolean isValidationActive() {
		return this.validationPredicate.test(this.getInputNode());
	}
	
	public void overrideInputValidation(InputValidationPane.ValidationState state) {
		Objects.requireNonNull(state, "Validation state must not be null");
		this.setPseudoClassValue("valid", false);
		this.setPseudoClassValue("invalid", false);
		if (state == ValidationState.VALID) {
			this.setPseudoClassValue("valid", true);
		} else if (state == ValidationState.INVALID) {
			this.setPseudoClassValue("invalid", true);
		}
	}
	
	public void addChildNode(Node node) {
		this.childNodes.add(node);
		this.update();
	}
	
	public void removeChildNode(@Nullable Node node) {
		if (this.childNodes.remove(node)) {
			Objects.requireNonNull(node, "Node must not be null");
			CssUtils.setPseudoClassValue(node, "valid", false);
			CssUtils.setPseudoClassValue(node, "invalid", false);
		}
		this.update();
	}
	
	public enum ValidationState {
		
		VALID(), INVALID(), DEFAULT()
	}
}
