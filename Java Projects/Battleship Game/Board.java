import java.util.*;

public class Board {
  Scanner scan = new Scanner(System.in);
  private int xLength;
  private int yLength;
  private int shipCounter = 5;
  private int opponentShipCounter = 5;
  private int hitCounter = 0;
  private int opponentHitCounter = 0;
// This constructor and the above variables provide the meat and bones of the Board object.
 public Board(int xLength, int yLength) {
    this.xLength = xLength;
    this.yLength = yLength;
    this.grid = new char[xLength][yLength];
}

  private Boolean hit = false;
  private Boolean gameOver = false;

// Getters and Setters

  public int getxLength() {
	return xLength;
}

public int getyLength() {
	return yLength;
}

public char[][] getGrid() {
	return grid;
}
// The methods below are called by the for loop in main to check whether to end the game.
public Boolean checkShips()
  {
    if (shipCounter < 1)
    {
      return true;
    }
    return false;
  }

  public Boolean checkOpponentShips()
  {
    if (opponentShipCounter < 1)
    {
      return true;
    }
    return false;
  }

  public Boolean checkHitCounter()
  {
    if (hitCounter >= 10)
    {
      return true;
    }
    return false;
  }
  public Boolean checkOpponentHitCounter()
  {
    if (hitCounter >= 10)
    {
      return true;
    }
    return false;
  }

// These 2D arrays serve as the game boards.
private char[][] grid = new char[5][5];
private char[][] opponentGrid = new char[5][5];
  private char[][] opponentShipGrid = new char[5][5];

  Ship[] ships = new Ship[5];
  Ship[] opponentShips = new Ship[5];

  // These arrays are of type Ship[]. They contain each side's 5 ships.

