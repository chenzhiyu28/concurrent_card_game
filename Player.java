import java.util.ArrayList;
import java.util.Random;

/*
Name: Zhiyu Chen
Student Number: 45714568
 */

public class Player extends Thread {
    /* shared game instance for players. */
    Game game;
    /* the number of this player. */
    int number;
    /* remaining number of cards in the pile. */
    int cardsInPile = 29;
    /* cards in players hand. */
    ArrayList<Card> myCards = new ArrayList<>();

    /**
     * instantiate the player with the game instance it belongs to.
     * @param game - the game instance that the player engages.
     */
    public Player(Game game, int number) {
        this.game = game;
        this.number = number;
        myCards.add(new Card(new Random()));
        myCards.add(new Card(new Random()));
        myCards.add(new Card(new Random()));
    }

    public int getNumber() {
        return this.number;
    }

    /**
     * Pick up a card if the number of myCards is less than 3.
     */
    public void pickUpCard() {
        if (this.cardsInPile > 0) {
            Card newCard = new Card(new Random());

            this.cardsInPile -= 1;
            this.myCards.add(newCard);

            Event.PlayerPicksNewCard(this.number, newCard);
        } else if (this.cardsInPile == 0 && myCards.isEmpty()) {
            this.win();
        }
    }

    /**
     * place the card in hand to the public pile
     * @param card - the card played by the player
     */
    public void placeCard1(Card card) {
        this.myCards.remove(card);
        this.game.updatePile1Card(card);

        Event.PlayerPlacesCard(this.number, 1, card);
    }

    /**
     * place the card in hand to the public pile
     * @param card - the card played by the player
     */
    public void placeCard2(Card card) {
        this.myCards.remove(card);
        this.game.updatePile2Card(card);

        Event.PlayerPlacesCard(this.number, 2, card);
    }

    /**
     * player wins the game
     */
    public void win() {
        this.game.playerWins(this);
    }

    /**
     * check whether player could place card
     * @return the card index of player
     */
    public Card checkPile1() {
        for (Card card : this.myCards) {
            if (card.matches(game.pile1Card)) {
                return card;
            }
        }
        return null;
    }

    /**
     * check whether player could place card
     * @return the card index of player
     */
    public Card checkPile2() {
        for (Card card : this.myCards) {
            if (card.matches(game.pile2Card)) {
                return card;
            }
        }
        return null;
    }
}
