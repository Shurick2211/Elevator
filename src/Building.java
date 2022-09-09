/**
 * The main class of project
 */
public class Building implements Const{

  /**
   * The start method of the project.
   * @param args the args array
   */
  public static void main(String[] args) {
    System.out.println("The building has " + createBuilding() + " floors.");
    cycleOfElevator();
    //Arrays.stream(BUILDING).forEach(System.out::println);
  }

  private static void cycleOfElevator() {
    Elevator elevator = new Elevator();
    while (true) {
      separator();
      System.out.println(BUILDING[elevator.getCurrentFloor() - 1]);
      if (elevator.needToStop()) elevator.fillFloor();
      else System.out.println("Elevator don't stop!!!");
      System.out.println(elevator);
      timeToMoving();
      elevator.nextFloor();
    }
  }

  /**
   * Method creates building.
   */
  private static int createBuilding() {
    for (int i = 0; i < N; i++)
      BUILDING[i] = new Floor(i + 1);
    return N;
  }

  /**
   * Method imitates the time for elevator move between floors.
   */
  private static void timeToMoving() {
    System.out.println("Elevator MO-O-O-O-OVE ..........");
    try {
      Thread.sleep(PAUSE_TIME);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private static void separator() {
    System.out.println("-------------------------------------------------------------------------");
  }
}
