// Welcome to Battleship!

import java.util.*;

class Main {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    System.out.println("---------------");
    System.out.println("  Battleship");
    System.out.println("---------------");
    System.out.println();
    System.out.println("Welcome to Battleship!");
    System.out.println("The board is 5x5.");

    Board board = new Board(5, 5);
// These methods from the board class all serve various purposes as indicated by their name, in order to run the steps of the game in their specific order.
    board.placePieces();
    board.setGrid();
    board.printGrid();
    System.out.println();
    System.out.println("O represents a space unoccupied by a ship, while an S represents a space that a ship occupies. X represents a space that has been fired at but is not occupied by a ship.");
    board.startGame();

    // 25 is the maximum amount of possible turns on a 5x5 grid, however 30 is used to account for misinputs.
    // This for loop checks whether all ships have been hit and sunk to end the game accordingly to which side lost.
    for (int i = 0; i < 30; i++) {
      board.play();

      if (board.checkHitCounter())
      {
        System.out.println();
        System.out.println("Your opponents fleet has been sunk! You Win!");
        System.out.println("Here was your opponent's board:");
        board.printOpponentShipGrid();
        break;
      }
      else if (board.checkOpponentHitCounter())
      {
        System.out.println();
        System.out.println("Game Over! Your ships have been sunk...");
        System.out.println("Here was your opponent's board:");
        board.printOpponentShipGrid();
        break;
      }
    }
  }
}