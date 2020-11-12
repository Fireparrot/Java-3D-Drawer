package drawer;

import java.awt.Color;

public class Triangle {
    
    private final int color;
    private final Point[] points = new Point[3];
    
    public Triangle(Point p1, Point p2, Point p3) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        color = Color.WHITE.getRGB();
    }
    public Triangle(Point p1, Point p2, Point p3, int color) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        this.color = color;
    }
    public Triangle(Point p1, Point p2, Point p3, Color color) {
        points[0] = p1;
        points[1] = p2;
        points[2] = p3;
        this.color = color.getRGB();
    }
    
    public Point[] getPoints() {return points;}
    public Point getPoint(int i) {if(i>=0 && i<3) {return points[i];} else {return null;}}
    public int getColor() {return color;}
    
    @Override
    public String toString() {return points[0] + "; " + points[1] + "; " + points[2];}
    
}