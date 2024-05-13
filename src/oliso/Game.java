package oliso;
import java.util.*;
import java.util.ArrayList;

public class Game {
  private int[][] board;
  private int turn;
  private int playerTurn;
  private Player players[];
  private int playersNumbers[];
  private int playerCount;

  private Player currentPlayer;

  public Game(int players) {
    this.board = new int[3][3];
    this.turn = 0;
    this.playerTurn = 2;
    this.players = new Player[4];
    this.players[0] = new Player("Player 1");
    this.players[1] = new Player("Player 2");
    this.players[2] = new Player("Player 3");
    this.players[3] = new Player("Player 4");
    this.playersNumbers = new int[4];
    this.playersNumbers[0] = 2;
    this.playersNumbers[1] = 3;
    this.playersNumbers[2] = 5;
    this.playersNumbers[3] = 7;
    this.playerCount = players;
    this.currentPlayer = this.players[0];
  }

  /**
   * Cicle for advance 1 position in function of the players
   */
  public void nextTurn() {
    turn = (turn +1) % playerCount;
    playerTurn = playersNumbers[turn];
    currentPlayer = players[turn];
  }

  public int[][] getBoard() {
    return board;
  }

  public void setBoard(int[][] board) {
    this.board = board;
  }

  public int getTurn() {
    return turn;
  }

  public void setTurn(int turn) {
    this.turn = turn;
  }

  public int getPlayerTurn() {
    return playerTurn;
  }

  public void setPlayerTurn(int playerTurn) {
    this.playerTurn = playerTurn;
  }

  public Player[] getPlayers() {
    return players;
  }

  public void setPlayers(Player[] players) {
    this.players = players;
  }

  public int[] getPlayersNumbers() {
    return playersNumbers;
  }

  public void setPlayersNumbers(int[] playersNumbers) {
    this.playersNumbers = playersNumbers;
  }

  public Player getCurrentPlayer() {
    return currentPlayer;
  }

  /**
   * @param
   * @param size
   * @param x
   * @param y
   * @return
   */
  public boolean addPiece(int size, int x, int y) {
    // When the board hasn't has numbers
    if (board[x][y] == 0) {
      board[x][y] += transforSizeToInt3(size, playerTurn);
      return true;
    }
    // When the board has only one number like 002
    else if (board[x][y] < 10) {

      if (size == 0) {
        return false;
      } else {
        board[x][y] += transforSizeToInt3(size, playerTurn);
        return true;
      }
    }
    // When the board has two numbers like 020 or 025
    else if (board[x][y] < 100) {
      if (size == 0 && board[x][y] % 10 == 0) {
        board[x][y] += transforSizeToInt3(size, playerTurn);
        return true;
      } else if (size == 2) {
        board[x][y] += transforSizeToInt3(size, playerTurn);
        return true;
      } else {
        return false;
      }
    }
    // When the board has three numbers like 300, 320 or 325
    else {
      if (size == 0 && board[x][y] % 10 == 0) {
        board[x][y] += transforSizeToInt3(size, playerTurn);
        return true;
      } else if (size == 1 && (board[x][y] % 100 - board[x][y] % 10) == 0) {
        board[x][y] += transforSizeToInt3(size, playerTurn);
        return true;
      } else {
        return false;
      }
    }
  }

  /**
   * @param size   [0, 2]
   * @param player 2, 3, 5, 7
   * @return a number 030, 300, 333
   */
  private int transforSizeToInt3(int size, int player) {
    if (size == 0) {
      return player;
    } else if (size == 1) {
      return player * 10;
    } else {
      return player * 100;
    }
  }
}
