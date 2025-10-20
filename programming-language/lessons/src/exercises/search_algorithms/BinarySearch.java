package exercises.search_algorithms;

public class BinarySearch {
  public static int search(int[] arr, int target) {
    int start = 0, end = arr.length - 1;

    while (start <= end) {
      int middle = (start + end) / 2;

      if (arr[middle] == target)
        return middle;
      if (arr[middle] < target)
        end = middle - 1;
      if (arr[middle] > target)
        start = middle + 1;
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
