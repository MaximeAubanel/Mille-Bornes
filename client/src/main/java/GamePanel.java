import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.imageio.ImageIO;
import javax.swing.*;

public class GamePanel {
	
	private JPanel			_Panel			= new JPanel(new BorderLayout());		
	public 	JPanel			getPanel() 		{ return _Panel; }

	private JPanel			_NorthSide		= new JPanel();	
	private JPanel			_Field			= new JPanel();

	private JPanel			_MySide			= new JPanel(new BorderLayout());
	private JPanel			_MyCard			= new JPanel();
	private JPanel			_MyInfo			= new JPanel();
	
	private ImageIcon		_Card_25		= null;

	private ArrayList<msgToSendListener> listeners = new ArrayList<msgToSendListener>();

	public GamePanel() {
		loadRessources();
		loadMySide();
		
		_Panel.add(_NorthSide, BorderLayout.PAGE_START);
		_NorthSide.setBackground(Color.BLUE);
		_NorthSide.setPreferredSize(new Dimension(0, 75));
		
		_Panel.add(_Field, BorderLayout.CENTER);
		_Field.setBackground(Color.PINK);
		
		_Panel.add(_MySide, BorderLayout.PAGE_END);

		showPicture(_Card_25);
		showPicture(_Card_25);
		showPicture(_Card_25);
		showPicture(_Card_25);
		showPicture(_Card_25);
		showPicture(_Card_25);
		showPicture(_Card_25);
		showPicture(_Card_25);
	}

	public void loadMySide() {
		_MySide.setBackground(Color.BLACK);
		_MySide.setPreferredSize(new Dimension(0, 275));

		_MyCard.setPreferredSize(new Dimension(0, 240));
		_MySide.add(_MyCard, BorderLayout.PAGE_END);
		
		_MyInfo.setBackground(Color.RED);
		_MyInfo.setPreferredSize(new Dimension(0, 35));
		_MySide.add(_MyInfo, BorderLayout.PAGE_START);
	}
	
	public void loadRessources() {
		try {
			_Card_25 = new ImageIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("25.jpg"))).getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT));
			// more card
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void showPicture(ImageIcon pic) {
		JLabel picLabel = new JLabel(pic);
		_MyCard.add(picLabel);
	}
	
	public void sendMsg() {
		
	}

	public void addMsg(String msg) {
		
	}
	
	public void addListeners(msgToSendListener toAdd) {
		listeners.add(toAdd);
	}
}