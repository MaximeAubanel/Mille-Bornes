import java.awt.List;
import java.util.ArrayList;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;


public class NetworkHandler extends SimpleChannelInboundHandler<String> {
	
	private ArrayList<msgReceivedListener> _Listeners = new ArrayList<msgReceivedListener>();
    
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
		 System.out.println("msg received");		 

		String header = msg.substring(0, 5);
    	if (header.compareTo(Global.LOBBY) == 0)
    		LobbyHandler(msg.substring(5));    		 
    	else if (header.compareTo(Global.GAME) == 0)
			GameHandler(msg.substring(5));    		
		else if (header.compareTo(Global.SERVER) == 0)
    		ServerHandler(msg.substring(5));
		else if (header.compareTo(Global.STATE) == 0)
    		StateHandler(msg.substring(5));
    }
	
    public void ServerHandler(String  msg) {
		 
    }
    public void LobbyHandler(String  msg) {
		 for (msgReceivedListener x : _Listeners)
			 x.messageReceived(msg);
    }
    public void GameHandler(String  msg) {
    	
    }
    public void StateHandler(String newState) {
		 for (msgReceivedListener x : _Listeners)
			 x.changeState(newState);
    }
    
	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable cause) {
		 cause.printStackTrace();
		 arg0.close();
	}
	public void addLobbyListeners(msgReceivedListener toAdd) {
		_Listeners.add(toAdd);
	}
}

