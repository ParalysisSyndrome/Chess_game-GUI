/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSplitPane;
import javax.swing.border.Border;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import pieces.Bishop;
import pieces.King;
import pieces.Knight;
import pieces.Pawn;
import pieces.Piece;
import pieces.Queen;
import pieces.Rook;

public class Main extends JFrame implements MouseListener {
    private static final long serialVersionUID = 1L;
    private static final int Height = 700;
    private static final int Width = 1110;
    private static Rook wr01;
    private static Rook wr02;
    private static Rook br01;
    private static Rook br02;
    private static Knight wk01;
    private static Knight wk02;
    private static Knight bk01;
    private static Knight bk02;
    private static Bishop wb01;
    private static Bishop wb02;
    private static Bishop bb01;
    private static Bishop bb02;
    private static Pawn[] wp;
    private static Pawn[] bp;
    private static Queen wq;
    private static Queen bq;
    private static King wk;
    private static King bk;
    private Cell c;
    private Cell previous;
    private int chance = 0;
    private Cell[][] boardState;
    private ArrayList<Cell> destinationlist = new ArrayList();
    private Player White = null;
    private Player Black = null;
    private JPanel board = new JPanel(new GridLayout(8, 8));
    private JPanel wdetails = new JPanel(new GridLayout(3, 3));
    private JPanel bdetails = new JPanel(new GridLayout(3, 3));
    private JPanel wcombopanel = new JPanel();
    private JPanel bcombopanel = new JPanel();
    private JPanel controlPanel;
    private JPanel WhitePlayer;
    private JPanel BlackPlayer;
    private JPanel temp;
    private JPanel displayTime;
    private JPanel showPlayer;
    private JPanel time;
    private JSplitPane split;
    private JLabel label;
    private JLabel mov;
    private static JLabel CHNC;
    private Time timer;
    public static Main Mainboard;
    private boolean selected = false;
    private boolean end = false;
    private Container content;
    private ArrayList<Player> wplayer;
    private ArrayList<Player> bplayer;
    private ArrayList<String> Wnames = new ArrayList();
    private ArrayList<String> Bnames = new ArrayList();
    private JComboBox<String> wcombo;
    private JComboBox<String> bcombo;
    private String wname = null;
    private String bname = null;
    private String winner = null;
    static String move;
    private Player tempPlayer;
    private JScrollPane wscroll;
    private JScrollPane bscroll;
    private String[] WNames = new String[0];
    private String[] BNames = new String[0];
    private JSlider timeSlider;
    private BufferedImage image;
    private Button start;
    private Button wselect;
    private Button bselect;
    private Button WNewPlayer;
    private Button BNewPlayer;
    public static int timeRemaining = 60;

    public static void main(String[] args) {
        wr01 = new Rook("WR01", "White_Rook.png", 0);
        wr02 = new Rook("WR02", "White_Rook.png", 0);
        br01 = new Rook("BR01", "Black_Rook.png", 1);
        br02 = new Rook("BR02", "Black_Rook.png", 1);
        wk01 = new Knight("WK01", "White_Knight.png", 0);
        wk02 = new Knight("WK02", "White_Knight.png", 0);
        bk01 = new Knight("BK01", "Black_Knight.png", 1);
        bk02 = new Knight("BK02", "Black_Knight.png", 1);
        wb01 = new Bishop("WB01", "White_Bishop.png", 0);
        wb02 = new Bishop("WB02", "White_Bishop.png", 0);
        bb01 = new Bishop("BB01", "Black_Bishop.png", 1);
        bb02 = new Bishop("BB02", "Black_Bishop.png", 1);
        wq = new Queen("WQ", "White_Queen.png", 0);
        bq = new Queen("BQ", "Black_Queen.png", 1);
        wk = new King("WK", "White_King.png", 0, 7, 4);
        bk = new King("BK", "Black_King.png", 1, 0, 4);
        wp = new Pawn[8];
        bp = new Pawn[8];

        for(int i = 0; i < 8; ++i) {
            wp[i] = new Pawn("WP0" + (i + 1), "White_Pawn.png", 0);
            bp[i] = new Pawn("BP0" + (i + 1), "Black_Pawn.png", 1);
        }

        Mainboard = new Main();
        Mainboard.setVisible(true);
        Mainboard.setResizable(false);
    }

