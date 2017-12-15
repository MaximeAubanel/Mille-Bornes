package milleborne.client;

import java.awt.List;
import java.util.ArrayList;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;


public class NetworkHandler extends SimpleChannelInboundHandler<String> {
	
    private ArrayList<msgReceivedListener> listeners = new ArrayList<msgReceivedListener>();
    
	@Override
	public void channelRead0(ChannelHandlerContext arg0, String arg1) throws Exception {
		 System.out.println(arg1);		 
		 for (msgReceivedListener x : listeners)
			 x.messageReceived(arg1);
	}

	public void addListeners(msgReceivedListener toAdd) {
		listeners.add(toAdd);
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable cause) {
		 cause.printStackTrace();
		 arg0.close();
	}
}

