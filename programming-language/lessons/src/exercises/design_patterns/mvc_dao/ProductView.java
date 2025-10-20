package exercises.design_patterns.mvc_dao;

import java.util.Scanner;

public class ProductView {
  private Scanner scanner;

  public ProductView() {
    this.scanner = new Scanner(System.in);
  }

  public void displayMenu() {
    System.out.println("\n=== Sistema de Gerenciamento de Produtos ===");
    System.out.println("1. Adicionar Produto");
    System.out.println("2. Visualizar Produto por Nome");
    System.out.println("3. Visualizar Produto por Índice");
    System.out.println("4. Atualizar Produto por Nome");
    System.out.println("5. Atualizar Produto por Índice");
    System.out.println("6. Excluir Produto por Nome");
    System.out.println("7. Excluir Produto por Índice");
    System.out.println("0. Sair");
    System.out.print("Escolha uma opção: ");
  }

  public int getMenuChoice() {
    try {
      return this.scanner.nextInt();
    } catch (Exception e) {
      this.scanner.nextLine(); // Clear the invalid input
      return -1;
    }
  }

  public String getProductName() {
    System.out.print("Digite o nome do produto: ");
    this.scanner.nextLine(); // Clear the buffer
    return this.scanner.nextLine();
  }

  public String getNewProductName() {
    System.out.print("Digite o novo nome do produto: ");
    return this.scanner.nextLine();
  }

  public String getCurrentProductName() {
    System.out.print("Digite o nome atual do produto: ");
    this.scanner.nextLine(); // Clear the buffer
    return this.scanner.nextLine();
  }

  public int getProductIndex() {
    System.out.print("Digite o índice do produto: ");
    try {
      return this.scanner.nextInt();
    } catch (Exception e) {
      this.scanner.nextLine(); // Clear the invalid input
      return -1;
    }
  }

  public void displayProduct(Product product) {
    if (product != null) {
      System.out.println("Produto encontrado: " + product.getName());
    } else {
      System.out.println("Produto não encontrado.");
    }
  }

  public void displaySuccessMessage(String message) {
    System.out.println("✓ " + message);
  }

  public void displayErrorMessage(String message) {
    System.out.println("✗ " + message);
  }

  public void clearScreen() {
    // Simple way to clear screen (works on most terminals)
    System.out.print("\033[2J\033[H");
  }

  public void pressEnterToContinue() {
    System.out.print("Pressione Enter para continuar...");
    this.scanner.nextLine();
  }
}