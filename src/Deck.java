import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// // Make it Builder
public class Deck {
    List<Card> availableCards;
    List<Card> unavailableCards;
    public Deck() {
        initializeDeck();
    }

    public void initializeDeck() {
        availableCards = new ArrayList<>();
        for(int i = 1 ; i <= 13 ; i++)
        {
            availableCards.add( new Card(Suit.CLUBS, i ));
            availableCards.add( new Card(Suit.DIAMONDS, i ));
            availableCards.add( new Card(Suit.HEART, i ));
            availableCards.add( new Card(Suit.SPADES, i ));
        }
    }

    public void shuffle() {
        // Randomize all available cards.
    }

    public void reset() {
        System.out.println( " Resetting all cards in deck");
        initializeDeck();
    }

    public Card getRandomCard() {
        Random random = new Random();
        int pickIndex = random.nextInt(availableCards.size());
        System.out.println( " Picking a random index with index - " + pickIndex + " from size- " + availableCards.size());

        Card pickedCard = availableCards.get(pickIndex);
        availableCards.remove(pickedCard);
        System.out.println( " Card picked is - " + pickedCard.value + " of " +  pickedCard.suit);
        return pickedCard;
    }
}
