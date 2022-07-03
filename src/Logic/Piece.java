package Logic;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;

public abstract class Piece {
    final static int SPRITE_CONSTANT = 200;
    public PieceColor color;
    public PieceType type;
    public Image image;

    public Piece(PieceColor color, PieceType type){
        this.color = color;
        this.type = type;
        try {
            BufferedImage spriteSheet = ImageIO.read(new File("sprite_sheet.png"));
            int x = type.ordinal() * SPRITE_CONSTANT;
            int y = color.ordinal() * SPRITE_CONSTANT;
            BufferedImage pieceImage = spriteSheet.getSubimage(x, y, SPRITE_CONSTANT, SPRITE_CONSTANT);
            this.image = new ImageIcon(pieceImage).getImage();
        }catch(Exception e){
            // TODO: tell the user in a friendly alert box
           e.printStackTrace();
        }
    }

    public abstract int[][] getPossibleMoves(Piece board[][], int i, int j);

    protected int[][] fixOutFormat(Object [] temp){
        int[][] out = new int[temp.length][2];
        for(int i = 0; i < temp.length; i++){
            out[i] = (int[])temp[i];
        }
        return out;
    }

    protected boolean inBoard(int i, int j, Piece[][] board){
       if(i < 0)  return false;
       if(j < 0) return false;
       if(i >= board.length) return false;
       if(j >= board.length) return false;
       return true;
    }

    // works for pieces (Bishop, Rook, Queen)
    // Which can move freely on specific vectors
    protected int[][] getMovesFromVector(int[][] moveVectors, int i, int j, Piece[][] board){
        ArrayList<int[]> moves = new ArrayList<>();
        for(int k = 0; k < moveVectors.length; k++){
            int currentI = i;
            int currentJ = j;

            while(inBoard(currentI + moveVectors[k][0], currentJ + moveVectors[k][1], board)){
                currentI += moveVectors[k][0];
                currentJ += moveVectors[k][1];
                moves.add(new int[]{currentI, currentJ});
                if(board[currentI][currentJ] != null) {
                    if (board[currentI][currentJ].color == this.color)
                        moves.remove(moves.size() - 1);
                    break;
                }
            }
        }
        return fixOutFormat(moves.toArray());
    }

}
