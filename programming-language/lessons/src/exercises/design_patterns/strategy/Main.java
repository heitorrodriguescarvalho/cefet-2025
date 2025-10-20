package exercises.design_patterns.strategy;

public class Main {
  public static void main(String[] args) {
    double price = 100.0;

    System.out.println("Preço original: $" + price);
    System.out.println();

    // Desconto para idosos
    Discount elderlyDiscount = new ElderlyDiscount();
    double elderlyDiscountAmount = elderlyDiscount.applyDiscount(price);
    System.out.printf("Desconto Idosos: $%.2f | Preço final: $%.2f%n",
        elderlyDiscountAmount, price - elderlyDiscountAmount);

    // Desconto estudantil
    Discount studentDiscount = new StudentDiscount();
    double studentDiscountAmount = studentDiscount.applyDiscount(price);
    System.out.printf("Desconto Estudantil: $%.2f | Preço final: $%.2f%n",
        studentDiscountAmount, price - studentDiscountAmount);
  }
}