package services;

import models.Ticket;
import models.Trip;
import repos.TicketRepo;

public class SalesService {
  public static Ticket sellTicket(Trip trip, int passagerAge) {
    if (ConsultaService.getRemainingSeats(trip.getId()) <= 0) {
      return null;
    }

    double price = passagerAge >= 60 ? 0 : trip.getLine().getPrice();

    Ticket ticket = new Ticket(trip, price);

    TicketRepo.getInstance().create(ticket);

    return ticket;
  }
}
