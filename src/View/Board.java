package View;

import java.awt.*;
import java.util.ArrayList;
import javax.swing.JPanel;

import Logic.*;

/*
    View.Board class is a JPanel that displays the board
    it needs to know its width and height (both should be the equal)
    what it does is it sets the board tile width and height accordingly
    it then creates 64 board tiles in alternating colors and adds the pieces
 */

public class Board extends JPanel{

    final static Color black = new Color(0x769556);
    final static Color white = new Color(0xeaecd0);
    final static Color highlightBlack = new Color(0xbacc36);
    final static Color highlightWhite = new Color(0xf6f879);

    // selected piece to move i and j coordinates on board
    private int selectedI;
    private int selectedJ;

    public final static int BLACK_START = 0;
    public final static int WHITE_START = 7;

    final int BOARD_WIDTH = 8;
    final int BOARD_HEIGHT = 8;

    private Piece board[][];
    private BoardTile[][] guiBoard;

    private ArrayList<BoardTile> highlightedPieces;

    int currWidth;
    int currHeight;

    public Board(int width, int height){
        this.setLayout(new GridLayout(BOARD_WIDTH,BOARD_HEIGHT));

        board = new Piece[BOARD_HEIGHT][BOARD_WIDTH];
        guiBoard = new BoardTile[BOARD_HEIGHT][BOARD_WIDTH];
        highlightedPieces = new ArrayList<>();

        for(int i = 0; i < board.length; i++){
            board[BLACK_START + 1][i] = new Pawn(PieceColor.BLACK);
            board[WHITE_START - 1][i] = new Pawn(PieceColor.WHITE);
        }

        for(int i = 0; i < 2; i++){
            PieceColor color = PieceColor.BLACK;
            if(i == 1) color = PieceColor.WHITE;
            board[i*WHITE_START][0] = new Rook(color);
            board[i*WHITE_START][1] = new Knight(color);
            board[i*WHITE_START][2] = new Bishop(color);
            board[i*WHITE_START][3] = new Queen(color);
            board[i*WHITE_START][4] = new King(color);
            board[i*WHITE_START][5] = new Bishop(color);
            board[i*WHITE_START][6] = new Knight(color);
            board[i*WHITE_START][7] = new Rook(color);
        }

        changeDimension(width, height);
    }

    public void changeDimension(int width, int height){
        currWidth = width;
        currHeight = height;
        this.removeAll();
        this.setSize(new Dimension(width, height));
        this.setPreferredSize(new Dimension(width, height));

        BoardTile.width = width / 8;
        BoardTile.height = height / 8;

        for(int i = 0; i < board.length; i++){
            Color currentColor = white;
            if(i % 2 == 1) currentColor = black;
            for(int j = 0; j < board[i].length; j++){
                BoardTile tile;
                if(board[i][j] != null)
                    tile = new BoardTile(currentColor, i, j, this, board[i][j]);
                else
                    tile = new BoardTile(currentColor, i, j, this);
                this.add(tile);
                guiBoard[i][j] = tile;
                if(currentColor ==  black)
                    currentColor = white;
                else
                    currentColor = black;
            }
        }

        this.revalidate();
        this.repaint();
    }

    public Piece[][] getBoard(){
        return board;
    }

    public void setBoard(Piece [][] board){
        this.board = board;
        changeDimension(currWidth, currHeight);
    }

    public void highlightPossibleMoves(int[][] possibleMoves){
        for(int i = 0; i < possibleMoves.length; i++){
            BoardTile currPiece = guiBoard[possibleMoves[i][0]][possibleMoves[i][1]];
            currPiece.highlight();
            highlightedPieces.add(currPiece);
        }
    }

    public void unhighlight(){
       for(int i = 0; i < highlightedPieces.size(); i++) {
           highlightedPieces.get(i).unhighlight();
       }
       highlightedPieces = new ArrayList<>();
    }

    public int getSelectedI() {
        return selectedI;
    }

    public void setSelectedI(int selectedI) {
        this.selectedI = selectedI;
    }

    public int getSelectedJ() {
        return selectedJ;
    }

    public void setSelectedJ(int selectedJ) {
        this.selectedJ = selectedJ;
    }
}
