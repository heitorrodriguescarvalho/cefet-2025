package exercises.design_patterns.strategy;

interface Discount {
  double applyDiscount(double value);
}

class ElderlyDiscount implements Discount {
  public double applyDiscount(double value) {
    return value * 0.2;
  }
}

class StudentDiscount implements Discount {
  public double applyDiscount(double value) {
    return value * 0.5;
  }
}