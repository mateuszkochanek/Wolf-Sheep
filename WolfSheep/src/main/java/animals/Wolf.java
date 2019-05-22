package animals;

import java.awt.Color;
import java.util.ArrayList;

import calculations.*;
import wolfSheepSimulation.WolfSheepSimulation;

public class Wolf extends Thread {
    WolfSheepSimulation simulation;
    public Point wolfPoint;
    protected int food = 0;
    public Wolf(WolfSheepSimulation simulationReference){
        simulation = simulationReference;
        wolfPoint = new Point(simulation.generator.nextInt(simulation.height),simulation.generator.nextInt(simulation.width));
        simulation.Board[wolfPoint.x][wolfPoint.y].setBackground(Color.BLACK);
    }

    public void run(){
        synchronized(simulation.Board){
            while(simulation.sheep.size() > 0) {
                if(food>0){
                    this.food--;
                } else {
                    Move();
                    simulation.validate();
                }
                try{
                    simulation.Board.wait(simulation.generator.nextInt(simulation.delay+1)+(simulation.delay/2));
                } catch (Exception ex){
                    System.out.println(ex.getMessage());
                }
            }
        }
    }

    private void Move(){
        ArrayList<Point> sheepPointsMinDist = new ArrayList<Point>();
        ArrayList<Sheep> sheepMinDist = new ArrayList<Sheep>();
        Point movingPoint;
        int minDistance=simulation.height*simulation.width;
        int distance,randIndex;

        for(Sheep sheepo : simulation.sheep ) {
            distance=Point.DistanceOfTwoPoints(sheepo.sheepPoint,this.wolfPoint);
            if(distance < minDistance){
                minDistance = distance;
                sheepPointsMinDist.clear();
                sheepMinDist.clear();
                sheepPointsMinDist.add(sheepo.sheepPoint);
                sheepMinDist.add(sheepo);
            } else if(distance == minDistance) {
                sheepPointsMinDist.add(sheepo.sheepPoint);
                sheepMinDist.add(sheepo);
            }
       }

       ArrayList<Point> minimalDistancePoints = FindPointsWithMinimaldistance(sheepPointsMinDist,minDistance);
       randIndex = simulation.generator.nextInt(minimalDistancePoints.size());
       movingPoint = minimalDistancePoints.get(randIndex);
       simulation.Board[wolfPoint.x][wolfPoint.y].setBackground(simulation.Green);
       wolfPoint = new Point(movingPoint.x,movingPoint.y);
       if(simulation.Board[wolfPoint.x][wolfPoint.y].getBackground()==Color.WHITE){
            for(Sheep sheepo : sheepMinDist ) {
                if(sheepo.sheepPoint.x == wolfPoint.x && sheepo.sheepPoint.y == wolfPoint.y){
                    sheepo.IsDead();
                    this.food = 5;
                    break;
                }
            }
       }
       simulation.Board[wolfPoint.x][wolfPoint.y].setBackground(Color.BLACK);
    }

    private ArrayList<Point> FindPointsWithMinimaldistance(ArrayList<Point> sheepPointsMinDist, int minDistance){
        ArrayList<Point> availablePoints = FindAvailablePoints();
        ArrayList<Point> mindistPoints = new ArrayList<Point>();
        int distance;
        for(Point point : availablePoints){
            for(Point pointSheep : sheepPointsMinDist){
                distance=Point.DistanceOfTwoPoints(point,pointSheep);
                if(distance < minDistance){
                    mindistPoints.add(point);
                    break;
                }
            }
        }
        //System.out.println(mindistPoints.size() + "    ");
        return mindistPoints;
    }

    private ArrayList<Point> FindAvailablePoints(){
        ArrayList<Point> availablePoints = new ArrayList<Point>();
        for(int i = wolfPoint.x-1 ; i<=wolfPoint.x+1 ; i++){
            for(int j = wolfPoint.y-1 ; j<=wolfPoint.y+1 ; j++){
                if(((i>=0 && i<simulation.height) && (j>=0 && j<simulation.width )) && !(j==wolfPoint.y && i==wolfPoint.x)){
                    availablePoints.add(new Point(i,j));
                }
            }
        }
        //System.out.println(availablePoints.size() + "    ");
        return availablePoints;
    }
}