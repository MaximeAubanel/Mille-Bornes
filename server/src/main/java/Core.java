public class Core {
	Network			_Network;

	public Core() {
		_Network = new Network(8889);
	}
	
	public void Run() throws Exception {
		_Network.Run();
	}
}
