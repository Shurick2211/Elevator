import java.util.Random;

/** The interface for const*/
public interface Const {
  /**The number of floors of building*/
  int N = 5 + new Random().nextInt(15);

  /**Create our building*/
  Floor[] BUILDING = new Floor[N];

  /**The maximum elevator capacity*/
  int Elevator_CAPACITY = 5;


}
