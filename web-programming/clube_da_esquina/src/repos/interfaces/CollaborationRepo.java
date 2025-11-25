package repos.interfaces;

import java.time.LocalDate;
import java.util.List;

import models.Collaboration;

public interface CollaborationRepo {
  void create(Collaboration collaboration);

  Collaboration retrieve(int artistId, int musicId);

  List<Collaboration> retrieveAll();

  void update(Collaboration currentCollaboration, Collaboration newCollaboration);

  void delete(int id, LocalDate date);
}
