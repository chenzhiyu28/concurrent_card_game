/*
Name: Zhiyu Chen
Student Number: 45714568
 */

public class Player2 extends Player {

    /**
     * instantiate the player with the game instance it belongs to.
     *
     * @param game - the game instance that the player engages.
     */
    public Player2(Game game) {
        super(game, 2);
    }

    public void run() {
        while (!game.isOver) {

            /* non-critical section */
            game.wantq = true;
            game.last = 2;

            /* return the index of card if matches, otherwise -1 */
            Card cardForPile1 = this.checkPile1();
            Card cardForPile2 = this.checkPile2();



            /* only enter critical section while player could act, otherwise skip */
            if ((cardForPile1 != null) || (cardForPile2 != null)) {
                this.game.player2Stuck = false;
                while (game.wantp && (game.last == 2)) {
                    Thread.yield();
                }

                // System.out.println("P2 remaining cards in pile: " + cardsInPile);

                /* critical section */

                if (cardForPile1 != null) {
                    /* check again before placing a card into the pile */
                    if (cardForPile1.matches(game.pile1Card) && !this.game.isOver) {
                        /* place card and draw a new card */
                        this.placeCard1(cardForPile1);
                        this.pickUpCard();
                    }
                } else {
                    /* check again before placing a card into the pile */
                    if (cardForPile2.matches(game.pile2Card) && !this.game.isOver) {
                        /* place card and draw a new card */
                        this.placeCard2(cardForPile2);
                        this.pickUpCard();
                    }
                }
            } else {
                this.game.player2Stuck = true;
            }
            /* exit critical section */
            game.wantq = false;
        }
    }
}
