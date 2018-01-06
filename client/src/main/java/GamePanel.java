import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
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
	
	private DeckCard		_CARDS	 		= new DeckCard();	// full deck

	private JPanel			_NorthSide		= new JPanel();	
	private JPanel			_Field			= new JPanel();

	private JPanel			_MySide			= new JPanel(new BorderLayout());
	private JPanel			_MyInfo			= new JPanel();

	private JPanel			_MyCard			= new JPanel(); 	//visual	
	private DeckCard		_MyDeck	 		= new DeckCard();	//intern
	
	private ArrayList<msgToSendListener> listeners = new ArrayList<msgToSendListener>();
	//private Timer 			_Timer = new Timer();
	
	public GamePanel() {
		InitCards();
		//NORTH
		_Panel.add(_NorthSide, BorderLayout.PAGE_START);
		_NorthSide.setBackground(Color.BLUE);
		_NorthSide.setPreferredSize(new Dimension(0, 75));
		//FIELD
		_Panel.add(_Field, BorderLayout.CENTER);
		_Field.setBackground(Color.PINK);
		//MYSIDE
		_Panel.add(_MySide, BorderLayout.PAGE_END);

		loadMySide();
		
		
		addCardToMyDeck(_CARDS.getCard("25"));
	}

	public void displayMyDeck() {
		for (Card card : _MyDeck.getArray()) {
			JLabel picLabel = new JLabel(card.getImg());
			card.setLabel(picLabel);
			_MyCard.add(picLabel);
		}
	}
	
	public void addCardToMyDeck(Card Card) {
		_MyDeck.add(Card);
		displayMyDeck();
	}
	
	public void removeCardFromMyDeck(Card Card) {
		_MyDeck.remove(Card);
		_MyCard.remove(Card.getLabel());
	}
	
	public void refresh() {
		_Panel.revalidate();
		_Panel.repaint();
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
		
	public void sendMsg() {
		
	}

	public void addMsg(String msg) {
		
	}
	
	public void addListeners(msgToSendListener toAdd) {
		listeners.add(toAdd);
	}
	
	// faire un loader
	public void InitCards() {
		try {
			_CARDS.add(new Card(new ImageIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("25.jpg"))).getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT)), "25"));
			//_CARDS.add(new Card(new ImageIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("50.jpg"))).getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT)), "25"));
			//_CARDS.add(new Card(new ImageIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("75.jpg"))).getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT)), "25"));
			//_CARDS.add(new Card(new ImageIcon(new ImageIcon(ImageIO.read(Thread.currentThread().getContextClassLoader().getResource("100.jpg"))).getImage().getScaledInstance(200, 240, Image.SCALE_DEFAULT)), "25"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}