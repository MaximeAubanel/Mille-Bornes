import java.util.ArrayList;

public class DeckCard {
	
	private ArrayList<Card>	_List = new ArrayList<Card>();
	
	public DeckCard() {
		
	}
	
	public Card getCard(String name) {
		for (Card a : _List) {
			if (a.getName().compareTo(name) == 0)
				return (a);
		}
		return null;
	}
	
	public void add(Card newCard) {
		_List.add(newCard);		
	}
	
	public ArrayList<Card> getArray() { return _List; }
	
	public void remove(Card newCard) {
		_List.remove(newCard);
	}
}
