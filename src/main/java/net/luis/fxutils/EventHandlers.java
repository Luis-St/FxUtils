package net.luis.fxutils;

import javafx.event.Event;
import javafx.event.EventHandler;
import org.jetbrains.annotations.NotNull;

/**
 *
 * @author Luis-st
 *
 */

public class EventHandlers {
	
	public static <T extends Event> @NotNull EventHandler<T> create(Runnable eventAction) {
		return event -> eventAction.run();
	}
}
