package exercises.todo_list;

import java.util.Objects; // Para comparação segura de nulos

/**
 * Uma implementação didática de um Vetor (Array Dinâmico) Genérico.
 * Usa <T> (Tipo) para aceitar qualquer tipo de Objeto.
 * * @param <T> O tipo de elemento que este vetor irá armazenar.
 */
public class Vetor<T> {

  // --- Atributos ---
  private T[] elementos; // O array interno onde os dados são guardados
  private int tamanhoLogico; // Quantos elementos REALMENTE temos

  /**
   * Construtor
   * 
   * @param capacidadeInicial A capacidade máxima inicial do vetor.
   */
  // Suprimindo o aviso do 'cast' de Object[] para T[]
  @SuppressWarnings("unchecked")
  public Vetor(int capacidadeInicial) {
    if (capacidadeInicial <= 0) {
      throw new IllegalArgumentException("Capacidade deve ser maior que zero.");
    }
    // O "truque": não podemos fazer 'new T[...]', então criamos
    // um array de Object e fazemos o 'cast' (conversao).
    this.elementos = (T[]) new Object[capacidadeInicial];
    this.tamanhoLogico = 0;
  }

  public Vetor() {
    this(10);
  }

  /**
   * Adiciona um elemento ao FINAL do vetor.
   * Custo: O(1) (Amortizado, por causa do redimensionamento)
   * 
   * @param elemento O elemento a ser adicionado.
   * @return true se foi bem-sucedido.
   */
  public boolean adicionar(T elemento) {
    // Verifica se ainda temos espaço
    this.asseguraCapacidade();

    // Adiciona no final
    this.elementos[this.tamanhoLogico] = elemento;
    this.tamanhoLogico++;
    return true;
  }

  /**
   * Insere um elemento em uma POSIÇÃO específica.
   * "Empurra" (shift) os elementos para a direita.
   * Custo: O(N)
   * 
   * @param elemento O elemento a ser inserido.
   * @param posicao  O índice onde inserir.
   */
  public void inserir(T elemento, int posicao) {
    // Validação da posição
    if (posicao < 0 || posicao > this.tamanhoLogico) {
      throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
    }

    // Verifica se ainda temos espaço
    this.asseguraCapacidade();

    // 1. O "Shift" (Empurrar para a direita)
    // Começamos do FIM e vamos voltando
    for (int i = this.tamanhoLogico - 1; i >= posicao; i--) {
      this.elementos[i + 1] = this.elementos[i];
    }

    // 2. Inserir na posição vaga
    this.elementos[posicao] = elemento;

    // 3. Atualizar tamanho
    this.tamanhoLogico++;
  }

  /**
   * Remove um elemento de uma POSIÇÃO específica.
   * "Puxa" (shift) os elementos para a esquerda.
   * Custo: O(N)
   * 
   * @param posicao O índice do elemento a ser removido.
   * @return O elemento que foi removido.
   */
  public T remover(int posicao) {
    // Validação da posição
    if (posicao < 0 || posicao >= this.tamanhoLogico) {
      throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
    }

    // 1. Salva quem será removido (para retornar)
    T elementoRemovido = this.elementos[posicao];

    // 2. O "Shift" (Puxar para a esquerda)
    for (int i = posicao; i < this.tamanhoLogico - 1; i++) {
      this.elementos[i] = this.elementos[i + 1];
    }

    // 3. Atualizar tamanho
    this.tamanhoLogico--;

    // 4. [PONTO DIDÁTICO CRUCIAL]
    // Limpar a última posição. Se não fizermos isso, teremos uma
    // referência "fantasma" ao objeto, impedindo o Garbage Collector (GC)
    // de limpá-lo da memória. Isso é um VAZAMENTO DE MEMÓRIA.
    this.elementos[this.tamanhoLogico] = null;

    return elementoRemovido;
  }

  /**
   * Busca um elemento pelo seu CONTEÚDO.
   * Retorna o índice da primeira ocorrência.
   * Custo: O(N)
   * 
   * @param elemento O elemento a ser buscado.
   * @return O índice (int) ou -1 se não for encontrado.
   */
  public int buscar(T elemento) {
    for (int i = 0; i < this.tamanhoLogico; i++) {
      // [PONTO DIDÁTICO CRUCIAL]
      // Não podemos usar '=='. Devemos usar '.equals()'.
      // Usar 'Objects.equals()' é a forma mais segura, pois
      // ela trata o caso de 'elemento' ou 'this.elementos[i]' ser nulo.
      if (Objects.equals(this.elementos[i], elemento)) {
        return i;
      }
    }
    return -1; // Não encontrado
  }

