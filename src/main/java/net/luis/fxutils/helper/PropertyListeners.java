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
 * Utility class for creating change listeners.<br>
 *
 * @author Luis-st
 */
public class PropertyListeners {
	
	/**
	 * Creates a change listener that runs the given action when the value of the observable changes.<br>
	 * The action is run always and regardless of the old and new value of the observable.<br>
	 * @param listenerAction The action to run when the value of the observable changes
	 * @return The created change listener
	 * @param <T> The type of the observable value
	 * @throws NullPointerException If the listener action is null
	 */
	public static <T> @NotNull ChangeListener<T> create(@NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> listenerAction.run();
	}
	
	/**
	 * Creates a change listener that runs the given action when the value of the observable changes.<br>
	 * The action is run always with the old and new value of the observable.<br>
	 * @param listenerAction The action to run when the value of the observable changes
	 * @return The created change listener
	 * @param <T> The type of the observable value
	 * @throws NullPointerException If the listener action is null
	 */
	public static <T> @NotNull ChangeListener<T> create(@NotNull BiConsumer<T, T> listenerAction) {
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> listenerAction.accept(oldValue, newValue);
	}
	
	/**
	 * Creates a change listener that runs the given action when the value of the observable changes.<br>
	 * The action is run only if the given condition is met.<br>
	 * The condition is tested with the old (first) and new value (second) of the observable.<br>
	 * @param listenerCondition The condition that must be met to run the action
	 * @param listenerAction The action to run when the value of the observable changes
	 * @return The created change listener
	 * @param <T> The type of the observable value
	 * @throws NullPointerException If the condition or action is null
	 */
	public static <T> @NotNull ChangeListener<T> create(@NotNull BiPredicate<T, T> listenerCondition, @NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerCondition, "Listener condition must not be null");
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(oldValue, newValue)) {
				listenerAction.run();
			}
		};
	}
	
	/**
	 * Creates a change listener that runs the given action when the value of the observable changes.<br>
	 * The action is run only if the given condition is met.<br>
	 * The condition is tested with the old value of the observable.<br>
	 * @param listenerCondition The condition that must be met to run the action
	 * @param listenerAction The action to run when the value of the observable changes
	 * @return The created change listener
	 * @param <T> The type of the observable value
	 * @throws NullPointerException If the condition or action is null
	 */
	public static <T> @NotNull ChangeListener<T> createWithOld(@NotNull Predicate<T> listenerCondition, @NotNull Runnable listenerAction) {
		Objects.requireNonNull(listenerCondition, "Listener condition must not be null");
		Objects.requireNonNull(listenerAction, "Listener action must not be null");
		return (observable, oldValue, newValue) -> {
			if (listenerCondition.test(oldValue)) {
				listenerAction.run();
			}
		};
	}
	
	/**
	 * Creates a change listener that runs the given action when the value of the observable changes.<br>
	 * The action is run only if the given condition is met.<br>
	 * The condition is tested with the new value of the observable.<br>
	 * @param listenerCondition The condition that must be met to run the action
	 * @param listenerAction The action to run when the value of the observable changes
	 * @return The created change listener
	 * @param <T> The type of the observable value
	 * @throws NullPointerException If the condition or action is null
	 */
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
