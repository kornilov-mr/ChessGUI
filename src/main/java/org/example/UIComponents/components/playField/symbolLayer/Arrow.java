package org.example.UIComponents.components.playField.symbolLayer;

import org.example.utils.logical.Point2d;

import java.awt.*;
import java.util.Objects;

/**
 * Graphical Arrow on playing field
 */
public class Arrow {
    private final int xStart;
    private int yStart;
    private final int xEnd;
    private int yEnd;


    public Arrow(int xStart, int yStart, int xEnd, int yEnd) {
        this.xStart = xStart;
        this.yStart =  yStart;
        this.xEnd = xEnd;
        this.yEnd =  yEnd;
    }


    public Shape getArrowHeadPolygon(int squareSize) {

        Point2d[] arrowHeadPoints = new Point2d[3];

        arrowHeadPoints[0] = new Point2d(-1 * (double) squareSize / 4, 0);
        arrowHeadPoints[1] = new Point2d(0, -1 * (double) squareSize / 4);
        arrowHeadPoints[2] = new Point2d((double) squareSize / 4, 0);

        Point2d radiusVector = new Point2d(xStart - xEnd, yStart - yEnd);
        double angleOfRadiusVector = radiusVector.getAngleFromXoYo();
        double angleToRotateHead = (angleOfRadiusVector - Math.PI / 2);

        Point2d cornerPoint = new Point2d(squareSize * xEnd+ (double) squareSize /2,
                squareSize * yEnd+ (double) squareSize /2);

        arrowHeadPoints[0] = arrowHeadPoints[0].rotateAroundOrigin(angleToRotateHead).addPoint(cornerPoint);
        arrowHeadPoints[1] = arrowHeadPoints[1].rotateAroundOrigin(angleToRotateHead).addPoint(cornerPoint);
        arrowHeadPoints[2] = arrowHeadPoints[2].rotateAroundOrigin(angleToRotateHead).addPoint(cornerPoint);

        int[] XPoints = new int[3];
        int[] YPoints = new int[3];

        XPoints[0] = (int) arrowHeadPoints[0].getX();
        YPoints[0] = (int) arrowHeadPoints[0].getY();
        XPoints[1] = (int) arrowHeadPoints[1].getX();
        YPoints[1] = (int) arrowHeadPoints[1].getY();
        XPoints[2] = (int) arrowHeadPoints[2].getX();
        YPoints[2] = (int) arrowHeadPoints[2].getY();
        return new Polygon(XPoints, YPoints, 3);
    }

    public Shape getArrowBodyPolygon(int squareSize) {
        Point2d[] bodyPoints = new Point2d[4];

        double width = new Point2d(xStart - xEnd, yStart - yEnd).getDistanceFromOrigin() * squareSize;

        bodyPoints[0] = new Point2d(0,-1 * (double) squareSize / 8);
        bodyPoints[1] = new Point2d(0,(double) squareSize / 8);
        bodyPoints[2] = new Point2d(width,(double) squareSize / 8);
        bodyPoints[3] = new Point2d(width,-1 *(double) squareSize / 8);

        Point2d radiusVector = new Point2d(xStart - xEnd, yStart - yEnd);
        double angleOfRadiusVector = radiusVector.getAngleFromXoYo();

        Point2d cornerPoint = new Point2d(squareSize * xEnd+ (double) squareSize /2,
                squareSize * yEnd+ (double) squareSize /2);

        bodyPoints[0] = bodyPoints[0].rotateAroundOrigin(angleOfRadiusVector).addPoint(cornerPoint);
        bodyPoints[1] = bodyPoints[1].rotateAroundOrigin(angleOfRadiusVector).addPoint(cornerPoint);
        bodyPoints[2] = bodyPoints[2].rotateAroundOrigin(angleOfRadiusVector).addPoint(cornerPoint);
        bodyPoints[3] = bodyPoints[3].rotateAroundOrigin(angleOfRadiusVector).addPoint(cornerPoint);

        int[] XPoints = new int[4];
        int[] YPoints = new int[4];

        XPoints[0] = (int) bodyPoints[0].getX();
        YPoints[0] = (int) bodyPoints[0].getY();
        XPoints[1] = (int) bodyPoints[1].getX();
        YPoints[1] = (int) bodyPoints[1].getY();
        XPoints[2] = (int) bodyPoints[2].getX();
        YPoints[2] = (int) bodyPoints[2].getY();
        XPoints[3] = (int) bodyPoints[3].getX();
        YPoints[3] = (int) bodyPoints[3].getY();
        return new Polygon(XPoints, YPoints, 4);
    }
    public void changeOrientation(){
        yStart=7-yStart;
        yEnd=7-yEnd;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Arrow arrow)) return false;
        return xStart == arrow.xStart && yStart == arrow.yStart && xEnd == arrow.xEnd && yEnd == arrow.yEnd;
    }

    @Override
    public int hashCode() {
        return Objects.hash(xStart, yStart, xEnd, yEnd);
    }

    @Override
    public String toString() {
        return "Arrow{" +
                "xStart=" + xStart +
                ", yStart=" + yStart +
                ", xEnd=" + xEnd +
                ", yEnd=" + yEnd +
                '}';
    }
}
