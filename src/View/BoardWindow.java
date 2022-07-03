package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

public class BoardWindow extends JFrame{
    Board board;
    final static int BOARD_PADDING = 80;

    public BoardWindow(){
        board = new Board(800,800);
        this.setTitle("Chess");
        this.setSize(new Dimension(900, 900));
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        JPanel panel = new JPanel();
        panel.setBackground(new Color(0x303030));
        panel.setLayout(new GridBagLayout());
        panel.add(board);
        this.add(panel);
        this.addComponentListener(new ResizeEvent(this, board));
        this.setVisible(true);
    }

    public static void main(String args[]){
        new BoardWindow();
    }
}

class ResizeEvent extends ComponentAdapter {
    JFrame frame;
    Board board;

    public ResizeEvent(JFrame frame, Board board){
        this.frame = frame;
        this.board = board;
    }

    public void componentResized(ComponentEvent componentEvent) {
        int currWidth = frame.getWidth();
        int currHeight = frame.getHeight();
        int size = Math.min(currHeight, currWidth);
        size -= BoardWindow.BOARD_PADDING;
        size -= size % 8;
        board.changeDimension(size, size);
    }
}
