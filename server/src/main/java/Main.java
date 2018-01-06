public class Main {

	public static void help() {
		System.out.println("You must provide a port between 5000 & 8000");
	}
	
	public static void main(String[] args) throws Exception {
		
		if (args.length == 0) {
			help();
			return;
		}
		
		int port = 0;
		try {
			port = Integer.parseInt(args[0]);
			if (port < 5000 || port > 8000) {
				help();
				return;
			}
		} catch (Exception e) {
			help();
			return ;
		}
		Server		myServer = new Server(port);
		
		myServer.Run();
		
		System.exit(0);
	}

}
