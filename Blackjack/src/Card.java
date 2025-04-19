// Make it Builder
public class Card {
    public Suit suit;
    public int value;
    public boolean isFace;
    Card(Suit suit, int value) {
        this.suit = suit;
        this.value = Math.min(value, 10);
        isFace = value <= 10;
    }
}
