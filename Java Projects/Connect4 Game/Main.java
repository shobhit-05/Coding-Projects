import java.util.*;

public class Main {
  //Creates the character for the 2 players that will be
  //playing the game.
  private static final char[] PLAYERS = {'X', 'O'};

  public static void main(final String[] args) {

    //Prints the rules and instructions for the game.
    System.out.println("\n\nGame Rules: \n1. Player 1 is \'X\' and Player 2 is \'O\'. Player 1 has first turn. \nIn order to win a 4 pieces can be made in a horizontal, vertical, diagonal. Diagonal can also be in any direction. \n\n");

    Scanner input = new Scanner(System.in);
    //declares the basic parameters of the board.
    int height = 6;
    int width = 7;
    int moves = height * width;

    //Creates the board for the game
    Connect4 board = new Connect4(width, height);

    //explains how to choose where to put the piece
    //Prompts the user for the input
    System.out.println("Use 0-" + (width - 1) + " to choose a column \n");

    System.out.println(board);

    //repeats the method till there is no more moves to play
    //or the game has been won by a player.
    //Also accounts for the switching of turn in each turn.
    for (int player = 0; moves-- > 0; player = 1 - player) {

      char symbol = PLAYERS[player];

      //runs the method that implements the move entered
      //by the user.
      board.chooseAndDrop(symbol, input);


      System.out.println(board);

      //Checks if a player has won the game. If player won
      //the game then the game ends and winning player's name
      //is returned. If the game is not over the loop
      //keeps running.
      if (board.isWinningPlay()) {
        System.out.println("\nPlayer " + symbol + " wins!");
        return;
      }
    }
    System.out.println("Game over. No Winner. Try again!");
  }
}