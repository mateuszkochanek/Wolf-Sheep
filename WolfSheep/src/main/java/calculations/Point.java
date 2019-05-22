package calculations;

public class Point {
    public int x;
    public int y;
    public Point(int xx, int yy) {
        x=xx;
        y=yy;
    }
    public static int DistanceOfTwoPoints(Point first, Point second) {
        return  Math.max(Math.abs(first.x-second.x),Math.abs(first.y-second.y));
    }
}