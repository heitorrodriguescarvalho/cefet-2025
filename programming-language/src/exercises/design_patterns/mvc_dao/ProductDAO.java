package exercises.design_patterns.mvc_dao;

import java.util.ArrayList;

class ProductDAO {
  private ArrayList<Product> products = new ArrayList<>();

  public void create(Product product) {
    this.products.add(product);
  }

  public Product getByName(String name) {
    for (Product product : this.products) {
      if (product.getName().equals(name)) {
        return product;
      }
    }

    return null;
  }

  public Product getByIndex(int index) {
    if (index < 0 || index >= this.products.size()) {
      return null;
    }

    return this.products.get(index);
  }

  public boolean updateByName(String currentName, String newName) {
    for (Product product : this.products) {
      if (product.getName().equals(currentName)) {
        product.setName(newName);

        return true;
      }
    }

    return false;
  }

  public boolean updateByIndex(int index, String newName) {
    if (index < 0 || index >= this.products.size()) {
      return false;
    }

    this.products.get(index).setName(newName);
    return true;
  }

  public boolean deleteByName(String name) {
    for (Product product : this.products) {
      if (product.getName().equals(name)) {
        this.products.remove(product);

        return true;
      }
    }

    return false;
  }

  public boolean deleteByIndex(int index) {
    if (index < 0 || index >= this.products.size()) {
      return false;
    }

    this.products.remove(index);
    return true;
  }
}