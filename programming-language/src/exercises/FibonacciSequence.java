package exercises;

public class FibonacciSequence {
  public static void main(String[] args) {
    int n1 = 1, n2 = 1, temp;

    System.out.println(n1);
    System.out.println(n2);

    for (int i = 2; i < 30; i++) {
      temp = n2;
      n2 += n1;
      n1 = temp;

      System.out.println(n2);
    }
  }
}
