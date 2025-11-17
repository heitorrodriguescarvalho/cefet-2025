Projeto: Controle de Refeições (linha de comando)
Estrutura:
- modelos/Refeicao.java
- repositorios/RepositorioRefeicao.java
- repositorios/RepositorioRefeicaoMemoria.java
- app/Main.java

Requisitos: JDK 11+ (ou 8+), pois usa java.time.LocalDate.

Compilar e executar:
No diretório onde estão as pastas 'modelos', 'repositorios' e 'app', execute:

javac modelos/Refeicao.java repositorios/RepositorioRefeicao.java repositorios/RepositorioRefeicaoMemoria.java app/Main.java
java app.Main

Observações:
- O modelo Refeicao usa apenas LocalDate (dd/MM/yyyy), portanto garante no máximo 1 refeição por estudante por dia (hora ignorada).
- O método 'create' lança IllegalArgumentException se tentar registrar uma refeição já existente (mesmo id e mesma data).
- Para simplificar, a opção de remover um índice faz a remoção na lista retornada pelo repositório em memória (isto é suficiente para testes locais). Se quiser, eu altero para prover um método de delete mais preciso no repositório.

