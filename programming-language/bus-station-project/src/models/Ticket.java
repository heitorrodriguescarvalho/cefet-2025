package models;

public class Ticket {
  private int id;
  private static int idCounter = 0;
  private Trip trip;
  private double amountPaid;

  public Ticket(Trip trip, double payedPrice) {
    this.id = idCounter++;
    this.trip = trip;
    this.amountPaid = payedPrice;
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

  public double getAmountPaid() {
    return this.amountPaid;
  }

  public void setAmountPaid(double price) {
    this.amountPaid = price;
  }
}