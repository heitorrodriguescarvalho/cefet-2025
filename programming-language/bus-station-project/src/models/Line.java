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

  public String getfirstCity() {
    return startCity;
  }

  public String getsecondCity() {
    return endCity;
  }

  public void setfirstCity(String firstCity) {
    this.startCity = firstCity;
  }

  public void setsecondCity(String secondCity) {
    this.endCity = secondCity;
  }
}