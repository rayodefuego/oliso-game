package oliso;

import java.util.Scanner;

public class ConsoleUI {

  private Scanner scanner;

  public ConsoleUI() {
    scanner = new Scanner(System.in);

  }

  public static void main(String[] args) {
    Game game = new Game(4);
    printBoard(game.getBoard());
  }

  private static void printBoard(int[][] board) {

    System.out.println("BoardCheacker:");

    for (int i = 0; i < board.length; i++) {

      for (int j = 0; j < board[i].length; j++) {
        System.out.print(board[i][j]);
        if (j != board[i].length - 1) {
          System.out.print("|");
        }
      }

      System.out.println();
      if (i != board.length - 1) {
        System.out.print("-----\n");
      }
    }
  }
}
