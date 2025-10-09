package exercises.sort_algorithms;

public class BubbleSort {
  public static void sort(int[] arr) {
    int n = arr.length;

    for (int i = 0; i < n - 1; i++) {
      for (int j = 0; j < n - i - 1; j++) {
        if (arr[j] > arr[j + 1]) {
          int temp = arr[j];
          arr[j] = arr[j + 1];
          arr[j + 1] = temp;
        }
      }
    }
  }

  public static void main(String[] args) {
    int[] arr = { 4, 2, 7, 1, 3 };

    BubbleSort.sort(arr);

    System.out.print("Sorted vector: ");

    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
}
