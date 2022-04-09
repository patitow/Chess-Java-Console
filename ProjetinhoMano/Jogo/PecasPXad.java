package ProjetinhoMano.Jogo;

public abstract class PecasPXad {
    private final PieceType type;
    private final PieceColor color;
    private final Key.Movimento[] movimentos;
    private final String name;
    private final char charValue;
    private final boolean repeatableMoves;

    protected PecasPXad(PieceType type, PieceColor color, Key.Movimento[] movimentos, boolean repeatableMoves) {
        this.type = type;
        this.color = color;
        this.movimentos = movimentos;
        this.repeatableMoves = repeatableMoves;
        name = type.name();
        charValue = type.name().trim().charAt(0);
    }

    public enum PieceType {
        Pawn, Rook, Knight, Bishop, Queen, King
    }

    public enum PieceColor {
        White, Black
    }

    public Key.Movimento[] getMoves() {
        return movimentos;
    }

    public String getName() {
        return name;
    }

    public PieceColor getColor() {
        return color;
    }

    public char getCharValue() {
        return charValue;
    }

    public boolean hasRepeatableMoves() {
        return repeatableMoves;
    }

    public PieceType getPieceType() {
        return type;
    }

    public static PieceColor player2(PieceColor color) {
        return (color == PieceColor.Black) ? PieceColor.White : PieceColor.Black;
    }

}
