package ProjetinhoMano.Jogo;


public class Key {

    private PecasPXad piece;
    private final TileColor color;

    public enum TileColor {
        White, Black
    }


    public Key(TileColor color) {
        this.color = color;
    }

    public Key(TileColor color, PecasPXad piece) {
        this.color = color;
        this.piece = piece;
    }

    public void setPiece(PecasPXad piece) {
        this.piece = piece;
    }

    public PecasPXad getPiece() {
        return this.piece;
    }

    public String getValue() {
        if (piece != null) {
            return "[" + piece.getCharValue() + "]";
        } else {
            return "[ ]";
        }
    }

    public boolean isVazio() {
        return piece == null;
    }

    public void vazio() {
        piece = null;
    }

    public static class Movimento {
        public final int x;
        public final int y;
        public final boolean primeiroMovimento;
        public final boolean emCaptura;

        //método constutor
        public Movimento(int x, int y, boolean primeiroMovimento, boolean emCaptura) {
            this.x = x;
            this.y = y;
            this.primeiroMovimento = primeiroMovimento;
            this.emCaptura = emCaptura;
        }
    }
}
