package Logic;

import java.util.ArrayList;

public class King extends Piece{
    public King(PieceColor color) {
        super(color, PieceType.KING);
    }

    int[][] possibleMoves = {
            {1,1},
            {1,-1},
            {-1,1},
            {-1,-1},
            {1, 0},
            {-1, 0},
            {0, 1},
            {0, -1},
    };

    @Override
    public int[][] getPossibleMoves(Piece[][] board, int i, int j) {
        ArrayList<int[]> moves = new ArrayList<>();

        for(int k = 0; k < possibleMoves.length; k++){
            int[] possibleMove = {i + possibleMoves[k][0], j + possibleMoves[k][1]};
            if(inBoard(possibleMove[0], possibleMove[1], board)){
                if(board[possibleMove[0]][possibleMove[1]] == null || board[possibleMove[0]][possibleMove[1]].color != this.color)
                    moves.add(possibleMove);
            }
        }

        return fixOutFormat(moves.toArray());
    }
}
