package animals;

import java.awt.Color;
import java.util.ArrayList;

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
            if(simulation.Board[sheepPoint.x][sheepPoint.y].getBackground() == simulation.Green ){
                simulation.Board[sheepPoint.x][sheepPoint.y].setBackground(Color.WHITE);
                iscreated = true;
            }
        }
	}

	public void run(){
        synchronized(simulation.Board){
            while(isAlive) {
                    Move();
                    simulation.validate();
                try{
                    simulation.Board.wait(simulation.generator.nextInt(simulation.delay+1)+(simulation.delay/2));
                } catch (Exception e){
                    System.out.println("cos sie zepsulo");
                }
            }
            simulation.sheep.remove(this);
        }
    }
    private void Move(){

       ArrayList<Point> maximalDistancePoints = FindPointsWithMaximaldistance();
       ArrayList<Point> availablePoints = FindAvailablePoints();
       int randIndex;
       Point movingPoint;

       if((sheepPoint.x == 0 || sheepPoint.x == simulation.height-1 || sheepPoint.y == 0 || sheepPoint.y == simulation.width-1) && availablePoints.size() !=0 ){
            randIndex = simulation.generator.nextInt(availablePoints.size());
            movingPoint = availablePoints.get(randIndex);
       } else if(maximalDistancePoints.size() != 0){
            randIndex = simulation.generator.nextInt(maximalDistancePoints.size());
            movingPoint = maximalDistancePoints.get(randIndex);
       } else{
            movingPoint = sheepPoint;
       }
       simulation.Board[sheepPoint.x][sheepPoint.y].setBackground(simulation.Green);
       sheepPoint = new Point(movingPoint.x,movingPoint.y);
       simulation.Board[sheepPoint.x][sheepPoint.y].setBackground(Color.WHITE);
    }

    private ArrayList<Point> FindAvailablePoints(){
        ArrayList<Point> availablePoints = new ArrayList<Point>();
        for(int i = sheepPoint.x-1 ; i<=sheepPoint.x+1 ; i++){
            for(int j = sheepPoint.y-1 ; j<=sheepPoint.y+1 ; j++){
                if(((i>=0 && i<simulation.height) && (j>=0 && j<simulation.width )) && !(j==sheepPoint.y && i==sheepPoint.x) && simulation.Board[i][j].getBackground()!=Color.WHITE && simulation.Board[i][j].getBackground()!=Color.BLACK){
                    availablePoints.add(new Point(i,j));
                }
            }
        }
        return availablePoints;
    }

    private ArrayList<Point> FindPointsWithMaximaldistance(){
        ArrayList<Point> availablePoints = FindAvailablePoints();
        ArrayList<Point> maxdistPoints = new ArrayList<Point>();
        int distance, distanceFromWolf = Point.DistanceOfTwoPoints(simulation.wolf.wolfPoint,sheepPoint);
        for(Point point : availablePoints){
            distance=Point.DistanceOfTwoPoints(point,simulation.wolf.wolfPoint);
            if(distance > distanceFromWolf){
                maxdistPoints.add(point);
            }   
        }
        return maxdistPoints;
    }

    public void IsDead(){
        isAlive = false;
    }
}