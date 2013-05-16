package org.fyleserver.model;

public interface ServerHandlerSubscriber {
	void publish(ServerHandlerEvent event);
}
