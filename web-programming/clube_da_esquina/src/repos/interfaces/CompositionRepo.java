package repos.interfaces;

import java.util.List;

import models.Composition;

public interface CompositionRepo {
  void create(Composition composition);

  Composition retrieve(int musicId, int artistId);

  List<Composition> retrieveByArtist(int artistId);

  List<Composition> retrieveByMusic(int musicId);

  List<Composition> retrieveAll();

  void update(Composition currentComposition, Composition updatedComposition);

  void delete(int artistId, int musicId);
}
