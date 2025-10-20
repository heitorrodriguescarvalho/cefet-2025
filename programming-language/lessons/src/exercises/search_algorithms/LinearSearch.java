package exercises.search_algorithms;

public class LinearSearch {
  public static int search(int[] arr, int target) {
    for (int i = 0; i < arr.length; i++) {
      if (arr[i] == target) {
        return i;
      }
    }
    return -1;
  }

  public static void main(String[] args) {
    int[] arr = { 4, 2, 7, 1, 3 };
    int target = 7;

    int result = LinearSearch.search(arr, target);

    if (result != -1) {
      System.out.println("Element found at index: " + result);
    } else {
      System.out.println("Element not found in the array.");
    }
  }
}
