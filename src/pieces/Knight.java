/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Cell;
import java.util.ArrayList;

public class Knight extends Piece {
    public Knight(String i, String p, int c) {
        this.setId(i);
        this.setPath(p);
        this.setColor(c);
    }

    public ArrayList<Cell> move(Cell[][] state, int x, int y) {
        this.possiblemoves.clear();
        int[] posx = new int[]{x + 1, x + 1, x + 2, x + 2, x - 1, x - 1, x - 2, x - 2};
        int[] posy = new int[]{y - 2, y + 2, y - 1, y + 1, y - 2, y + 2, y - 1, y + 1};

        for(int i = 0; i < 8; ++i) {
            if (posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8 && (state[posx[i]][posy[i]].getpiece() == null || state[posx[i]][posy[i]].getpiece().getcolor() != this.getcolor())) {
                this.possiblemoves.add(state[posx[i]][posy[i]]);
            }
        }

        return this.possiblemoves;
    }
}