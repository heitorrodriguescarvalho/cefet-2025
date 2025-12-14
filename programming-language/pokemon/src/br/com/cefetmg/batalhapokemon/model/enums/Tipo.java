package br.com.cefetmg.batalhapokemon.model.enums;

public enum Tipo {
    FOGO, AGUA, PLANTA, ELETRICO, NORMAL;

    /**
     * Calcula o multiplicador de dano.
     * @param tipoAtaque O tipo do movimento usado (ex: Água).
     * @param tipoDefensor O tipo do Pokémon que recebe o ataque (ex: Fogo).
     * @return 2.0 (Super Efetivo), 0.5 (Não muito efetivo), 1.0 (Neutro) ou 0.0 (Imune).
     */
    public static double obterMultiplicador(Tipo tipoAtaque, Tipo tipoDefensor) {
        // Switch Expression (Java 14+) - Retorna valor diretamente
        return switch (tipoAtaque) {
            case FOGO -> switch (tipoDefensor) {
                case PLANTA -> 2.0; // Fogo queima Planta
                case AGUA, FOGO -> 0.5; // Água apaga Fogo / Fogo resiste Fogo
                default -> 1.0;
            };
            case AGUA -> switch (tipoDefensor) {
                case FOGO -> 2.0; // Água apaga Fogo
                case PLANTA, AGUA -> 0.5; // Planta absorve Água
                default -> 1.0;
            };
            case PLANTA -> switch (tipoDefensor) {
                case AGUA -> 2.0; // Planta absorve Água
                case FOGO, PLANTA -> 0.5; // Fogo queima Planta
                default -> 1.0;
            };
            case ELETRICO -> switch (tipoDefensor) {
                case AGUA -> 2.0; // Água conduz eletricidade
                case PLANTA, ELETRICO -> 0.5;
                default -> 1.0;
            };
            // Normal geralmente é neutro, mas bate fraco em Pedra/Aço (não listados aqui)
            default -> 1.0;
        };
    }
}