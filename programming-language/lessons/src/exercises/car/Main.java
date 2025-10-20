package exercises.car;

public class Main {
  static public void main(String[] args) {
    Car car1 = new Car("Opala", 1978);
    Car car2 = new Car("Maverick GT", 1974);

    car1.accelerate(120);
    car1.slowDown(5);

    car2.accelerate(90);
    car2.accelerate(12);

    car1.printInfos();
    car2.printInfos();
  }
}
