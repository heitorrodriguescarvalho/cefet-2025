package exercises;

import java.text.MessageFormat;
import java.util.Scanner;

public class StudentGrades {
  public static void main(String[] args) {
    Scanner scan = new Scanner(System.in);
    float[] grades = new float[10];

    for (int i = 0; i < grades.length; i++) {
      System.out.print(
          MessageFormat.format("Digite a {0}º nota: ", i + 1));
      grades[i] = scan.nextFloat();
    }

    scan.close();

    float sum = 0;

    for (int i = 0; i < grades.length; i++)
      sum += grades[i];

    float mean = sum / grades.length;

    int aboveCount = 0, belowCount = 0;

    for (int i = 0; i < grades.length; i++)
      if (grades[i] > mean)
        aboveCount++;
      else if (grades[i] < mean)
        belowCount++;

    System.out.println(
        MessageFormat.format(
            "\nNota média: {0}\nNotas acima da média: {1}\nNotas abaixo do média: {2}",
            mean,
            aboveCount,
            belowCount));
  }
}
