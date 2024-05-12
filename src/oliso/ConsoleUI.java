package oliso;

import java.util.Scanner;

public class ConsoleUI {


  public ConsoleUI() {

  }

  public static void main(String[] args) {
    Game game = new Game(4);
    Scanner scanner = new Scanner(System.in);
    printBoard(game.getBoard());
    askForMove(scanner);
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

  private static void askForMove(Scanner scanner){
    System.out.println("player turn");
    int xPos = askForXPosition(scanner);
    int yPos = askForYPosition(scanner);
    int size = askForPieceSize(scanner);
    System.out.println("xPos: " + xPos + ", yPos: " + yPos + ", size: " + size);
  }
  private static int askForXPosition(Scanner scanner){
    int xPos;
    System.out.println("Please enter the x position of the move:");
    try{
      xPos = Integer.parseInt(scanner.nextLine());
    }catch (NumberFormatException ex){
      System.out.println("This is not a valid number, please try again");
      return askForXPosition(scanner);
    }

    if(xPos < 0 || xPos > 2){
      System.out.println("This is not a valid number, please try again");
      return askForXPosition(scanner);
    }

    return xPos;
  }
  private static int askForYPosition(Scanner scanner){
    int yPos;
    System.out.println("Please enter the y position of the move:");
    try{
      yPos = Integer.parseInt(scanner.nextLine());
    }catch (NumberFormatException ex){
      System.out.println("This is not a valid number, please try again");
      return askForYPosition(scanner);
    }

    if(yPos < 0 || yPos > 2){
      System.out.println("This is not a valid number, please try again");
      return askForYPosition(scanner);
    }

    return yPos;
  }
  private  static int askForPieceSize(Scanner scanner){
    System.out.println("s{Small}, m{Medium}, b{Big}");
    System.out.println("Please enter the size of the piece");

    String size = scanner.nextLine();

    switch (size){
      case "s", "S", "small", "Small":
        return 0;
      case "m", "M", "medium", "Medium":
        return 1;
      case "b", "B", "big", "Big":
        return 2;
      default:
        System.out.println(size + "is not a valid input, please try again");
        return askForPieceSize(scanner);
    }
  }
}
