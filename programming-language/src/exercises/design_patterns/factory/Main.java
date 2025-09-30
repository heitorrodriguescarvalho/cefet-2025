package exercises.design_patterns.factory;

import java.util.Scanner;

class Main {
  static public void main(String[] args) {
    Scanner scan = new Scanner(System.in);

    System.out.print("Digite o tipo de forma geométrica (square/triangle): ");
    String type = scan.nextLine();

    scan.close();

    GeometricShape shape = GeometricShapeFactory.createGeometricShape(type);

    if (shape == null) {
      System.out.println("Tipo de forma geométrica inválida.");

      return;
    }

    System.out.println("Você criou uma forma geométrica com " + shape.getNumOfSides() + " lados.");
  }
}