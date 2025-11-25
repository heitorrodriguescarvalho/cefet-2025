package repos.interfaces;

import java.util.List;

import models.Music;

public interface MusicRepo {
  void create(Music music);

  Music retrieve(int id);

  List<Music> retrieveAll();

  void update(Music currentMusic, Music updatedMusic);

  void delete(int id);
}
