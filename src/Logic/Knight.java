package Logic;

import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(PieceColor color) {
        super(color, PieceType.KNIGHT);
    }

    private int[][] possibleMoves = {
            {2, 1},
            {2, -1},
            {1, 2},
            {1, -2},
            {-2, 1},
            {-2, -1},
            {-1, 2},
            {-1, -2}
    };

    @Override
    public int[][] getPossibleMoves(Piece[][] board, int i, int j) {
        ArrayList<int[]> moves = new ArrayList<>();

        for(int k = 0; k < possibleMoves.length; k++){
            // if move is on board
            if(inBoard(i + possibleMoves[k][0], j + possibleMoves[k][1], board)) {
                Piece targetPiece = board[i + possibleMoves[k][0]][j + possibleMoves[k][1]];
                if(targetPiece == null || targetPiece.color != this.color)
                    moves.add(new int[]{i + possibleMoves[k][0], j + possibleMoves[k][1]});
            }
        }

        return fixOutFormat(moves.toArray());
    }
}
