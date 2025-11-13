package exercises.priority_queue;

public class Plane implements Comparable<Plane> {
  private String name;
  private int priority;

  public Plane(String name, int priority) {
    this.name = name;
    this.priority = priority;
  }

  public String getName() {
    return this.name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getPriority() {
    return this.priority;
  }

  public void setPriority(int priority) {
    this.priority = priority;
  }

  @Override
  public int compareTo(Plane other) {
    return Integer.compare(this.priority, other.priority);
  }
}
