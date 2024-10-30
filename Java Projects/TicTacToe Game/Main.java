import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println(" ");
        System.out.println("Starting at 1 1 and ending at 3 3, enter the row followed by a  space and then the collumn, here is a game of tic tac toe.");
        System.out.println(" ");
        Scanner tart = new Scanner(System.in);
        System.out.println("Enter 1 to start...");
        int newgame = tart.nextInt();
        if (newgame == 1){
            TicTacToe game = new TicTacToe();
            game.main(new String[0]);
        }
    }
}