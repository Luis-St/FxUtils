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
	
	static <T extends FxApplication> void launch(@NotNull Supplier<T> application, String @NotNull [] args) {
		Objects.requireNonNull(application, "Application supplier must not be null");
		launch(application.get(), args);
	}
	
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
