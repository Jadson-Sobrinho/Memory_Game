import java.util.Scanner;
public class MangaRosaMemoryGame {
    public static void main(String[] args) {
    menu();

    }

    public static void menu(){
        Scanner scanner = new Scanner(System.in);
        int start;

        System.out.println("\n------------------------------------------------------------------------------");
        System.out.println("----------------------------Manga Rosa Memory Game----------------------------");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println(" 1- INICIAR\n 2- PONTUAÇÃO PARTICIPANTES\n 3- REGRAS DO JOGO\n 4- SAIR\n");
        System.out.println("------------------------------------------------------------------------------");
        System.out.println("Informe sua opção: ");
        start = scanner.nextInt();

        switch (start){
            case 1:
            {
                int op;

                System.out.println("------------------------------------------------------------------------------");
                System.out.println("----------------------------Configurações do Jogo-----------------------------");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("Qual tamanho do tabuleiro dejesa jogar?");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------------");
                System.out.println("------------------------------------------------------------------------------");
                break;
            }
            case 2:
            {
                break;
            }
            case 3:
            {
                System.out.println("\n 1 - Cada participante deve ter atribuído a si uma cor (vermelho ou azul) no início do jogo. ");
                System.out.println("\n 2- Todo participante deve ter um nome registrado. Senão, o nome padrão “PARTICIPANTE01” e “PARTICIPANTE02” devem ser atribuídos a cada um das(os) participantes");
                System.out.println("\n 3- Cada participante possui uma pontuação atrelada a si.");
                System.out.println("\n 4- Se a(o) participante encontrar um par de cartas com fundo amarelo, fatura 1 ponto");
                System.out.println("\n 5- Se a(o) participante encontrar um par de cartas com o fundo da sua cor, fatura 5 pontos");
                System.out.println("\n 6- Se a(o) participante encontrar um par de cartas com o fundo da cor de seu adversário e errar, perde 2 pontos. Porém, se acertar, ganha apenas 1 ponto.  ");
                System.out.println("\n 7- A(o) participante não pode ter pontuação negativa. Se ela(ele) perder mais pontos do que possui, ficará com a pontuação zerada.");
                System.out.println("\n 8- Se a(o) participante encontrar uma carta com o fundo preto e errar o seu par, perde o jogo, mesmo que tenha a pontuação superior à da(o) outra(o) participante. Mas se acertar, ganha o jogo.");

                break;
            }
            case 4:
            {
                break;
            }
            default:
                System.out.println("Opção invalida!");
                break;

        }



    }
}