/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class Database {
    private Connection con;
    private Statement stmt;
    private ResultSet rs;
    ArrayList<Player> ply;

    public Database() {
    }

    public void connect() {
        try {
            String url = "jdbc:mysql://localhost/db_catur";
            String username = "root";
            String pass = "";
            this.con = DriverManager.getConnection(url, username, pass);
            this.stmt = this.con.createStatement();
            System.out.println("Connected");
        } catch (SQLException var4) {
            System.out.println("connection error");
            var4.printStackTrace();
        }

    }

    public void disconnect() {
        try {
            this.con.close();
            this.stmt.close();
        } catch (Exception var2) {
            var2.printStackTrace();
        }

    }

    public void loadPlayer() {
        this.connect();

        try {
            this.ply = new ArrayList();
            this.rs = this.stmt.executeQuery("SELECT * FROM riwayat_permainan");

            while(this.rs.next()) {
                this.ply.add(new Player(this.rs.getString("Nama"), this.rs.getInt("Jumlah_Permainan"), this.rs.getInt("Jumlah_Kemenanangan")));
            }
        } catch (Exception var2) {
            var2.printStackTrace();
        }

        this.disconnect();
    }

    public boolean insertRiwayat(Player c) {
        this.connect();
        boolean cek = false;

        try {
            int row = this.stmt.executeUpdate("INSERT INTO riwayat_permainan(Nama, Jumlah_Permainan, Jumlah_Kemenangan)VALUES('" + c.name() + "', '" + c.gamesplayed() + "', '" + c.gameswon() + "')");
            if (row > 0) {
                cek = true;
            }
        } catch (Exception var5) {
            var5.printStackTrace();
        }

        this.disconnect();
        return cek;
    }

    public ArrayList<Player> getPlayer() {
        this.loadPlayer();
        return this.ply;
    }
}