package exercises.sort_algorithms;

public class SelectSort {
  public static void sort(int[] arr) {
    int n = arr.length, steps = 0;

    for (int i = 0; i < n - 1; i++) {
      steps++;
      int minIdx = i;

      for (int j = i + 1; j < n; j++) {
        if (arr[j] < arr[minIdx]) {
          minIdx = j;
        }
      }

      int temp = arr[minIdx];
      arr[minIdx] = arr[i];
      arr[i] = temp;
    }

    System.out.println("Steps: " + steps);
  }

  public static void main(String[] args) {
    int[] arr = { 4, 2, 7, 1, 3 };

    SelectSort.sort(arr);

    System.out.print("Sorted vector: ");

    for (int i : arr) {
      System.out.print(i + " ");
    }
  }
}
