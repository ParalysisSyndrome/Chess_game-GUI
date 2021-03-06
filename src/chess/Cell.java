/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import pieces.King;
import pieces.Piece;

public class Cell extends JPanel implements Cloneable {
    private static final long serialVersionUID = 1L;
    private boolean ispossibledestination;
    private JLabel content;
    private Piece piece;
    int x;
    int y;
    private boolean isSelected = false;
    private boolean ischeck = false;

    public Cell(int x, int y, Piece p) {
        this.x = x;
        this.y = y;
        this.setLayout(new BorderLayout());
        if ((x + y) % 2 == 0) {
            this.setBackground(Color.white);
        } else {
            this.setBackground(new Color(50, 50, 50));
        }

        if (p != null) {
            this.setPiece(p);
        }

    }

    public Cell(Cell cell) throws CloneNotSupportedException {
        this.x = cell.x;
        this.y = cell.y;
        this.setLayout(new BorderLayout());
        if ((this.x + this.y) % 2 == 0) {
            this.setBackground(Color.red);
        } else {
            this.setBackground(new Color(50, 50, 50));
        }

        if (cell.getpiece() != null) {
            this.setPiece(cell.getpiece().getcopy());
        } else {
            this.piece = null;
        }

    }

    public void setPiece(Piece p) {
        this.piece = p;
        ImageIcon img = new ImageIcon(this.getClass().getResource(p.getPath()));
        this.content = new JLabel(img);
        this.add(this.content);
    }

    public void removePiece() {
        if (this.piece instanceof King) {
            this.piece = null;
            this.remove(this.content);
        } else {
            this.piece = null;
            this.remove(this.content);
        }

    }

    public Piece getpiece() {
        return this.piece;
    }

    public void select() {
        this.setBorder(BorderFactory.createLineBorder(Color.red, 6));
        this.isSelected = true;
    }

    public boolean isselected() {
        return this.isSelected;
    }

    public void deselect() {
        this.setBorder((Border)null);
        this.isSelected = false;
    }

    public void setpossibledestination() {
        this.setBorder(BorderFactory.createLineBorder(Color.blue, 4));
        this.ispossibledestination = true;
    }

    public void removepossibledestination() {
        this.setBorder((Border)null);
        this.ispossibledestination = false;
    }

    public boolean ispossibledestination() {
        return this.ispossibledestination;
    }

    public void setcheck() {
        this.setBackground(Color.RED);
        this.ischeck = true;
    }

    public void removecheck() {
        this.setBorder((Border)null);
        if ((this.x + this.y) % 2 == 0) {
            this.setBackground(Color.white);
        } else {
            this.setBackground(new Color(50, 50, 50));
        }

        this.ischeck = false;
    }

    public boolean ischeck() {
        return this.ischeck;
    }
}