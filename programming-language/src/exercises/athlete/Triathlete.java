package exercises.athlete;

public class Triathlete extends Person implements Runner, Swimmer, Cyclist {
  public Triathlete(String name) {
    super(name);
  }

  @Override
  public void warmUp() {
    System.out.println("Triatleta aquecendo.");
  }

  @Override
  public void run() {
    System.out.println("Triatleta correndo.");
  }

  @Override
  public void swim() {
    System.out.println("Triatleta nadando.");
  }

  @Override
  public void ride() {
    System.out.println("Triatleta andando de bicicleta.");
  }

  public static void main(String[] args) {
    Triathlete t = new Triathlete("Heitor");
    t.warmUp();
    t.swim();
    t.run();
    t.ride();
  }
}