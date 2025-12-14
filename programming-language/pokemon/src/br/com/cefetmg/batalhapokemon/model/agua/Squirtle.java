package br.com.cefetmg.batalhapokemon.model.agua;

import br.com.cefetmg.batalhapokemon.model.Pokemon;
import br.com.cefetmg.batalhapokemon.model.dtos.Ataque;
import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

public class Squirtle extends Pokemon {
  public Squirtle(String apelido) {
    super(
        apelido,
        "Squirtle",
        Tipo.AGUA,
        1,
        44.0,
        48.0,
        65.0,
        43.0);

    adicionarAtaque(new Ataque("Tackle", 40, Tipo.NORMAL));
  }

  @Override
  public Pokemon evoluir() {
    return new Wartortle(this.getApelido());
  }

  @Override
  public void realizarSom() {
    System.out.println("Squir! Squir-tle!");
  }

}