  /**
   * Obtém o elemento de uma POSIÇÃO específica.
   * Custo: O(1) (A superpotência do Array!)
   * 
   * @param posicao O índice do elemento.
   * @return O elemento (do tipo T).
   */
  public T obter(int posicao) {
    // Validação da posição
    if (posicao < 0 || posicao >= this.tamanhoLogico) {
      // Lançar uma exceção é o comportamento padrão do Java
      throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
    }
    return this.elementos[posicao];
  }

  /**
   * Retorna o número de elementos REALMENTE no vetor.
   * 
   * @return O tamanho lógico (int).
   */
  public int getTamanho() {
    return this.tamanhoLogico;
  }

  /**
   * Retorna se o vetor esta vazio.
   * 
   * @return true se vazio.
   */
  public boolean estaVazio() {
    return this.tamanhoLogico == 0;
  }

  /**
   * Retorna se o vetor esta cheio.
   * 
   * @return true se cheio.
   */
  public boolean estaCheio() {
    return this.tamanhoLogico == this.elementos.length;
  }

  // --- Métodos Privados Auxiliares ---

  /**
   * Método auxiliar para garantir que o vetor tenha capacidade
   * de receber novos elementos. Se estiver cheio, ele dobra
   * a capacidade.
   */
  @SuppressWarnings("unchecked")
  private void asseguraCapacidade() {
    if (this.tamanhoLogico == this.elementos.length) {
      // O vetor está cheio. Hora de crescer!
      // Estratégia: Dobrar o tamanho.
      int novaCapacidade = this.elementos.length * 2;
      T[] novoVetor = (T[]) new Object[novaCapacidade];

      // Copiar os elementos antigos para o vetor novo
      // System.arraycopy() seria mais rápido, mas o 'for' é mais didático
      for (int i = 0; i < this.tamanhoLogico; i++) {
        novoVetor[i] = this.elementos[i];
      }

      // O vetor antigo será "esquecido" e o GC vai limpá-lo
      this.elementos = novoVetor;
    }
  }

  /**
   * Define um elemento em uma POSIÇÃO específica, substituindo
   * o elemento que já estava lá.
   * Custo: O(1)
   * 
   * @param posicao  O índice onde alterar.
   * @param elemento O novo elemento.
   * @throws IndexOutOfBoundsException se a posição for inválida.
   */
  public void definirPosicao(int posicao, T elemento) {
    // Validação da posição
    if (posicao < 0 || posicao >= this.tamanhoLogico) {
      throw new IndexOutOfBoundsException("Posição inválida: " + posicao);
    }
    this.elementos[posicao] = elemento;
  }

  /**
   * Remove e retorna o ÚLTIMO elemento do vetor.
   * Custo: O(1)
   * 
   * @return O último elemento.
   * @throws IndexOutOfBoundsException se o vetor estiver vazio.
   */
  public T removerUltimo() {
    if (this.tamanhoLogico == 0) {
      throw new IndexOutOfBoundsException("Vetor está vazio.");
    }
    // Remove da última posição (tamanhoLogico - 1)
    return this.remover(this.tamanhoLogico - 1);
  }

  /**
   * Remove e retorna o PRIMEIRO elemento do vetor.
   * Custo: O(N) (porque precisa fazer shift de todos os elementos)
   * 
   * @return O primeiro elemento.
   * @throws IndexOutOfBoundsException se o vetor estiver vazio.
   */
  public T removerPrimeiro() {
    if (this.tamanhoLogico == 0) {
      throw new IndexOutOfBoundsException("Vetor está vazio.");
    }
    // Remove da primeira posição (índice 0)
    return this.remover(0);
  }

  /**
   * Verifica se um elemento EXISTE no vetor.
   * Custo: O(N)
   * 
   * @param elemento O elemento a ser verificado.
   * @return true se o elemento existe, false caso contrário.
   */
  public boolean contem(T elemento) {
    return this.buscar(elemento) != -1;
  }

  /**
   * Um 'toString' customizado para imprimir o vetor de forma limpa,
   * mostrando apenas os elementos do tamanho lógico.
   */
  @Override
  public String toString() {
    // [10, 20, 30]
    if (this.tamanhoLogico == 0) {
      return "[]";
    }

    // StringBuilder é mais eficiente para montar Strings
    StringBuilder sb = new StringBuilder();
    sb.append("[");

    for (int i = 0; i < this.tamanhoLogico - 1; i++) {
      sb.append(this.elementos[i]);
      sb.append(", ");
    }

    // Adiciona o último elemento (sem vírgula)
    sb.append(this.elementos[this.tamanhoLogico - 1]);
    sb.append("]");

    return sb.toString();
  }
}