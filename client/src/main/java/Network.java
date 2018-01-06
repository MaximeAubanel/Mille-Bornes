import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Network implements msgToSendListener {
	
	private boolean			_isOver = false;
	private boolean 		_isConnected = false;
	private String 			_Host;
	private int				_Port;
	private Channel 		_Server;
	private EventLoopGroup 	_Group;
	private Bootstrap		_Bootstrap;
	public NetworkInitializer _NetworkInitializer = new NetworkInitializer();
	
	
	public Network() {
	
	}
	
	public void setListenersForMsgReceived(msgReceivedListener toAdd) {
		_NetworkInitializer._NetworkHandler.addLobbyListeners(toAdd);
	}
	
	public void setNetwork(String host, String port) {
		this._Host = host;
		try {
			this._Port = Integer.parseInt(port);			
		} catch (Exception e) {
			this._Port = 0;
		}
	}
	
	public int tryConnect() {
		_Group = new NioEventLoopGroup();
		
		try {
			_Bootstrap = new Bootstrap()
					.group(_Group)
					.channel(NioSocketChannel.class)
					.handler(_NetworkInitializer);
			
			_Server = _Bootstrap.connect(_Host, _Port).sync().channel();
			_isConnected = true;
			return (0);
		} catch (final Exception e) {
			return (-1);
		}
	}
	
	public void Send(String msg) {
		_Server.writeAndFlush(msg + "\r\n");
	}
	
	public void Stop() {
		if (_isConnected == true)
			_Group.shutdownGracefully();
	}
	
	public boolean isOver() {
		return (_isOver);
	}

	public void reset() {
		_Group.shutdownGracefully();
		_NetworkInitializer = new NetworkInitializer();
	}
	
	@Override
	public void sendMsg(String msg) {
		_Server.writeAndFlush(msg + "\r\n");
	}
}
