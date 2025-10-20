package exercises.design_patterns.mvc_dao;

public class Main {
  public static void main(String[] args) {
    ProductDAO productDAO = new ProductDAO();

    ProductView productView = new ProductView();

    ProductController productController = new ProductController(productDAO, productView);

    System.out.println("Bem-vindo ao Sistema de Gerenciamento de Produtos!");
    productController.run();

    System.out.println("Obrigado por usar o sistema!");
  }
}