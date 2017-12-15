package milleborne.client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.*;

public class LobbyPanel {
	
	private JPanel			_Panel			= new JPanel(new BorderLayout());	
	private JTextPane		_TextPane 		= new JTextPane();
	private JTextField		_UserInput		= new JTextField();
	private Document		_Document		= _TextPane.getDocument();
	
	public 	JPanel			getPanel() 		{ return _Panel; }

	private ArrayList<msgToSendListener> listeners = new ArrayList<msgToSendListener>();

	LobbyPanel() {
		
		_TextPane.setEditable(false);
		_Panel.add(_TextPane, BorderLayout.CENTER);
	
		JPanel pan = new JPanel(new BorderLayout());
		_Panel.add(pan, BorderLayout.PAGE_END);
		
		JButton button = new JButton("Send");
		pan.add(button, BorderLayout.SOUTH);	
		
		pan.add(_UserInput, BorderLayout.NORTH);	
		   
		JPanel user = new JPanel(new BorderLayout());
		_Panel.add(user, BorderLayout.EAST);
		
		JLabel usertitle = new JLabel("Online User");
		user.add(usertitle, BorderLayout.NORTH);
		
		JTextPane userpan = new JTextPane();
		user.add(userpan, BorderLayout.SOUTH);
			
		ActionListener ListenerStart = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String UserInput = _UserInput.getText().trim();
				if (UserInput.isEmpty() == false)
				{
					_UserInput.setText("");
					for (msgToSendListener x : listeners)
						x.sendMsg(UserInput);
				}

			}  
		};
		button.addActionListener(ListenerStart);
	}
	
	public void addMsg(String msg) {
		if (msg != null) {
			try {
				_Document.insertString(_Document.getLength(), msg + "\n", null);
			} catch (BadLocationException ex) {
				ex.printStackTrace();
			}
		}
	}
	
	public void addListeners(msgToSendListener toAdd) {
		listeners.add(toAdd);
	}

	
}