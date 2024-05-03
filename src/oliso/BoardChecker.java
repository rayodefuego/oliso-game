package oliso;

public class BoardChecker {
  private boolean checkForSpace1() {
    return true;

  }


  public static boolean checkForEmptySpace() {
    return true;
  }

  private static boolean check3in1(int[][] board) {
    for (int i = 0; i < board.length; i++) {
      for (int j = 0; j < board[i].length; j++) {
        if(board[i][j] == 222) return true;
        if(board[i][j] == 333) return true;
        if(board[i][j] == 555) return true;
        if(board[i][j] == 777) return true;
      }
    }
    return false;
      }
}
