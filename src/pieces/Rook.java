/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Cell;
import java.util.ArrayList;

public class Rook extends Piece {
    public Rook(String i, String p, int c) {
        this.setId(i);
        this.setPath(p);
        this.setColor(c);
    }

    public ArrayList<Cell> move(Cell[][] state, int x, int y) {
        this.possiblemoves.clear();

        int tempx;
        for(tempx = x - 1; tempx >= 0; --tempx) {
            if (state[tempx][y].getpiece() != null) {
                if (state[tempx][y].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[tempx][y]);
                }
                break;
            }

            this.possiblemoves.add(state[tempx][y]);
        }

        for(tempx = x + 1; tempx < 8; ++tempx) {
            if (state[tempx][y].getpiece() != null) {
                if (state[tempx][y].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[tempx][y]);
                }
                break;
            }

            this.possiblemoves.add(state[tempx][y]);
        }

        int tempy;
        for(tempy = y - 1; tempy >= 0; --tempy) {
            if (state[x][tempy].getpiece() != null) {
                if (state[x][tempy].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[x][tempy]);
                }
                break;
            }

            this.possiblemoves.add(state[x][tempy]);
        }

        for(tempy = y + 1; tempy < 8; ++tempy) {
            if (state[x][tempy].getpiece() != null) {
                if (state[x][tempy].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[x][tempy]);
                }
                break;
            }

            this.possiblemoves.add(state[x][tempy]);
        }

        return this.possiblemoves;
    }
}