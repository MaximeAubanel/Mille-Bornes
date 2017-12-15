package milleborne.server;

import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NetworkHandler extends SimpleChannelInboundHandler<String> {

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);
	
	@Override
	public void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();
		
		System.out.println("[SERVER] - " + incoming.remoteAddress() + " has joined !\n");
		for (Channel channel: channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has joined !\n");
		}
		channels.add(ctx.channel());
	}

	@Override
	public void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		Channel incoming = ctx.channel();

		System.out.println("[SERVER] - " + incoming.remoteAddress() + " has left !\n");
		for (Channel channel: channels) {
			channel.writeAndFlush("[SERVER] - " + incoming.remoteAddress() + " has left !\n");
		}
		ctx.close();
		channels.remove(ctx.channel());
	}

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    	System.out.println("msg received");
        // Send the received message to all channels but the current one.
        for (Channel c: channels) {
            if (c != ctx.channel()) {
                c.writeAndFlush("[" + ctx.channel().remoteAddress() + "] " + msg + '\n');
            } else {
                c.writeAndFlush("[you] " + msg + '\n');
            }
        }

        // Close the connection if the client has sent 'bye'.
        if ("bye".equals(msg.toLowerCase())) {
    		channels.remove(ctx.channel());
        }
    }
    
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
        // cause.printStackTrace();
        ctx.close();
    }
}
