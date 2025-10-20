package exercises.sort_algorithms;

public class InsertionSort {
  public static void sort(int[] arr) {
    int n = arr.length, steps = 0;

    for (int i = 1; i < n; i++) {
      steps++;
      int key = arr[i];
      int j = i - 1;

      while (j >= 0 && arr[j] > key) {
        arr[j + 1] = arr[j];
        j = j - 1;
      }
      arr[j + 1] = key;
    }

    System.out.println("Steps: " + steps);
  }

  public static void main(String[] args) {
    int[] arr = { 4, 2, 7, 1, 3 };

    InsertionSort.sort(arr);

    System.out.print("Sorted vector: ");

    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
}
