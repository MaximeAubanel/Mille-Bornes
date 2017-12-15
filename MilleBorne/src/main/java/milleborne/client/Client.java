package milleborne.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Client {

	volatile private GUI 		_GUI = new GUI();
	volatile private Core		_Core = new Core();
	volatile private Network 	_Network = new Network();
	
	public Client() {
		
		_Network.setListenersForMsgReceived(_GUI);
		_GUI.setListenersForMsgToSend(_Network);
	}
	
	public void Run() throws Exception {
		
		_Core.Run();
		_GUI.Run();
			
		InitStartPanel();
		
		while (_GUI.isOver() == false && _Core.isOver() == false && _Network.isOver() == false)
		{
			
		}
		
		_Core.Stop();
		_GUI.Stop();
		_Network.Stop();
	}
	
	private void InitStartPanel() {
		ActionListener ListenerStart = new ActionListener() {
			public void actionPerformed(ActionEvent e) { 
				
				String Username = "Maxime";
				String IP = "127.0.0.1";
				int Port = 8889;
				
				if (Username.isEmpty() || IP.isEmpty() || Port == 0)
					_GUI._StartPanel.getErrorMsg().setText("Username or address IP invalid");
				else
				{
					_Network.setNetwork(IP, Port);
					if (_Network.tryConnect() == -1)
						_GUI._StartPanel.getErrorMsg().setText("Unable to connect to the server");
					else
						_GUI.DisplayLobby();
				}
			}  
		};
		_GUI._StartPanel.getButton().addActionListener(ListenerStart);
	}
}
