package exercises.queue;

class Node<T> {
  public T val;
  public Node<T> next;

  public Node(T val) {
    this.val = val;
    this.next = null;
  }
}

public class Queue<T> {
  private Node<T> first = null;
  private Node<T> last = null;

  public void add(T val) {
    if (this.first == null) {
      this.first = new Node<>(val);
      this.last = this.first;
    } else {
      this.last.next = new Node<>(val);
      this.last = this.last.next;
    }
  }

  public T get() {
    if (this.first == null) {
      return null;
    }

    T val = this.first.val;

    this.first = this.first.next;

    if (this.first == null) {
      this.last = null;
    }

    return val;
  }
}
