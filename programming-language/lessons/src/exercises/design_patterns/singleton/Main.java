package exercises.design_patterns.singleton;

class Main {
  static public void main(String[] args) {
    Database db1 = Database.getInstance();
    Database db2 = Database.getInstance();

    System.out.println("Hashcode da instância 1: " + db1.hashCode());
    System.out.println("Hashcode da instância 2: " + db2.hashCode());

    System.out.println("db1 == db2: " + String.valueOf(db1 == db2));
  }
}