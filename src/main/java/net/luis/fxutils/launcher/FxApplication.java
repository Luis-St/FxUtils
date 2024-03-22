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
 *
 * @author Luis-St
 *
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
						throw new RuntimeException("Failed to stop application", e);
					}
				});
				application.start(primaryStage);
			} catch (Exception e) {
				throw new RuntimeException("Failed to start application", e);
			}
		});
	}
	
	void init(String @NotNull [] args) throws Exception;
	
	void start(@NotNull Stage primaryStage) throws Exception;
	
	void stop() throws Exception;
}
