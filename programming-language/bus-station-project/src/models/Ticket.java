package models;

public class Ticket {
  private int id;
  private static int idCounter = 0;
  private Trip trip;
  private double price;

  public Ticket(Trip trip, double price) {
    this.id = idCounter++;
    this.trip = trip;
    this.price = price;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Trip getTrip() {
    return this.trip;
  }

  public void setTrip(Trip trip) {
    this.trip = trip;
  }

  public double getPrice() {
    return this.price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}