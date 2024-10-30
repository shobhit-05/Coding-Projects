import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Connect4{
  private final int width;
  private final int height;
  //2d array for the board
  private final char [][] grid;
  //Row and column for the board
  private int lastCol = -1;
  private int lastTop = -1;

  //Initializes the board and creates it.
  public Connect4(int w, int h){
    width = w;
    height = h;
    grid = new char[h][];

    //Fills the blank array with a symbol
    for (int i = 0; i < h; i++){
      Arrays.fill(grid[i] = new char[w], '.');
    }
  }

  //returns and represents the board.
  //Using Streams to make it more concise.
  public String toString() {
    return IntStream.range(0, width).
        mapToObj(Integer::toString).
        collect(Collectors.joining())
        + "\n" +
        Arrays.stream(grid).
        map(String::new).
        collect(Collectors.joining("\n"));
  }

  //Returns the row that the last player played in.
  public String horizontal(){
    return new String (grid[lastTop]);
  }

  //returns the column that the last player played in.
  public String vertical(){
    StringBuilder sb = new StringBuilder(height);

    for (int h = 0; h < height; h++){
      sb.append(grid[h][lastCol]);
    }

    return sb.toString();
  }

  //returns the '/' position that was played by the last player
  public String slashDiagonal(){
    StringBuilder sb = new StringBuilder(height);

    for (int h = 0; h < height; h++){
      int w = lastCol + lastTop - h;
      if(0 <= w && w < width) {
        sb.append(grid[h][w]);
      }
    }
    return sb.toString();
  }

  //returns the '\' position that was played by the last player
  public String backSlashDiagonal(){
    StringBuilder sb = new StringBuilder(height);

    for (int h = 0; h < height; h++){
      int w = lastCol - lastTop + h;
      if(0 <= w && w < width) {
        sb.append(grid[h][w]);
      }
    }
    return sb.toString();
  }

  //checks if 'str' is in the substring
  public static boolean contains(String str, String substring){
    return str.indexOf(substring) >= 0;
  }

  //keeps running the method till the game is won.
  //Checks whether a player has won the game or not.
  public boolean isWinningPlay(){
    //checks to see if there is a valid input that has been entered.
    if (lastCol == -1){
      System.err.println("Move hasn't been made yet");
      return false;
    }

    char sym = grid[lastTop][lastCol];
    //checks for the streak
    String streak = String.format("%c%c%c%c", sym, sym, sym, sym);

    //Checks if connect 4 has been made using streak.
    return contains(horizontal(), streak) ||
           contains(vertical(), streak) ||
           contains(slashDiagonal(), streak) ||
           contains(backSlashDiagonal(), streak);
  }

  //Asks the user for the place they want to move at next.
  //Keeps asking if a valid input isn't entered.
  public void chooseAndDrop(char symbol, Scanner input) {
    //A type of while loop that does something till the condition is true
    do {
      System.out.println("\nPlayer " + symbol + " turn: ");
      int col = input.nextInt();

      //checks if the input is valid
      if(!(0 <= col && col < width)){
        System.out.println("Column must be between 0 and " + (width - 1));
        continue;
      }

      //Adds the symbol in the first available row of the column
      //chose by the user.
      for (int h = height - 1; h >= 0; h--){
        if(grid[h][col] == '.'){
          grid[lastTop = h][lastCol = col] = symbol;
          return;
        }
      }

      //Checks to see if the column is full.
      //if the column is full, repeats the method for user to
      //enter an another input.
      System.out.println("Column " + col + " is full.");
    } while(true);
  }
}