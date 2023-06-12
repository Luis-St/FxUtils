package net.luis.fxutils;

import org.jetbrains.annotations.NotNull;

import javafx.event.Event;
import javafx.event.EventHandler;

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
