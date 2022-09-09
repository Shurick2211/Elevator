import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
    List<Person> arrivals = peopleOnElevator.stream()
            .filter(p -> p.getNeedFloor() == currentFloor).collect(Collectors.toList());
    arrivals.forEach(person -> {
      peopleOnElevator.remove(person);
      BUILDING[currentFloor-1].addPersonOnFloor(person);
    });
    List<Person> leaving = new ArrayList<>();
    Person person;
    while (peopleOnElevator.size() != Elevator_CAPACITY ) {
      if (trend == Trend.UP) {
        person = BUILDING[currentFloor - 1].trendUp.poll();
      } else {
        person = BUILDING[currentFloor - 1].trendDown.poll();
      }
      if (person == null) break;
      peopleOnElevator.add(person);
      leaving.add(person);
    }
    StringBuilder builder = new StringBuilder();
    if (arrivals.size() > 0) builder.append(". The ").append(arrivals.size()).append(" has arrived.");
    if (leaving.size() > 0) builder.append(" The ").append(leaving.size()).append(" has leaving.");
    builder.append("In the elevator ").append(peopleOnElevator.size()).append(" persons");
    System.out.println(builder);
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
