package exercises.priority_queue;

import java.util.PriorityQueue;
import java.util.Queue;

public class PlaneQueue {
  Queue<Plane> queue = new PriorityQueue<>();

  public void add(Plane plane) {
    this.queue.add(plane);
  }

  public Plane get() {
    return this.queue.poll();
  }

  public boolean isEmpty() {
    return this.queue.isEmpty();
  }
}
