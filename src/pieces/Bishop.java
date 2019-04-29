/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Cell;
import java.util.ArrayList;

public class Bishop extends Piece {
    public Bishop(String i, String p, int c) {
        this.setId(i);
        this.setPath(p);
        this.setColor(c);
    }

    public ArrayList<Cell> move(Cell[][] state, int x, int y) {
        this.possiblemoves.clear();
        int tempx = x + 1;

        int tempy;
        for(tempy = y - 1; tempx < 8 && tempy >= 0; --tempy) {
            if (state[tempx][tempy].getpiece() != null) {
                if (state[tempx][tempy].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[tempx][tempy]);
                }
                break;
            }

            this.possiblemoves.add(state[tempx][tempy]);
            ++tempx;
        }

        tempx = x - 1;

        for(tempy = y + 1; tempx >= 0 && tempy < 8; ++tempy) {
            if (state[tempx][tempy].getpiece() != null) {
                if (state[tempx][tempy].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[tempx][tempy]);
                }
                break;
            }

            this.possiblemoves.add(state[tempx][tempy]);
            --tempx;
        }

        tempx = x - 1;

        for(tempy = y - 1; tempx >= 0 && tempy >= 0; --tempy) {
            if (state[tempx][tempy].getpiece() != null) {
                if (state[tempx][tempy].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[tempx][tempy]);
                }
                break;
            }

            this.possiblemoves.add(state[tempx][tempy]);
            --tempx;
        }

        tempx = x + 1;

        for(tempy = y + 1; tempx < 8 && tempy < 8; ++tempy) {
            if (state[tempx][tempy].getpiece() != null) {
                if (state[tempx][tempy].getpiece().getcolor() != this.getcolor()) {
                    this.possiblemoves.add(state[tempx][tempy]);
                }
                break;
            }

            this.possiblemoves.add(state[tempx][tempy]);
            ++tempx;
        }

        return this.possiblemoves;
    }
}