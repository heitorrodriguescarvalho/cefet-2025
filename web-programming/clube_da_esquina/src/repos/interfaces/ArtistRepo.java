package repos.interfaces;

import java.util.List;

import models.Artist;

public interface ArtistRepo {
  void create(Artist artist);

  Artist retrieve(int id);

  List<Artist> retrieveAll();

  void update(Artist currentArtist, Artist updatedArtist);

  void delete(int id);
}
