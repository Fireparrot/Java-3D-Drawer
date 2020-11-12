package drawer;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.stream.ImageOutputStream;

public class Environment {
    
    private Triangle[] triangles;
    private Line[] lines;
    private final Renderer renderer;
    private static final Triangle testTriangle = new Triangle(new Point(-10, -10, 50), new Point(10, -10, 50), new Point(0, 10, 50));

    public Environment() {
        this.triangles = new Triangle[10000];
        this.lines = new Line[100000];
        this.renderer = new Renderer();
    }
    
    
    public void addLine(Line l) {
        for(int i = 0; i < lines.length; i++) {
            if(lines[i] == null) {
                lines[i] = l;
                break;
            }
        }
    }
    public void addLine(Point p1, Point p2) {
        Line l = new Line(p1, p2);
        for(int i = 0; i < lines.length; i++) {
            if(lines[i] == null) {
                lines[i] = l;
                break;
            }
        }
    }
    public void addLine(Point p1, Point p2, int color) {
        Line l = new Line(p1, p2, color);
        for(int i = 0; i < lines.length; i++) {
            if(lines[i] == null) {
                lines[i] = l;
                break;
            }
        }
    }
    public void addLine(Point p1, Point p2, Color color) {
        Line l = new Line(p1, p2, color.getRGB());
        for(int i = 0; i < lines.length; i++) {
            if(lines[i] == null) {
                lines[i] = l;
                break;
            }
        }
    }
    public void clearLines() {this.lines = new Line[lines.length];}
    
    public void addTriangle(Triangle t) {
        for(int i = 0; i < triangles.length; i++) {
            if(triangles[i] == null) {
                triangles[i] = t;
                break;
            }
        }
    }
    public void addTriangle(Point p1, Point p2, Point p3) {
        Triangle t = new Triangle(p1, p2, p3);
        for(int i = 0; i < triangles.length; i++) {
            if(triangles[i] == null) {
                triangles[i] = t;
                break;
            }
        }
    }
    public void addTriangle(Point p1, Point p2, Point p3, int color) {
        Triangle t = new Triangle(p1, p2, p3, color);
        for(int i = 0; i < triangles.length; i++) {
            if(triangles[i] == null) {
                triangles[i] = t;
                break;
            }
        }
    }
    public void addTriangle(Point p1, Point p2, Point p3, Color color) {
        Triangle t = new Triangle(p1, p2, p3, color);
        for(int i = 0; i < triangles.length; i++) {
            if(triangles[i] == null) {
                triangles[i] = t;
                break;
            }
        }
    }
    public void clearTriangles() {this.triangles = new Triangle[triangles.length];}
    public void addTetra(Point p1, Point p2, Point p3, Point p4, int color) {
        addTriangle(p1, p2, p3, color);
        addTriangle(p1, p2, p4, color);
        addTriangle(p1, p3, p4, color);
        addTriangle(p2, p3, p4, color);
    }
    
    public void addBezier(Point[] path) {
        for(double i = 0; i < 100; i++) {
            addLine(Point.bezier(path, i/100), Point.bezier(path, (i+1)/100));
        }
    }
    public void addBezier(Point[] path, int color) {
        for(double i = 0; i < 100; i++) {
            addLine(Point.bezier(path, i/100), Point.bezier(path, (i+1)/100), color);
        }
    }
    
    public void update() throws InterruptedException {
        renderer.clearCanvas();
        
        for (Line line : lines) {
            if (line == null) {
                break;
            }
            renderer.drawUserLine(line);
        }
        for (Triangle triangle : triangles) {
            if (triangle == null) {
                break;
            }
            renderer.drawUserTriangle(triangle);
        }
        /*
        double[][] map = Func.perlinCubic(500, 500, 0, 10, 0.3);
        double lowest = Func.lowest(map);
        for(int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++) {
                double p = map[i][j]*5;
                double d = (map[i][j]-lowest+0.2)*200;
                //renderer.drawPoint(new Color((int)(Func.decimalDifference(p)*96), (int)(Func.decimalDifference(p)*64), (int)(Func.decimalDifference(p)*16)), i, j, 10);
                renderer.drawPoint(Func.getColor((int)(d/1), (int)(d/1), (int)(d/1)), i, j, 10);
                //renderer.drawPoint(Func.getColor((int)(d), (int)(d/1.8), (int)(d/4)), 2*i, 2*j, 10);
                //renderer.drawPoint(Func.getColor((int)(d), (int)(d/1.8), (int)(d/4)), 2*i+1, 2*j, 10);
                //renderer.drawPoint(Func.getColor((int)(d), (int)(d/1.8), (int)(d/4)), 2*i, 2*j+1, 10);
                //renderer.drawPoint(Func.getColor((int)(d), (int)(d/1.8), (int)(d/4)), 2*i+1, 2*j+1, 10);
            }
        }
        */
        //Thread.sleep(20);
        //Func.blur(renderer.getCanvas(), 1);
        renderer.repaint();
        Thread.sleep(10);
        
        /*
        double[][][] map = Func.perlinCubic(250, 250, 250, 2, 6, 0.5);
        for(int counter = 0; counter < map[0][0].length; counter += 1) {
            renderer.clearCanvas();
            System.out.println("counter: " + counter);
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[0].length; j++) {
                    double p = map[i][j][counter]*5;
                    double d = map[i][j][counter]*128;
                    //renderer.drawPoint(new Color((int)(Func.decimalDifference(p)*96), (int)(Func.decimalDifference(p)*64), (int)(Func.decimalDifference(p)*16)), i, j, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i, 2*j, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i+1, 2*j, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i, 2*j+1, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i+1, 2*j+1, 10);
                }
            }
            
            //Thread.sleep(20);
            //Func.blur(renderer.getCanvas(), 1);
            renderer.repaint();
            Thread.sleep(10);
        }
        */
    }
    public void update(BufferedImage bi, ImageOutputStream ios, GifSequenceWriter gsw) throws InterruptedException, IOException {
        renderer.clearCanvas();
        
        double[][][] map = Func.perlinCubic(500, 400, 50, 2, 8, 0.5);
        for(int counter = 0; counter < map[0][0].length; counter += 1) {
            renderer.clearCanvas();
            System.out.println("counter: " + counter);
            for(int i = 0; i < map.length; i++) {
                for(int j = 0; j < map[0].length; j++) {
                    double p = map[i][j][counter]*5;
                    double d = map[i][j][counter]*128;
                    //renderer.drawPoint(new Color((int)(Func.decimalDifference(p)*96), (int)(Func.decimalDifference(p)*64), (int)(Func.decimalDifference(p)*16)), i, j, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i, 2*j, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i+1, 2*j, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i, 2*j+1, 10);
                        renderer.drawPoint(Func.getColor((int)(d), (int)(d/1), (int)(d/1)), 2*i+1, 2*j+1, 10);
                }
            }
                if(counter == 0) {
                    bi = getRenderer().getCanvas();
                    gsw = new GifSequenceWriter(ios,
                            bi.getType(), 1, false);
                    gsw.writeToSequence(bi);
                } else {
                    draw(gsw);
                }
            renderer.repaint();
            Thread.sleep(20);
        }
        gsw.close();
        ios.close();
        System.out.println("Done!");
        
    }
    
    public Renderer getRenderer() {return renderer;}
    public Line[] getLines() {return lines;}
    public Triangle[] getTriangles() {return triangles;}
    public Line getLine(int n) {return lines[n];}
    public Triangle getTriangle(int n) {return triangles[n];}
    
    public void draw(GifSequenceWriter writer) throws IOException {
        writer.writeToSequence(renderer.getCanvas());
    }
    
}