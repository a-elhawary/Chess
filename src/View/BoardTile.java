package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

import Logic.*;

public class BoardTile extends JButton{
    Board parent;

    Piece child;
    static int width;
    static int height;
    int i;
    int j;
    Color baseColor;
    boolean isHighlighted;

    public BoardTile(Color color, int i, int j, Board parent){
        this.setBackground(color);
        this.setSize(new Dimension(width, height));
        this.setPreferredSize(new Dimension(width, height));
        this.setLayout(new GridBagLayout());
        this.setOpaque(true);
        this.setBorderPainted(false);
        this.addActionListener(new BoardTileListener(this));
        this.i = i;
        this.j = j;
        this.parent = parent;
        this.baseColor = color;
        this.isHighlighted = false;
    }

    public BoardTile(Color color, int i, int j, Board parent, Piece piece){
        this(color, i, j, parent);
        setChild(piece);
    }

    public void highlight(){
        isHighlighted = true;
        if(baseColor == Board.black)
            this.setBackground(Board.highlightBlack);
        else
            this.setBackground(Board.highlightWhite);
        this.revalidate();
        this.repaint();
    }

    public void unhighlight(){
        isHighlighted = false;
        this.setBackground(baseColor);
        this.revalidate();
        this.repaint();
    }

    public void setChild(Piece child){
        this.child = child;
        if(child != null){
            Image resizedImage = child.image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
            this.add(new JLabel(new ImageIcon(resizedImage)));
        }else{
            this.removeAll();
        }
    }

    public Piece getChild(){
        return child;
    }

    public Board getBoard(){
        return parent;
    }

    public Piece[][] getBoardState() {
        return parent.getBoard();
    }

    public int getI() {
        return i;
    }

    public int getJ() {
        return j;
    }
}

class BoardTileListener implements ActionListener{
    BoardTile parent;

    public BoardTileListener(BoardTile parent){
        this.parent = parent;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(parent.isHighlighted){
            Piece [][] board = parent.getBoardState();
            int oldI = parent.getBoard().getSelectedI();
            int oldJ = parent.getBoard().getSelectedJ();
            Piece moving = board[oldI][oldJ];
            board[oldI][oldJ] = null;
            board[parent.getI()][parent.getJ()] = moving;
            parent.getBoard().setBoard(board);
            return;
        }

        parent.getBoard().unhighlight();
        if(parent.getChild() == null) return;
        parent.getBoard().setSelectedI(parent.getI());
        parent.getBoard().setSelectedJ(parent.getJ());
        int[][] moves = parent.getChild().getPossibleMoves(parent.getBoardState(), parent.getI(), parent.getJ());
        parent.getBoard().highlightPossibleMoves(moves);
    }
}
