import java.util.Random;

/**The class for creating a person*/
public class Person implements Const{
  private int needFloor;

  public Person() {
    setNeedFloor();
  }

  public int getNeedFloor() {
    return needFloor;
  }

  public void setNeedFloor() {
    this.needFloor = 1 + new Random().nextInt(N - 1);
  }
}
