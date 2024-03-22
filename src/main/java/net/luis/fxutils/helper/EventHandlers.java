package net.luis.fxutils.helper;

import javafx.event.Event;
import javafx.event.EventHandler;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 *
 * @author Luis-st
 *
 */

public class EventHandlers {
	
	public static <T extends Event> @NotNull EventHandler<T> create(@NotNull Runnable eventAction) {
		Objects.requireNonNull(eventAction, "Event action must not be null");
		return event -> eventAction.run();
	}
}
