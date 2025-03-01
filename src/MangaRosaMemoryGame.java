import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class MangaRosaMemoryGame {

    public static void main(String[] args) {
        menu();
    }

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int opcao;
        int tamanhoJogo;
        String player01, player02, color1;
        boolean exit = false;

        while (!exit) {
            System.out.println("\n------------------------------------------------------------------------------");
            System.out.println("----------------------------Manga Rosa Memory Game----------------------------");
            System.out.println("------------------------------------------------------------------------------");
            System.out.println(" 1- INICIAR\n 2- PONTUAÇÃO PARTICIPANTES\n 3- REGRAS DO JOGO\n 4- SAIR\n");
            System.out.println("------------------------------------------------------------------------------");
            System.out.print("Informe sua opção: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // consumindo o '\n'

            switch (opcao){
                case 1: {
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("----------------------------Configurações do Jogo-----------------------------");
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.println("Qual tamanho do tabuleiro deseja jogar?");
                    System.out.println(" 1- 4x4\n 2- 6x6\n 3- 8x8\n 4- 10x10\n");
                    System.out.print("Digite a opção: ");
                    int tamanhoOpcao = scanner.nextInt();
                    scanner.nextLine();

                    switch(tamanhoOpcao){
                        case 1: tamanhoJogo = 4; break;
                        case 2: tamanhoJogo = 6; break;
                        case 3: tamanhoJogo = 8; break;
                        case 4: tamanhoJogo = 10; break;
                        default:
                            System.out.println("Opção inválida, tamanho padrão 4x4 será utilizado.");
                            tamanhoJogo = 4;
                            break;
                    }

                    System.out.println("------------------------------------------------------------------------------");
                    System.out.print("Qual o nome do Jogador 01: ");
                    player01 = scanner.nextLine().trim();
                    if(player01.isEmpty()){
                        player01 = "PARTICIPANTE01";
                    }
                    System.out.println("------------------------------------------------------------------------------");
                    System.out.print("Qual o nome do Jogador 02: ");
                    player02 = scanner.nextLine().trim();
                    if(player02.isEmpty()){
                        player02 = "PARTICIPANTE02";
                    }
                    System.out.println("------------------------------------------------------------------------------");
                    color1 = "vermelho";

                    // Cria e inicia o jogo com as configurações informadas
                    Game game = new Game(tamanhoJogo, player01, player02, color1);
                    game.playGame();
                    break;
                }
                case 2: {
                    //Pontuação de todos os pontos ganhos? Jogos ganhos?
                    break;
                }
                case 3: {
                    System.out.println("\n 1 - Cada participante deve ter atribuído a si uma cor (vermelho ou azul) no início do jogo.");
                    System.out.println("\n 2 - Todo participante deve ter um nome registrado. Senão, o nome padrão “PARTICIPANTE01” e “PARTICIPANTE02” devem ser atribuídos a cada um das(os) participantes.");
                    System.out.println("\n 3 - Cada participante possui uma pontuação atrelada a si.");
                    System.out.println("\n 4 - Se a(o) participante encontrar um par de cartas com fundo amarelo, fatura 1 ponto.");
                    System.out.println("\n 5 - Se a(o) participante encontrar um par de cartas com o fundo da sua cor, fatura 5 pontos.");
                    System.out.println("\n 6 - Se a(o) participante encontrar um par de cartas com o fundo da cor de seu adversário e errar, perde 2 pontos. Porém, se acertar, ganha apenas 1 ponto.");
                    System.out.println("\n 7 - A(o) participante não pode ter pontuação negativa. Se ela(ele) perder mais pontos do que possui, ficará com a pontuação zerada.");
                    System.out.println("\n 8 - Se a(o) participante encontrar uma carta com o fundo preto e errar o seu par, perde o jogo, mesmo que tenha a pontuação superior à da(o) outra(o) participante. Mas se acertar, ganha o jogo.");
                    break;
                }
                case 4: {
                    exit = true;
                    System.out.println("Saindo do jogo. Até mais!");
                    break;
                }
                default: {
                    System.out.println("Opção inválida!");
                    break;
                }
            }
        }
        scanner.close();
    }
}

class Card {
    String pairId;      // Identificador do par (para comparar se duas cartas formam um par)
    String color;       // Cor de fundo: "vermelho", "azul", "amarelo" ou "preto"
    boolean revealed;   // Indica se a carta já foi virada

    public Card(String pairId, String color) {
        this.pairId = pairId;
        this.color = color;
        this.revealed = false;
    }

    @Override
    public String toString() {
        final String CIANO = "\u001B[36m";
        final String RESET = "\u001B[0m";
        if (revealed) {
            // Exibe o identificador do par e a primeira letra da cor em maiúsculo
            return pairId + "(" + Character.toUpperCase(color.charAt(0)) + ")";
        } else {
            return CIANO + "[ C ]" + RESET;
        }
    }
}

