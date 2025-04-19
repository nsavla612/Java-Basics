// Make it singleton
public class BlackJackManager {
    Deck deck;
    ScoreManager scoreManager;
    GameState state;
    BlackJackManager() {
        deck = new Deck();
        scoreManager = new ScoreManager();
        GameState state = GameState.NOT_STARTED;
    }

    public  void startGame(Player player, Player dealer) {
        System.out.println( " Blackjack game has started ");
        state = GameState.IN_PROGRESS;
        deck.reset();
        initializeHands(player, dealer);
        playGame(player, dealer);
    }

    public void playGame(Player player, Player dealer) {
        System.out.println( " ");
        System.out.println( " Playing Game ");
        while(state != GameState.ENDED) {
            int currentPlayerScore = scoreManager.calculateMaximumScoreLessThan21(player);
            System.out.println( " currentPlayerScore- " + currentPlayerScore);
            if(currentPlayerScore <= 15) {
                System.out.println( " currentPlayerScore is less or equal to 15");
                player.pickCard(deck);
            } else if(currentPlayerScore > 21) {
                System.out.println( " currentPlayerScore is more than 21 - Busted");
                state = GameState.ENDED;
            } else {
                decideWinner(  player,  dealer);
                state = GameState.ENDED;
            }

        }
    }

    public void initializeHands(Player player, Player dealer) {
        System.out.println( " Adding first card to dealer ");
        dealer.pickCard(deck);
        System.out.println( " Adding second card to dealer ");
        dealer.pickCard(deck);
        System.out.println( " Adding first card to player ");
        player.pickCard(deck);
    }

    public void decideWinner( Player player, Player dealer) {
        System.out.println( " deciding winner between player and dealer ");
        int dealerScore = scoreManager.calculateMaximumScoreLessThan21(dealer);
        System.out.println( " dealerScore-  " + dealerScore);
        int playerScore = scoreManager.calculateMaximumScoreLessThan21(player);
        System.out.println( " playerScore-  " + playerScore);

        if(dealerScore > 21) {
            System.out.println( " Congrats. Player won. ");
        } else if(playerScore > 21) {
            System.out.println( " Sorry. Dealer won. ");
        } else if(dealerScore > playerScore) {
            System.out.println( " Sorry. Dealer won. ");
        } else {
            System.out.println( " Congrats. Player won. ");
        }

    }
}
