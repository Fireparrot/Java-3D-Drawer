package drawer;

import java.awt.Color;

public class Line {
    
    private final int color;
    Point p1, p2;
    
    public Line(Point p1, Point p2) {
        this.color = Color.BLUE.getRGB();
        this.p1 = p1;
        this.p2 = p2;
    }
    public Line(Point p1, Point p2, int color) {
        this.color = color;
        this.p1 = p1;
        this.p2 = p2;
    }
    public Point[] getPoints() {
        Point[] points = {p1, p2};
        return points;
    }
    public Point getPoint(int i) {
        switch(i) {
            case(0):
                return p1;
            case(1):
                return p2;
            default:
                return null;
        }
    }
    public int getColor() {return color;}
    
    @Override
    public String toString() {return p1 + "; " + p2;}
    
    
}