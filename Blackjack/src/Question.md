Question - 
Design Blackjack OOD

Clarifying Questions - 
1. We need to design Generic Card
2. Extend the card to have blackjack.
3. Game should be playable.
4. Concurrency should be there

Entities - 
Card, Suit
Game, Deck, Player, Score

Relationships - 
Card has a value and suit.
Deck has 52 cards and keeps track of which card is available in deck.
Player can start a game.
Player can draw card.
Game keeps score.

Design Decisions - 
GameManager to be singleton - only one instance
