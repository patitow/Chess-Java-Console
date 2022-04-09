package ProjetinhoMano.Jogo.Bricks;

import ProjetinhoMano.Jogo.Key;
import ProjetinhoMano.Jogo.PecasPXad;

public class Torre extends PecasPXad {
    public Torre(PieceColor color){
        super(PieceType.Rook, color, validMoves(), true);
    }


    private static Key.Movimento[] validMoves(){
        return new Key.Movimento[]{	new Key.Movimento(1, 0, false, false), new Key.Movimento(0, 1, false, false),
                new Key.Movimento(-1, 0, false, false), new Key.Movimento(0, -1, false, false)};
    }
}
