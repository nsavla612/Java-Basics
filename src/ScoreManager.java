import java.util.List;

// Make it singleton
public class ScoreManager {
    public int calculateScore(Player dealer) {
        System.out.println(" Calculating score of player ");
        int score = 0;
        for(Card card : dealer.cardsInHand) {
            score += card.value;
        }
        dealer.maximumScorePossibleLessThan21 = score;
        return  score;
    }

    public int calculateMaximumScoreLessThan21(Player player) {
        System.out.println(" Calculating max possible score of " + player.name + " less than 21 ");
        return calculateMaximumScoreLessThan21(player.cardsInHand, 0 , 0);
    }

    public int calculateMaximumScoreLessThan21(List<Card> cards, int cardIndex, int score) {
        if(cardIndex >= cards.size()) return score;
        if(score > 21) return score;

        if(cards.get(cardIndex).value == 1) {
            return Math.max(
                    calculateMaximumScoreLessThan21(cards, cardIndex + 1, score + 1 ),
                    calculateMaximumScoreLessThan21(cards, cardIndex + 1, score + 11)
            );
        } else {
            return calculateMaximumScoreLessThan21(cards, cardIndex + 1, score + cards.get(cardIndex).value);
        }
    }
}
