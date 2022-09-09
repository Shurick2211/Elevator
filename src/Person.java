import java.util.Random;

/**The class for creating a person*/
public class Person implements Const{
  private int needFloor;

  /**The constructor of person*/
  public Person() {
    setNeedFloor();
  }

  /**
   * The method returns the floor that the person needs.
   * @return number of floor.
   */
  public int getNeedFloor() {
    return needFloor;
  }

  /**
   * The method sets the floor that the person needs.
   */
  public void setNeedFloor() {
    this.needFloor = 1 + new Random().nextInt(N - 1);
  }
}
