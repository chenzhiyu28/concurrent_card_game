/*
Name: Zhiyu Chen
Student Number: 45714568
 */

public class Player1 extends Player {
    /**
     * instantiate the player with the game instance it belongs to.
     *
     * @param game - the game instance that the player engages.
     */
    public Player1(Game game) {
        super(game, 1);
    }

    public void run() {
        while (!game.isOver) {

            /* non-critical section */
            game.wantp = true;
            game.last = 1;

            /* return the index of card if matches, otherwise null */
            Card cardForPile1 = this.checkPile1();
            Card cardForPile2 = this.checkPile2();



            /* only enter critical section while player could act, otherwise skip */
            if ((cardForPile1 != null) || (cardForPile2 != null)) {
                this.game.player1Stuck = false;
                while (game.wantq && (game.last == 1)) {
                    Thread.yield();
                }

                // System.out.println("P1 remaining cards in pile: " + cardsInPile);

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
                this.game.player1Stuck = true;
            }

            /* exit critical section */
            game.wantp = false;
        }
    }
}
