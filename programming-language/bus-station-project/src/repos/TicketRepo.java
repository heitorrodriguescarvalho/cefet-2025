package repos;

import java.util.ArrayList;

import models.Ticket;

public class TicketRepo implements Repository {
  private ArrayList<Ticket> tickets = new ArrayList<>();

  private static TicketRepo instance = null;

  public static TicketRepo getInstance() {
    if (instance == null) {
      instance = new TicketRepo();
    }

    return instance;
  }

  @Override
  public void create(Object ticket) {
    tickets.add((Ticket) ticket);
  }

  public int getSoldTicketsCountByTripId(int tripId) {
    int count = 0;

    for (Ticket ticket : tickets) {
      if (ticket.getTrip().getId() == tripId) {
        count++;
      }
    }

    return count;
  }

  @Override
  public Object get(int id) {
    for (Ticket ticket : tickets) {
      if (ticket.getId() == id) {
        return ticket;
      }
    }

    return null;
  }

  @Override
  public void update(int id, Object newObj) {
    for (int i = 0; i < tickets.size(); i++) {
      if (tickets.get(i).getId() == id) {
        tickets.set(i, (Ticket) newObj);

        return;
      }
    }
  }

  @Override
  public void delete(int id) {
    for (int i = 0; i < tickets.size(); i++) {
      if (tickets.get(i).getId() == id) {
        tickets.remove(i);

        return;
      }
    }
  }
}