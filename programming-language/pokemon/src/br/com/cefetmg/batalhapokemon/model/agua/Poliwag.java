package br.com.cefetmg.batalhapokemon.model.agua;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Poliwag extends Pokemon {
  public Poliwag(String apelido) {
    super(
        apelido,
        "Poliwag",
        Tipo.AGUA,
        1,
        40.0,
        50.0,
        40.0,
        90.0);

    adicionarAtaque(new Ataque("Bubble", 40, Tipo.AGUA));
  }

  @Override
  public Pokemon evoluir() {
    return new Poliwhirl(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Poli-wag!");
  }

}
