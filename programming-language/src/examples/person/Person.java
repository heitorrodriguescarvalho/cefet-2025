package examples.person;

public class Person {
  public String name;
  public int age;

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
  }

  public Person(String name) {
    this.name = name;
    this.age = 0;
  }

  public Person(int age) {
    this.age = age;
  }

  public void present() {
    System.out.println("Meu nome é " + this.name + " e eu tenho " + String.valueOf(this.age) + " anos.");
  }
}
