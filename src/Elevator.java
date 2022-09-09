import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**The class for create elevator (the lift)*/
public class Elevator implements Const{
  private int currentFloor;
  private final List<Person> peopleOnElevator;
  private Trend trend;

  /**
   * Constructor creates an elevator for first floor.
   */
  public Elevator() {
    currentFloor = 1;
    peopleOnElevator = new ArrayList<>(Elevator_CAPACITY);
    trend = Trend.UP;
  }

  /**
   * Method to exchange people between floor and elevator.
   */
  public void fillFloor(){
    peopleOnElevator.stream().filter(p -> p.getNeedFloor() == currentFloor)
            .forEach(person -> {
              peopleOnElevator.remove(person);
              BUILDING[currentFloor-1].addPersonOnFloor(person);
            });
    while (peopleOnElevator.size() != Elevator_CAPACITY) {
      if (trend == Trend.UP) peopleOnElevator.add(BUILDING[currentFloor - 1].trendUp.poll());
      else peopleOnElevator.add(BUILDING[currentFloor - 1].trendDown.poll());
    }
  }

  /**
   * Method returns the current floor for the elevator
   * @return number of floor
   */
  public int getCurrentFloor() {
    return currentFloor;
  }

  /**
   * Method moves elevator for next floor.
   */
  public void nextFloor() {
    if (currentFloor == N || !hasUpCall()) trend = Trend.DOWN;
    if (currentFloor == 1 || !hasDownCall()) trend = Trend.UP;
    if (currentFloor < N && trend == Trend.UP) this.currentFloor++;
    if (currentFloor > 1 && trend == Trend.DOWN) this.currentFloor--;
  }

  /**
   * The method returns true if there are calls at the empty elevator above.
   * @return true or false
   */
  private boolean hasUpCall() {
    return peopleOnElevator.isEmpty()
            && Arrays.stream(BUILDING).anyMatch(f -> f.getNumber() > currentFloor && f.getNumberOfPerson() > 0);
  }

  /**
   * The method returns true if the empty elevator below has calls.
   * @return true or false
   */
  private boolean hasDownCall() {
    return peopleOnElevator.isEmpty()
            && Arrays.stream(BUILDING).anyMatch(f -> f.getNumber() < currentFloor && f.getNumberOfPerson() > 0);
  }

}
