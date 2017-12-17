import io.netty.channel.ChannelHandlerContext;

public class User {
	
	//private String	 				_Username = null;
	private ChannelHandlerContext 	_Connection = null;
	private boolean					_Ready = false;
	
	public ChannelHandlerContext 	getConnection() { return _Connection; }
	
	public void 					setReady(boolean b) { _Ready = b; }
	public boolean 					isReady() { return _Ready; }
	
	public User(ChannelHandlerContext ctx) {
		//_Username = Username;
		_Connection = ctx;
	}
}
