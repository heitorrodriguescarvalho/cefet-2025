package examples;

import java.io.FileWriter;

public class ExampleUsingFiles {
  public static void main(String[] args) {
    try (FileWriter file = new FileWriter("./output/example.txt", true)) {
      file.write("\nHello, World!");
      System.out.println("Successfully wrote to the file.");
    } catch (Exception e) {
      System.out.println("An error occurred: " + e.getMessage());
    }
  }
}
