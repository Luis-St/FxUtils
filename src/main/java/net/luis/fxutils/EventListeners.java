package net.luis.fxutils;

import org.jetbrains.annotations.NotNull;

import javafx.event.Event;
import javafx.event.EventHandler;

/**
 *
 * @author Luis-st
 *
 */

public class EventListeners {
	
	@NotNull
	public static <T extends Event> EventHandler<T> create(Runnable eventAction) {
		return new EventHandler<T>() {
			@Override
			public void handle(T event) {
				eventAction.run();
			}
		};
	}
	
}
