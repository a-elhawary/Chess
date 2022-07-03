package Logic;

public class Bishop extends Piece{
    public Bishop(PieceColor color) {
        super(color, PieceType.BISHOP);
    }

    int[][] moveVectors = {
            {1,1},
            {1,-1},
            {-1,1},
            {-1,-1},
    };

    @Override
    public int[][] getPossibleMoves(Piece[][] board, int i, int j) {
        return getMovesFromVector(moveVectors, i, j, board);
    }
}
