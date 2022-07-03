package Logic;

public class Rook extends Piece{
    public Rook(PieceColor color) {
        super(color, PieceType.ROOK);
    }

    int[][] moveVectors = {
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
