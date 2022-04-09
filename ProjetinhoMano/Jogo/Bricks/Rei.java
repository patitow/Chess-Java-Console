package ProjetinhoMano.Jogo.Bricks;
import ProjetinhoMano.Jogo.Key;
import ProjetinhoMano.Jogo.PecasPXad;

public class Rei extends PecasPXad {
    public Rei(PecasPXad.PieceColor color){
        super(PieceType.King, color, validMoves(), false);
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
