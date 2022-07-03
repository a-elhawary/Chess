package Logic;

import View.Board;

import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(PieceColor color) {
        super(color, PieceType.PAWN);
    }

    @Override
    public int[][] getPossibleMoves(Piece[][] board, int i, int j) {
        ArrayList<int[]> moves = new ArrayList<>();

        // depending on white or black move up or down the board
        int moveVector = Board.BLACK_START == 0 ? 1 : -1;
        int start = Board.BLACK_START;
        if (color == PieceColor.WHITE) {
            moveVector = Board.WHITE_START == 0 ? 1 : -1;
            start = Board.WHITE_START;
        }

        // if no piece is blocking move forward once
        if (board[i + moveVector][j] == null)
            moves.add(new int[]{i + moveVector, j});

        // if no piece is blocking and in starting square move forward twice
        if(i == start + moveVector && board[i + 2*moveVector][j] == null)
            moves.add(new int[]{i + 2*moveVector, j});

        // if an opposing piece is on the diagonal
        if(j + 1 < board.length && board[i + moveVector][j + 1] != null && board[i + moveVector][j+1].color != this.color)
            moves.add(new int[]{i+moveVector, j+1});

        if(j - 1 < board.length && board[i + moveVector][j - 1] != null && board[i + moveVector][j-1].color != this.color)
            moves.add(new int[]{i+moveVector, j-1});

        return fixOutFormat(moves.toArray());
    }
}
