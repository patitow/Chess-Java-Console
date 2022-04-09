package ProjetinhoMano.Jogo.Bricks;
import ProjetinhoMano.Jogo.Key;
import ProjetinhoMano.Jogo.PecasPXad;

public class Rainha extends PecasPXad {
    public Rainha(PecasPXad.PieceColor color){
        super(PieceType.Queen, color, validMoves(), true);
    }

    private static Key.Movimento[] validMoves(){
        return new Key.Movimento[]{	new Key.Movimento(1, 0, false, false),
                new Key.Movimento(0, 1, false, false),
                new Key.Movimento(-1, 0, false, false),
                new Key.Movimento(0, -1, false, false),
                new Key.Movimento(1, 1, false, false),
                new Key.Movimento(1, -1, false, false),
                new Key.Movimento(-1, 1, false, false),
                new Key.Movimento(-1, -1, false, false)};
    }
}
