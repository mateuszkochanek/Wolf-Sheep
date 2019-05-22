package animals;
import java.awt.Color;

import calculations.*;
import wolfSheepSimulation.WolfSheepSimulation;

public class Wolf extends Thread {
    WolfSheepSimulation simulation;
    public Point wolfPoint;
    public Wolf(WolfSheepSimulation simulationReference){
        simulation = simulationReference;
        wolfPoint = new Point(simulation.generator.nextInt(simulation.height),simulation.generator.nextInt(simulation.width));
        simulation.Board[wolfPoint.x][wolfPoint.y].setBackground(Color.BLACK);
    }

    public void run(){
        Move();
    }

    private void Move(){

    }
}