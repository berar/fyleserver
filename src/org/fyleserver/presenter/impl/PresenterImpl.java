package org.fyleserver.presenter.impl;

import org.fyleserver.model.ServerHandlerEvent;
import org.fyleserver.model.ServerHandlerSubscriber;
import org.fyleserver.server.Server;
import org.fyleserver.util.MessageReceivedParser;
import org.fyleserver.util.impl.MessageReceivedParserImpl;
import org.fyleserver.util.mrhandler.MessageReceivedHandler;
import org.jboss.netty.channel.ChannelFutureListener;
import org.jboss.netty.channel.MessageEvent;

public class PresenterImpl implements ServerHandlerSubscriber {

	private Server server;

	public PresenterImpl(Server server) {
		this.server = server;
		init();
	}

	private void init() {
		this.server.getServerPipelineFactory().getServerHandler()
				.addSubscriber(this);
	}

	@Override
	public void publish(final ServerHandlerEvent event) {
		new Thread(new Runnable() {
			@Override
			public void run() {
				if ("CHANNEL_CONNECTED".equals(event.getType())) {
					return;
				}
				if ("MESSAGE_RECEIVED".equals(event.getType())) {
					MessageEvent messageEvent = (MessageEvent) event
							.getObject();
					String message = (String) messageEvent.getMessage();
					MessageReceivedParser mrp = new MessageReceivedParserImpl(
							message);
					MessageReceivedHandler mrh = mrp.handle();
					String response = mrh.createResponse();
					if ("DISC".equals(response)) {
						messageEvent.getFuture().addListener(
								ChannelFutureListener.CLOSE);
					} else {
						messageEvent.getChannel().write(response + "\n");
					}
					return;
				}
				if ("EXCEPTION_CAUGHT".equals(event.getType())) {
					return;
				}
			}
		}).start();
	}
}
