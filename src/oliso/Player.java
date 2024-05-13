package oliso;

import javax.lang.model.element.Name;

public class Player{
  private int winsCount;    //Declare total wins variable winsCount as integer
  private int smallPiece;   //Declare the number of smallPieces as integer
  private int mediumPiece;  //Declare the number of mediumPiece as integer
  private int bigPiece;     //Declare the number of bigPieces as integer
  private String name;

  public Player(){
    this.winsCount = 0;   //Initialize winsCount as 0
    this.smallPiece = 4;  //Maximum number of smallPieces is 4
    this.mediumPiece = 4; //Maximum number of mediumPieces is 4
    this.bigPiece = 4;    //Maximum number of bigPieces is 4
  }

  //Getters and setters for wins count,small big and medium pieces
  public int getWinsCount() {
    return winsCount;
  }

  public void setWinsCount(int winsCount) {
    this.winsCount = winsCount;
  }

  public int getSmallPiece() {
    return smallPiece;
  }

  public void setSmallPiece(int smallPiece) {
    this.smallPiece = smallPiece;
  }

  public int getMediumPiece() {
    return mediumPiece;
  }

  public void setMediumPiece(int mediumPiece) {
    this.mediumPiece = mediumPiece;
  }

  public int getBigPiece() {
    return bigPiece;
  }

  public void setBigPiece(int bigPiece) {
    this.bigPiece = bigPiece;
  }

}