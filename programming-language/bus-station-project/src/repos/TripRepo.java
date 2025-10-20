package repos;

import java.util.ArrayList;

import models.Trip;

public class TripRepo {
  private ArrayList<Trip> trips = new ArrayList<>();

  private static TripRepo instance = null;

  public static TripRepo getInstance() {
    if (instance == null) {
      instance = new TripRepo();
    }

    return instance;
  }

  public void create(Trip trip) {
    trips.add(trip);
  }

  public Trip get(int id) {
    for (Trip trip : trips) {
      if (trip.getId() == id) {
        return trip;
      }
    }

    return null;
  }

  public void update(int id, Trip newTrip) {
    for (int i = 0; i < trips.size(); i++) {
      if (trips.get(i).getId() == id) {
        trips.set(i, newTrip);

        return;
      }
    }
  }

  public void delete(int id) {
    for (int i = 0; i < trips.size(); i++) {
      if (trips.get(i).getId() == id) {
        trips.remove(i);

        return;
      }
    }
  }
}
