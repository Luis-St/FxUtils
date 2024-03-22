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
