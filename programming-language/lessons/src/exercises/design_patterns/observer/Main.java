package exercises.design_patterns.observer;

class Main {
  static public void main(String[] args) {
    News newspaper = new News();

    Subscriber subscriber1 = new Subscriber("Assinante 1");
    Subscriber subscriber2 = new Subscriber("Assinante 2");

    newspaper.subscribe(subscriber1);

    newspaper.addNews("Notícia 1: Novo filme lançado!");

    newspaper.subscribe(subscriber2);

    newspaper.addNews("Notícia 2: Novo livro publicado!");
  }
}