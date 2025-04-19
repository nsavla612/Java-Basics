import java.util.ArrayList;
import java.util.List;

public class Player {
    String name;
    List<Card> cardsInHand;
    int maximumScorePossibleLessThan21;
    Player( String name) {
        this.name = name;
        cardsInHand = new ArrayList<>();
    }


    public void pickCard(Deck deck) {
       System.out.println( name + " is picking a card ");
       this.cardsInHand.add( deck.getRandomCard());
    }


}
