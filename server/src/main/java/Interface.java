import io.netty.channel.ChannelHandlerContext;

interface UserModifListeners {
	void addUser(ChannelHandlerContext ctx);
	void removeUser(ChannelHandlerContext ctx);
	void Ready(ChannelHandlerContext ctx);
	void CancelReady(ChannelHandlerContext ctx);
}

interface CoreListeners {
	void startCountDown();
	void stopCountDown();
}