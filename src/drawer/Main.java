package drawer;


import java.awt.Color;
import java.awt.Container;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import static java.lang.Double.NaN;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;
import javax.swing.JFrame;

public class Main{
    public static double pi = Math.PI;
    
    public static int XASPECT = 1000;
    public static int YASPECT = 800;
    public static double RATIO = 300;
    public static double FOV = Math.PI/3;
    public static double hither = 4;
    public static double AM = Math.PI/180;
    
    private static final JFrame jf = new JFrame();
    private static final Environment environment = new Environment();
    
    private static double userX = 0;
    private static double userY = 0;
    private static double userZ = 0;
    private static double userA = 0;
    private static double userB = 0;
    private static double userC = 0;
    
    private static final Point[] path = {new Point(0,0,-50),
        new Point(200,-100,-150),
        new Point(50,500,300),
        new Point(100,40,50),
        new Point(-50,-175,200),
        new Point(-100,-70,20),
        new Point(-100, -150, 100),
        new Point(-100, -200, 150),
        new Point(100, -100, 50),
        new Point(0,0,-50)};
      
    public static void main(String[] args) throws InterruptedException, IOException {
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setVisible(true);
        initiateCanvas(jf);
        Frame3d controller = new Frame3d();
        controller.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        controller.setVisible(true);
        //addCalcBezier();
        addRandom();
        setUserCoords(150, 0, -500);
        //update();
            
        BufferedImage firstImage = null;
        ImageOutputStream output = new FileImageOutputStream(new File("path1.gif"));
        GifSequenceWriter writer = null;
        
        
        //setUserCoords(new Point(-100, 0, 0));
            
        for(double i = 0; i < 359.8; i += 0.02) {
            Thread.sleep(8);
            setUserCoords(Point.bezier(path, (i%100)/100));
            centerOn(0, 0, 0);
            
            //update(firstImage, output, writer);
            update();
            
            
            if(i == 0) {
                firstImage = environment.getRenderer().getCanvas();
                writer = new GifSequenceWriter(output, firstImage.getType(), 1, true);
                writer.writeToSequence(firstImage);
            } else {
                BufferedImage nextImage = environment.getRenderer().getCanvas();
                writer.writeToSequence(nextImage);
            }
            
        }
        
        writer.close();
        output.close();
        
    }
    
    public static void update() throws InterruptedException {
        environment.update();
    }
    public static void update(BufferedImage bi, ImageOutputStream ios, GifSequenceWriter gsw) throws InterruptedException, IOException {
        environment.update(bi, ios, gsw);
    }
    
    public static void initiateCanvas(JFrame f) {
        f.setSize(XASPECT+16,YASPECT+30);
        Container cp = f.getContentPane();
        cp.add(environment.getRenderer());
        cp.revalidate();
    }
    
    public static void addCalcCamera() {
        
        Point p1 = new Point(0, 400, 0);
        Point p2 = new Point(0,-400, 0);
        Point p3 = new Point(0,-400, 600);
        Point p4 = new Point(0, 400, 600);
        
        Point camera = new Point(1000, 0, 0);
        
        Point lp1 = new Point(230, -285, 102);
        Point lp2 = new Point(860, 105, 264);
        
        environment.addLine(p1, p2, Func.getColor(0, 0, 255));
        environment.addLine(p2, p3, Func.getColor(0, 0, 255));
        environment.addLine(p3, p4, Func.getColor(0, 0, 255));
        environment.addLine(p4, p1, Func.getColor(0, 0, 255));
        
        environment.addLine(p1, camera, Func.getColor(255, 0, 0));
        environment.addLine(p2, camera, Func.getColor(255, 0, 0));
        environment.addLine(p3, camera, Func.getColor(255, 0, 0));
        environment.addLine(p4, camera, Func.getColor(255, 0, 0));
        
        environment.addLine(lp1, lp2, Func.getColor(0, 255, 0));
        
        Point rp1 = new Point(621, -147, 206);
        Point rp2 = new Point(563, 31, 242);
        Point rp3 = new Point(657, -111, 86);
        Point rp4 = new Point(599, 67, 122);
        
        environment.addTriangle(rp1, rp2, rp3, Func.getColor(0, 127, 127));
        environment.addTriangle(rp4, rp2, rp3, Func.getColor(0, 127, 127));
        
    }
    
