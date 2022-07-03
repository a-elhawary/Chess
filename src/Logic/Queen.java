package Logic;

public class Queen extends Piece{
    public Queen(PieceColor color) {
        super(color, PieceType.QUEEN);
    }

    int[][] moveVectors = {
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
        return getMovesFromVector(moveVectors, i, j, board);
    }
}
