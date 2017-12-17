public class Server {
	Core			_Core;
	
	public Server() {
		_Core = new Core();
	}
	
	public void Run() throws Exception {
		_Core.Run();
	}
}
