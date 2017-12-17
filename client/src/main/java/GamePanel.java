import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.*;

public class GamePanel {
	
	private JPanel			_Panel			= new JPanel(new BorderLayout());		
	public 	JPanel			getPanel() 		{ return _Panel; }

	private JPanel			_MySide			= new JPanel();
	private JPanel			_NorthSide		= new JPanel();
	private JPanel			_Field			= new JPanel();
	
	private ArrayList<msgToSendListener> listeners = new ArrayList<msgToSendListener>();

	GamePanel() {
		
		_Panel.add(_NorthSide, BorderLayout.PAGE_START);
		_NorthSide.setBackground(Color.BLUE);
		_NorthSide.setPreferredSize(new Dimension(0, 150));
		
		_Panel.add(_Field, BorderLayout.CENTER);
		_Field.setBackground(Color.PINK);
		_Field.setPreferredSize(new Dimension(200, 200));
		
		_Panel.add(_MySide, BorderLayout.PAGE_END);
		_MySide.setBackground(Color.BLACK);
		_MySide.setPreferredSize(new Dimension(0, 300));
	}

	public void sendMsg() {
		
	}

	public void addMsg(String msg) {
		
	}
	
	public void addListeners(msgToSendListener toAdd) {
		listeners.add(toAdd);
	}
}