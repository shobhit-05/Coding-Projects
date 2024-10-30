import java.util.Scanner;

public class TicTacToe {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    char[][] board = new char[3][3];
    boolean gameOver = false;
    char currentPlayer = 'X';

    // creates the board
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        board[i][j] = ' ';
      }
    }

    while (!gameOver) {

      System.out.println("-------------");
      for (int i = 0; i < 3; i++) {
        System.out.print("| ");
        for (int j = 0; j < 3; j++) {
          System.out.print(board[i][j] + " | ");
        }
        System.out.println();
        System.out.println("-------------");
      }

      // asks player for move
      System.out.println("Player " + currentPlayer + ", please enter your move - row (1-3) then column (1-3):");
      int row = scanner.nextInt() - 1;
      int col = scanner.nextInt() - 1;

      if (row < 0 || row > 2 || col < 0 || col > 2 || board[row][col] != ' ') {
        System.out.println("Invalid move. Please try again.");
      } else {

        board[row][col] = currentPlayer;

        // winning condition
        if (checkWin(board, currentPlayer)) {

          System.out.println("Player " + currentPlayer + " wins!");
          gameOver = true;
        } else if (checkTie(board)) {
          System.out.println("It's a tie!");
          gameOver = true;
        } else {
          // switches players
          currentPlayer = currentPlayer == 'X' ? 'O' : 'X';
        }
      }

    }

    scanner.close();
  }

  // another winning condition
  private static boolean checkWin(char[][] board, char player) {
    // Check rows
    for (int i = 0; i < 3; i++) {
      if (board[i][0] == player && board[i][1] == player && board[i][2] == player) {
        return true;
      }
    }

    // columns
    for (int j = 0; j < 3; j++) {
      if (board[0][j] == player && board[1][j] == player && board[2][j] == player) {
        return true;
      }
    }

    // diagonals
    if (board[0][0] == player && board[1][1] == player && board[2][2] == player) {
      return true;
    }
    if (board[0][2] == player && board[1][1] == player && board[2][0] == player) {
      return true;
    }

    return false;
  }

  // tie condition
  private static boolean checkTie(char[][] board) {
    for (int i = 0; i < 3; i++) {
      for (int j = 0; j < 3; j++) {
        if (board[i][j] == ' ') {
          return false;
        }
      }
    }
    return true;
  }
}