package repos;

public interface Repository {
  public void create(Object obj);

  public Object get(int id);

  public void update(int id, Object newObj);

  public void delete(int id);
}