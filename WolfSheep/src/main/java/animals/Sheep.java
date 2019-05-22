package animals;
import java.awt.Color;

import calculations.*;
import wolfSheepSimulation.WolfSheepSimulation;

public class Sheep extends Thread {
    WolfSheepSimulation simulation;
    public Point sheepPoint;
    private boolean isAlive = true;
    public Sheep(WolfSheepSimulation simulationReference) {
        simulation = simulationReference;
        boolean iscreated = false;
        while(!iscreated){
            sheepPoint = new Point(simulation.generator.nextInt(simulation.height),simulation.generator.nextInt(simulation.width));
            if(simulation.Board[sheepPoint.x][sheepPoint.y].getBackground() == simulation.color ){
                simulation.Board[sheepPoint.x][sheepPoint.y].setBackground(Color.WHITE);
                iscreated = true;
            }
        }
	}

	public void run(){
        
    }

    public void IsDead(){
        isAlive = false;
        simulation.sheep.remove(this);
    }
}