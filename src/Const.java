import java.util.Random;

/** The interface for const*/
public interface Const {
  /**The number of floors of building*/
  int N = 5 + new Random().nextInt(15);

  /**Create our building*/
  Floor[] BUILDING = new Floor[N];

  /**The maximum elevator capacity*/
  int ELEVATOR_CAPACITY = 5;

  /**Time imitates the time for elevator move between floors*/
  int PAUSE_TIME = 1000;
}