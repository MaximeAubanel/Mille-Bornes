public class Server {
	volatile Core			_Core;
	volatile Network			_Network;

	public Server() {
		_Core = new Core();
		_Network = new Network(8889);
	}
	
	public void Run() throws Exception {
		_Core.Run();
		_Network._NetworkInitializer._NetworkHandler.addListeners(_Core);
		_Core.addListeners(_Network._NetworkInitializer._NetworkHandler);
		_Network.Run();
	}
}
