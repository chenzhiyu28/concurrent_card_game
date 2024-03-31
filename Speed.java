/*
Name: Zhiyu Chen
Student Number: 45714568
 */

public class Speed {
    public static void main(String[] args) {
        Game game = new Game();

        Player1 player1 = new Player1(game);
        Player2 player2 = new Player2(game);

        game.start();
        player1.start();
        player2.start();
    }
}

