package app;

import controllers.RefeicaoController;
import repositorios.RepositorioRefeicao;
import repositorios.RepositorioRefeicaoSQL;
import views.RefeicaoView;

public class Main {
  public static void main(String[] args) {
    // Modelo/Repositório
    RepositorioRefeicao repositorio = new RepositorioRefeicaoSQL(
        "jdbc:mariadb://localhost:3306/refeicoes", "root", "");

    // View
    RefeicaoView view = new RefeicaoView();

    // Controller
    RefeicaoController controller = new RefeicaoController(repositorio, view);

    // Inicia a aplicação
    controller.executar();
  }
}
