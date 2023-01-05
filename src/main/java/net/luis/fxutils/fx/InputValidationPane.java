package net.luis.fxutils.fx;

import javafx.scene.Node;
import net.luis.fxutils.CssUtils;
import net.luis.fxutils.EventHandlers;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Function;

/**
 *
 * @author Luis-st
 *
 */

public class InputValidationPane<T extends Node> extends InputPane<T> {
	
	private final List<Node> childNodes = new ArrayList<>();
	private Runnable inputValidation;
	
	public InputValidationPane(String inputText, T inputNode) {
		super(inputText, inputNode);
	}
	
	public InputValidationPane(String inputText, T inputNode, Function<T, ValidationState> inputValidation) {
		super(inputText, inputNode);
		this.setInputValidation(inputValidation);
	}
	
	@Override
	protected void update() {
		this.getInputNode().setOnKeyTyped(EventHandlers.create(this.inputValidation));
		this.getInputNode().setOnMouseClicked(EventHandlers.create(this.inputValidation));
	}
	
	public void setInputValidation(Function<T, InputValidationPane.ValidationState> inputValidation) {
		this.inputValidation = () -> {
			this.setPseudoClassValue("valid", false);
			this.setPseudoClassValue("invalid", false);
			ValidationState state = Objects.requireNonNull(inputValidation).apply(this.getInputNode());
			if (state == ValidationState.VALID) {
				this.setPseudoClassValue("valid", true);
			} else if (state == ValidationState.INVALID) {
				this.setPseudoClassValue("invalid", true);
			}
		};
		this.update();
	}
	
	private void setPseudoClassValue(String clazz, boolean value) {
		CssUtils.setPseudoClassValue(this.getInputNode(), clazz, value);
		for (Node node : this.childNodes) {
			CssUtils.setPseudoClassValue(node, clazz, value);
		}
	}
	
	public void validateInput() {
		this.inputValidation.run();
	}
	
	public void addChildNode(Node node) {
		this.childNodes.add(node);
		this.update();
	}
	
	public void removeChildNode(Node node) {
		if (this.childNodes.remove(node)) {
			CssUtils.setPseudoClassValue(node, "valid", false);
			CssUtils.setPseudoClassValue(node, "invalid", false);
		}
		this.update();
	}
	
	public enum ValidationState {
		
		VALID(), INVALID(), DEFAULT()
		
	}
	
}
