package wolfSheepSimulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class WolfSheepSimulation extends JFrame {
    final Color color = Color.GREEN;
    public WolfSheepSimulation(int width, int height, int mseconds, int sheepAmount) {
        InitUI(width, height, mseconds, sheepAmount);
    }

    void InitUI(int width, int height, int mseconds, int sheepAmount) {
        this.setTitle("WolfSheepSimulation");
        this.setSize(width*100, height*100);
        this.setLocationRelativeTo(null);
        GridLayout layout = new GridLayout(height, width);
        this.setLayout(layout);
        JButton[][] Board = new JButton[height][width];

        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                Board[i][j] = new JButton();
                Board[i][j].setPreferredSize(new Dimension(5,5));
                Board[i][j].setBackground(color);
                this.add(Board[i][j]);
            }
        }



        this.setVisible(true);
    }
    

}