/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Cell;
import java.util.ArrayList;

public abstract class Piece implements Cloneable {
    private int color;
    private String id = null;
    private String path;
    protected ArrayList<Cell> possiblemoves = new ArrayList();

    public Piece() {
    }

    public abstract ArrayList<Cell> move(Cell[][] var1, int var2, int var3);

    public void setId(String id) {
        this.id = id;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public void setColor(int c) {
        this.color = c;
    }

    public String getPath() {
        return this.path;
    }

    public String getId() {
        return this.id;
    }

    public int getcolor() {
        return this.color;
    }

    public Piece getcopy() throws CloneNotSupportedException {
        return (Piece)this.clone();
    }
}