class Board {
    int size;
    Card[][] grid;

    public Board(int size) {
        this.size = size;
        generateBoard();
    }

    // Gera o tabuleiro conforme as regras de distribuição:
    // - 1 par com fundo preto;
    // - Metade dos pares distribuídos entre vermelho e azul (divididos igualmente);
    // - O restante dos pares com fundo amarelo.
    private void generateBoard() {
        final String VERMELHO = "\u001B[31m";
        final String AMARELO = "\u001B[33m";
        final String PRETO = "\u001B[30m";
        final String AZUL = "\u001B[34m";
        final String RESET = "\u001B[0m";

        int totalCards = size * size;
        int totalPairs = totalCards / 2;

        int totalRedBluePairs = totalPairs / 2;
        int redPairs = (int) Math.ceil(totalRedBluePairs / 2.0);
        int bluePairs = totalRedBluePairs - redPairs;
        int blackPairs = 1;
        int yellowPairs = totalPairs - (redPairs + bluePairs + blackPairs);

        List<Card> cards = new ArrayList<>();

        // Cria os pares vermelhos
        for (int i = 1; i <= redPairs; i++) {
            String id = VERMELHO + "R" + i + RESET;
            cards.add(new Card(id, "vermelho"));
            cards.add(new Card(id, "vermelho"));
        }
        // Cria os pares azuis
        for (int i = 1; i <= bluePairs; i++) {
            String id = AZUL + "B" + i + RESET;
            cards.add(new Card(id, "azul"));
            cards.add(new Card(id, "azul"));
        }
        // Cria o par preto
        for (int i = 1; i <= blackPairs; i++) {
            String id = PRETO + "K" + RESET;
            cards.add(new Card(id, "preto"));
            cards.add(new Card(id, "preto"));
        }
        // Cria os pares amarelos
        for (int i = 1; i <= yellowPairs; i++) {
            String id = AMARELO + "Y" + i + RESET;
            cards.add(new Card(id, "amarelo"));
            cards.add(new Card(id, "amarelo"));
        }

        Collections.shuffle(cards);

        grid = new Card[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                grid[i][j] = cards.get(i * size + j);
            }
        }
    }

    public void displayBoard() {
        final String MAGENTA = "\u001B[35m";
        final String RESET = "\u001B[0m";

        System.out.println("\nTabuleiro:");

        // Imprime os índices das colunas no topo
        System.out.print("       "); // Espaço para alinhar com os índices das linhas
        for (int j = 0; j < size; j++) {
            System.out.print("| " + (j + 1) + " |  ");
        }
        System.out.println("\n");

        // Imprime as linhas com índices na lateral
        for (int i = 0; i < size; i++) {
            System.out.print("| " + (i + 1) + " |  "); // Índice da linha
            for (int j = 0; j < size; j++) {
                System.out.print(grid[i][j].toString() + "  "); // Imprime a carta
            }
            System.out.println("\n"); // Espaçamento entre as linhas
        }
        System.out.println();
    }


    public boolean allRevealed() {
        for (int i = 0; i < size; i++){
            for (int j = 0; j < size; j++){
                if (!grid[i][j].revealed)
                    return false;
            }
        }
        return true;
    }

    public Card getCard(int row, int col) {
        return grid[row][col];
    }
}


class Player {
    String name;
    String color;
    int score;

    public Player(String name, String color) {
        this.name = name;
        this.color = color;
        this.score = 0;
    }

    public Player(){

    }

    public void addScore(int points) {
        score += points;
    }

    public void subtractScore(int points) {
        score -= points;
        if (score < 0) {
            score = 0;
        }
    }
}

class Game {
    Board board;
    Player[] players = new Player[2];
    int currentPlayerIndex = 0;
    boolean gameOver = false;
    Scanner scanner = new Scanner(System.in);

    public Game (){

    }
    public Game(int boardSize, String player01Name, String player02Name, String player01Color) {
        board = new Board(boardSize);
        String player1Color = player01Color;
        String player2Color = player01Color.equals("vermelho") ? "azul" : "vermelho";
        players[0] = new Player(player01Name, player1Color);
        players[1] = new Player(player02Name, player2Color);
    }

