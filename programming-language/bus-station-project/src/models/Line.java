package models;

public class Line {
  private int id;
  private static int idCounter = 0;
  private double price;
  private String startCity;
  private String endCity;

  public Line(String firstCity, String secondCity, double price) {
    this.id = idCounter++;
    this.startCity = firstCity;
    this.endCity = secondCity;
    this.price = price;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }

  public String getStartCity() {
    return startCity;
  }

  public String getEndCity() {
    return endCity;
  }

  public void setStartCity(String startCity) {
    this.startCity = startCity;
  }

  public void setEndCity(String endCity) {
    this.endCity = endCity;
  }
}