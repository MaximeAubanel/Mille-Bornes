import java.awt.List;
import java.util.ArrayList;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.SimpleChannelInboundHandler;


public class NetworkHandler extends SimpleChannelInboundHandler<String> {
	
	final static String SERVER 			= "[0x0]";	
	final static String LOBBY 			= "[0x1]";
	final static String GAME 			= "[0x2]";

	private ArrayList<msgReceivedListener> lobbyListeners = new ArrayList<msgReceivedListener>();
    
	@Override
	public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
		
		 System.out.println("msg received");		 

		String header = msg.substring(0, 5);
    	if (header.compareTo(LOBBY) == 0)
    		LobbyHandler(msg.substring(5));    		 
    	else if (header.compareTo(GAME) == 0)
			GameHandler(msg.substring(5));    		
		else if (header.compareTo(SERVER) == 0)
    		ServerHandler(msg.substring(5));
    }
	
    public void ServerHandler(String  msg) {
		 
    }
    public void LobbyHandler(String  msg) {
		 for (msgReceivedListener x : lobbyListeners)
			 x.messageReceived(msg);
    }
    public void GameHandler(String  msg) {
    	
    }
    
	@Override
	public void exceptionCaught(ChannelHandlerContext arg0, Throwable cause) {
		 cause.printStackTrace();
		 arg0.close();
	}
	public void addLobbyListeners(msgReceivedListener toAdd) {
		lobbyListeners.add(toAdd);
	}
}

