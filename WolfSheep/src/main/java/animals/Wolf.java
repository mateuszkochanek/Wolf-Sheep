package animals;

import java.awt.Color;
import java.util.ArrayList;

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
        while(simulation.sheep.size() > 0) {
            Move();
        }
    }

    private void Move(){
        ArrayList<Point> sheepPointsMinDist = new ArrayList<Point>();
        int minDistance=simulation.height*simulation.width;
        int distance;
        for(Sheep sheepo : simulation.sheep ) {
            distance=Point.DistanceOfTwoPoints(sheepo.sheepPoint,this.wolfPoint);
            if(distance < minDistance){
                minDistance = distance;
                sheepPointsMinDist.clear();
                sheepPointsMinDist.add(sheepo.sheepPoint);
            } else if(distance == minDistance) {
                sheepPointsMinDist.add(sheepo.sheepPoint);
            }
       }

    }
    private ArrayList<Point> FindAvailablePoints(){
        ArrayList<Point> availablePoints = new ArrayList<Point>();
        
        return availablePoints;
    }
}