    public static void addCalcBezier() {
        
        Point p11 = new Point( 0, -50, 0);
        Point p12 = new Point(35, 250, 0);
        Point p13 = new Point(35,-250, 0);
        Point p14 = new Point(70,  50, 0);
        Point[] path1 = {p11, p12, p13, p14};
        
        Point p21 = new Point( 80,   0, 0);
        Point p22 = new Point( 60, -75, 0);
        Point p23 = new Point(100, -30, 0);
        Point[] path2 = {p21, p22, p23};
        
        Point p3a1 = new Point(120, -50, 0);
        Point p3a2 = new Point(140, 125, 0);
        Point p3a3 = new Point(185,  50, 0);
        Point p3a4 = new Point(123, -20, 0);
        Point[] path3a = {p3a1, p3a2, p3a3, p3a4};
        Point p3b1 = new Point(p3a4);
        Point p3b2 = new Point(190,  30, 0);
        Point p3b3 = new Point(190, -30, 0);
        Point p3b4 = new Point(p3b1);
        Point[] path3b = {p3b1, p3b2, p3b3, p3b4};
        Point p3c1 = new Point(p3b4);
        Point p3c2 = new Point(160, -65, 0);
        Point p3c3 = new Point(170, -30, 0);
        Point[] path3c = {p3c1, p3c2, p3c3};
        
        Point p41 = new Point(200,   0, 0);
        Point p42 = new Point(180, -75, 0);
        Point p43 = new Point(220, -30, 0);
        Point[] path4 = {p41, p42, p43};
        Point p5a1 = new Point(260,   50, 0);
        Point p5a2 = new Point(220, -100, 0);
        Point p5a3 = new Point(270,  -20, 0);
        Point[] path5a = {p5a1, p5a2, p5a3};
        Point p5b1 = new Point(230, 15, 0);
        Point p5b2 = new Point(270, 15, 0);
        Point[] path5b = {p5b1, p5b2};
        
        Point p6a1 = new Point(328, -10, 0);
        Point p6a2 = new Point(305,  30, 0);
        Point p6a3 = new Point(255, -30, 0);
        Point p6a4 = new Point(310,-100, 0);
        Point p6a5 = new Point(330,   0, 0);
        Point[] path6a = {p6a1, p6a2, p6a3, p6a4, p6a5};
        Point p6b1 = new Point(p6a5);
        Point p6b2 = new Point(310, -75, 0);
        Point p6b3 = new Point(350, -30, 0);
        Point[] path6b = {p6b1, p6b2, p6b3};
        
        int color = Func.getColor(255,255,0);
        
        environment.addBezier(path1, color);
        
        environment.addBezier(path2, color);
        
        environment.addBezier(path3a, color);
        environment.addBezier(path3b, color);
        environment.addBezier(path3c, color);
        
        environment.addBezier(path4, color);
        
        environment.addBezier(path5a, color);
        environment.addBezier(path5b, color);
        
        environment.addBezier(path6a, color);
        environment.addBezier(path6b, color);
        
    }
    
    public static void addTetra() {
        environment.clearLines();
        
        Point tp1 = new Point(-20,  0, -10);
        Point tp2 = new Point( 20,  0, -10);
        Point tp3 = new Point(  0,  0,  20);
        Point tp4 = new Point(  0, 30,   0);
        environment.addTriangle(tp1, tp2, tp3, Func.getColor(127, 127, 127));
        environment.addTriangle(tp1, tp2, tp4, Func.getColor(  0, 127,   0));
        environment.addTriangle(tp1, tp4, tp3, Func.getColor(127,   0,   0));
        environment.addTriangle(tp4, tp2, tp3, Func.getColor(  0,   0, 127));
    }
    
