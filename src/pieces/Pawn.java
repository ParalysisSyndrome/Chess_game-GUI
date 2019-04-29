/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Cell;
import java.util.ArrayList;

public class Pawn extends Piece {
    public Pawn(String i, String p, int c) {
        this.setId(i);
        this.setPath(p);
        this.setColor(c);
    }

    public ArrayList<Cell> move(Cell[][] state, int x, int y) {
        this.possiblemoves.clear();
        if (this.getcolor() == 0) {
            if (x == 0) {
                return this.possiblemoves;
            }

            if (state[x - 1][y].getpiece() == null) {
                this.possiblemoves.add(state[x - 1][y]);
                if (x == 6 && state[4][y].getpiece() == null) {
                    this.possiblemoves.add(state[4][y]);
                }
            }

            if (y > 0 && state[x - 1][y - 1].getpiece() != null && state[x - 1][y - 1].getpiece().getcolor() != this.getcolor()) {
                this.possiblemoves.add(state[x - 1][y - 1]);
            }

            if (y < 7 && state[x - 1][y + 1].getpiece() != null && state[x - 1][y + 1].getpiece().getcolor() != this.getcolor()) {
                this.possiblemoves.add(state[x - 1][y + 1]);
            }
        } else {
            if (x == 8) {
                return this.possiblemoves;
            }

            if (state[x + 1][y].getpiece() == null) {
                this.possiblemoves.add(state[x + 1][y]);
                if (x == 1 && state[3][y].getpiece() == null) {
                    this.possiblemoves.add(state[3][y]);
                }
            }

            if (y > 0 && state[x + 1][y - 1].getpiece() != null && state[x + 1][y - 1].getpiece().getcolor() != this.getcolor()) {
                this.possiblemoves.add(state[x + 1][y - 1]);
            }

            if (y < 7 && state[x + 1][y + 1].getpiece() != null && state[x + 1][y + 1].getpiece().getcolor() != this.getcolor()) {
                this.possiblemoves.add(state[x + 1][y + 1]);
            }
        }

        return this.possiblemoves;
    }
}
