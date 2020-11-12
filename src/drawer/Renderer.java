package drawer;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import javax.swing.JComponent;

class Renderer extends JComponent {
    
    private final BufferedImage canvas;
    private double[][] depth;
    
    public BufferedImage getCanvas() {return canvas;}
    public double[][] getDepth() {return depth;}
    
    public Renderer(int x, int y) {
        canvas = new BufferedImage(x, y, BufferedImage.TYPE_INT_ARGB);
        canvas.setAccelerationPriority(1);
        depth = new double[x][y];
    }
    public Renderer() {
        canvas = new BufferedImage(Main.XASPECT, Main.YASPECT, BufferedImage.TYPE_INT_ARGB);
        canvas.setAccelerationPriority(1);
        depth = new double[Main.XASPECT][Main.YASPECT];
    }
    
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }


    public void fillCanvas(Color c) {
        int color = c.getRGB();
                depth = new double[depth.length][depth[0].length];
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
            }
        }
        //repaint();
    }
    public void fillCanvas(int color) {
        for (int x = 0; x < canvas.getWidth(); x++) {
            for (int y = 0; y < canvas.getHeight(); y++) {
                canvas.setRGB(x, y, color);
                depth[x][y] = 0;
            }
        }
        //repaint();
    }
    public void clearCanvas() {
        fillCanvas(Color.BLACK.getRGB());
    }
    
    public void drawPoint(Color c, Point p) {
        int x = (int)p.getX();
        int y = (int)p.getY();
        if(x >= 0 && y >= 0 && x < depth.length && y < depth[0].length) {
            if(p.getZ() > Main.hither) {
                if(depth[x][y] == 0) {
                    canvas.setRGB(x,y,c.getRGB());
                    depth[x][y] = p.getZ();
                } else {
                    if(depth[x][y] > p.getZ()) {
                        canvas.setRGB(x,y,c.getRGB());
                        depth[x][y] = p.getZ();
                    }
                }
            }
        }
    }
    public void drawPoint(int c, Point p) {
        int x = (int)p.getX();
        int y = (int)p.getY();
        if(x >= 0 && y >= 0 && x < 600 && y < 600) {
            if(p.getZ() > Main.hither) {
                if(depth[x][y] == 0) {
                    canvas.setRGB(x,y,c);
                    depth[x][y] = p.getZ();
                } else {
                    if(depth[x][y] > p.getZ()) {
                        canvas.setRGB(x,y,c);
                        depth[x][y] = p.getZ();
                    }
                }
            }
        }
    }
    public void drawPoint(Color c, double x, double y, double z) {
        int newX = (int)(x);
        int newY = (int)(y);
        if(newX >= 0 && newY >= 0 && newX < depth.length && newY < depth[0].length) {
            if(z > Main.hither) {
                if(depth[newX][newY] == 0) {
                    canvas.setRGB(newX,newY,c.getRGB());
                    depth[newX][newY] = z;
                } else {
                    if(depth[newX][newY] > z) {
                        canvas.setRGB(newX,newY,c.getRGB());
                        depth[newX][newY] = z;
                    }
                }
            }
        }
    }
    public void drawPoint(int color, double x, double y, double z) {
        int newX = (int)(x);
        int newY = (int)(y);
        if(newX >= 0 && newY >= 0 && newX < depth.length && newY < depth[0].length) {
            if(z > Main.hither) {
                if(depth[newX][newY] == 0) {
                    canvas.setRGB(newX,newY,color);
                    depth[newX][newY] = z;
                } else {
                    if(depth[newX][newY] > z) {
                        canvas.setRGB(newX,newY,color);
                        depth[newX][newY] = z;
                    }
                }
            }
        }
    }
    
    public void drawUserLine(Line l) {
        int color = l.getColor();
        
        Point originalP1 = l.getPoint(0).relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP2 = l.getPoint(1).relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawLine(color, transP1, transP2);
        /*
        if(transP1.getZ() < Main.hither) {
            if(transP2.getZ() < Main.hither) {
                //Do nothing
            } else {
                Point newP1 = transP1.onLine(transP2, new Point(0,0,1));
                drawLine(color, newP1, transP2);
            }
        } else {
            if(transP2.getZ() < Main.hither) {
                Point newP2 = transP2.onLine(transP1, new Point(0,0,1));
                drawLine(color, transP1, newP2);
            } else {
                drawLine(color, transP1, transP2);
            }
        }
        */
        //repaint();
    }
    public void drawUserLine(Color c, int x1, int y1, int z1, int x2, int y2, int z2) {
        
        int color = c.getRGB();
        
        Point originalP1 = new Point(x1-Main.getUserX(), y1-Main.getUserY(), z1-Main.getUserZ());
        Point originalP2 = new Point(x2-Main.getUserX(), y2-Main.getUserY(), z2-Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawLine(color, transP1, transP2);
        /*
        if(transP1.getZ() < Main.hither) {
            if(transP2.getZ() < Main.hither) {
                //Do nothing
            } else {
                Point newP1 = transP1.onLine(transP2, new Point(0,0,1));
                drawLine(color, newP1, transP2);
            }
        } else {
            if(transP2.getZ() < Main.hither) {
                Point newP2 = transP2.onLine(transP1, new Point(0,0,1));
                drawLine(color, transP1, newP2);
            } else {
                drawLine(color, transP1, transP2);
            }
        }
        */
        //repaint();
    }
    
    public void drawUserLine(Color c, Point p1, Point p2) {
        
        int color = c.getRGB();
        
        Point originalP1 = p1.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP2 = p2.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawLine(color, transP1, transP2);
        /*
        if(transP1.getZ() < Main.hither) {
            if(transP2.getZ() < Main.hither) {
                //Do nothing
            } else {
                Point newP1 = transP1.onLine(transP2, new Point(0,0,1));
                drawLine(color, newP1, transP2);
            }
        } else {
            if(transP2.getZ() < Main.hither) {
                Point newP2 = transP2.onLine(transP1, new Point(0,0,1));
                drawLine(color, transP1, newP2);
            } else {
                drawLine(color, transP1, transP2);
            }
        }
        */
        //repaint();
    }
    
    public void drawUserTriangle(Color c, Point p1, Point p2, Point p3) {
        
        int color = c.getRGB();
        
        Point originalP1 = p1.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP2 = p2.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP3 = p3.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP3 = originalP3.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawTriangle(color, transP1, transP2, transP3);
        
    }
    public void drawUserTriangle(int color, Point p1, Point p2, Point p3) {
        
        Point originalP1 = p1.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP2 = p2.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP3 = p3.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP3 = originalP3.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawTriangle(color, transP1, transP2, transP3);
        
    }
    public void drawUserTriangle(Triangle t) {
        
        int color = t.getColor();
        Point p1 = t.getPoint(0);
        Point p2 = t.getPoint(1);
        Point p3 = t.getPoint(2);
        
        Point originalP1 = p1.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP2 = p2.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP3 = p3.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP3 = originalP3.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawTriangle(color, transP1, transP2, transP3);
        
    }
    public void drawUserTriangle(int color, Triangle t) {
        
        Point p1 = t.getPoint(0);
        Point p2 = t.getPoint(1);
        Point p3 = t.getPoint(2);
        
        Point originalP1 = p1.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP2 = p2.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        Point originalP3 = p3.relative(Main.getUserX(), Main.getUserY(), Main.getUserZ());
        
        Quaternion rotor1 = new Quaternion(-Math.cos(Main.getUserA()/2), 0, -Math.sin(Main.getUserA()/2), 0);
        Quaternion rotor2 = new Quaternion(-Math.cos(Main.getUserB()/2), -Math.sin(Main.getUserB()/2), 0, 0);
        Quaternion rotor3 = new Quaternion(-Math.cos(Main.getUserC()/2), 0, 0, -Math.sin(Main.getUserC()/2));
        
        Point transP1 = originalP1.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP2 = originalP2.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        Point transP3 = originalP3.rotate(rotor1).rotate(rotor2).rotate(rotor3);
        
        drawTriangle(color, transP1, transP2, transP3);
        
    }
    
    public void drawLine(Color c, Point p1, Point p2) {
        double z1 = p1.getZ();
        double z2 = p2.getZ();
        if(z1 < Main.hither || z2 < Main.hither) {
            //Do nothing
        } else {
            double x1 = p1.getX()/p1.getZ();
            double x2 = p2.getX()/p2.getZ();
            double y1 = p1.getY()/p1.getZ();
            double y2 = p2.getY()/p2.getZ();
            int color = c.getRGB();
            x1 = Main.XASPECT/2 + Main.YASPECT/2*x1/Math.tan(Main.FOV/2);
            x2 = Main.XASPECT/2 + Main.YASPECT/2*x2/Math.tan(Main.FOV/2);
            y1 = Main.YASPECT/2 - Main.YASPECT/2*y1/Math.tan(Main.FOV/2);
            y2 = Main.YASPECT/2 - Main.YASPECT/2*y2/Math.tan(Main.FOV/2);
            if(Math.abs(x2-x1)>Math.abs(y2-y1)) {
                for(int i = 0; i < Math.abs(x2-x1); i++) {
                    drawPoint(color, new Point((x1 + i*Math.signum(x2-x1)), (y1 + (double)i*(y2-y1)/(x2-x1)*Math.signum(x2-x1)), z1 + (double)i*(z2-z1)/(x2-x1)*Math.signum(x2-x1)));
                }
            } else {
                for(int i = 0; i < Math.abs(y2-y1); i++) {
                    drawPoint(color, new Point((x1 + (double)i*(x2-x1)/(y2-y1)*Math.signum(y2-y1)), (y1 + i*Math.signum(y2-y1)), z1 + (double)i*(z2-z1)/(y2-y1)*Math.signum(y2-y1)));
                }
            }
        }
    }
    public void drawLine(int color, Point p1, Point p2) {
        double z1 = p1.getZ();
        double z2 = p2.getZ();
        if(z1 < Main.hither || z2 < Main.hither) {
            //Do nothing
        } else {
            double x1 = p1.getX()/p1.getZ();
            double x2 = p2.getX()/p2.getZ();
            double y1 = p1.getY()/p1.getZ();
            double y2 = p2.getY()/p2.getZ();
            x1 = Main.XASPECT/2 + Main.YASPECT/2*x1/Math.tan(Main.FOV/2);
            x2 = Main.XASPECT/2 + Main.YASPECT/2*x2/Math.tan(Main.FOV/2);
            y1 = Main.YASPECT/2 - Main.YASPECT/2*y1/Math.tan(Main.FOV/2);
            y2 = Main.YASPECT/2 - Main.YASPECT/2*y2/Math.tan(Main.FOV/2);
            
            if(Math.abs(x2-x1)>Math.abs(y2-y1)) {
                for(int i = 0; i < Math.abs(x2-x1); i++) {
                    drawPoint(color,(x1 + i*Math.signum(x2-x1)), (y1 + (double)i*(y2-y1)/(x2-x1)*Math.signum(x2-x1)), z1 + (double)i*(z2-z1)/(x2-x1)*Math.signum(x2-x1));
                }
            } else {
                for(int i = 0; i < Math.abs(y2-y1); i++) {
                    drawPoint(color, (x1 + (double)i*(x2-x1)/(y2-y1)*Math.signum(y2-y1)), (y1 + i*Math.signum(y2-y1)), z1 + (double)i*(z2-z1)/(y2-y1)*Math.signum(y2-y1));
                }
            }
        }
    }
    public void drawTriangle(Color c, Point p1, Point p2, Point p3) {
        double z1 = p1.getZ();
        double z2 = p2.getZ();
        double z3 = p3.getZ();
        if(z1 < Main.hither || z2 < Main.hither || z3 < Main.hither) {
            //Do nothing
        } else {
            double x1 = p1.getX()/p1.getZ();
            double x2 = p2.getX()/p2.getZ();
            double x3 = p3.getX()/p3.getZ();
            double y1 = p1.getY()/p1.getZ();
            double y2 = p2.getY()/p2.getZ();
            double y3 = p3.getY()/p3.getZ();
            int color = c.getRGB();
            x1 = Main.XASPECT/2 + Main.YASPECT/2*x1/Math.tan(Main.FOV/2);
            x2 = Main.XASPECT/2 + Main.YASPECT/2*x2/Math.tan(Main.FOV/2);
            x3 = Main.XASPECT/2 + Main.YASPECT/2*x3/Math.tan(Main.FOV/2);
            y1 = Main.YASPECT/2 - Main.YASPECT/2*y1/Math.tan(Main.FOV/2);
            y2 = Main.YASPECT/2 - Main.YASPECT/2*y2/Math.tan(Main.FOV/2);
            y3 = Main.YASPECT/2 - Main.YASPECT/2*y3/Math.tan(Main.FOV/2);
            Point newP1 = new Point(x1, y1, z1);
            Point newP2 = new Point(x2, y2, z2);
            Point newP3 = new Point(x3, y3, z3);
            
            for(int i = (int)Func.lowBounded(Point.lowestX(newP1, newP2, newP3), 0); i < (int)Func.highBounded(Point.highestX(newP1, newP2, newP3)+1, canvas.getWidth()); i++) {
                for(int j = (int)Func.lowBounded(Point.lowestY(newP1, newP2, newP3), 0); j < (int)Func.highBounded(Point.highestY(newP1, newP2, newP3)+1, canvas.getWidth()); j++) {
                    if(Point.inside(i, j, newP1, newP2, newP3)) {
                        drawPoint(color, i, j, Point.onTriangle(newP1, newP2, newP3, i, j).getZ());
                    }
                }
            }
        }
    }
    public void drawTriangle(int color, Point p1, Point p2, Point p3) {
        double z1 = p1.getZ();
        double z2 = p2.getZ();
        double z3 = p3.getZ();
        if(z1 < Main.hither || z2 < Main.hither || z3 < Main.hither) {
            //Do nothing
        } else {
            double x1 = p1.getX()/p1.getZ();
            double x2 = p2.getX()/p2.getZ();
            double x3 = p3.getX()/p3.getZ();
            double y1 = p1.getY()/p1.getZ();
            double y2 = p2.getY()/p2.getZ();
            double y3 = p3.getY()/p3.getZ();
            x1 = Main.XASPECT/2 + Main.YASPECT/2*x1/Math.tan(Main.FOV/2);
            x2 = Main.XASPECT/2 + Main.YASPECT/2*x2/Math.tan(Main.FOV/2);
            x3 = Main.XASPECT/2 + Main.YASPECT/2*x3/Math.tan(Main.FOV/2);
            y1 = Main.YASPECT/2 - Main.YASPECT/2*y1/Math.tan(Main.FOV/2);
            y2 = Main.YASPECT/2 - Main.YASPECT/2*y2/Math.tan(Main.FOV/2);
            y3 = Main.YASPECT/2 - Main.YASPECT/2*y3/Math.tan(Main.FOV/2);
            Point newP1 = new Point(x1, y1, z1);
            Point newP2 = new Point(x2, y2, z2);
            Point newP3 = new Point(x3, y3, z3);
            
            for(int i = (int)Func.lowBounded(Point.lowestX(newP1, newP2, newP3), 0); i < (int)Func.highBounded(Point.highestX(newP1, newP2, newP3)+1, canvas.getWidth()); i++) {
                for(int j = (int)Func.lowBounded(Point.lowestY(newP1, newP2, newP3), 0); j < (int)Func.highBounded(Point.highestY(newP1, newP2, newP3)+1, canvas.getWidth()); j++) {
                    if(Point.inside(i, j, newP1, newP2, newP3)) {
                        drawPoint(color, i, j, Point.onTriangle(newP1, newP2, newP3, i, j).getZ());
                    }
                }
            }
        }
    }
    public void drawTriangle(Triangle t) {
        
        int color = t.getColor();
        Point p1 = t.getPoint(0);
        Point p2 = t.getPoint(1);
        Point p3 = t.getPoint(2);
        double z1 = p1.getZ();
        double z2 = p2.getZ();
        double z3 = p3.getZ();
        if(z1 < Main.hither || z2 < Main.hither || z3 < Main.hither) {
            //Do nothing
        } else {
            double x1 = p1.getX()/p1.getZ();
            double x2 = p2.getX()/p2.getZ();
            double x3 = p3.getX()/p3.getZ();
            double y1 = p1.getY()/p1.getZ();
            double y2 = p2.getY()/p2.getZ();
            double y3 = p3.getY()/p3.getZ();
            x1 = Main.XASPECT/2 + Main.YASPECT/2*x1/Math.tan(Main.FOV/2);
            x2 = Main.XASPECT/2 + Main.YASPECT/2*x2/Math.tan(Main.FOV/2);
            x3 = Main.XASPECT/2 + Main.YASPECT/2*x3/Math.tan(Main.FOV/2);
            y1 = Main.YASPECT/2 - Main.YASPECT/2*y1/Math.tan(Main.FOV/2);
            y2 = Main.YASPECT/2 - Main.YASPECT/2*y2/Math.tan(Main.FOV/2);
            y3 = Main.YASPECT/2 - Main.YASPECT/2*y3/Math.tan(Main.FOV/2);
            Point newP1 = new Point(x1, y1, z1);
            Point newP2 = new Point(x2, y2, z2);
            Point newP3 = new Point(x3, y3, z3);
            for(int i = (int)Func.lowBounded(Point.lowestX(newP1, newP2, newP3), 0); i < (int)Func.highBounded(Point.highestX(newP1, newP2, newP3)+1, canvas.getWidth()); i++) {
                for(int j = (int)Func.lowBounded(Point.lowestY(newP1, newP2, newP3), 0); j < (int)Func.highBounded(Point.highestY(newP1, newP2, newP3)+1, canvas.getWidth()); j++) {
                    if(Point.inside(i, j, newP1, newP2, newP3)) {
                        drawPoint(color, i, j, Point.onTriangle(newP1, newP2, newP3, i, j).getZ());
                    }
                }
            }
        }
    }
    public void drawTriangle(int color, Triangle t) {
        
        Point p1 = t.getPoint(0);
        Point p2 = t.getPoint(1);
        Point p3 = t.getPoint(2);
        double z1 = p1.getZ();
        double z2 = p2.getZ();
        double z3 = p3.getZ();
        if(z1 < Main.hither || z2 < Main.hither || z3 < Main.hither) {
            //Do nothing
        } else {
            double x1 = p1.getX()/p1.getZ();
            double x2 = p2.getX()/p2.getZ();
            double x3 = p3.getX()/p3.getZ();
            double y1 = p1.getY()/p1.getZ();
            double y2 = p2.getY()/p2.getZ();
            double y3 = p3.getY()/p3.getZ();
            x1 = Main.XASPECT/2 + Main.YASPECT/2*x1/Math.tan(Main.FOV/2);
            x2 = Main.XASPECT/2 + Main.YASPECT/2*x2/Math.tan(Main.FOV/2);
            x3 = Main.XASPECT/2 + Main.YASPECT/2*x3/Math.tan(Main.FOV/2);
            y1 = Main.YASPECT/2 - Main.YASPECT/2*y1/Math.tan(Main.FOV/2);
            y2 = Main.YASPECT/2 - Main.YASPECT/2*y2/Math.tan(Main.FOV/2);
            y3 = Main.YASPECT/2 - Main.YASPECT/2*y3/Math.tan(Main.FOV/2);
            Point newP1 = new Point(x1, y1, z1);
            Point newP2 = new Point(x2, y2, z2);
            Point newP3 = new Point(x3, y3, z3);
            
            for(int i = (int)Func.lowBounded(Point.lowestX(newP1, newP2, newP3), 0); i < (int)Func.highBounded(Point.highestX(newP1, newP2, newP3)+1, canvas.getWidth()); i++) {
                for(int j = (int)Func.lowBounded(Point.lowestY(newP1, newP2, newP3), 0); j < (int)Func.highBounded(Point.highestY(newP1, newP2, newP3)+1, canvas.getWidth()); j++) {
                    if(Point.inside(i, j, newP1, newP2, newP3)) {
                        drawPoint(color, i, j, Point.onTriangle(newP1, newP2, newP3, i, j).getZ());
                    }
                }
            }
        }
    }
    
    
}