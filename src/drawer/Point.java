package drawer;

public class Point {
    
    private double x;
    private double y;
    private double z;
    
    public Point(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Point(Quaternion q) {
        x = q.getX();
        y = q.getY();
        z = q.getZ();
    }
    public Point(Point p) {
        x = p.getX();
        y = p.getY();
        z = p.getZ();
    }
    
    public double[] getCoordinates() {
        double[] coordinates = {x,y,z};
        return coordinates;
    }
    public double getX() {return x;}
    public double getY() {return y;}
    public double getZ() {return z;}
    public double[] getXY() {double[] d = {x,y}; return d;}
    public Point getXYPoint() {return new Point(x,y,0);}
    public Point getZoomedPoint() {return new Point(x/z, y/z, 1);}
    
    public Point setCoordinates(double x, double y) {
        this.x = x;
        this.y = y;
        this.z = 1;
        return this;
    }
    public Point setCoordinates(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
        return this;
    }
    public Point setCoordinates(double[] d) {
        switch(d.length) {
            case(0):
                x = 0;
                y = 0;
                z = 0;
                break;
            case(1):
                x = d[0];
                y = 0;
                z = 0;
                break;
            case(2):
                x = d[0];
                y = d[1];
                z = 1;
                break;
            default:
                x = d[0];
                y = d[1];
                z = d[2];
                break;
        }
        return this;
    }
    public Point setCoordinates(Point p) {
        x = p.getX();
        y = p.getY();
        z = p.getZ();
        return this;
    }
    public Point setCoordinates(Quaternion q) {
        x = q.getX();
        y = q.getY();
        z = q.getZ();
        return this;
    }
    
    public Point relative(Point p) {
        return new Point(this.x - p.getX(), this.y - p.getY(), this.z - p.getZ());
    }
    public Point relative(double x, double y, double z) {
        return new Point(this.x - x, this.y - y, this.z - z);
    }
    
    public Point divideF(double d) {
        x /= d;
        y /= d;
        z /= d;
        return this;
    }
    public Point multiplyF(double d) {
        x *= d;
        y *= d;
        z *= d;
        return this;
    }
    public Point add(Point p) {
        return new Point(this.x + p.getX(), this.y + p.getY(), this.z + p.getZ());
    }
    public Point subtract(Point p) {
        return new Point(this.x - p.getX(), this.y - p.getY(), this.z - p.getZ());
    }
    public Point multiply(double d) {
        return new Point(this.x*d, this.y*d, this.z*d);
    }
    public Point divide(double d) {
        return new Point(this.x/d, this.y/d, this.z/d);
    }
    public double crossProduct(Point p) {
        return x * (p.getY()+p.getZ()) + y * (p.getX()+p.getZ()) + z * (p.getX()+p.getY());
    }
    
    public Point rotate(Quaternion q) {
        return new Point(q.multiply(new Quaternion(this)).multiply(q.reciprocal()));
    }
    
    public Point onLine(Point p, Point p2) {
        if(p2.getX() == 0 && p2.getY() == 0) {
            if(this.z != p.getZ()) {
                return new Point(this.x + (this.x-p.getX())/(this.z-p.getZ())*(p2.getZ()-this.z), this.y + (this.y-p.getY())/(this.z-p.getZ())*(p2.getZ()-this.z), p2.getZ());
            } else {
                return this;
            }
        } else if(p2.getX() == 0 && p2.getZ() == 0) {
            if(this.z != p.getZ()) {
                return new Point(this.x + (this.x-p.getX())/(this.y-p.getY())*(p2.getY()-this.y), p2.getY(), this.z + (this.z-p.getZ())/(this.y-p.getY())*(p2.getY()-this.y));
            } else {
                return this;
            }
        } else if(p2.getY() == 0 && p2.getZ() == 0) {
            if(this.z != p.getZ()) {
                return new Point(p2.getX(), this.y + (this.y-p.getY())/(this.x-p.getX())*(p2.getX()-this.x), this.z + (this.z-p.getZ())/(this.x-p.getX())*(p2.getX()-this.x));
            } else {
                return this;
            }
        } else {
            return this;
        }
    }
    
    public double[] toScreen() {
        double[] d = {x/z, y/z};
        return d;
    }
    
    public boolean side(Point p) {
        if(p.getX() == 0) {
            return this.x <= 0;
        } else {
            return this.x/this.y >= p.getX()/p.getY();
        }
    }
    public static boolean side(double x, double y, Point p) {
        if(p.getX() == 0) {
            return x <= 0;
        } else {
            return x/y >= p.getX()/p.getY();
        }
    }
    public boolean side(Point p1, Point p2) {
        if(p1.getX()==p2.getX()) {
            return this.getX()-p2.getX() <= 0;
        } else {
            return (p2.getY()-p1.getY())/(p2.getX()-p1.getX())*(this.getX()-p2.getX())+p2.getY() >= this.getY();
        }
    }
    public static boolean side(double x, double y, Point p1, Point p2) {
        if(p1.getX()==p2.getX()) {
            return x-p2.getX() <= 0;
        } else {
            return (p2.getY()-p1.getY())/(p2.getX()-p1.getX())*(x-p2.getX())+p2.getY() >= y;
        }
    }
    public boolean inside(Point p1, Point p2, Point p3) {
        return this.side(p1, p2)==p3.side(p1, p2) && this.side(p1, p3)==p2.side(p1,p3) && this.side(p2, p3)==p1.side(p2, p3);
    }
    public static boolean inside(double x, double y, Point p1, Point p2, Point p3) {
        return side(x, y, p1, p2)==p3.side(p1, p2) && side(x, y, p1, p3)==p2.side(p1,p3) && side(x, y, p2, p3)==p1.side(p2, p3);
    }
    
    public static Point onTriangle(Point p1, Point p2, Point p3, double x, double y) {
        if(p1.sameXY(p2) && p2.sameXY(p3)) {
            Point[] ps = {p1,p2,p3};
            return new Point(x, y, lowestZ(ps));
        } else {
            if(p1.sameXY(p2)) {
                Point[] ps = {p1,p2};
                return new Point(x, y, whichHasLowestZ(ps).onLine(p3, new Point(x, 0, 0)).getZ());
            } else if(p1.sameXY(p3)) {
                Point[] ps = {p1,p3};
                return new Point(x, y, whichHasLowestZ(ps).onLine(p2, new Point(x, 0, 0)).getZ());
            } else if(p2.sameXY(p3)) {
                Point[] ps = {p2,p3};
                return new Point(x, y, whichHasLowestZ(ps).onLine(p1, new Point(x, 0, 0)).getZ());
            } else {
                return new Point(x, y, p1.getZ() + (
                        (x-p1.getX()) * ((p2.getY()-p1.getY()) * (p3.getZ()-p1.getZ())  -  (p3.getY()-p1.getY()) * (p2.getZ()-p1.getZ())) +
                        (y-p1.getY()) * ((p3.getX()-p1.getX()) * (p2.getZ()-p1.getZ())  -  (p2.getX()-p1.getX()) * (p3.getZ()-p1.getZ()))   )   /
                        ((p2.getY()-p1.getY()) * (p3.getX()-p1.getX())  -  (p3.getY()-p1.getY()) * (p2.getX()-p1.getX()))
                );
            }
        }
    }
    public Point onTriangle(Point p1, Point p2, Point p3) {
        if(p1.sameXY(p2) && p2.sameXY(p3)) {
            Point[] ps = {p1,p2,p3};
            return new Point(x, y, lowestZ(ps));
        } else {
            if(p1.sameXY(p2)) {
                return new Point(x, y, whichHasLowestZ(p1, p2).onLine(p3, new Point(0, y, 0)).getZ());
            } else if(p1.sameXY(p3)) {
                return new Point(x, y, whichHasLowestZ(p1, p3).onLine(p2, new Point(0, y, 0)).getZ());
            } else if(p2.sameXY(p3)) {
                return new Point(x, y, whichHasLowestZ(p2, p3).onLine(p1, new Point(0, y, 0)).getZ());
            } else {
                return new Point(x, y, p1.getZ() + (
                        (x-p1.getX()) * ((p2.getY()-p1.getY()) * (p3.getZ()-p1.getZ())  -  (p3.getY()-p1.getY()) * (p2.getZ()-p1.getZ())) +
                        (y-p1.getY()) * ((p3.getX()-p1.getX()) * (p2.getZ()-p1.getZ())  -  (p2.getX()-p1.getX()) * (p3.getZ()-p1.getZ()))   )   /
                        ((p2.getY()-p1.getY()) * (p3.getX()-p1.getX())  -  (p3.getY()-p1.getY()) * (p2.getX()-p1.getX()))
                );
            }
        }
    }
    
    
    public boolean sameXY(Point p) {return this.getX()==p.getX() && this.getY()==p.getY();}
    public static double lowestZ(Point[] ps) {
        double ans = 0;
        for (Point p : ps) {
            if (p.getZ() < ans) {
                ans = p.getZ();
            }
        }
        return ans;
    }
    public static double lowestZ(Point p1, Point p2) {
        return Func.lowest(p1.getZ(), p2.getZ());
    }
    public static double lowestZ(Point p1, Point p2, Point p3) {
        return Func.lowest(p1.getZ(), p2.getZ(), p3.getZ());
    }
    public static double lowestY(Point[] ps) {
        double ans = 0;
        for (Point p : ps) {
            if (p.getY() < ans) {
                ans = p.getY();
            }
        }
        return ans;
    }
    public static double lowestY(Point p1, Point p2) {
        return Func.lowest(p1.getY(), p2.getY());
    }
    public static double lowestY(Point p1, Point p2, Point p3) {
        return Func.lowest(p1.getY(), p2.getY(), p3.getY());
    }
    public static double lowestX(Point[] ps) {
        double ans = 0;
        for (Point p : ps) {
            if (p.getX() < ans) {
                ans = p.getX();
            }
        }
        return ans;
    }
    public static double lowestX(Point p1, Point p2) {
        return Func.lowest(p1.getX(), p2.getX());
    }
    public static double lowestX(Point p1, Point p2, Point p3) {
        return Func.lowest(p1.getX(), p2.getX(), p3.getX());
    }
    public static double highestZ(Point[] ps) {
        double ans = 0;
        for (Point p : ps) {
            if (p.getZ() > ans) {
                ans = p.getZ();
            }
        }
        return ans;
    }
    public static double highestZ(Point p1, Point p2) {
        return Func.highest(p1.getZ(), p2.getZ());
    }
    public static double highestZ(Point p1, Point p2, Point p3) {
        return Func.highest(p1.getZ(), p2.getZ(), p3.getZ());
    }
    public static double highestY(Point[] ps) {
        double ans = 0;
        for (Point p : ps) {
            if (p.getY() > ans) {
                ans = p.getY();
            }
        }
        return ans;
    }
    public static double highestY(Point p1, Point p2) {
        return Func.highest(p1.getY(), p2.getY());
    }
    public static double highestY(Point p1, Point p2, Point p3) {
        return Func.highest(p1.getY(), p2.getY(), p3.getY());
    }
    public static double highestX(Point[] ps) {
        double ans = 0;
        for (Point p : ps) {
            if (p.getX() > ans) {
                ans = p.getX();
            }
        }
        return ans;
    }
    public static double highestX(Point p1, Point p2) {
        return Func.highest(p1.getX(), p2.getX());
    }
    public static double highestX(Point p1, Point p2, Point p3) {
        return Func.highest(p1.getX(), p2.getX(), p3.getX());
    }
    public static Point whichHasLowestZ(Point[] ps) {
        Point ans = ps[0];
        for (Point p : ps) {
            if (p.getZ() < ans.getZ()) {
                ans = p;
            }
        }
        return ans;
    }
    public static Point whichHasLowestZ(Point p1, Point p2) {
        if(p1.getZ() < p2.getZ()) {
            return p1;
        } else {
            return p2;
        }
    }
    
    public static Point bezier(Point[] ps, double per) {
        Point[] buffer1 = new Point[ps.length];
        for(int i = 0; i < ps.length; i++) {
            buffer1[i] = new Point(ps[i]);
        }
        while(Func.trueLength(buffer1) > 1) {
            for(int i = 0; i < Func.trueLength(buffer1)-1; i++) {
                buffer1[i] = buffer1[i].multiply(1-per).add(buffer1[i+1].multiply(per));
            }
            buffer1[Func.trueLength(buffer1)-1] = null;
        }
        
        return buffer1[0];
    }
    
    
    
    @Override
    public String toString() {
        return "P(" + x + ", " + y + ", " + z + ")";
    }
    
}