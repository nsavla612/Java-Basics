public class BlackJackGame {
    public static void main(String[] args) {
        Player player1 = new Player("Player1");
        Player dealer = new Player("Dealer");
        BlackJackManager blackJackManager = new BlackJackManager();
        blackJackManager.startGame(player1, dealer);
    }
}
