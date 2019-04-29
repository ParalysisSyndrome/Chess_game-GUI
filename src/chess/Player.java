/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.Component;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Player implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private Integer gamesplayed;
    private Integer gameswon;

    public Player(String name) {
        this.name = name.trim();
        this.gamesplayed = new Integer(0);
        this.gameswon = new Integer(0);
    }

    Player(String string, int aInt, int aInt0) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String name() {
        return this.name;
    }

    public Integer gamesplayed() {
        return this.gamesplayed;
    }

    public Integer gameswon() {
        return this.gameswon;
    }

    public Integer winpercent() {
        return new Integer(this.gameswon * 100 / this.gamesplayed);
    }

    public void updateGamesPlayed() {
        Integer var1 = this.gamesplayed;
        Integer var2 = this.gamesplayed = this.gamesplayed + 1;
    }

    public void updateGamesWon() {
        Integer var1 = this.gameswon;
        Integer var2 = this.gameswon = this.gameswon + 1;
    }

    public static ArrayList<Player> fetch_players() {
        ObjectInputStream input = null;
        ArrayList players = new ArrayList();

        try {
            File infile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            input = new ObjectInputStream(new FileInputStream(infile));

            try {
                while(true) {
                    Player tempplayer = (Player)input.readObject();
                    players.add(tempplayer);
                }
            } catch (EOFException var6) {
                input.close();
            }
        } catch (FileNotFoundException var7) {
            players.clear();
            return players;
        } catch (IOException var8) {
            var8.printStackTrace();

            try {
                input.close();
            } catch (IOException var5) {
            }

            JOptionPane.showMessageDialog((Component)null, "Unable to read the required Game files !!");
        } catch (ClassNotFoundException var9) {
            var9.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception var10) {
            var10.printStackTrace();
        }

        return players;
    }

    public void Update_Player() {
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        File inputfile = null;
        File outputfile = null;

        try {
            inputfile = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            outputfile = new File(System.getProperty("user.dir") + File.separator + "tempfile.dat");
        } catch (SecurityException var8) {
            JOptionPane.showMessageDialog((Component)null, "Read-Write Permission Denied !! Program Cannot Start");
            System.exit(0);
        }

        try {
            if (!outputfile.exists()) {
                outputfile.createNewFile();
            }

            if (!inputfile.exists()) {
                output = new ObjectOutputStream(new FileOutputStream(outputfile, true));
                output.writeObject(this);
            } else {
                input = new ObjectInputStream(new FileInputStream(inputfile));
                output = new ObjectOutputStream(new FileOutputStream(outputfile));
                boolean playerdonotexist = true;

                try {
                    while(true) {
                        while(true) {
                            Player temp_player = (Player)input.readObject();
                            if (temp_player.name().equals(this.name())) {
                                output.writeObject(this);
                                playerdonotexist = false;
                            } else {
                                output.writeObject(temp_player);
                            }
                        }
                    }
                } catch (EOFException var9) {
                    input.close();
                    if (playerdonotexist) {
                        output.writeObject(this);
                    }
                }
            }

            inputfile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir") + File.separator + "chessgamedata.dat");
            if (!outputfile.renameTo(newf)) {
                System.out.println("File Renameing Unsuccessful");
            }
        } catch (FileNotFoundException var10) {
            var10.printStackTrace();
        } catch (IOException var11) {
            var11.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Unable to read/write the required Game files !! Press ok to continue");
        } catch (ClassNotFoundException var12) {
            var12.printStackTrace();
            JOptionPane.showMessageDialog((Component)null, "Game Data File Corrupted !! Click Ok to Continue Builing New File");
        } catch (Exception var13) {
        }

    }
}