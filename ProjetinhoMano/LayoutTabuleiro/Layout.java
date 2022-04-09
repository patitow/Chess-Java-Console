package ProjetinhoMano.LayoutTabuleiro;

import ProjetinhoMano.Jogo.Tabuleiro;
import ProjetinhoMano.Jogo.Key;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Layout {

    public static void printBoard(Tabuleiro board) {
        clearConsole();
        Key[][] b = board.getBoardArray();

        System.out.println("\n\t\t\t\t\t\t  Xadrez UPE\n");
        System.out.println("P = Peão | R = Torre | K = Cavalo / Rei | B = Bispo | Q = Rainha  ");
        System.out.println();
        System.out.println("      |A||B||C||D||E||F||G||H| \n");
        for (int i = 0; i < 8; i++) {
            System.out.print("|" + (8 - i) + "|   ");

            for (int j = 0; j < 8; j++) {
                System.out.print(b[i][j].getValue());
            }

            System.out.println("   |" + (8 - i) + "|");
        }

        System.out.println("\n      |A||B||C||D||E||F||G||H|\n");
    }


    //método responsável na limpeza ASCII da shell do Windows no Looping-Game
    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");

            if (os.contains("Windows")) {
                //ASCII escape code
                System.out.print("\033[H\033[2J");
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Erro na execução do comando clean terminal");
        }
    }

    static class CoordPerc {

        public CoordPerc() {

        }

        public int map(int val) {
            switch (val) {
                case 1:
                    return 7;
                case 2:
                    return 6;
                case 3:
                    return 5;
                case 4:
                    return 4;
                case 5:
                    return 3;
                case 6:
                    return 2;
                case 7:
                    return 1;
                case 8:
                    return 0;
            }
            return -1;
        }

        public int map(char val) {
            switch (Character.toLowerCase(val)) {
                case 'a':
                    return 0;
                case 'b':
                    return 1;
                case 'c':
                    return 2;
                case 'd':
                    return 3;
                case 'e':
                    return 4;
                case 'f':
                    return 5;
                case 'g':
                    return 6;
                case 'h':
                    return 7;
            }
            return -1;
        }
    }

    public static class EntradaUsuario {

        private final static Pattern validMove = Pattern.compile("([a-hA-H][1-8])([:])([a-hA-H][1-8])", Pattern.CASE_INSENSITIVE);
        private final CoordPerc mapper;

        public EntradaUsuario() {
            mapper = new CoordPerc();
        }

        public Tabuleiro.ValidaPar parse(String val) {
            int x = mapper.map(val.charAt(0));
            int y = mapper.map(Integer.parseInt(String.valueOf(val.charAt(1))));

            return new Tabuleiro.ValidaPar(x, y);
        }

        public Tabuleiro.ValidaPar getFrom(String val) {
            Matcher matcher = validMove.matcher(val);
            matcher.matches();
            String coords = matcher.group(1);

            return parse(coords);
        }

        public Tabuleiro.ValidaPar getTo(String val) {
            Matcher matcher = validMove.matcher(val);
            matcher.matches();
            String coords = matcher.group(3);

            return parse(coords);
        }

        public boolean isValid(String val) {
            Matcher matcher = validMove.matcher(val);

            return matcher.matches();
        }
    }
}
