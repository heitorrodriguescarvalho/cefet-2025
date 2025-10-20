package exercises;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class GradesAnalyzer {
  public static void main(String[] args) {
    BufferedReader fileReader = null;
    FileWriter fileWriter = null;

    try {
      fileReader = new BufferedReader(new FileReader("./input/grades.txt"));
      fileWriter = new FileWriter("./output/grades.txt");

      while (true) {
        String line = fileReader.readLine();

        if (line == null)
          break;

        String[] words = line.split(";");

        String name = words[0];
        float average = (Float.parseFloat(words[1]) + Float.parseFloat(words[2]) + Float.parseFloat(words[3])) / 3;

        fileWriter.write(name + ": " + String.valueOf(average) + "\n");
      }
    } catch (IOException e) {
      System.out.println("An error occurred: " + e.getMessage());
    } finally {
      if (fileWriter != null) {
        try {
          fileWriter.close();
        } catch (IOException e) {
          System.out.println("Failed to close fileWriter: " + e.getMessage());
        }
      }

      if (fileReader != null) {
        try {
          fileReader.close();
        } catch (IOException e) {
          System.out.println("Failed to close file: " + e.getMessage());
        }
      }
    }
  }
}
