import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.group.ChannelGroup;
import io.netty.channel.group.DefaultChannelGroup;
import io.netty.util.concurrent.GlobalEventExecutor;

public class NetworkHandler extends SimpleChannelInboundHandler<String> {

	final static String SERVER 			= "[0x0]";	
	final static String LOBBY 			= "[0x1]";
	final static String GAME 			= "[0x2]";

	private static final ChannelGroup channels = new DefaultChannelGroup(GlobalEventExecutor.INSTANCE);

    @Override
    public void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception {
    	String header = msg.substring(0, 5);

    	if (header.compareTo(SERVER) == 0)
    		ServerHandler(msg.substring(5), ctx);
		else if (header.compareTo(LOBBY) == 0)
    		LobbyHandler(msg.substring(5), ctx);    		 
    	else if (header.compareTo(GAME) == 0)
			GameHandler(msg.substring(5), ctx);    		  		
    } 
    
    public void LobbyHandler(String  msg, ChannelHandlerContext ctx) {
    	if (msg.compareTo("ready") == 0) {
    		
    	} else {
    		broadcastMessageToAll(LOBBY, msg, ctx);
    	}
    }  
    public void ServerHandler(String  msg, ChannelHandlerContext ctx) {
    	
    }
    public void GameHandler(String  msg, ChannelHandlerContext ctx) {
    	
    } 

    public void broadcastMessageToAll(String header, String msg) {
        for (Channel c: channels) {
        	c.writeAndFlush(header + msg + '\n');        		
        }
    }
    public void broadcastMessageToAll(String header, String msg, ChannelHandlerContext ctx) {
        for (Channel c: channels) {
        	if (c != ctx.channel())
        		c.writeAndFlush(header + "[" + ctx.channel().remoteAddress() + "] : " + msg + '\n');        		
        	else
        		c.writeAndFlush(header + "[you] : " + msg + '\n');
        }
    }
    
	@Override
	public 	void handlerAdded(ChannelHandlerContext ctx) throws Exception {
		addUser(ctx);
	}
	@Override
	public 	void handlerRemoved(ChannelHandlerContext ctx) throws Exception {
		removeUser(ctx);
	}
    private void addUser(ChannelHandlerContext ctx) {
		myPrint("[" + ctx.channel().remoteAddress() + "] has joined !\n");
		
		broadcastMessageToAll(LOBBY, "[" + ctx.channel().remoteAddress() + "] has joined !");
		channels.add(ctx.channel());
    }
    private void removeUser(ChannelHandlerContext ctx) {
    	myPrint("[" + ctx.channel().remoteAddress() + "] has left !\n");
		
		broadcastMessageToAll(LOBBY, "[" + ctx.channel().remoteAddress() + "] has left !");
		ctx.close();
		channels.remove(ctx.channel());
    }
    
    private void myPrint(String str) {
    	System.out.println(str);
    }
    @Override
    public 	void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) {
         cause.printStackTrace();
        ctx.close();
    }
}