    // Solicita e valida a posição da carta a ser virada
    // Retorna um array com {linha, coluna} ou null se o jogador errar 3 vezes
    public int[] getValidPosition() {
        int attempts = 0;
        int size = board.size;
        while (attempts < 3) {
            System.out.print("Digite a posição (linha e coluna separados por espaço, ex: '2 3'): ");
            String input = scanner.nextLine();
            String[] parts = input.split(" ");
            if (parts.length != 2) {
                System.out.println("Posição da carta inválida, por favor, insira uma posição válida");
                attempts++;
                continue;
            }
            int row, col;
            try {
                row = Integer.parseInt(parts[0]) - 1;
                col = Integer.parseInt(parts[1]) - 1;
            } catch (NumberFormatException e) {
                System.out.println("Posição da carta inválida, por favor, insira uma posição válida");
                attempts++;
                continue;
            }
            if (row < 0 || row >= size || col < 0 || col >= size) {
                System.out.println("Posição da carta inválida, por favor, insira uma posição válida");
                attempts++;
                continue;
            }
            Card card = board.getCard(row, col);
            if (card.revealed) {
                System.out.println("A carta da posição informada já está virada, por favor, escolha outra posição");
                attempts++;
                continue;
            }
            return new int[]{row, col};
        }
        System.out.println("Você errou 3 vezes e perdeu a vez.");
        return null;
    }

    // Executa o turno do jogador atual
    public void playTurn() {
        Player currentPlayer = players[currentPlayerIndex];
        System.out.println("\nÉ a vez de " + currentPlayer.name + " (Cor: " + currentPlayer.color + ", Pontos: " + currentPlayer.score + ")");
        board.displayBoard();

        // Seleciona a primeira carta
        int[] pos1 = getValidPosition();
        if (pos1 == null) {
            return; // turno perdido por erros consecutivos
        }
        int row1 = pos1[0];
        int col1 = pos1[1];
        Card card1 = board.getCard(row1, col1);
        card1.revealed = true;
        board.displayBoard();

        // Seleciona a segunda carta
        int[] pos2 = getValidPosition();
        if (pos2 == null) {
            card1.revealed = false;
            return;
        }
        int row2 = pos2[0];
        int col2 = pos2[1];
        Card card2 = board.getCard(row2, col2);
        card2.revealed = true;
        board.displayBoard();

        // Verifica se as duas cartas formam um par
        if (card1.pairId.equals(card2.pairId)) {
            System.out.println("Par encontrado!");
            // Se for o par preto e o jogador acertar, vence imediatamente
            if (card1.color.equals("preto")) {
                System.out.println(currentPlayer.name + " encontrou o par preto e venceu o jogo!");
                gameOver = true;
                return;
            }
            // Atualiza a pontuação conforme a cor do par encontrado
            if (card1.color.equals(currentPlayer.color)) {
                currentPlayer.addScore(5);
                System.out.println("Você ganhou 5 pontos! (Pontuação: " + currentPlayer.score + ")");
            } else if (card1.color.equals("amarelo")) {
                currentPlayer.addScore(1);
                System.out.println("Você ganhou 1 ponto! (Pontuação: " + currentPlayer.score + ")");
            } else {
                // Se for o par da cor do adversário
                currentPlayer.addScore(1);
                System.out.println("Você ganhou 1 ponto (par do adversário)! (Pontuação: " + currentPlayer.score + ")");
            }
            // As cartas permanecem reveladas
        } else {
            System.out.println("Não foi um par.");
            // Se o jogador tentou encontrar o par do adversário e errou, perde 2 pontos
            String opponentColor = currentPlayer.color.equals("vermelho") ? "azul" : "vermelho";
            if (card1.color.equals(opponentColor) || card2.color.equals(opponentColor)) {
                currentPlayer.subtractScore(2);
                System.out.println("Erro ao tentar encontrar par do adversário. Você perde 2 pontos. (Pontuação: " + currentPlayer.score + ")");
            }
            // Regra especial: se alguma carta for preta e o par não for acertado, o jogador perde o jogo
            if (card1.color.equals("preto") || card2.color.equals("preto")) {
                System.out.println(currentPlayer.name + " errou o par preto e perdeu o jogo!");
                gameOver = true;
                return;
            }
            System.out.println("Pressione Enter para continuar...");
            scanner.nextLine();
            card1.revealed = false;
            card2.revealed = false;
        }

        if (!gameOver) {
            currentPlayerIndex = (currentPlayerIndex + 1) % 2;
        }

    }

    public void displayPlayersInfo() {
        System.out.println("\n------------------------------------------------------");
        System.out.println("---------------Manga Rosa Memory Game-----------------");
        for (Player player : players) {
            System.out.println("Nome: " + player.name + " | Cor: " + player.color + " | Pontuação: " + player.score);
        }
        System.out.println("------------------------------------------------------");
    }


    public void playGame() {
        while (!gameOver && !board.allRevealed()) {
            displayPlayersInfo();
            playTurn();
        }
        if (!gameOver) {
            System.out.println("O jogo terminou!");
            Player p1 = players[0];
            Player p2 = players[1];
            System.out.println(p1.name + " Pontos: " + p1.score);
            System.out.println(p2.name + " Pontos: " + p2.score);
            if (p1.score > p2.score) {
                System.out.println(p1.name + " venceu!");
            } else if (p2.score > p1.score) {
                System.out.println(p2.name + " venceu!");
            } else {
                System.out.println("Empate!");
            }
        }
    }
}
