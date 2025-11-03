package models;

import java.util.Date;

public class Trip {
  private int id;
  private static int idCounter = 0;
  private Line line;
  private boolean isComingBack;
  private Date departureTime;

  public Trip(Line line, boolean isComingBack, Date departureTime) {
    this.id = idCounter++;
    this.line = line;
    this.isComingBack = isComingBack;
    this.departureTime = departureTime != null ? new Date(departureTime.getTime()) : null;
  }

  public int getId() {
    return this.id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Line getLine() {
    return this.line;
  }

  public void setLine(Line line) {
    this.line = line;
  }

  public boolean getIsComingBack() {
    return this.isComingBack;
  }

  public void setIsComingBack(boolean isComingBack) {
    this.isComingBack = isComingBack;
  }

  public Date getDepartureTime() {
    return departureTime != null ? new Date(departureTime.getTime()) : null;
  }

  public void setDepartureTime(Date departureTime) {
    this.departureTime = departureTime != null ? new Date(departureTime.getTime()) : null;
  }
}
