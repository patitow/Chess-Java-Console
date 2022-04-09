import ProjetinhoMano.Jogo.Engine;
import ProjetinhoMano.Jogo.Tabuleiro;
import ProjetinhoMano.LayoutTabuleiro.Layout;

import java.util.Scanner;

public class Game {

    public static void main(String args[]) throws Exception {
        Scanner scanner = new Scanner(System.in);
        System.out.println("1-Jogar\n2-Grupo\nSua Escolha: ");
        int opt = scanner.nextInt();
        if (opt == 1){
            String jogador[] = new String[2];
            System.out.println("Digite o nome do jogador 1:");
            jogador[0]=scanner.next();
            System.out.println("Digite o nome do jogador 2");
            jogador[1]=scanner.next();
            int vezdojogador=0;
            Layout.EntradaUsuario handler = new Layout.EntradaUsuario();
            Engine game = new Engine();
            Layout.clearConsole();
            Layout.printBoard(game.getBoard());
            while (!game.isFinished()) {
                if(vezdojogador>1){vezdojogador=0;}
                System.out.println("Jogador Acima: "+jogador[1]+" Jogador abaixo: "+jogador[0]);
                System.out.println("Vez do jogador: "+jogador[vezdojogador]+" Informe a Jogada (EX:c2:c4) ou digite desistir para desistir: ");
                String input = scanner.next();
                if(input.equals("desistir")){
                    vezdojogador++;
                    if(vezdojogador>1){vezdojogador=0;}
                    System.out.println("Fim de Jogo"+"\nVitória do Jogador: "+jogador[vezdojogador]);
                    return;
                }
                if (!handler.isValid(input)) {
                    System.out.println("Essa jogada não é possível, exemplos de jogadas válidas [c2:c4], [e7:e6], [h2:h3]");
                } else {
                    Tabuleiro.ValidaPar from = handler.getFrom(input);
                    Tabuleiro.ValidaPar to = handler.getTo(input);

                    boolean movePlayed = game.playMove(from, to);
                    if (!movePlayed)
                        System.out.println("Essa jogada fere as regras do Xadrez");
                    else {
                        Layout.clearConsole();
                        Layout.printBoard(game.getBoard());
                        vezdojogador++;
                    }
                }
            }
            scanner.close();
            System.out.println("Fim de Jogo");
        } else if (opt == 2){
            System.out.println("Alunos: \nSamuel Lima Maranhão\nMatheus Souza Oliveira");
        } else {
            System.out.println("Opção inválida");
        }
    }
}
