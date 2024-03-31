import java.util.Random;

/*
Name: Zhiyu Chen
Student Number: 45714568
 */


/**
 * Keep tracking the game status and provide public information for both players to observe.
 */

public class Game extends Thread{
    /* The last player who enters the critical section */
    volatile int last = 1;
    /* Process p wants to enter critical section */
    volatile boolean wantp = false;
    /* Process q wants to enter critical section */
    volatile boolean wantq = false;
    /* public piles */
    volatile Card pile1Card = new Card(new Random());
    volatile Card pile2Card = new Card(new Random());
    /* record whether game ends. */
    volatile boolean isOver = false;
    /* records whether draw happens */
    volatile boolean player1Stuck = false;
    volatile boolean player2Stuck = false;

    /**
     * update pile1 cards
     * @param newCard - the new card to be replaced
     */
    public void updatePile1Card(Card newCard){
        this.pile1Card = newCard;
    }

    /**
     * update pile2 cards
     * @param newCard - the new card to be replaced
     */
    public void updatePile2Card(Card newCard){
        this.pile2Card = newCard;
    }

    /**
     *
     * @param player - the player who win the game
     */
    public void playerWins(Player player) {
        int number = player.getNumber();
        this.isOver = true;

        Event.PlayerWins(number);
    }

    /**
     * Execute when game reaches a draw.
     */
    public void gameDraw() {
        Event.GameEndsAsDraw();
    }

    public void run() {
        while (!isOver) {
            if (player1Stuck && player2Stuck) {
                gameDraw();
                isOver = true;
            }
        }
    }


}
