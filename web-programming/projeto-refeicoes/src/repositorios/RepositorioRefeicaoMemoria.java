package repositorios;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import modelos.Refeicao;

public class RepositorioRefeicaoMemoria implements RepositorioRefeicao {
  private ArrayList<Refeicao> listaRefeicoes = new ArrayList<Refeicao>();

  @Override
  public void createRefeicao(Refeicao r) {
    if (this.listaRefeicoes.contains(r))
      return;

    this.listaRefeicoes.add(r);
  }

  @Override
  public void deleteRefeicao(Refeicao r) {
    int indiceASerDeletado = -1;
    for (int i = 0; i < this.listaRefeicoes.size(); i++) {
      Refeicao aux = this.listaRefeicoes.get(i);
      if (aux.getData().equals(r.getData()) &&
          aux.getId().equals(r.getId())) {
        indiceASerDeletado = i;
        break;
      }
    }
    if (indiceASerDeletado != -1) {
      this.listaRefeicoes.remove(indiceASerDeletado);
    } else {
      System.err.println("Erro, o objeto a ser deletado não existe");
    }
  }

  @Override
  public Refeicao retrieveRefeicao(String id, LocalDate data) {
    for (int i = 0; i < this.listaRefeicoes.size(); i++) {
      Refeicao aux = this.listaRefeicoes.get(i);
      if (aux.getData().equals(data) &&
          aux.getId().equals(id)) {
        return aux;
      }
    }
    return null;
  }

  @Override
  public List<Refeicao> retrieveRefeicoes() {
    return this.listaRefeicoes;
  }

  @Override
  public void updateRefeicao(Refeicao rOld, Refeicao rNew) {
    int indiceASerAtualizado = -1;
    for (int i = 0; i < this.listaRefeicoes.size(); i++) {
      Refeicao aux = this.listaRefeicoes.get(i);
      if (rOld.getData().equals(aux.getData()) &&
          rOld.getId().equals(aux.getId())) {
        indiceASerAtualizado = i;
        break;
      }
    }
    if (indiceASerAtualizado == -1) {
      System.err.println("Erro o objeto a ser atualizado não existe");
      return;
    }
    this.listaRefeicoes.set(indiceASerAtualizado, rNew);
  }
}
