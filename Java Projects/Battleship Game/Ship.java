// This class allows for the use of the ship object to determine ship locations and health, as well as if it is horizontal or vertical.
public class Ship {

  private boolean isHorizontal;
  private int health;
  private int shipLocation;

  public Ship(Boolean isHorizontal, int health, int shipLocation)
  {
    this.isHorizontal = isHorizontal;
    this.health = health;
    this.shipLocation = shipLocation;
  }

  public int getShipLocation() {
    return shipLocation;
  }

  public void setShipLocation(int shipLocation) {
    this.shipLocation = shipLocation;
  }

  public boolean isHorizontal() {
    return isHorizontal;
  }

  public void setHorizontal(boolean isHorizontal) {
    this.isHorizontal = isHorizontal;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth() {
    health--;
  }
}
