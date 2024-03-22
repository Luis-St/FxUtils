package net.luis.fxutils.helper;

import javafx.beans.value.ChangeListener;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.*;

/**
 *
 * @author Luis-st
 *
 */

public class PropertyListeners {
	
	public static <T> @NotNull ChangeListener<T> create(@NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> listenerAction.run();
	}
	
	public static <T> @NotNull ChangeListener<T> create(@NotNull BiConsumer<T, T> listenerAction) {
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> listenerAction.accept(oldValue, newValue);
	}
	
	public static <T> @NotNull ChangeListener<T> create(@NotNull BiPredicate<T, T> listenerCondition, @NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerCondition, "Listener condition must not be null");
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(oldValue, newValue)) {
				listenerAction.run();
			}
		};
	}
	
	public static <T> @NotNull ChangeListener<T> createWithOld(@NotNull Predicate<T> listenerCondition, @NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerCondition, "Listener condition must not be null");
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(oldValue)) {
				listenerAction.run();
			}
		};
	}
	
	public static <T> @NotNull ChangeListener<T> createWithNew(@NotNull Predicate<T> listenerCondition, @NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerCondition, "Listener condition must not be null");
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(newValue)) {
				listenerAction.run();
			}
		};
	}
}
