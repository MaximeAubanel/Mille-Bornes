import java.awt.CardLayout;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class GUI implements msgReceivedListener {

	final static String STARTPANEL 		= "startpanel";
	final static String LOBBYPANEL 		= "lobbypanel";
	final static String GAMEPANEL 		= "gamepanel";
	final static String ENDPANEL 		= "endpanel";
	
	private	JFrame		_MainWindow 	= new JFrame();
	private CardLayout	_MainLayout		= new CardLayout();
	private JPanel	 	_MainPanel 		= new JPanel(_MainLayout);
	
	public StartPanel	_StartPanel 	= new StartPanel();
	public LobbyPanel	_LobbyPanel 	= new LobbyPanel();
	public GamePanel	_GamePanel 		= new GamePanel();
	
	
	public GUI() {
		InitWindow();
		InitPanelLayout();
		
		DisplayStart();
	}
	
	public void setListenersForMsgToSend(msgToSendListener toAdd) {
		_LobbyPanel.addListeners(toAdd);
	}
	
	public void Run() {
		
	}
	
	public void Stop() {
		_MainWindow.dispose();
	}
	
	private void InitWindow() {
		_MainWindow.setTitle("Mille Borne");
		_MainWindow.setSize(550, 700);
		_MainWindow.setResizable(false);
		_MainWindow.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		//_MainWindow.setLocationRelativeTo(null);
		_MainWindow.setVisible(true);
	}
	
	private void InitPanelLayout() {
		_MainWindow.add(_MainPanel);
		
		_MainPanel.add(_StartPanel.getPanel(), STARTPANEL);
		_MainPanel.add(_LobbyPanel.getPanel(), LOBBYPANEL);
		_MainPanel.add(_GamePanel.getPanel(), GAMEPANEL);
		//_MainPanel.add(ENDPANEL);
	}
	
	public void DisplayStart() {
		_MainLayout.show(_MainPanel, STARTPANEL);
	}
	
	public void DisplayLobby() {
		_MainLayout.show(_MainPanel, LOBBYPANEL);
	}

	public void DisplayGame() {
		_MainLayout.show(_MainPanel, GAMEPANEL);
	}
	
	public boolean isOver() {
		if (_MainWindow.isVisible() == true) {
			return (false);
		}
		return (true);
	}
	
	@Override
	public void messageReceived(String msg) {
		_LobbyPanel.addMsg(msg);
	}

	@Override
	public void changeState(String newState) {
		if (newState.compareTo(Global.STATE_GAME) == 0)
			DisplayGame();
		else if (newState.compareTo(Global.STATE_LOBBY) == 0)
			DisplayLobby();
//		else if (newState.compareTo(Global.STATE_START) == 0)
	//		DisplayStart();
//		else if (newState.compareTo(Global.STATE_END) == 0)
	//		DisplayStart();

	}	
}