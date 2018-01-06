import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Card {
	private ImageIcon		_Img		= null;
	private String 			_Name		= null;
	private JLabel			_Label		= null;
	
	public ImageIcon getImg() { return _Img; }
	public String getName() { return _Name; }
	public void setLabel(JLabel newLabel) { _Label = newLabel; }
	public JLabel getLabel() { return _Label; }
	
	public Card(ImageIcon img, String name) {
		_Img = img;
		_Name = name;
	}
}
