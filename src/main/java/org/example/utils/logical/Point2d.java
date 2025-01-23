package org.example.utils.logical;

public class Point2d {
    private final double x;
    private final double y;

    public Point2d(double x, double y) {
        this.x = x;
        this.y = y;
    }
    public Point2d rotateAroundOrigin(double angle) {
        return new Point2d(Math.cos(angle)*x- Math.sin(angle)*y,
                Math.sin(angle)*x + Math.cos(angle)*y);
    }
    public double getAngleFromXoYo(){
        return Math.atan2(y,x);
    }
    public Point2d addPoint(Point2d otherPoint){
        return new Point2d(x+otherPoint.x,y+otherPoint.y);
    }
    public double getDistanceFromOrigin(){
        return Math.sqrt(Math.pow(x,2)+Math.pow(y,2));
    }
    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }
}
