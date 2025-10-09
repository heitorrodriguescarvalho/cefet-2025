package models;

public class Pluviometro {
  private static int idCounter = 0;
  private int id;
  private double maxCapacity;
  private String location;

  public Pluviometro(double maxCapacity, String location) {
    this.id = ++idCounter;
    this.maxCapacity = maxCapacity;
    this.location = location;
  }

  public int getId() {
    return id;
  }

  public double getMaxCapacity() {
    return maxCapacity;
  }

  public void setMaxCapacity(double maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  @Override
  public boolean equals(Object pluviometro) {
    if (pluviometro instanceof Pluviometro) {
      Pluviometro p = (Pluviometro) pluviometro;

      return this.location.equals(p.location) && this.maxCapacity == p.maxCapacity;
    }

    return false;
  }
}
