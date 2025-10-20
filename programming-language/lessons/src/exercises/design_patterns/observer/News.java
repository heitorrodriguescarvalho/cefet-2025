package exercises.design_patterns.observer;

import java.util.ArrayList;

interface Observer {
  void update(String message);
}

public class News {
  ArrayList<Observer> subscribers = new ArrayList<>();

  public void subscribe(Subscriber subscriber) {
    this.subscribers.add(subscriber);
  }

  public void addNews(String news) {
    for (Observer subscriber : this.subscribers) {
      subscriber.update(news);
    }
  }
}

class Subscriber implements Observer {
  public String name;

  public Subscriber(String name) {
    this.name = name;
  }

  public void update(String news) {
    System.out.println(this.name + " recebeu a notícia: " + news);
  }
}
