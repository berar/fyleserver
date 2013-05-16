package org.fyleserver.model;

import org.jboss.netty.channel.ChannelHandlerContext;

public class ServerHandlerEvent {
	private final String type;
	private final ChannelHandlerContext ctx;
	private final Object obj;
	
	public ServerHandlerEvent(String type, ChannelHandlerContext ctx, Object obj){
		this.type = type;
		this.ctx = ctx;
		this.obj = obj;
	}
	public String getType(){ return this.type; }
	public ChannelHandlerContext getCtx(){ return this.ctx; }
	public Object getObject(){ return this.obj; }
}
