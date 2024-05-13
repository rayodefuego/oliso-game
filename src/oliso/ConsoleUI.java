package oliso;

import java.util.Scanner;

public class ConsoleUI {


  public ConsoleUI() {

  }

  public static void main(String[] args) {
    Game game = new Game(4);
    Scanner scanner = new Scanner(System.in);

    do{
      printBoard(game.getBoard());
      askForMove(scanner, game);
      game.nextTurn();
      System.out.println("Test");
      System.out.println(BoardChecker.checkForWin(game.getBoard()));
    }while (BoardChecker.checkForWin(game.getBoard()));


  }

  /**
   * print the board in the console
   * @param board
   */
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

  /**
   * ask the player all the information about the movement
   * @param scanner Scanner used in main
   * @param game Game used in main
   */
  private static void askForMove(Scanner scanner, Game game){
    System.out.println("player turn");
    int xPos = askForXPosition(scanner);
    int yPos = askForYPosition(scanner);
    int size = askForPieceSize(scanner);
    if (!game.addPiece(size, xPos, yPos)){
      System.out.println("The Move is not empty, please try again");
      askForMove(scanner, game);
    }
  }

  /**
   * Ask the player what x position would like to the move
   * @param scanner Scanner used in main
   * @return the position that is between 0 and 2
   */
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
  /**
   * Ask the player what y position would like to the move
   * @param scanner Scanner used in main
   * @return the position that is between 0 and 2
   */
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
  /**
   * Ask the player what size of piece want
   * @param scanner Scanner used in main
   * @return the size that is between 0 and 2 [0 small, 1 medium, 2 big]
   */
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
