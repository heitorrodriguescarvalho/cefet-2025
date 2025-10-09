package repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Refeicao;

class RepositorioRefeicaoMemoria implements RepositorioRefeicao {
  private ArrayList<Refeicao> listaRefeicoes = new ArrayList<>();

  @Override
  public void create(Refeicao refeicao) {
    listaRefeicoes.add(refeicao);
  }

  @Override
  public Refeicao retrieve(String idCartaoEstudante, LocalDate dataRefeicao) {
    for (Refeicao r : listaRefeicoes) {
      if (r.getId().equals(idCartaoEstudante) && r.getDataRefeicao().equals(dataRefeicao)) {
        return r;
      }
    }

    return null;
  }

  @Override
  public List<Refeicao> retrieveAll() {
    return this.listaRefeicoes;
  }

  @Override
  public void update(Refeicao oldRefeicao, Refeicao newRefeicao) {
    int index;

    for (int i = 0; i < listaRefeicoes.size(); i++) {
      Refeicao r = listaRefeicoes.get(i);
      if (r.getId().equals(oldRefeicao.getId()) && r.getDataRefeicao().equals(oldRefeicao.getDataRefeicao())) {
        index = listaRefeicoes.indexOf(r);
        this.listaRefeicoes.set(index, newRefeicao);

        return;
      }
    }

    System.out.println("Refeição não encontrada.");
  }

  @Override
  public void delete(Refeicao refeicao) {
    int index = -1;

    for (int i = 0; i < listaRefeicoes.size(); i++) {
      Refeicao r = listaRefeicoes.get(i);

      if (r.getId().equals(refeicao.getId()) && r.getDataRefeicao().equals(refeicao.getDataRefeicao())) {
        index = i;
        break;
      }
    }

    if (index == -1) {
      System.out.println("Refeição não encontrada.");
      return;
    }

    this.listaRefeicoes.remove(index);
  }
}