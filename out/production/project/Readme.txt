Zhiyu Chen 45714568

Class List:
1.	Player.java: This class extends thread to simulate players in the game, it provides all functionalities required to play in a game such as place cards, pick up cards. It is coupled with the game instance to achieve volatile.
2.	Player1.java: This class extends Player class to perform the role of the first player in the game, it implements run( ) method for starting the thread and applies Peterson’s algorithm to ensure the correctness during concurrency.
3.	Player2.java: Similar to Player1.java, this class is acting as the second player in the game and also applies Peterson’s algorithm for correctness.
4.	Game.java: This class extends thread to simulate the game instance. It contains a variety of volatile variables which represents game status for both player threads to achieve. It also provides interfaces to update pile cards and change winning or draw status.
5.	Speed.java : This class is the launcher of the game, it runs three threads for players and game instances. 

About concurrency correctness: This game applies Peterson’s algorithm. Both players check volatile variables for pile status before they enter critical section and double check that pile again before placing cards. This ensures that only one player is placing the card and their behavior must be legal. 

About programming approach: The loop for players is controlled by the status of game. Both players would terminate automatically when the game reaches a draw or winning. The game instance is for gaming environment and game thread is for controlling game and players. 