    private Main() {
        timeRemaining = 60;
        this.timeSlider = new JSlider();
        move = "Putih";
        this.wname = null;
        this.bname = null;
        this.winner = null;
        this.board = new JPanel(new GridLayout(8, 8));
        this.wdetails = new JPanel(new GridLayout(3, 3));
        this.bdetails = new JPanel(new GridLayout(3, 3));
        this.bcombopanel = new JPanel();
        this.wcombopanel = new JPanel();
        this.Wnames = new ArrayList();
        this.Bnames = new ArrayList();
        this.board.setMinimumSize(new Dimension(800, 700));
        ImageIcon img = new ImageIcon(this.getClass().getResource("icon.png"));
        this.setIconImage(img.getImage());
        this.timeSlider.setMinimum(1);
        this.timeSlider.setMaximum(15);
        this.timeSlider.setValue(1);
        this.timeSlider.setMajorTickSpacing(2);
        this.timeSlider.setPaintLabels(true);
        this.timeSlider.setPaintTicks(true);
        this.timeSlider.addChangeListener(new Main.TimeChange());
        this.wplayer = Player.fetch_players();
        Iterator witr = this.wplayer.iterator();

        while(witr.hasNext()) {
            this.Wnames.add(((Player)witr.next()).name());
        }

        this.bplayer = Player.fetch_players();
        Iterator bitr = this.bplayer.iterator();

        while(bitr.hasNext()) {
            this.Bnames.add(((Player)bitr.next()).name());
        }

        this.WNames = (String[])this.Wnames.toArray(this.WNames);
        this.BNames = (String[])this.Bnames.toArray(this.BNames);
        this.board.setBorder(BorderFactory.createLoweredBevelBorder());
        this.content = this.getContentPane();
        this.setSize(1110, 700);
        this.setTitle("Chess");
        this.content.setBackground(Color.black);
        this.controlPanel = new JPanel();
        this.content.setLayout(new BorderLayout());
        this.controlPanel.setLayout(new GridLayout(3, 3));
        this.controlPanel.setBorder(BorderFactory.createTitledBorder((Border)null, "Statistik", 2, 2, new Font("Lucida Calligraphy", 0, 20), Color.ORANGE));
        this.WhitePlayer = new JPanel();
        this.WhitePlayer.setBorder(BorderFactory.createTitledBorder((Border)null, "Pemain Putih", 2, 2, new Font("times new roman", 1, 18), Color.RED));
        this.WhitePlayer.setLayout(new BorderLayout());
        this.BlackPlayer = new JPanel();
        this.BlackPlayer.setBorder(BorderFactory.createTitledBorder((Border)null, "Pemain Hitam", 2, 2, new Font("times new roman", 1, 18), Color.BLUE));
        this.BlackPlayer.setLayout(new BorderLayout());
        JPanel whitestats = new JPanel(new GridLayout(3, 3));
        JPanel blackstats = new JPanel(new GridLayout(3, 3));
        this.wcombo = new JComboBox(this.WNames);
        this.bcombo = new JComboBox(this.BNames);
        this.wscroll = new JScrollPane(this.wcombo);
        this.bscroll = new JScrollPane(this.bcombo);
        this.wcombopanel.setLayout(new FlowLayout());
        this.bcombopanel.setLayout(new FlowLayout());
        this.wselect = new Button("Pilih");
        this.bselect = new Button("Pilih");
        this.wselect.addActionListener(new Main.SelectHandler(0));
        this.bselect.addActionListener(new Main.SelectHandler(1));
        this.WNewPlayer = new Button("Pemain Baru");
        this.BNewPlayer = new Button("Pemain Baru");
        this.WNewPlayer.addActionListener(new Main.Handler(0));
        this.BNewPlayer.addActionListener(new Main.Handler(1));
        this.wcombopanel.add(this.wscroll);
        this.wcombopanel.add(this.wselect);
        this.wcombopanel.add(this.WNewPlayer);
        this.bcombopanel.add(this.bscroll);
        this.bcombopanel.add(this.bselect);
        this.bcombopanel.add(this.BNewPlayer);
        this.WhitePlayer.add(this.wcombopanel, "North");
        this.BlackPlayer.add(this.bcombopanel, "North");
        whitestats.add(new JLabel("Nama   :"));
        whitestats.add(new JLabel("Jumlah permainan :"));
        whitestats.add(new JLabel("Menang    :"));
        blackstats.add(new JLabel("Nama  :"));
        blackstats.add(new JLabel("Jumlah Permainan :"));
        blackstats.add(new JLabel("Menang    :"));
        this.WhitePlayer.add(whitestats, "West");
        this.BlackPlayer.add(blackstats, "West");
        this.controlPanel.add(this.WhitePlayer);
        this.controlPanel.add(this.BlackPlayer);
        this.boardState = new Cell[8][8];

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                Piece P = null;
                if (i == 0 && j == 0) {
                    P = br01;
                } else if (i == 0 && j == 7) {
                    P = br02;
                } else if (i == 7 && j == 0) {
                    P = wr01;
                } else if (i == 7 && j == 7) {
                    P = wr02;
                } else if (i == 0 && j == 1) {
                    P = bk01;
                } else if (i == 0 && j == 6) {
                    P = bk02;
                } else if (i == 7 && j == 1) {
                    P = wk01;
                } else if (i == 7 && j == 6) {
                    P = wk02;
                } else if (i == 0 && j == 2) {
                    P = bb01;
                } else if (i == 0 && j == 5) {
                    P = bb02;
                } else if (i == 7 && j == 2) {
                    P = wb01;
                } else if (i == 7 && j == 5) {
                    P = wb02;
                } else if (i == 0 && j == 3) {
                    P = bq;
                } else if (i == 0 && j == 4) {
                    P = bk;
                } else if (i == 7 && j == 3) {
                    P = wq;
                } else if (i == 7 && j == 4) {
                    P = wk;
                } else if (i == 1) {
                    P = bp[j];
                } else if (i == 6) {
                    P = wp[j];
                }

                Cell cell = new Cell(i, j, (Piece)P);
                cell.addMouseListener(this);
                this.board.add(cell);
                this.boardState[i][j] = cell;
            }
        }

        this.showPlayer = new JPanel(new FlowLayout());
        this.showPlayer.add(this.timeSlider);
        JLabel setTime = new JLabel("Setting Waktu(dalam menit):");
        this.start = new Button("Start");
        this.start.setBackground(Color.black);
        this.start.setForeground(Color.white);
        this.start.addActionListener(new Main.START());
        this.start.setPreferredSize(new Dimension(120, 40));
        setTime.setFont(new Font("Arial", 1, 16));
        this.label = new JLabel("Waktu Dimulai !", 0);
        this.label.setFont(new Font("SERIF", 1, 30));
        this.displayTime = new JPanel(new FlowLayout());
        this.time = new JPanel(new GridLayout(3, 3));
        this.time.add(setTime);
        this.time.add(this.showPlayer);
        this.displayTime.add(this.start);
        this.time.add(this.displayTime);
        this.controlPanel.add(this.time);
        this.board.setMinimumSize(new Dimension(800, 700));
        this.temp = new JPanel() {
            private static final long serialVersionUID = 1L;

            public void paintComponent(Graphics g) {
                try {
                    Main.this.image = ImageIO.read(this.getClass().getResource("clash.jpg"));
                } catch (IOException var3) {
                    System.out.println("not found");
                }

                g.drawImage(Main.this.image, 0, 0, (ImageObserver)null);
            }
        };
        this.temp.setMinimumSize(new Dimension(800, 700));
        this.controlPanel.setMinimumSize(new Dimension(285, 700));
        this.split = new JSplitPane(1, this.temp, this.controlPanel);
        this.content.add(this.split);
        this.setDefaultCloseOperation(3);
    }

    public void changechance() {
        if (this.boardState[this.getKing(this.chance).getx()][this.getKing(this.chance).gety()].ischeck()) {
            this.chance ^= 1;
            this.gameend();
        }

        if (!this.destinationlist.isEmpty()) {
            this.cleandestinations(this.destinationlist);
        }

        if (this.previous != null) {
            this.previous.deselect();
        }

        this.previous = null;
        this.chance ^= 1;
        if (!this.end && this.timer != null) {
            this.timer.reset();
            this.timer.start();
            this.showPlayer.remove(CHNC);
            if (move == "Putih") {
                move = "Hitam";
            } else {
                move = "Putih";
            }

            CHNC.setText(move);
            this.showPlayer.add(CHNC);
        }

    }

    private King getKing(int color) {
        return color == 0 ? wk : bk;
    }

    private void cleandestinations(ArrayList<Cell> destlist) {
        ListIterator it = destlist.listIterator();

        while(it.hasNext()) {
            ((Cell)it.next()).removepossibledestination();
        }

    }

    private void highlightdestinations(ArrayList<Cell> destlist) {
        ListIterator it = destlist.listIterator();

        while(it.hasNext()) {
            ((Cell)it.next()).setpossibledestination();
        }

    }

    private boolean willkingbeindanger(Cell fromcell, Cell tocell) {
        Cell[][] newboardstate = new Cell[8][8];

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                try {
                    newboardstate[i][j] = new Cell(this.boardState[i][j]);
                } catch (CloneNotSupportedException var7) {
                    var7.printStackTrace();
                    System.out.println("There is a problem with cloning !!");
                }
            }
        }

        if (newboardstate[tocell.x][tocell.y].getpiece() != null) {
            newboardstate[tocell.x][tocell.y].removePiece();
        }

        newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
        if (newboardstate[tocell.x][tocell.y].getpiece() instanceof King) {
            ((King)newboardstate[tocell.x][tocell.y].getpiece()).setx(tocell.x);
            ((King)newboardstate[tocell.x][tocell.y].getpiece()).sety(tocell.y);
        }

        newboardstate[fromcell.x][fromcell.y].removePiece();
        if (((King)newboardstate[this.getKing(this.chance).getx()][this.getKing(this.chance).gety()].getpiece()).isindanger(newboardstate)) {
            return true;
        } else {
            return false;
        }
    }

    private ArrayList<Cell> filterdestination(ArrayList<Cell> destlist, Cell fromcell) {
        ArrayList<Cell> newlist = new ArrayList();
        Cell[][] newboardstate = new Cell[8][8];
        ListIterator it = destlist.listIterator();

        while(it.hasNext()) {
            for(int i = 0; i < 8; ++i) {
                for(int j = 0; j < 8; ++j) {
                    try {
                        newboardstate[i][j] = new Cell(this.boardState[i][j]);
                    } catch (CloneNotSupportedException var11) {
                        var11.printStackTrace();
                    }
                }
            }

            Cell tempc = (Cell)it.next();
            if (newboardstate[tempc.x][tempc.y].getpiece() != null) {
                newboardstate[tempc.x][tempc.y].removePiece();
            }

            newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
            int x = this.getKing(this.chance).getx();
            int y = this.getKing(this.chance).gety();
            if (newboardstate[fromcell.x][fromcell.y].getpiece() instanceof King) {
                ((King)newboardstate[tempc.x][tempc.y].getpiece()).setx(tempc.x);
                ((King)newboardstate[tempc.x][tempc.y].getpiece()).sety(tempc.y);
                x = tempc.x;
                y = tempc.y;
            }

            newboardstate[fromcell.x][fromcell.y].removePiece();
            if (!((King)newboardstate[x][y].getpiece()).isindanger(newboardstate)) {
                newlist.add(tempc);
            }
        }

        return newlist;
    }

    private ArrayList<Cell> incheckfilter(ArrayList<Cell> destlist, Cell fromcell, int color) {
        ArrayList<Cell> newlist = new ArrayList();
        Cell[][] newboardstate = new Cell[8][8];
        ListIterator it = destlist.listIterator();

        while(it.hasNext()) {
            for(int i = 0; i < 8; ++i) {
                for(int j = 0; j < 8; ++j) {
                    try {
                        newboardstate[i][j] = new Cell(this.boardState[i][j]);
                    } catch (CloneNotSupportedException var12) {
                        var12.printStackTrace();
                    }
                }
            }

            Cell tempc = (Cell)it.next();
            if (newboardstate[tempc.x][tempc.y].getpiece() != null) {
                newboardstate[tempc.x][tempc.y].removePiece();
            }

            newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getpiece());
            int x = this.getKing(color).getx();
            int y = this.getKing(color).gety();
            if (newboardstate[tempc.x][tempc.y].getpiece() instanceof King) {
                ((King)newboardstate[tempc.x][tempc.y].getpiece()).setx(tempc.x);
                ((King)newboardstate[tempc.x][tempc.y].getpiece()).sety(tempc.y);
                x = tempc.x;
                y = tempc.y;
            }

            newboardstate[fromcell.x][fromcell.y].removePiece();
            if (!((King)newboardstate[x][y].getpiece()).isindanger(newboardstate)) {
                newlist.add(tempc);
            }
        }

        return newlist;
    }

    public boolean checkmate(int color) {
        ArrayList<Cell> dlist = new ArrayList();

        for(int i = 0; i < 8; ++i) {
            for(int j = 0; j < 8; ++j) {
                if (this.boardState[i][j].getpiece() != null && this.boardState[i][j].getpiece().getcolor() == color) {
                    dlist.clear();
                    dlist = this.boardState[i][j].getpiece().move(this.boardState, i, j);
                    dlist = this.incheckfilter(dlist, this.boardState[i][j], color);
                    if (dlist.size() != 0) {
                        return false;
                    }
                }
            }
        }

        return true;
    }

    private void gameend() {
        this.cleandestinations(this.destinationlist);
        this.displayTime.disable();
        this.timer.countdownTimer.stop();
        if (this.previous != null) {
            this.previous.removePiece();
        }

        if (this.chance == 0) {
            this.White.updateGamesWon();
            this.White.Update_Player();
            this.winner = this.White.name();
        } else {
            this.Black.updateGamesWon();
            this.Black.Update_Player();
            this.winner = this.Black.name();
        }

        JOptionPane.showMessageDialog(this.board, "Skakmat\n" + this.winner + " Menang !");
        this.WhitePlayer.remove(this.wdetails);
        this.BlackPlayer.remove(this.bdetails);
        this.displayTime.remove(this.label);
        this.displayTime.add(this.start);
        this.showPlayer.remove(this.mov);
        this.showPlayer.remove(CHNC);
        this.showPlayer.revalidate();
        this.showPlayer.add(this.timeSlider);
        this.split.remove(this.board);
        this.split.add(this.temp);
        this.WNewPlayer.enable();
        this.BNewPlayer.enable();
        this.wselect.enable();
        this.bselect.enable();
        this.end = true;
        Mainboard.disable();
        Mainboard.dispose();
        Mainboard = new Main();
        Mainboard.setVisible(true);
        Mainboard.setResizable(false);
    }

    public void mouseClicked(MouseEvent arg0) {
        this.c = (Cell)arg0.getSource();
        if (this.previous == null) {
            if (this.c.getpiece() != null) {
                if (this.c.getpiece().getcolor() != this.chance) {
                    return;
                }

                this.c.select();
                this.previous = this.c;
                this.destinationlist.clear();
                this.destinationlist = this.c.getpiece().move(this.boardState, this.c.x, this.c.y);
                if (this.c.getpiece() instanceof King) {
                    this.destinationlist = this.filterdestination(this.destinationlist, this.c);
                } else if (this.boardState[this.getKing(this.chance).getx()][this.getKing(this.chance).gety()].ischeck()) {
                    this.destinationlist = new ArrayList(this.filterdestination(this.destinationlist, this.c));
                } else if (!this.destinationlist.isEmpty() && this.willkingbeindanger(this.c, (Cell)this.destinationlist.get(0))) {
                    this.destinationlist.clear();
                }

                this.highlightdestinations(this.destinationlist);
            }
        } else if (this.c.x == this.previous.x && this.c.y == this.previous.y) {
            this.c.deselect();
            this.cleandestinations(this.destinationlist);
            this.destinationlist.clear();
            this.previous = null;
        } else if (this.c.getpiece() != null && this.previous.getpiece().getcolor() == this.c.getpiece().getcolor()) {
            if (this.previous.getpiece().getcolor() == this.c.getpiece().getcolor()) {
                this.previous.deselect();
                this.cleandestinations(this.destinationlist);
                this.destinationlist.clear();
                this.c.select();
                this.previous = this.c;
                this.destinationlist = this.c.getpiece().move(this.boardState, this.c.x, this.c.y);
                if (this.c.getpiece() instanceof King) {
                    this.destinationlist = this.filterdestination(this.destinationlist, this.c);
                } else if (this.boardState[this.getKing(this.chance).getx()][this.getKing(this.chance).gety()].ischeck()) {
                    this.destinationlist = new ArrayList(this.filterdestination(this.destinationlist, this.c));
                } else if (!this.destinationlist.isEmpty() && this.willkingbeindanger(this.c, (Cell)this.destinationlist.get(0))) {
                    this.destinationlist.clear();
                }

                this.highlightdestinations(this.destinationlist);
            }
        } else {
            if (this.c.ispossibledestination()) {
                if (this.c.getpiece() != null) {
                    this.c.removePiece();
                }

                this.c.setPiece(this.previous.getpiece());
                if (this.previous.ischeck()) {
                    this.previous.removecheck();
                }

                this.previous.removePiece();
                if (this.getKing(this.chance ^ 1).isindanger(this.boardState)) {
                    this.boardState[this.getKing(this.chance ^ 1).getx()][this.getKing(this.chance ^ 1).gety()].setcheck();
                    if (this.checkmate(this.getKing(this.chance ^ 1).getcolor())) {
                        this.previous.deselect();
                        if (this.previous.getpiece() != null) {
                            this.previous.removePiece();
                        }

                        this.gameend();
                    }
                }

                if (!this.getKing(this.chance).isindanger(this.boardState)) {
                    this.boardState[this.getKing(this.chance).getx()][this.getKing(this.chance).gety()].removecheck();
                }

                if (this.c.getpiece() instanceof King) {
                    ((King)this.c.getpiece()).setx(this.c.x);
                    ((King)this.c.getpiece()).sety(this.c.y);
                }

                this.changechance();
                if (!this.end) {
                    this.timer.reset();
                    this.timer.start();
                }
            }

            if (this.previous != null) {
                this.previous.deselect();
                this.previous = null;
            }

            this.cleandestinations(this.destinationlist);
            this.destinationlist.clear();
        }

        if (this.c.getpiece() != null && this.c.getpiece() instanceof King) {
            ((King)this.c.getpiece()).setx(this.c.x);
            ((King)this.c.getpiece()).sety(this.c.y);
        }

    }

    public void mouseEntered(MouseEvent arg0) {
    }

    public void mouseExited(MouseEvent arg0) {
    }

    public void mousePressed(MouseEvent arg0) {
    }

    public void mouseReleased(MouseEvent arg0) {
    }

    class Handler implements ActionListener {
        private int color;

        Handler(int i) {
            this.color = i;
        }

      
        public void actionPerformed(ActionEvent e) {
            if (this.color == 0) {
                String wname1 = Main.this.wname;
            } else {
                String bname1 = Main.this.bname;
            }

            JPanel j = this.color == 0 ? Main.this.WhitePlayer : Main.this.BlackPlayer;
            ArrayList<Player> N = Player.fetch_players();
            Iterator<Player> it = N.iterator();
            JPanel det = this.color == 0 ? Main.this.wdetails : Main.this.bdetails;
            String n = JOptionPane.showInputDialog(j, "Masukkan nama");
            if (n != null) {
                do {
                    if (!it.hasNext()) {
                        if (n.length() != 0) {
                            Player tem = new Player(n);
                            tem.Update_Player();
                            if (this.color == 0) {
                                Main.this.White = tem;
                            } else {
                                Main.this.Black = tem;
                            }

                            det.removeAll();
                            det.add(new JLabel(" " + n));
                            det.add(new JLabel(" 0"));
                            det.add(new JLabel(" 0"));
                            j.revalidate();
                            j.repaint();
                            j.add(det);
                            Main.this.selected = true;
                            return;
                        }

                        return;
                    }
                } while(!((Player)it.next()).name().equals(n));

                JOptionPane.showMessageDialog(j, "Player exists");
            }
        }
    }

    class SelectHandler implements ActionListener {
        private int color;

        SelectHandler(int i) {
            this.color = i;
        }

        public void actionPerformed(ActionEvent arg0) {
            Main.this.tempPlayer = null;
            if (this.color == 0) {
                String wname1 = Main.this.wname;
            } else {
                String bname2 = Main.this.bname;
            }

            JComboBox<String> jc = this.color == 0 ? Main.this.wcombo : Main.this.bcombo;
            JComboBox<String> ojc = this.color == 0 ? Main.this.bcombo : Main.this.wcombo;
            ArrayList<Player> pl = this.color == 0 ? Main.this.wplayer : Main.this.bplayer;
            ArrayList<Player> opl = Player.fetch_players();
            if (!opl.isEmpty()) {
                JPanel det = this.color == 0 ? Main.this.wdetails : Main.this.bdetails;
                JPanel PL = this.color == 0 ? Main.this.WhitePlayer : Main.this.BlackPlayer;
                if (Main.this.selected) {
                    det.removeAll();
                }

                String n = (String)jc.getSelectedItem();
                Iterator<Player> it = pl.iterator();
                Iterator oit = opl.iterator();

                Player p;
                while(it.hasNext()) {
                    p = (Player)it.next();
                    if (p.name().equals(n)) {
                        Main.this.tempPlayer = p;
                        break;
                    }
                }

                while(oit.hasNext()) {
                    p = (Player)oit.next();
                    if (p.name().equals(n)) {
                        opl.remove(p);
                        break;
                    }
                }

                if (Main.this.tempPlayer != null) {
                    if (this.color == 0) {
                        Main.this.White = Main.this.tempPlayer;
                    } else {
                        Main.this.Black = Main.this.tempPlayer;
                    }

                    Main.this.bplayer = opl;
                    ojc.removeAllItems();
                    Iterator var13 = opl.iterator();

                    while(var13.hasNext()) {
                        Player s = (Player)var13.next();
                        ojc.addItem(s.name());
                    }

                    det.add(new JLabel(" " + Main.this.tempPlayer.name()));
                    det.add(new JLabel(" " + Main.this.tempPlayer.gamesplayed()));
                    det.add(new JLabel(" " + Main.this.tempPlayer.gameswon()));
                    PL.revalidate();
                    PL.repaint();
                    PL.add(det);
                    Main.this.selected = true;
                }
            }
        }
    }

    class TimeChange implements ChangeListener {
        TimeChange() {
        }

        public void stateChanged(ChangeEvent arg0) {
            Main.timeRemaining = Main.this.timeSlider.getValue() * 60;
        }
    }

    class START implements ActionListener {
        START() {
        }

        public void actionPerformed(ActionEvent arg0) {
            if (Main.this.White != null && Main.this.Black != null) {
                Main.this.White.updateGamesPlayed();
                Main.this.White.Update_Player();
                Main.this.Black.updateGamesPlayed();
                Main.this.Black.Update_Player();
                Main.this.WNewPlayer.disable();
                Main.this.BNewPlayer.disable();
                Main.this.wselect.disable();
                Main.this.bselect.disable();
                Main.this.split.remove(Main.this.temp);
                Main.this.split.add(Main.this.board);
                Main.this.showPlayer.remove(Main.this.timeSlider);
                Main.this.mov = new JLabel("Gerak: ");
                Main.this.mov.setFont(new Font("Comic Sans MS", 0, 20));
                Main.this.mov.setForeground(Color.red);
                Main.this.showPlayer.add(Main.this.mov);
                Main.CHNC = new JLabel(Main.move);
                Main.CHNC.setFont(new Font("Comic Sans MS", 1, 20));
                Main.CHNC.setForeground(Color.blue);
                Main.this.showPlayer.add(Main.CHNC);
                Main.this.displayTime.remove(Main.this.start);
                Main.this.displayTime.add(Main.this.label);
                Main.this.timer = new Time(Main.this.label);
                Main.this.timer.start();
            } else {
                JOptionPane.showMessageDialog(Main.this.controlPanel, "Fill in the details");
            }
        }
    }
}