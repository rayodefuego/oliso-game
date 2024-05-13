package oliso;

public class BoardChecker {
  public static boolean checkForWin(int[][] board) {
    if(check3in1(board)) return true;
    if(checkForSmall(board)) return true;
    if(checkForMedium(board)) return true;
    if(checkForBig(board)) return true;
    if(checkForBigToSmall(board)) return true;
    if(checkForSmallToBig(board)) return true;

    return false;

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

  private static int getSmall(int num3) {
    return num3%10;
  }
  private static int getMedium(int num3) {
    return ((num3-(num3%10))%100)/10;
  }
  private static int getBig(int num3) {
    return num3/100;
  }
  private static boolean checkForSmall (int [][] board) {
    for (int i = 0; i < board.length; i++) {
      if ((getSmall(board[i][0]) == getSmall(board[i][1])) && (getSmall(board[i][0]) == getSmall(board[i][2]))){
        if((getSmall(board[i][0]) == 0||getSmall(board[i][1]) == 0||getSmall(board[i][2])== 0)){
          continue;
        }
        return true;
      }
      if ((getSmall(board[0][i]) == getSmall(board[1][i])) && (getSmall(board[0][i]) == getSmall(board[2][i]))){
        if((getSmall(board[0][i]) == 0||getSmall(board[1][i]) == 0||getSmall(board[2][i])== 0)){
          continue;
        }
        return true;
      }
    }
    if ((getSmall(board[0][0]) == getSmall(board[1][1])) && (getSmall(board[0][0]) == getSmall(board[2][2]))){
      if((getSmall(board[0][0]) != 0||getSmall(board[1][1]) != 0||getSmall(board[2][2]) != 0)){
        return true;
      }
    }
    if ((getSmall(board[0][2]) == getSmall(board[1][1])) && (getSmall(board[0][2]) == getSmall(board[2][0]))){
      if((getSmall(board[0][2]) != 0||getSmall(board[1][1]) != 0||getSmall(board[2][0]) != 0)){
        return true;
      }
    }
    return false;
  }
  private static boolean checkForMedium (int [][] board) {
    for (int i = 0; i < board.length; i++) {
      if ((getMedium(board[i][0]) == getMedium(board[i][1])) && (getMedium(board[i][0]) == getMedium(board[i][2]))){
        if((getMedium(board[i][0]) == 0||getMedium(board[i][1]) == 0||getMedium(board[i][2])== 0)){
          continue;
        }
        return true;
      }
      if ((getMedium(board[0][i]) == getMedium(board[1][i])) && (getMedium(board[0][i]) == getMedium(board[2][i]))){
        if((getMedium(board[0][i]) == 0||getMedium(board[1][i]) == 0||getMedium(board[2][i])== 0)){
          continue;
        }
        return true;
      }
    }
    if ((getMedium(board[0][0]) == getMedium(board[1][1])) && (getMedium(board[0][0]) == getMedium(board[2][2]))){
      if((getMedium(board[0][0]) != 0||getMedium(board[1][1]) != 0||getMedium(board[2][2]) != 0)){
        return true;
      }
    }
    if ((getMedium(board[0][2]) == getMedium(board[1][1])) && (getMedium(board[0][2]) == getMedium(board[2][0]))){
      if((getMedium(board[0][2]) != 0||getMedium(board[1][1]) != 0||getMedium(board[2][0]) != 0)){
        return true;
      }
    }
    return false;
  }
  private static boolean checkForBig (int [][] board) {
    for (int i = 0; i < board.length; i++) {
      if ((getBig(board[i][0]) == getBig(board[i][1])) && (getBig(board[i][0]) == getBig(board[i][2]))){
        if((getBig(board[i][0]) == 0||getBig(board[i][1]) == 0||getBig(board[i][2])== 0)){
          continue;
        }
        return true;
      }
      if ((getBig(board[0][i]) == getBig(board[1][i])) && (getBig(board[0][i]) == getBig(board[2][i]))){
        if((getBig(board[0][i]) == 0||getBig(board[1][i]) == 0||getBig(board[2][i])== 0)){
          continue;
        }
        return true;
      }
    }
    if ((getBig(board[0][0]) == getBig(board[1][1])) && (getBig(board[0][0]) == getBig(board[2][2]))){
      if((getBig(board[0][0]) != 0||getBig(board[1][1]) != 0||getBig(board[2][2]) != 0)){
        return true;
      }
    }
    if ((getBig(board[0][2]) == getBig(board[1][1])) && (getBig(board[0][2]) == getBig(board[2][0]))){
      if((getBig(board[0][2]) != 0||getBig(board[1][1]) != 0||getBig(board[2][0]) != 0)){
        return true;
      }
    }
    return false;
  }
  private static boolean checkForBigToSmall(int[][]board) {
    for (int i = 0; i < board.length; i++) {
      if ((getBig(board[i][0]) == getMedium(board[i][1])) && (getBig(board[i][0]) == getSmall(board[i][2]))){
        if((getBig(board[i][0]) == 0)){
          continue;
        }
        return true;
      }
      if ((getBig(board[0][i]) == getMedium(board[1][i])) && (getBig(board[0][i]) == getSmall(board[2][i]))){
        if((getBig(board[0][i]) == 0)){
          continue;
        }
        return true;
      }
    }
    if ((getBig(board[0][0]) == getMedium(board[1][1])) && (getBig(board[0][0]) == getSmall(board[2][2]))){
      if((getBig(board[0][0]) != 0)){
        return true;
      }
    }
    if ((getBig(board[0][2]) == getMedium(board[1][1])) && (getBig(board[0][2]) == getSmall(board[2][0]))){
      if((getBig(board[0][2]) != 0)){
        return true;
      }
    }
    return false;

  }
  private static boolean checkForSmallToBig(int[][]board) {
    for (int i = 0; i < board.length; i++) {
      if ((getSmall(board[i][0]) == getMedium(board[i][1])) && (getSmall(board[i][0]) == getBig(board[i][2]))){
        if(getSmall(board[i][0]) == 0){
          continue;
        }
        return true;
      }
      if ((getSmall(board[0][i]) == getMedium(board[1][i])) && (getSmall(board[0][i]) == getBig(board[2][i]))){
        if(getSmall(board[0][i]) == 0){
          continue;
        }
        return true;
      }
    }
    if ((getSmall(board[0][0]) == getMedium(board[1][1])) && (getSmall(board[0][0]) == getBig(board[2][2]))){
      if(getSmall(board[0][0]) != 0){
        return true;
      }

    }
    if ((getSmall(board[0][2]) == getMedium(board[1][1])) && (getSmall(board[0][2]) == getBig(board[2][0]))){
      if(getSmall(board[0][2]) != 0){
        return true;
      }
    }
    return false;

  }
}
