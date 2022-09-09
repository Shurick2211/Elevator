import java.util.Arrays;

public class Building implements Const{


  public static void main(String[] args) {
    createBuilding();
    Arrays.stream(BUILDING).forEach(System.out::println);
  }

  /**
   * Method creates building.
   */
  private static void createBuilding() {
    for (int i = 0; i < N; i++)
      BUILDING[i] = new Floor(i + 1);
  }


}
