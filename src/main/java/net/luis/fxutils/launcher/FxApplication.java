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

package net.luis.fxutils.launcher;

import javafx.application.Platform;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.function.Supplier;

/**
 * Represents a custom JavaFX application.<br>
 * <p>
 *     The custom application instance must be created and passed to the {@link #launch(FxApplication, String[])} method.<br>
 *     The application must implement the {@link #start(Stage)} method to define the application's main stage.<br>
 * </p>
 * <p>
 *     Application lifecycle is as follows:<br>
 * </p>
 * <ul>
 *     <li>{@link #init(String[])}: Called before the application is started.</li>
 *     <li>{@link #start(Stage)}: Called when the application is started.</li>
 *     <li>{@link #stop()}: Called when the application is closed.</li>
 * </ul>
 *
 * @author Luis-St
 */
public interface FxApplication {
	
	static <T extends FxApplication> void launch(@NotNull T application, String @NotNull [] args) {
		Objects.requireNonNull(application, "Application must not be null");
		Objects.requireNonNull(args, "Arguments must not be null");
		Platform.startup(() -> {
			try {
				application.init(args);
				Stage primaryStage = new Stage();
				primaryStage.setOnCloseRequest(event -> {
					try {
						application.stop();
					} catch (Exception e) {
						throw new RuntimeException("Error in application lifecycle", e);
					}
				});
				application.start(primaryStage);
			} catch (Exception e) {
				throw new RuntimeException("Error in application lifecycle", e);
			}
		});
	}
	
	default void init(String @NotNull [] args) throws Exception {}
	
	void start(@NotNull Stage primaryStage) throws Exception;
	
	default void stop() throws Exception {}
}
