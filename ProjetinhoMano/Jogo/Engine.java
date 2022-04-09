package ProjetinhoMano.Jogo;

import ProjetinhoMano.Jogo.PecasPXad.*;

import java.util.ArrayList;

public class Engine {

    private final Tabuleiro board;
    private boolean isFinished;
    private PieceColor currentPlayer;

    public Engine() {
        board = new Tabuleiro();
        currentPlayer = PieceColor.White;
        isFinished = false;
    }


    public boolean playMove(Tabuleiro.ValidaPar from, Tabuleiro.ValidaPar to) {
        if (isValidMove(from, to, false)) {
            Key fromKey = board.getBoardArray()[from.Y()][from.X()];
            PecasPXad pieceToMove = fromKey.getPiece();

            Key toKey = board.getBoardArray()[to.Y()][to.X()];
            toKey.setPiece(pieceToMove);

            fromKey.vazio();
            endTurn();
            return true;
        } else {
            return false;
        }
    }

    //retorna o tabuleiro com as peças atualizadas pelo GetLocatePecas
    public Tabuleiro getBoard() {
        return board;
    }

    //retorna a validação de que o jogo se encerrou
    public boolean isFinished() {
        return isFinished;
    }

    //método que impede que o jogador do turno exerça uma jogada a mais
    private void endTurn() {
        currentPlayer = (currentPlayer == PieceColor.White)
                ? PieceColor.Black
                : PieceColor.White;
    }


    //método que garante que o oponente não vá para uma posição aleatória que fere as regras do jogo
    private boolean canOpponentTakeLocation(Tabuleiro.ValidaPar location, PieceColor color) {
        PieceColor opponentColor = PecasPXad.player2(color);
        Tabuleiro.ValidaPar[] piecesLocation = board.getLocatePecas(opponentColor);

        for (Tabuleiro.ValidaPar fromValidaPar : piecesLocation) {
            if (isValidMove(fromValidaPar, location, true))
                return true;
        }
        return false;
    }


    //método que analisará a jogada e retornara a permissao caso a jogada seja possivel
    private boolean isValidMove(Tabuleiro.ValidaPar from, Tabuleiro.ValidaPar to, boolean validate) {
        Key fromKey = board.getTileFromTuple(from);
        Key toKey = board.getTileFromTuple(to);
        PecasPXad fromPiece = fromKey.getPiece();
        PecasPXad toPiece = toKey.getPiece();

        if (fromPiece == null) {
            return false;
        } else if (fromPiece.getColor() != currentPlayer) {
            return false;
        } else if (toPiece != null && toPiece.getColor() == currentPlayer) {
            return false;
        } else if (isValidMoveForPiece(from, to)) {
            //caso a jogada seja validada será retornado a permissao
            if (validate){
                return true;
            }

            toKey.setPiece(toPiece);
            fromKey.setPiece(fromPiece);

            return true;
        }
        return false;
    }

    //método de análise para todos os movimentos que um peça pode exercer, ou jogadas multiplas
    //executadas por ela
    private Tabuleiro.ValidaPar[] validMovesForPiece(PecasPXad piece, Tabuleiro.ValidaPar currentLocation) {
        return piece.hasRepeatableMoves()
                ? validMovesRepeatable(piece, currentLocation)
                : validMovesNonRepeatable(piece, currentLocation);
    }

    private Tabuleiro.ValidaPar[] validMovesRepeatable(PecasPXad piece, Tabuleiro.ValidaPar currentLocation) {
        Key.Movimento[] movimentos = piece.getMoves();
        ArrayList<Tabuleiro.ValidaPar> possibleMoves = new ArrayList<>();

        for (Key.Movimento movimento : movimentos) {
            for (int i = 1; i < 7; i++) {
                int newX = currentLocation.X() + movimento.x * i;
                int newY = currentLocation.Y() + movimento.y * i;
                if (newX < 0 || newX > 7 || newY < 0 || newY > 7) break;

                Tabuleiro.ValidaPar toLocation = new Tabuleiro.ValidaPar(newX, newY);
                Key key = board.getTileFromTuple(toLocation);
                if (key.isVazio()) {
                    possibleMoves.add(toLocation);
                } else {
                    if (key.getPiece().getColor() != piece.getColor())
                        possibleMoves.add(toLocation);
                    break;
                }
            }
        }
        return possibleMoves.toArray(new Tabuleiro.ValidaPar[0]);
    }


