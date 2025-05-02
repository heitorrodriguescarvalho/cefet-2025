package examples.example_using_constructor;

public class Person {
  String name;
  int age;
  String description;

  public Person(String name, int age, String description) {
    this.name = name;
    this.age = age;
    this.description = description;
  }

  public Person(String name, int age) {
    this.name = name;
    this.age = age;
    this.description = "";
  }
}
