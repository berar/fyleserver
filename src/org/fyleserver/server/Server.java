package org.fyleserver.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

/**
 * Simplistic telnet server.
 */
public class Server {

    private final int port;
    private static ServerPipelineFactory pipeline;

    public Server(int port) {
        this.port = port;
        pipeline = new ServerPipelineFactory();
    }

    public void run() {
        // Configure the server.
        ServerBootstrap bootstrap = new ServerBootstrap(
                new NioServerSocketChannelFactory(
                        Executors.newCachedThreadPool(),
                        Executors.newCachedThreadPool()));

        // Configure the pipeline factory.
        bootstrap.setPipelineFactory(pipeline);

        // Bind and start to accept incoming connections.
        bootstrap.bind(new InetSocketAddress(port));
    }
    
    public ServerPipelineFactory getServerPipelineFactory(){
    	return pipeline;
    }
}
