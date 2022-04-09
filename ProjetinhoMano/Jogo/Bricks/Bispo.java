package ProjetinhoMano.Jogo.Bricks;
import ProjetinhoMano.Jogo.Key;
import ProjetinhoMano.Jogo.PecasPXad;
public class Bispo extends PecasPXad {
    public Bispo(PieceColor color){
        super(PieceType.Bishop, color, validMoves(), true);
    }

    private static Key.Movimento[] validMoves() {
        return	new Key.Movimento[]{	new Key.Movimento(1, 1, false, false),
                new Key.Movimento(1, -1, false, false),
                new Key.Movimento(-1, 1, false, false),
                new Key.Movimento(-1, -1, false, false)};
    }
}
