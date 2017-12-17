import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

public class Network {
	
	private int					_Port;
	private EventLoopGroup		_BossGroup;
	private EventLoopGroup		_WorkerGroup;
	private ServerBootstrap		_Bootstrap;
	volatile public NetworkInitializer	_NetworkInitializer = new NetworkInitializer();
		
	public Network(int port) {
		_Port = port;
	}
	
	public void Run() throws Exception {
		System.out.println("server launched");

		_BossGroup = new NioEventLoopGroup();
		_WorkerGroup = new NioEventLoopGroup();
		
		try {
			_Bootstrap = new ServerBootstrap()
					.group(_BossGroup, _WorkerGroup)
					.channel(NioServerSocketChannel.class)
					.childHandler(_NetworkInitializer);
			
			_Bootstrap.bind(_Port).sync().channel().closeFuture().sync();
		} finally {
			_BossGroup.shutdownGracefully();
			_WorkerGroup.shutdownGracefully();
		}
	}

}
