package ProjetinhoMano.Jogo;

import ProjetinhoMano.Jogo.Bricks.*;

import java.util.ArrayList;

public class Tabuleiro {
    private final Key[][] board;

    public Tabuleiro() {
        board = new Key[8][8];
        initializeBoard();
        percTabuleiro();
    }

    public Key[][] getBoardArray() {
        return board;
    }

    private void initializeBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                if (j % 2 + i == 0) board[i][j] = new Key(Key.TileColor.Black);
                else board[i][j] = new Key(Key.TileColor.White);
            }
        }
    }

    public ValidaPar getKLocate(PecasPXad.PieceColor color){
        ValidaPar location = new ValidaPar(-1,-1);
        for (int x = 0; x <= 7; x++){
            for (int y = 0; y <= 7 ; y++){
                if (!board[y][x].isVazio()) {
                    PecasPXad piece = board[y][x].getPiece();
                    if (piece.getColor() == color && piece instanceof Rei){
                        location = new ValidaPar(x, y);
                    }
                }
            }
        }
        return location;
    }



    //metódo de alocar e guardar as posiçoes das peças no layout do tabuleiro
    public ValidaPar[] getLocatePecas(PecasPXad.PieceColor color) {
        ArrayList<ValidaPar> locations = new ArrayList<>();
        for (int x = 0; x <= 7; x++) {
            for (int y = 0; y <= 7; y++) {
                if (!board[y][x].isVazio() && board[y][x].getPiece().getColor() == color)
                    locations.add(new ValidaPar(x, y));
            }
        }
        return locations.toArray(new ValidaPar[0]);//aloca novas arrays automaticamente
    }

    public Key getTileFromTuple(ValidaPar validaPar) {
        return board[validaPar.Y()][validaPar.X()];
    }


    private void percTabuleiro() {
        //peões
        for (int i = 0; i < 8; i++) {
            board[1][i].setPiece(new Peao(PecasPXad.PieceColor.Black));
            board[6][i].setPiece(new Peao(PecasPXad.PieceColor.White));
        }

        //torres
        board[0][0].setPiece(new Torre(PecasPXad.PieceColor.Black));
        board[0][7].setPiece(new Torre(PecasPXad.PieceColor.Black));
        board[7][0].setPiece(new Torre(PecasPXad.PieceColor.White));
        board[7][7].setPiece(new Torre(PecasPXad.PieceColor.White));

        //cavalos
        board[0][1].setPiece(new Cavalo(PecasPXad.PieceColor.Black));
        board[0][6].setPiece(new Cavalo(PecasPXad.PieceColor.Black));
        board[7][1].setPiece(new Cavalo(PecasPXad.PieceColor.White));
        board[7][6].setPiece(new Cavalo(PecasPXad.PieceColor.White));

        //bispos
        board[0][2].setPiece(new Bispo(PecasPXad.PieceColor.Black));
        board[0][5].setPiece(new Bispo(PecasPXad.PieceColor.Black));
        board[7][2].setPiece(new Bispo(PecasPXad.PieceColor.White));
        board[7][5].setPiece(new Bispo(PecasPXad.PieceColor.White));

        //Rainhas
        board[0][3].setPiece(new Rainha(PecasPXad.PieceColor.Black));
        board[7][3].setPiece(new Rainha(PecasPXad.PieceColor.White));

        //Reis
        board[0][4].setPiece(new Rei(PecasPXad.PieceColor.Black));
        board[7][4].setPiece(new Rei(PecasPXad.PieceColor.White));


    }

    public static class ValidaPar {
        private final int x;
        private final int y;

        public ValidaPar(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int X() {
            return x;
        }

        public int Y() {
            return y;
        }

    }
}
