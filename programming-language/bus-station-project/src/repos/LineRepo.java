package repos;

import java.util.ArrayList;

import models.Line;

public class LineRepo implements Repository {
  private ArrayList<Line> lines = new ArrayList<>();

  private static LineRepo instance = null;

  public static LineRepo getInstance() {
    if (instance == null) {
      instance = new LineRepo();
    }

    return instance;
  }

  @Override
  public void create(Object line) {
    lines.add((Line) line);
  }

  @Override
  public Object get(int id) {
    for (Line line : lines) {
      if (line.getId() == id) {
        return line;
      }
    }

    return null;
  }

  @Override
  public void update(int id, Object newObj) {
    for (int i = 0; i < lines.size(); i++) {
      if (lines.get(i).getId() == id) {
        lines.set(i, (Line) newObj);

        return;
      }
    }
  }

  @Override
  public void delete(int id) {
    for (int i = 0; i < lines.size(); i++) {
      if (lines.get(i).getId() == id) {
        lines.remove(i);

        return;
      }
    }

  }
}