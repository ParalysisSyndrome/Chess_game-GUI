/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import javax.swing.Timer;

public class Time {
    private JLabel label;
    Timer countdownTimer = new Timer(1000, new Time.CountdownTimerListener());
    int Timerem;

    public Time(JLabel passedLabel) {
        this.label = passedLabel;
        this.Timerem = Main.timeRemaining;
    }

    public void start() {
        this.countdownTimer.start();
    }

    public void reset() {
        this.Timerem = Main.timeRemaining;
    }

    class CountdownTimerListener implements ActionListener {
        CountdownTimerListener() {
        }

        public void actionPerformed(ActionEvent e) {
            if (Time.this.Timerem > 0) {
                int min = Time.this.Timerem / 60;
                int sec = Time.this.Timerem % 60;
                Time.this.label.setText(min + ":" + (sec >= 10 ? String.valueOf(sec) : "0" + String.valueOf(sec)));
                --Time.this.Timerem;
            } else {
                Time.this.label.setText("Waktu Habis");
                Time.this.reset();
                Time.this.start();
                Main.Mainboard.changechance();
            }

        }
    }
}