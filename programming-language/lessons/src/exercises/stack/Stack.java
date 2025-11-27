package exercises.stack;

import java.util.ArrayList;

public class Stack<T> {
  ArrayList<T> stack = new ArrayList<>();

  public void push(T item) {
    this.stack.add(item);
  }

  public T getTop() {
    if (this.isEmpty()) {
      return null;
    }

    return this.stack.get(this.stack.size() - 1);
  }

  public T pop() {
    if (this.isEmpty()) {
      return null;
    }

    return this.stack.remove(this.stack.size() - 1);
  }

  public boolean isEmpty() {
    return this.stack.isEmpty();
  }
}