  // This method walks the user through placing their ships, as well as places the opponent's ships.
  public void placePieces() {
    for (int i = 0; i < 5; i++) {
        System.out.println("You have " + (5 - i) + " ships to place.");
        System.out.println("Please enter the 2D array coordinates at which you would like to place the ship. Do not separate with a comma. Ex. to place a ship at [1][2], input 12. Enter here: ");

        int shipLocation = -1;
        boolean isValidLocation = false;

        while (!isValidLocation) {
            if (scan.hasNextInt()) {
                shipLocation = scan.nextInt();
              int x = shipLocation / 10; // gets first index
              int y = shipLocation % 10; // gets second index
                scan.nextLine();

                if (x >= 0 && x <= 4 && y >= 0 && y <= 4) {
                    isValidLocation = true;
                } else {
                    System.out.println("Invalid input! Each index must be between 0 and 4. Please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid 2-digit integer.");
                scan.nextLine();
            }
        }

        System.out.println("Do you want the ship to be horizontal or vertical?: ");
        String direction = scan.nextLine();

        boolean isHorizontal = false;

        if (direction.equalsIgnoreCase("horizontal") || direction.equalsIgnoreCase("h")) {
            isHorizontal = true;
        }

        String shipName = "Ship " + (i + 1);
        ships[i] = new Ship(isHorizontal, 2, shipLocation);
    }
}





  // The methods below deal with setting the grids to 'O' values which means unaccompanied by a ship

  public void setGrid()
  {
    for (int k = 0; k < grid.length; k++) {
    for (int l = 0; l < grid[k].length; l++) {
        grid[k][l] = 'O';
    }
  }

    for (int i = 0; i < 5; i++)
      {
        int xy = ships[i].getShipLocation();
        int y = xy / 10;
        int x = xy % 10;

        grid[y][x] = 'S';


        if (ships[i].isHorizontal())
        {
          for (int j = 0; j < ships[i].getHealth(); j++)
          {
            if (y+j > 4 || x > 4)
            {
              break;
            }
            else
            {
              grid[x][y+j] = 'S';
            }

          }
        }
        else
        {
          for (int j = 0; j < ships[i].getHealth(); j++)
          {
            if (y+j > 4 || x > 4)
            {
              break;
            }
            else
            {
              grid[y+j][x] = 'S';
            }

          }
        }
      }
}

  public void printGrid()
  {
    int count = 0;
    for (int k = 0; k < grid.length; k++) {
    for (int l = 0; l < grid[k].length; l++) {
      if (count % 5 == 0)
      {
        System.out.println();
      }
        System.out.print(grid[l][k] + " ");
      count++;
    }
  }
    System.out.println();
  }

  public void hitGrid(int x, int y)
  {
    grid[x][y] = 'X';
  }

  public void setOpponentGrid(int x, int y)
  {
    if(hit)
    {
      opponentGrid[y][x] = 'S';
    }
    else
    {
      opponentGrid[y][x] = 'X';
    }
  }

  public void fillOpponentGrid()
  {
    for (int k = 0; k < grid.length; k++) {
    for (int l = 0; l < grid[k].length; l++) {
        grid[k][l] = 'O';
    }
  }
  }

  public void printOpponentGrid()
  {
    int count = 0;
    for (int k = 0; k < opponentGrid.length; k++) {
    for (int l = 0; l < opponentGrid[k].length; l++) {
      if (count % 5 == 0)
      {
        System.out.println();
      }
        System.out.print(opponentGrid[l][k] + " ");
      count++;
    }
  }
  }

  public void printOpponentShipGrid()
  {
    int count = 0;
    for (int k = 0; k < opponentGrid.length; k++) {
    for (int l = 0; l < opponentGrid[k].length; l++) {
      if (count % 5 == 0)
      {
        System.out.println();
      }
        System.out.print(opponentShipGrid[l][k] + " ");
      count++;
    }
  }
  }



  // The method below places the opponents ships.
  public void placeOpponentShips() {
    for (int k = 0; k < grid.length; k++) {
    for (int l = 0; l < grid[k].length; l++) {
        opponentShipGrid[k][l] = 'O';
    }

    Random rand = new Random();
    int count = 0;
    int combined = 0;
    while (count < 5) {
        int x = rand.nextInt(5);
        int y = rand.nextInt(5);
        boolean isHorizontal = rand.nextBoolean();
        if (isHorizontal) {
            if (x + 1 < xLength) {
                opponentShipGrid[x][y] = 'S';
                opponentShipGrid[x+1][y] = 'S';
                combined = x * 10 + y;
                opponentShips[count] = new Ship(isHorizontal, 2, combined);
                count++;
            }
        } else {
            if (y + 1 < yLength) {
                opponentShipGrid[x][y] = 'S';
                opponentShipGrid[x][y+1] = 'S';
              opponentShips[count] = new Ship(isHorizontal, 2, combined);
                count++;
            }
        }
    }
    for (int i = 0; i < grid.length; i++) {
    for (int j = 0; j < grid[k].length; j++) {
        opponentShipGrid[i][j] = 'O';
    }
    }
    // These values make testing/grading this project easier as you know where the opponent's ships are. To use the randomized algorith instead, delete the 10 lines below as well as the two for loops directly above this comment.
    opponentShipGrid[0][0] = 'S';
    opponentShipGrid[0][1] = 'S';
    opponentShipGrid[2][3] = 'S';
    opponentShipGrid[2][4] = 'S';
    opponentShipGrid[1][2] = 'S';
    opponentShipGrid[0][2] = 'S';
    opponentShipGrid[3][3] = 'S';
    opponentShipGrid[3][4] = 'S';
    opponentShipGrid[4][1] = 'S';
    opponentShipGrid[4][2] = 'S';
  }
}
  // The method below begins the game by announcing a commencement and then instructing the user to fire first.
  public void startGame()
  {
  for (int k = 0; k < opponentGrid.length; k++) {
    for (int l = 0; l < opponentGrid[k].length; l++) {
        opponentGrid[k][l] = 'O';

    }
  }
    placeOpponentShips();
    System.out.println();
    System.out.println("Commence the game!");
    System.out.println("Your opponent has placed their ships.");
    System.out.println("You will fire first.");
  }
  // The method below contains the core gameplay loop, in which the user fires at the opponent's fleet, and then the AI opponent fires back based on a randomized algorithm. This continues until the main calls the check methods that determine whether the game is over or not.
  public void play()
  {
    hit = false;
    System.out.println();
    System.out.println("Please enter the 2D array coordinates at which to fire on your opponent's fleet: ");

int target = -1;
    int x = -1;
    int y = -1;
boolean isValidTarget = false;

        while (!isValidTarget) {
            if (scan.hasNextInt()) {
                target = scan.nextInt();
              y = target / 10; // gets first index
              x = target % 10; // gets second index
                scan.nextLine();

                if (x >= 0 && x <= 4 && y >= 0 && y <= 4) {
                    isValidTarget = true;
                } else {
                    System.out.println("Invalid input! Each index must be between 0 and 4. Please try again.");
                }
            } else {
                System.out.println("Invalid input! Please enter a valid 2-digit integer.");
                scan.nextLine();
            }
        }

    if (opponentShipGrid[y][x] == 'S')
    {
      hit = true;
      System.out.println("Hit!");
      hitCounter++;

      for (int i = 0; i < ships.length; i++)
        {
          if(opponentShips[i].getShipLocation() == target)
          {
            opponentShips[i].setHealth();
            if (ships[i].getHealth() == 0)
            {
              System.out.println("An opponent ship has been sunk!");
              opponentShipCounter--;
            }
          }
        }
    }
    else
    {
      System.out.println("You missed your opponent's ships.");
    }
    setOpponentGrid(y, x);

    Random random = new Random();
   Random rand = new Random();
        int randomNumber = random.nextInt(5);
    int randomNumber2 = random.nextInt(5);

    String combinedString = String.valueOf(randomNumber) + String.valueOf(randomNumber2);
int combinedNumber = Integer.parseInt(combinedString);

    System.out.println("Your opponent fired at [" + randomNumber + "][" + randomNumber2 + "]");
    if (grid[randomNumber][randomNumber2] == 'S')
    {
      System.out.println("You have been hit!");
      opponentHitCounter++;
      for (int i = 0; i < ships.length; i++)
        {
          if(ships[i].getShipLocation() == combinedNumber)
          {
            ships[i].setHealth();
            if (ships[i].getHealth() == 0)
            {
              System.out.println("Your ship has been sunk!");
              shipCounter--;
            }
          }
        }

    }
    else
    {
      System.out.println("This was a miss.");
    }

    System.out.println("This board displays where you have fired.");
      printOpponentGrid();

      }
  }
