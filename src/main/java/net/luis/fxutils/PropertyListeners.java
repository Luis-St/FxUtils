package net.luis.fxutils;

import java.util.function.BiPredicate;
import java.util.function.Predicate;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

/**
 *
 * @author Luis-st
 *
 */

public class PropertyListeners {
	
	public static <T> ChangeListener<T> create(BiPredicate<T, T> listenerPredicate, Runnable listenerRunnable) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				if (listenerPredicate.test(oldValue, newValue)) {
					listenerRunnable.run();
				}
			}
		};
	}
	
	public static <T> ChangeListener<T> createWithOld(T conditionValue, Runnable listenerRunnable) {
		return createWithOld((value) -> {
			return value.equals(conditionValue);
		}, listenerRunnable);
	}
	
	public static <T> ChangeListener<T> createWithOld(Predicate<T> listenerPredicate, Runnable listenerRunnable) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				if (listenerPredicate.test(oldValue)) {
					listenerRunnable.run();
				}
			}
		};
	}
	
	public static <T> ChangeListener<T> createWithNew(T conditionValue, Runnable listenerRunnable) {
		return createWithNew((value) -> {
			return value.equals(conditionValue);
		}, listenerRunnable);
	}
	
	public static <T> ChangeListener<T> createWithNew(Predicate<T> listenerPredicate, Runnable listenerRunnable) {
		return new ChangeListener<T>() {
			@Override
			public void changed(ObservableValue<? extends T> observable, T oldValue, T newValue) {
				if (listenerPredicate.test(newValue)) {
					listenerRunnable.run();
				}
			}
		};
	}
	
}
