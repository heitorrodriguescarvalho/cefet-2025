package exercises.design_patterns.factory;

abstract class GeometricShape {
  public abstract int getNumOfSides();
}

class Square extends GeometricShape {
  public int getNumOfSides() {
    return 4;
  }
}

class Triangle extends GeometricShape {
  public int getNumOfSides() {
    return 3;
  }
}

class GeometricShapeFactory {
  public static GeometricShape createGeometricShape(String type) {
    if (type.toLowerCase().equals("square")) {
      return new Square();
    } else if (type.toLowerCase().equals("triangle")) {
      return new Triangle();
    } else {
      return null;
    }
  }
}