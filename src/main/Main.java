package main;

import java.awt.Component;
import javax.swing.JFrame;

public class Main extends JFrame {
    public static void main(String[] args) {
        JFrame windows = new JFrame();
        windows.setDefaultCloseOperation(3);
        windows.setResizable(false);
        Mappanel map = new Mappanel();
        windows.getContentPane().add(map);
        windows.pack();
        windows.setLocationRelativeTo(null);
        windows.setVisible(true);
        map.setupGame();
        map.startGameThread();
    }
}