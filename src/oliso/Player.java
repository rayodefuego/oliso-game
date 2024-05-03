package oliso;
public class Player{
  private int winsCount;
  private int smallPiece;
  private int mediumPiece;
  private int bigPiece;

  public Player(){
    this.winsCount = 0;
    this.smallPiece = 4;
    this.mediumPiece = 4;
    this.bigPiece = 4;
  }
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