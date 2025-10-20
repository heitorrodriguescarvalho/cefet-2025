package services;

import models.Trip;
import repos.TicketRepo;
import repos.TripRepo;

public class ConsultaService {
  public static int getRemainingSeats(int tripId) {
    Trip trip = TripRepo.getInstance().get(tripId);

    if (trip == null)
      return 0;

    return 40 - TicketRepo.getInstance().getSoldTicketsCountByTripId(tripId);
  }
}
