package wolfSheepSimulation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import animals.*;


public class WolfSheepSimulation extends JFrame {
    public final Color color = new Color(29,130,26);
    public JButton[][] Board;
    public Random generator = new Random();
    public Wolf wolf;
    public ArrayList<Sheep> sheep;
    public int height;
    public int width;
    public int mseconds;
    public int sheepAmount;
    public WolfSheepSimulation(int w, int h, int ms, int sa) {
        height = h;
        width = w;
        mseconds = ms;
        sheepAmount = sa;
        InitUI();
        InitSheepWolf();
    }

    void InitUI() {
        this.setTitle("WolfSheepSimulation");
        this.setSize(width*100, height*100);
        this.setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        GridLayout layout = new GridLayout(height, width);
        this.setLayout(layout);
        Board = new JButton[height][width];

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

    void InitSheepWolf() {
        wolf = new Wolf(this);
        sheep = new ArrayList<Sheep>();
        for(int i = 0; i < sheepAmount; i++)
            sheep.add(new Sheep(this));

    }
    

}