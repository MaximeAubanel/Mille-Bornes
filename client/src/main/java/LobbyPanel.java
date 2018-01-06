import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Dimension;

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
	private	static String	_Header 		= "[0x1]";
	
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
		user.setPreferredSize(new Dimension(150, 0));
		_Panel.add(user, BorderLayout.EAST);
		
		JLabel usertitle = new JLabel("Online User");
		usertitle.setHorizontalAlignment(JLabel.CENTER);
		user.add(usertitle, BorderLayout.NORTH);
		
		JTextPane userpan = new JTextPane();
		user.add(userpan, BorderLayout.SOUTH);
	
		_UserInput.addKeyListener(new KeyAdapter() {
			public void keyPressed(KeyEvent e)
		    	{
		        	if (e.getKeyCode() == KeyEvent.VK_ENTER) {
		        		sendMsg();
		        	}
		      }
		});
		
		ActionListener ListenerStart = new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				sendMsg();
			}  
		};
		button.addActionListener(ListenerStart);
	}
	
	
	private void sendMsg() {
		String UserInput = _UserInput.getText().trim();
		if (UserInput.isEmpty() == false) {
			_UserInput.setText("");
			for (msgToSendListener x : listeners)
				x.sendMsg(_Header + UserInput);
		}
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