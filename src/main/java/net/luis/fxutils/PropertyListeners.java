package net.luis.fxutils;

import java.util.function.BiConsumer;
import java.util.function.BiPredicate;
import java.util.function.Predicate;

import org.jetbrains.annotations.NotNull;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Luis-st
 *
 */

public class PropertyListeners {
	
	@NotNull
	public static <T> ChangeListener<T> create(BiConsumer<T, T> listenerAction) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				listenerAction.accept(oldValue, newValue);
			}
		};
	}
	
	@NotNull
	public static <T> ChangeListener<T> create(BiPredicate<T, T> listenerCondition, Runnable listenerAction) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				if (listenerCondition.test(oldValue, newValue)) {
					listenerAction.run();
				}
			}
		};
	}
	
	@NotNull
	public static <T> ChangeListener<T> createWithOld(T conditionValue, Runnable listenerAction) {
		return createWithOld((value) -> {
			return value.equals(conditionValue);
		}, listenerAction);
	}
	
	@NotNull
	public static <T> ChangeListener<T> createWithOld(Predicate<T> listenerCondition, Runnable listenerAction) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				if (listenerCondition.test(oldValue)) {
					listenerAction.run();
				}
			}
		};
	}
	
	@NotNull
	public static <T> ChangeListener<T> createWithNew(T conditionValue, Runnable listenerAction) {
		return createWithNew((value) -> {
			return value.equals(conditionValue);
		}, listenerAction);
	}
	
	@NotNull
	public static <T> ChangeListener<T> createWithNew(Predicate<T> listenerCondition, Runnable listenerAction) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				if (listenerCondition.test(newValue)) {
					listenerAction.run();
				}
			}
		};
	}
	
}
