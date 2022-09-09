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
    peopleOnElevator = new ArrayList<>(ELEVATOR_CAPACITY);
    trend = Trend.UP;
  }

  /**
   * Method to exchange people between floor and elevator.
   */
  public void fillFloor(){
    List<Person> arrivals = peopleOnElevator.stream()
            .filter(p -> p.getNeedFloor() == currentFloor).collect(Collectors.toList());
    arrivals.forEach(peopleOnElevator::remove);
    List<Person> leaving = new ArrayList<>();
    Person person;
    while (peopleOnElevator.size() != ELEVATOR_CAPACITY) {
      if (trend.equals( Trend.UP)) {
        person = BUILDING[currentFloor - 1].trendUp.poll();
      } else {
        person = BUILDING[currentFloor - 1].trendDown.poll();
      }
      if (person == null) break;
      peopleOnElevator.add(person);
      leaving.add(person);
    }
    arrivals.forEach(BUILDING[currentFloor-1]::addPersonOnFloor);
    if (hasNoUpCall())  this.trend = Trend.DOWN;
    if (hasNoDownCall()) this.trend = Trend.UP;
    StringBuilder builder = new StringBuilder();
    if (arrivals.size() > 0) builder.append("The ").append(arrivals.size()).append(" has arrived. ");
    if (leaving.size() > 0) builder.append("The ").append(leaving.size()).append(" has leaving. ");
   // builder.append("In the elevator ").append(peopleOnElevator.size()).append(" persons");
    if (!builder.isEmpty()) System.out.println(builder);
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
    if (currentFloor < N && trend.equals(Trend.UP)) this.currentFloor++;
    if (currentFloor > 1 && trend.equals(Trend.DOWN)) this.currentFloor--;
    if (currentFloor == N)  this.trend = Trend.DOWN;
    if (currentFloor == 1) this.trend = Trend.UP;
  }

  /**
   * Method checks to need stop elevator for the current floor.
   * @return true or false
   */
  public boolean needToStop() {
    boolean hasWaite = trend.equals(Trend.UP) ? BUILDING[currentFloor-1].trendUp.size() > 0 :
            BUILDING[currentFloor-1].trendDown.size() > 0;
    return (peopleOnElevator.size() < ELEVATOR_CAPACITY && hasWaite)
            || peopleOnElevator.stream().anyMatch(p -> p.getNeedFloor() == currentFloor) || currentFloor == N || currentFloor == 1;
  }

  /**
   * The method returns true if there are no calls at the empty elevator above.
   * @return true or false
   */
  private boolean hasNoUpCall() {
    return peopleOnElevator.isEmpty()
            && Arrays.stream(BUILDING).filter(f -> f.getNumber()-1 > currentFloor-1)
            .mapToInt(Floor::getNumberOfPerson).reduce(0, Integer::sum) == 0;
  }

  /**
   * The method returns true if the empty elevator below has no calls.
   * @return true or false
   */
  private boolean hasNoDownCall() {
    return  peopleOnElevator.isEmpty()
            && Arrays.stream(BUILDING).filter(f -> f.getNumber()-1 < currentFloor-1)
            .mapToInt(Floor::getNumberOfPerson).reduce(0, Integer::sum) == 0;
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("Elevator in ").append(currentFloor).append(" floor. Move to ").append(trend)
            .append(". The elevator ")
            .append(peopleOnElevator.isEmpty() ? "is empty" : "has " + peopleOnElevator.size() + " people")
            .append(peopleOnElevator);
    return builder.toString();
  }
}
