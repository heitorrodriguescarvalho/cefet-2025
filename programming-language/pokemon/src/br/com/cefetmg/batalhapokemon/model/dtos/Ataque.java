package br.com.cefetmg.batalhapokemon.model.dtos;

import br.com.cefetmg.batalhapokemon.model.enums.Tipo;

// Java 21: Records são ótimos para DTOs simples como Ataques
// DTO: Data Transfer Object, objeto de transferencia de dados
public record Ataque(String nome, int poder, Tipo tipo) {
}