package ProjetinhoMano.Jogo.Bricks;

import ProjetinhoMano.Jogo.Key;
import ProjetinhoMano.Jogo.PecasPXad;

public class Peao extends PecasPXad {

    public Peao(PieceColor color) {
        super(PieceType.Pawn, color, validMoves(color), false);
    }

    private static Key.Movimento[] validMoves(PieceColor color) {
        if (color == PieceColor.Black) {
            return new Key.Movimento[]{new Key.Movimento(0, 1, false, false), new Key.Movimento(0, 2, true, false),
                    new Key.Movimento(1, 1, false, true), new Key.Movimento(-1, 1, false, true)};
        } else {
            return new Key.Movimento[]{new Key.Movimento(0, -1, false, false), new Key.Movimento(0, -2, true, false),
                    new Key.Movimento(1, -1, false, true), new Key.Movimento(-1, -1, false, true)};
        }
    }
}