    //método que impede movimentos ilegais que a peça exercerá.
    private Tabuleiro.ValidaPar[] validMovesNonRepeatable(PecasPXad piece, Tabuleiro.ValidaPar currentLocation) {
        Key.Movimento[] movimentos = piece.getMoves();
        ArrayList<Tabuleiro.ValidaPar> possibleMoves = new ArrayList<>();

        for (Key.Movimento movimento : movimentos) {
            int currentX = currentLocation.X();
            int currentY = currentLocation.Y();
            int newX = currentX + movimento.x;
            int newY = currentY + movimento.y;
            if (newX < 0 || newX > 7 || newY < 0 || newY > 7) continue;
            Tabuleiro.ValidaPar newLocation = new Tabuleiro.ValidaPar(newX, newY);
            if (isValidMoveForPiece(currentLocation, newLocation)) possibleMoves.add(newLocation);
        }
        return possibleMoves.toArray(new Tabuleiro.ValidaPar[0]);
    }

    //método de análise para jogadas válidas das peças
    private boolean isValidMoveForPiece(Tabuleiro.ValidaPar from, Tabuleiro.ValidaPar to) {
        PecasPXad fromPiece = board.getTileFromTuple(from).getPiece();
        boolean repeatableMoves = fromPiece.hasRepeatableMoves();

        return repeatableMoves
                ? isValidMoveForPieceRepeatable(from, to)
                : isValidMoveForPieceNonRepeatable(from, to);
    }

    private boolean isValidMoveForPieceRepeatable(Tabuleiro.ValidaPar from, Tabuleiro.ValidaPar to) {
        PecasPXad fromPiece = board.getTileFromTuple(from).getPiece();
        Key.Movimento[] validMovimentos = fromPiece.getMoves();

        int xMove = to.X() - from.X();
        int yMove = to.Y() - from.Y();

        for (int i = 1; i <= 7; i++) {
            for (Key.Movimento movimento : validMovimentos) {

                //anáslise de movimentos possíveis
                if (movimento.x * i == xMove && movimento.y * i == yMove) {

                    for (int j = 1; j <= i; j++) {
                        Key key = board.getTileFromTuple(new Tabuleiro.ValidaPar(from.X() + movimento.x * j, from.Y() + movimento.y * j));
                        //if passing through non vazio key return false
                        if (j != i && !key.isVazio())
                            return false;

                        //checa se o slot é vazio e se for, permitirá o acesso aquele slot no layout do tabuleiro
                        if (j == i && (key.isVazio() || key.getPiece().getColor() != currentPlayer))
                            return true;
                    }
                }
            }
        }
        return false;
    }

    private boolean isValidMoveForPieceNonRepeatable(Tabuleiro.ValidaPar from, Tabuleiro.ValidaPar to) {
        PecasPXad fromPiece = board.getTileFromTuple(from).getPiece();
        Key.Movimento[] validMovimentos = fromPiece.getMoves();
        Key toKey = board.getTileFromTuple(to);

        int xMove = to.X() - from.X();
        int yMove = to.Y() - from.Y();

        for (Key.Movimento movimento : validMovimentos) {
            if (movimento.x == xMove && movimento.y == yMove) {
                if (movimento.emCaptura) {
                    //verifica se ação de movimento para os peões são validas
                    if (toKey.isVazio()) return false;

                    PecasPXad toPiece = toKey.getPiece();
                    return fromPiece.getColor() != toPiece.getColor();//se for um player diferente, valida o movimento

                    //checa se é o primeiro movimento do peão, se caso for, permite o avanço de 2 slots
                } else if (movimento.primeiroMovimento) {
                    return toKey.isVazio() && isFirstMoveForPawn(from, board);
                } else {
                    return toKey.isVazio();
                }
            }
        }
        return false;
    }

    public boolean isFirstMoveForPawn(Tabuleiro.ValidaPar from, Tabuleiro board) {
        Key key = board.getTileFromTuple(from);
        if (key.isVazio() || key.getPiece().getPieceType() != PieceType.Pawn) {
            return false;
        } else {
            PieceColor color = key.getPiece().getColor();
            return (color == PieceColor.White)
                    ? from.Y() == 6
                    : from.Y() == 1;
        }
    }
}
