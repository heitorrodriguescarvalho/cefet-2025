package exercises.car;

public class Car {
  public String model;
  public int year;
  public double speed;

  public Car(String model, int year) {
    this.model = model;
    this.year = year;
    this.speed = 0;
  }

  public void accelerate(double speed) {
    this.speed += speed;
  }

  public void slowDown(double speed) {
    if (speed > this.speed) {
      this.speed = 0;
      return;
    }

    this.speed -= speed;
  }

  public void printInfos() {
    System.out.println("Modelo: " + this.model);
    System.out.println("Ano: " + String.valueOf(this.year));
    System.out.println("Velocidade Atual: " + String.valueOf(this.speed) + "Km/h");
  }
}
