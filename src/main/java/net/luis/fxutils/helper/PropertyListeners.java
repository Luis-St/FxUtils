package net.luis.fxutils.helper;

import javafx.beans.value.ChangeListener;
import org.jetbrains.annotations.NotNull;

import java.util.function.*;

/**
 *
 * @author Luis-st
 *
 */

public class PropertyListeners {
	
	public static <T> @NotNull ChangeListener<T> create(Runnable listenerAction) {
		return (observable, oldValue, newValue) -> listenerAction.run();
	}
	
	public static <T> @NotNull ChangeListener<T> create(BiConsumer<T, T> listenerAction) {
		return (observable, oldValue, newValue) -> listenerAction.accept(oldValue, newValue);
	}
	
	public static <T> @NotNull ChangeListener<T> create(BiPredicate<T, T> listenerCondition, Runnable listenerAction) {
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(oldValue, newValue)) {
				listenerAction.run();
			}
		};
	}
	
	public static <T> @NotNull ChangeListener<T> createWithOld(Predicate<T> listenerCondition, Runnable listenerAction) {
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(oldValue)) {
				listenerAction.run();
			}
		};
	}
	
	public static <T> @NotNull ChangeListener<T> createWithNew(Predicate<T> listenerCondition, Runnable listenerAction) {
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(newValue)) {
				listenerAction.run();
			}
		};
	}
}
