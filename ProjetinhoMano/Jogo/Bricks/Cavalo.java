package ProjetinhoMano.Jogo.Bricks;
import ProjetinhoMano.Jogo.Key;
import ProjetinhoMano.Jogo.PecasPXad;

public class Cavalo extends PecasPXad {
    public Cavalo(PecasPXad.PieceColor color) {
        super(PieceType.Knight, color, validMoves(), false);
    }

    private static Key.Movimento[] validMoves() {
        return new Key.Movimento[]{new Key.Movimento(2, 1, false, false),
                new Key.Movimento(1, 2, false, false),
                new Key.Movimento(2, -1, false, false), new Key.Movimento(-1, 2, false, false),
                new Key.Movimento(2, -1, false, false), new Key.Movimento(-1, 2, false, false),
                new Key.Movimento(-2, 1, false, false), new Key.Movimento(1, -2, false, false),
                new Key.Movimento(-2, -1, false, false), new Key.Movimento(-1, -2, false, false),
                new Key.Movimento(-2, -1, false, false), new Key.Movimento(-1, -2, false, false)};
    }
}
