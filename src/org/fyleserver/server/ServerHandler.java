package org.fyleserver.server;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.fyleserver.model.ServerHandlerSubscriber;
import org.fyleserver.model.ServerHandlerEvent;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

/**
 * Handles a server-side channel.
 */
public class ServerHandler extends SimpleChannelUpstreamHandler {

    private static final Logger logger = Logger.getLogger(
            ServerHandler.class.getName());
    private List<ServerHandlerSubscriber> subs = new ArrayList<>();

    @Override
    public void handleUpstream(
            ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        if (e instanceof ChannelStateEvent) {
            logger.info(e.toString());
        }
        super.handleUpstream(ctx, e);
    }

    @Override
    public void channelConnected(
            ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
    	notifySubscribers("CHANNEL_CONNECTED", ctx, e);
    }

    @Override
    public void messageReceived(
            ChannelHandlerContext ctx, MessageEvent e) {
    	notifySubscribers("MESSAGE_RECEIVED", ctx, e);
    }

    @Override
    public void exceptionCaught(
            ChannelHandlerContext ctx, ExceptionEvent e) {
    	notifySubscribers("EXCEPTION_CAUGHT", ctx, e);
        logger.log(
                Level.WARNING,
                "Unexpected exception from downstream.",
                e.getCause());
        e.getChannel().close();
    }
    
    private void notifySubscribers(String type, ChannelHandlerContext ctx, Object obj){
    	for (ServerHandlerSubscriber sub : subs){
    		sub.publish(new ServerHandlerEvent(type, ctx, obj));
    	}
    }
    
    public void addSubscriber(ServerHandlerSubscriber sub){
    	subs.add(sub);
    }
}