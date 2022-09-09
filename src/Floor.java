import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**The class for creating a floor for building*/
public class Floor implements Const{
  private final int number;
  private int k;
  protected Queue<Person> trendUp = new LinkedList<>();
  protected Queue<Person> trendDown = new LinkedList<>();

  public Floor(int number) {
    this.number = number;
    this.k = new Random().nextInt(10);
    for (int i = 0; i < k; i++) {
      addPersonOnFloor(new Person());
    }
  }


  public int getNumber() {
    return number;
  }

  public int getNumberOfPerson() {
    this.k = trendUp.size() + trendDown.size();
    return k;
  }

  public void addPersonOnFloor(Person person) {
    while (person.getNeedFloor() == number) person.setNeedFloor();
    if (person.getNeedFloor() > number) trendUp.offer(person);
    else trendDown.offer(person);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    builder.append("In the floor ");
    builder.append(number);
    builder.append(". Wait ");
    if (number < N) {
      builder.append(trendUp.size());
      builder.append(" person elevator`s trend UP");
    }
    if (number > 1) {
      if (number < N) builder.append(", and ");
      builder.append(trendDown.size());
      builder.append(" person elevator`s trend DOWN");
    }
    return builder.toString();
  }
}
