# Documentação Completa do Código MangaRosaMemoryGame

Esta documentação detalha cada parte do código do **Manga Rosa Memory Game**, escrito em Java. O objetivo do jogo é encontrar pares de cartas em um tabuleiro, aplicando regras especiais de pontuação e condições de vitória. A seguir, encontram-se descritas as principais classes, métodos e a lógica do jogo.

---

## Sumário
- [Visão Geral](#visão-geral)
- [Requisitos e Dependências](#requisitos-e-dependências)
- [Estrutura do Código](#estrutura-do-código)
  - [1. Classe Principal: `MangaRosaMemoryGame`](#1-classe-principal-mangarosamemorygame)
  - [2. Classe `Card`](#2-classe-card)
  - [3. Classe `Board`](#3-classe-board)
  - [4. Classe `Player`](#4-classe-player)
  - [5. Classe `Game`](#5-classe-game)
- [Regras do Jogo](#regras-do-jogo)
- [Como Executar](#como-executar)

---

## Visão Geral

O código implementa um jogo de memória onde dois jogadores se revezam para revelar cartas em um tabuleiro. Cada carta pertence a um par, e as cartas podem ter diferentes cores que influenciam a pontuação dos jogadores:
- **Vermelho e Azul:** Distribuídos igualmente para cada jogador.
- **Amarelo:** Pontua 1 ponto ao acerto.
- **Preto:** Se acertado, garante vitória imediata; se errar, resulta na derrota do jogador.

Além disso, há penalidades para tentativas incorretas, e regras especiais relacionadas à escolha de cartas do adversário.

---

## Requisitos e Dependências

- **Linguagem:** Java
- **Bibliotecas Utilizadas:**
  - `java.util.Scanner` – para entrada de dados.
  - `java.util.ArrayList`, `java.util.List` – para manipulação de coleções de cartas.
  - `java.util.Collections` – para embaralhar as cartas.
  
O código pode ser compilado e executado em qualquer ambiente que suporte Java (JDK 8 ou superior).

---

## Estrutura do Código

### 1. Classe Principal: `MangaRosaMemoryGame`

- **Função:** Ponto de entrada do programa.
- **Métodos:**
  - `main(String[] args)`: Chama o método `menu()`, que inicia a interface do jogo.
  - `menu()`: Apresenta um menu interativo com as opções:
    - Iniciar o jogo (configuração do tabuleiro e jogadores).
    - Visualizar pontuação dos participantes (opção em aberto para implementação).
    - Exibir as regras do jogo.
    - Sair do programa.

**Fluxo de Execução:**
1. O menu é exibido em um loop até que o usuário opte por sair.
2. Ao escolher iniciar o jogo, o usuário define o tamanho do tabuleiro e os nomes dos jogadores (caso os nomes sejam omitidos, nomes padrão são atribuídos).
3. Uma instância de `Game` é criada com as configurações definidas e o jogo é iniciado através do método `playGame()`.

---

### 2. Classe `Card`

- **Objetivo:** Representar cada carta do jogo.
- **Atributos:**
  - `pairId`: Identificador do par (usado para verificar se duas cartas são correspondentes).
  - `color`: Cor de fundo da carta (pode ser "vermelho", "azul", "amarelo" ou "preto").
  - `revealed`: Indica se a carta já foi virada.
- **Construtor:**
  - Inicializa `pairId`, `color` e define `revealed` como `false`.
- **Método `toString()`:**
  - Se a carta estiver revelada, exibe seu `pairId` e a primeira letra da cor em maiúsculo.
  - Caso contrário, exibe uma representação padrão (ex. `[ C ]`) com destaque em ciano.

---

### 3. Classe `Board`

- **Objetivo:** Gerar e gerenciar o tabuleiro do jogo.
- **Atributos:**
  - `size`: Tamanho do tabuleiro (dimensão n x n).
  - `grid`: Matriz bidimensional que contém objetos `Card`.
- **Construtor:**
  - Recebe o tamanho do tabuleiro e chama `generateBoard()` para criar e distribuir as cartas.
- **Métodos Importantes:**
  - `generateBoard()`: 
    - Calcula o número total de cartas e de pares.
    - Define a distribuição dos pares conforme as regras:
      - 1 par preto.
      - Metade dos pares divididos entre vermelho e azul.
      - O restante dos pares com fundo amarelo.
    - Cria os objetos `Card` para cada par, embaralha-os e preenche a matriz `grid`.
  - `displayBoard()`: Exibe o tabuleiro na tela, mostrando índices de linhas e colunas, e o estado atual de cada carta.
  - `allRevealed()`: Retorna `true` se todas as cartas estiverem viradas.
  - `getCard(int row, int col)`: Retorna a carta na posição especificada.

---

### 4. Classe `Player`

- **Objetivo:** Representar os participantes do jogo.
- **Atributos:**
  - `name`: Nome do jogador.
  - `color`: Cor atribuída ao jogador.
  - `score`: Pontuação atual do jogador.
- **Construtores:**
  - Um construtor que inicializa `name` e `color`, e define a pontuação inicial como 0.
  - Um construtor padrão.
- **Métodos:**
  - `addScore(int points)`: Incrementa a pontuação do jogador.
  - `subtractScore(int points)`: Subtrai pontos e garante que a pontuação não fique negativa.

---

### 5. Classe `Game`

- **Objetivo:** Gerenciar a lógica do jogo, alternando turnos entre os jogadores, validando entradas e aplicando as regras de pontuação e penalidades.
- **Atributos:**
  - `Board board`: O tabuleiro onde o jogo ocorre.
  - `Player[] players`: Vetor com os dois jogadores.
  - `currentPlayerIndex`: Índice do jogador cuja vez é atual.
  - `gameOver`: Flag que indica se o jogo terminou.
  - `Scanner scanner`: Objeto para entrada de dados.
- **Construtores:**
  - Construtor padrão (pode ser estendido).
  - Construtor que recebe tamanho do tabuleiro, nomes dos jogadores e cor do jogador 1. A cor do jogador 2 é definida automaticamente como a cor oposta (vermelho ou azul).
- **Métodos Importantes:**
  - `getValidPosition()`: Solicita a posição de uma carta (linha e coluna) e realiza a validação. Permite até 3 tentativas antes de perder a vez.
  - `playTurn()`: 
    - Exibe as informações do jogador atual e o tabuleiro.
    - Solicita duas posições de cartas e revela-as.
    - Verifica se as duas cartas formam um par:
      - Se sim, aplica a pontuação de acordo com a cor:
        - Se a carta é da mesma cor do jogador, adiciona 5 pontos.
        - Se for amarelo, adiciona 1 ponto.
        - Se for do adversário (exceto a carta preta), adiciona 1 ponto.
      - Se o par for preto e acertado, o jogador vence imediatamente.
      - Se o par não for formado, penaliza o jogador:
        - Se a tentativa envolveu a carta do adversário, subtrai 2 pontos.
        - Se qualquer carta for preta e o par não for acertado, o jogador perde o jogo.
    - Alterna a vez entre os jogadores, caso o jogo não tenha terminado.
  - `displayPlayersInfo()`: Exibe a informação (nome, cor e pontuação) de cada jogador.
  - `playGame()`: Controla o loop principal do jogo, verificando a condição de término (quando todas as cartas forem reveladas ou se o jogo acabar prematuramente). Ao final, exibe o resultado final com a pontuação dos jogadores e declara o vencedor (ou empate).

---

## Regras do Jogo

As regras, conforme exibidas na opção 3 do menu, são as seguintes:

1. Cada participante deve escolher uma cor (vermelho ou azul) no início do jogo.
2. Os jogadores devem ter nomes registrados; caso contrário, os nomes padrão “PARTICIPANTE01” e “PARTICIPANTE02” serão usados.
3. Cada jogador possui uma pontuação.
4. Ao encontrar um par de cartas com fundo amarelo, o jogador ganha 1 ponto.
5. Se o par encontrado tiver o fundo da cor atribuída ao jogador, ele ganha 5 pontos.
6. Se o par pertencer à cor do adversário e o jogador errar a combinação, perde 2 pontos; se acertar, ganha apenas 1 ponto.
7. A pontuação não pode ser negativa; se o jogador perder mais pontos do que possui, a pontuação será zerada.
8. Se uma carta com fundo preto for selecionada e o par não for acertado, o jogador perde o jogo imediatamente. Se acertar, ganha o jogo.

---

## Como Executar

1. **Compilação:**
   - Certifique-se de ter o JDK instalado.
   - Compile o código usando o comando:
     ```bash
     javac MangaRosaMemoryGame.java
     ```
2. **Execução:**
   - Execute o jogo com o comando:
     ```bash
     java MangaRosaMemoryGame
     ```
3. **Interação:**
   - Siga as instruções do menu para iniciar o jogo, inserir nomes e escolher o tamanho do tabuleiro.
   - Durante cada turno, insira as posições (linha e coluna) das cartas que deseja revelar.
