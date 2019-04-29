/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pieces;

import chess.Cell;
import java.util.ArrayList;

public class King extends Piece {
    private int x;
    private int y;

    public King(String i, String p, int c, int x, int y) {
        this.setx(x);
        this.sety(y);
        this.setId(i);
        this.setPath(p);
        this.setColor(c);
    }

    public void setx(int x) {
        this.x = x;
    }

    public void sety(int y) {
        this.y = y;
    }

    public int getx() {
        return this.x;
    }

    public int gety() {
        return this.y;
    }

    public ArrayList<Cell> move(Cell[][] state, int x, int y) {
        this.possiblemoves.clear();
        int[] posx = new int[]{x, x, x + 1, x + 1, x + 1, x - 1, x - 1, x - 1};
        int[] posy = new int[]{y - 1, y + 1, y - 1, y, y + 1, y - 1, y, y + 1};

        for(int i = 0; i < 8; ++i) {
            if (posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8 && (state[posx[i]][posy[i]].getpiece() == null || state[posx[i]][posy[i]].getpiece().getcolor() != this.getcolor())) {
                this.possiblemoves.add(state[posx[i]][posy[i]]);
            }
        }

        return this.possiblemoves;
    }

    public boolean isindanger(Cell[][] state) {
        int tempx = this.x + 1;

        while(true) {
            if (tempx < 8) {
                if (state[tempx][this.y].getpiece() == null) {
                    ++tempx;
                    continue;
                }

                if (state[tempx][this.y].getpiece().getcolor() != this.getcolor() && (state[tempx][this.y].getpiece() instanceof Rook || state[tempx][this.y].getpiece() instanceof Queen)) {
                    return true;
                }
            }

            tempx = this.x - 1;

            while(true) {
                if (tempx >= 0) {
                    if (state[tempx][this.y].getpiece() == null) {
                        --tempx;
                        continue;
                    }

                    if (state[tempx][this.y].getpiece().getcolor() != this.getcolor() && (state[tempx][this.y].getpiece() instanceof Rook || state[tempx][this.y].getpiece() instanceof Queen)) {
                        return true;
                    }
                }

                tempx = this.y + 1;

                while(true) {
                    if (tempx < 8) {
                        if (state[this.x][tempx].getpiece() == null) {
                            ++tempx;
                            continue;
                        }

                        if (state[this.x][tempx].getpiece().getcolor() != this.getcolor() && (state[this.x][tempx].getpiece() instanceof Rook || state[this.x][tempx].getpiece() instanceof Queen)) {
                            return true;
                        }
                    }

                    for(tempx = this.y - 1; tempx >= 0; --tempx) {
                        if (state[this.x][tempx].getpiece() != null) {
                            if (state[this.x][tempx].getpiece().getcolor() == this.getcolor() || !(state[this.x][tempx].getpiece() instanceof Rook) && !(state[this.x][tempx].getpiece() instanceof Queen)) {
                                break;
                            }

                            return true;
                        }
                    }

                    tempx = this.x + 1;

                    int tempy;
                    for(tempy = this.y - 1; tempx < 8 && tempy >= 0; --tempy) {
                        if (state[tempx][tempy].getpiece() != null) {
                            if (state[tempx][tempy].getpiece().getcolor() == this.getcolor() || !(state[tempx][tempy].getpiece() instanceof Bishop) && !(state[tempx][tempy].getpiece() instanceof Queen)) {
                                break;
                            }

                            return true;
                        }

                        ++tempx;
                    }

                    tempx = this.x - 1;

                    for(tempy = this.y + 1; tempx >= 0 && tempy < 8; ++tempy) {
                        if (state[tempx][tempy].getpiece() != null) {
                            if (state[tempx][tempy].getpiece().getcolor() == this.getcolor() || !(state[tempx][tempy].getpiece() instanceof Bishop) && !(state[tempx][tempy].getpiece() instanceof Queen)) {
                                break;
                            }

                            return true;
                        }

                        --tempx;
                    }

                    tempx = this.x - 1;

                    for(tempy = this.y - 1; tempx >= 0 && tempy >= 0; --tempy) {
                        if (state[tempx][tempy].getpiece() != null) {
                            if (state[tempx][tempy].getpiece().getcolor() == this.getcolor() || !(state[tempx][tempy].getpiece() instanceof Bishop) && !(state[tempx][tempy].getpiece() instanceof Queen)) {
                                break;
                            }

                            return true;
                        }

                        --tempx;
                    }

                    tempx = this.x + 1;
                    tempy = this.y + 1;

                    while(true) {
                        if (tempx < 8 && tempy < 8) {
                            if (state[tempx][tempy].getpiece() == null) {
                                ++tempx;
                                ++tempy;
                                continue;
                            }

                            if (state[tempx][tempy].getpiece().getcolor() != this.getcolor() && (state[tempx][tempy].getpiece() instanceof Bishop || state[tempx][tempy].getpiece() instanceof Queen)) {
                                return true;
                            }
                        }

                        int[] posx = new int[]{this.x + 1, this.x + 1, this.x + 2, this.x + 2, this.x - 1, this.x - 1, this.x - 2, this.x - 2};
                        int[] posy = new int[]{this.y - 2, this.y + 2, this.y - 1, this.y + 1, this.y - 2, this.y + 2, this.y - 1, this.y + 1};

                        for(int i = 0; i < 8; ++i) {
                            if (posx[i] >= 0 && posx[i] < 8 && posy[i] >= 0 && posy[i] < 8 && state[posx[i]][posy[i]].getpiece() != null && state[posx[i]][posy[i]].getpiece().getcolor() != this.getcolor() && state[posx[i]][posy[i]].getpiece() instanceof Knight) {
                                return true;
                            }
                        }

                        int[] pox = new int[]{this.x + 1, this.x + 1, this.x + 1, this.x, this.x, this.x - 1, this.x - 1, this.x - 1};
                        int[] poy = new int[]{this.y - 1, this.y + 1, this.y, this.y + 1, this.y - 1, this.y + 1, this.y - 1, this.y};

                        for(int i = 0; i < 8; ++i) {
                            if (pox[i] >= 0 && pox[i] < 8 && poy[i] >= 0 && poy[i] < 8 && state[pox[i]][poy[i]].getpiece() != null && state[pox[i]][poy[i]].getpiece().getcolor() != this.getcolor() && state[pox[i]][poy[i]].getpiece() instanceof King) {
                                return true;
                            }
                        }

                        if (this.getcolor() == 0) {
                            if (this.x > 0 && this.y > 0 && state[this.x - 1][this.y - 1].getpiece() != null && state[this.x - 1][this.y - 1].getpiece().getcolor() == 1 && state[this.x - 1][this.y - 1].getpiece() instanceof Pawn) {
                                return true;
                            }

                            if (this.x > 0 && this.y < 7 && state[this.x - 1][this.y + 1].getpiece() != null && state[this.x - 1][this.y + 1].getpiece().getcolor() == 1 && state[this.x - 1][this.y + 1].getpiece() instanceof Pawn) {
                                return true;
                            }
                        } else {
                            if (this.x < 7 && this.y > 0 && state[this.x + 1][this.y - 1].getpiece() != null && state[this.x + 1][this.y - 1].getpiece().getcolor() == 0 && state[this.x + 1][this.y - 1].getpiece() instanceof Pawn) {
                                return true;
                            }

                            if (this.x < 7 && this.y < 7 && state[this.x + 1][this.y + 1].getpiece() != null && state[this.x + 1][this.y + 1].getpiece().getcolor() == 0 && state[this.x + 1][this.y + 1].getpiece() instanceof Pawn) {
                                return true;
                            }
                        }

                        return false;
                    }
                }
            }
        }
    }
}