    public static void addRandom() {
        Point p1 = new Point(-10, -10, 50);
        Point p2 = new Point(10, -10, 50);
        Point p3 = new Point(-10, -10, 70);
        Point p4 = new Point(10, -10, 70);
        Point p5 = new Point(0, 7, 60);
        environment.addTriangle(p1, p5, p2, Func.getColor(200, 200, 250));
        environment.addTriangle(p1, p5, p3, Func.getColor(160, 160, 200));
        environment.addTriangle(p2, p5, p4, Func.getColor(160, 160, 200));
        environment.addTriangle(p3, p5, p4, Func.getColor(120, 120, 150));
        environment.addTriangle(p1, p2, p3, Func.getColor(80, 80, 100));
        environment.addTriangle(p4, p2, p3, Func.getColor(80, 80, 100));
        
        Point[] ps = new Point[240];
        double size = 8;
        for(int i = 0; i < ps.length; i++) {
            if(i%2 == 0) {
                ps[i] = new Point(-3*Math.sin((double)i/ps.length*2*Math.PI)*size, 20, 60-3*Math.cos((double)i/ps.length*2*Math.PI)*size);
            } else {
                ps[i] = new Point(-Math.sin((double)i/ps.length*2*Math.PI)*size, 40, 60-Math.cos((double)i/ps.length*2*Math.PI)*size);
            }
        }
        for(int i = 0; i < ps.length; i++) {
            environment.addTriangle(ps[i%ps.length], ps[(i+1)%ps.length], ps[(i+2)%ps.length], Func.getColor(0, 30+(int)(120*Math.pow((double)(ps.length/2-i)/(ps.length/2), 2)), 0));
        }
        
        /*
        for(double i = 0; i < 360; i += 1) {
            environment.addLine(
                    new Point(20*Math.cos(userA),
                            20*Math.sin(userA)*Math.cos(i/180*Math.PI),
                            60+20*Math.sin(i/180*Math.PI)),
                    new Point(0,
                            30*Math.cos((i+1)/180*Math.PI),
                            60+30*Math.sin((i+1)/180*Math.PI)));
        }
        */
        
        double radius = 30;
        for(double i = 0; i < 180; i += 22.5) {
            for(double k = 0; k < 360; k += 3.6) {
                environment.addLine(
                        new Point(radius*Math.sin(i/180*Math.PI)*Math.sin(k/180*Math.PI),
                        radius*Math.cos(k/180*Math.PI),
                        60+radius*Math.cos(i/180*Math.PI)*Math.sin(k/180*Math.PI)),
                        new Point(radius*Math.sin(i/180*Math.PI)*Math.sin((k+1)/180*Math.PI),
                        radius*Math.cos((k+1)/180*Math.PI),
                        60+radius*Math.cos(i/180*Math.PI)*Math.sin((k+1)/180*Math.PI)));
            }
        }
        
        for(double i = 22.5; i < 180; i += 22.5) {
            for(double k = 0; k < 360; k += 3.6) {
                environment.addLine(
                        new Point(radius*Math.sin(i/180*Math.PI)*Math.cos(k/180*Math.PI),
                        radius*Math.cos(i/180*Math.PI),
                        60+radius*Math.sin(i/180*Math.PI)*Math.sin(k/180*Math.PI)),
                        new Point(radius*Math.sin(i/180*Math.PI)*Math.cos((k+1)/180*Math.PI),
                        radius*Math.cos(i/180*Math.PI),
                        60+radius*Math.sin(i/180*Math.PI)*Math.sin((k+1)/180*Math.PI)));
            }
        }
        
        for(double i = 0; i < 360; i += 1) {
            environment.addLine(Point.bezier(path, i/360), Point.bezier(path, (i+1)/360), Color.RED);
        }
        
        Point[] path2 = {
            new Point(-20,0,0),
            new Point(0,20,0),
            new Point(0,-20,0),
            new Point(20,-40,0),
            new Point(20,40,0),
            new Point(40,20,0),
            new Point(40,-20,0),
            new Point(60,0,0)
        };
        
        /*
        for(double i = 0; i < 360; i += 1) {
            environment.addLine(Point.bezier(path2, i/360), Point.bezier(path2, (i+1)/360), Color.GREEN);
        }
        */
    }
    public static void addObjects2() {
        Point[][][] ps = new Point[8][8][8];
        for(int i = 0; i < 8; i++) {
            for(int j = 0; j < 8; j++) {
                for(int k = 0; k < 8; k++) {
                    ps[i][j][k] = new Point(10*i, 10*j, 10*k);
                }
            }
        }
        
        int topColor = 200;
        int bottomColor = 100;
        int frontColor = 160;
        int backColor = 140;
        int leftColor = 120;
        int rightColor = 180;
        
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 7; j++) {
                for(int k = 0; k < 7; k++) {
                    if(!Func.holeCube(i,j,k)) {
                        double m = (double)(2+Func.bigger(Math.abs(3-i), Math.abs(3-j), Math.abs(3-k)))/5;
                        environment.addTetra(ps[i+0][j+1][k+0], ps[i+1][j+1][k+0], ps[i+0][j+1][k+1], ps[i+1][j+1][k+1], Func.getColor((int)(m*topColor), (int)(m*topColor), (int)(m*topColor)));
                        environment.addTetra(ps[i+0][j+0][k+0], ps[i+1][j+0][k+0], ps[i+0][j+0][k+1], ps[i+1][j+0][k+1], Func.getColor((int)(m*bottomColor), (int)(m*bottomColor), (int)(m*bottomColor)));
                        environment.addTetra(ps[i+0][j+0][k+0], ps[i+1][j+0][k+0], ps[i+0][j+1][k+0], ps[i+1][j+1][k+0], Func.getColor((int)(m*frontColor), (int)(m*frontColor), (int)(m*frontColor)));
                        environment.addTetra(ps[i+0][j+0][k+1], ps[i+1][j+0][k+1], ps[i+0][j+1][k+1], ps[i+1][j+1][k+1], Func.getColor((int)(m*backColor), (int)(m*backColor), (int)(m*backColor)));
                        environment.addTetra(ps[i+0][j+0][k+0], ps[i+0][j+1][k+0], ps[i+0][j+0][k+1], ps[i+0][j+1][k+1], Func.getColor((int)(m*leftColor), (int)(m*leftColor), (int)(m*leftColor)));
                        environment.addTetra(ps[i+1][j+0][k+0], ps[i+1][j+1][k+0], ps[i+1][j+0][k+1], ps[i+1][j+1][k+1], Func.getColor((int)(m*rightColor), (int)(m*rightColor), (int)(m*rightColor)));
                    }
                }
            }
        }
        
    }
    public static void readdObjects(double n) {
        environment.clearLines();
        
        n *= 5;
        
        Point p1 = new Point(0,0,0);
        Point p2 = p1.relative(5*Func.cosd(n, 30, 45), 5*Func.sind(n, 20, 50)*Func.cosd(n, 10, 30), -5*Func.sind(n, 20, 50)*Func.sind(n, 10, 30));
        Point p3 = p1.relative(-5*Func.cosd(n, 30, 45), 5*Func.sind(n, 20, 50)*Func.cosd(n, 10, 30), -5*Func.sind(n, 20, 50)*Func.sind(n, 10, 30));
        Point p4 = p2.relative(5*Func.sind(n, 0, 5), 5*Func.cosd(n, 0, 5)*Func.cosd(n, 0, 10), 5*Func.cosd(n, 0, 5)*Func.sind(n, 0, 10));
        Point p5 = p3.relative(-5*Func.sind(n, 0, 5), 5*Func.cosd(n, 0, 5)*Func.cosd(n, 0, 10), 5*Func.cosd(n, 0, 5)*Func.sind(n, 0, 10));
        
        Point p6 = p1.relative(0,-20,0);
        Point p7 = p6.relative(5*Func.sind(n, 40, 70), 5*Func.cosd(n, 40, 70)*Func.cosd(n, 20, 50), 5*Func.cosd(n, 40, 70)*Func.sind(n, 20, 50));
        Point p8 = p6.relative(-5*Func.sind(n, 40, 70), 5*Func.cosd(n, 40, 70)*Func.cosd(n, 20, 50), 5*Func.cosd(n, 40, 70)*Func.sind(n, 20, 50));
        Point p9 = p7.relative(-5*Func.sind(n, 10, 60), -5*Func.cosd(n, 10, 60), 0);
        Point p10 = p8.relative(5*Func.sind(n, 10, 60), -5*Func.cosd(n, 10, 60), 0);
        Point p11 = p6.relative(0,-2,0);
        
        double z = p4.getY()+10;
        Point[] ps = {p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11};
        for(int i = 0; i < ps.length; i++) {
            ps[i].setCoordinates(ps[i].relative(0, z, 0));
        }
        
        environment.addLine(p1, p2, Color.WHITE);
        environment.addLine(p1, p3, Color.WHITE);
        environment.addLine(p2, p4, Color.WHITE);
        environment.addLine(p3, p5, Color.WHITE);
        environment.addLine(p1, p6, Color.WHITE);
        environment.addLine(p6, p7, Color.WHITE);
        environment.addLine(p6, p8, Color.WHITE);
        environment.addLine(p7, p9, Color.WHITE);
        environment.addLine(p8, p10, Color.WHITE);
        environment.addLine(p6, p11, Color.WHITE);
        
        double headSize = 5;
        for(int i = 0; i < 360; i += 2) {
            environment.addLine(p11.relative(headSize*Math.sin(i*AM),headSize*Math.cos(i*AM)-headSize,0), p11.relative(headSize*Math.sin((i+2)*AM),headSize*Math.cos((i+2)*AM)-headSize,0), Color.WHITE);
        }
        
        
    }
    
    
    
    
    
    public static double getUserX() {
        return userX;
    }
    public static void setUserX(double userX) {
        Main.userX = userX;
    }
    public static void addUserX(double userX) {
        Main.userX += userX;
    }

    public static double getUserY() {
        return userY;
    }
    public static void setUserY(double userY) {
        Main.userY = userY;
    }
    public static void addUserY(double userY) {
        Main.userY += userY;
    }

    public static double getUserZ() {
        return userZ;
    }
    public static void setUserZ(double userZ) {
        Main.userZ = userZ;
    }
    public static void addUserZ(double userZ) {
        Main.userZ += userZ;
    }
    
    public static void setUserCoords(double x, double y, double z) {
        Main.userX = x;
        Main.userY = y;
        Main.userZ = z;
    }
    public static void setUserCoords(Point p) {
        Main.userX = p.getX();
        Main.userY = p.getY();
        Main.userZ = p.getZ();
    }
    
    public static double getUserA() {
        return userA;
    }
    public static void setUserA(double userA) {
        Main.userA = userA;
    }
    public static void addUserA(double userA) {
        Main.userA += userA;
    }
    
    public static double getUserB() {
        return userB;
    }
    public static void setUserB(double userB) {
        if(userB > -pi/2 && userB < pi/2) {Main.userB = userB;}
    }
    public static void addUserB(double userB) {
        if(Main.userB+userB > -pi/2 && Main.userB+userB < pi/2) {Main.userB += userB;}
    }
    
    public static double getUserC() {
        return userC;
    }
    public static void setUserC(double userC) {
        Main.userC = userC;
    }
    public static void addUserC(double userC) {
        Main.userC += userC;
    }
    
    public static void centerOn(double x, double y, double z) {
        if(z-userZ > 0) {
            userA = -Math.atan((x-userX)/(z-userZ));
        } else if(z-userZ == 0) {
            userA = -pi/2*Math.signum(x-userX);
        } else {
            userA = -Math.atan((x-userX)/(z-userZ)) - pi*Math.signum((x-userX));
        }
        userB = Math.atan((y-userY)/Math.sqrt((x-userX)*(x-userX) + (z-userZ)*(z-userZ)));
        if(userB == NaN) {userB = -pi/2*Math.signum(y-userY);}
        //userC = userB;
    }
    
}