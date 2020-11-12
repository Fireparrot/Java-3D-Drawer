package drawer;

public class Quaternion {
    
    private double w;
    private double x;
    private double y;
    private double z;
    
    public Quaternion(double w, double x, double y, double z) {
        this.w = w;
        this.x = x;
        this.y = y;
        this.z = z;
    }
    public Quaternion(Point p) {
        this.w = 0;
        this.x = p.getX();
        this.y = p.getY();
        this.z = p.getZ();
    }
    
    public Quaternion add(Quaternion q) {
        return new Quaternion(this.w+q.getW(), this.x+q.getX(), this.y+q.getY(), this.z+q.getZ());
    }
    public Quaternion subtract(Quaternion q) {
        return new Quaternion(this.w-q.getW(), this.x-q.getX(), this.y-q.getY(), this.z-q.getZ());
    }
    public Quaternion multiply(Quaternion q) {
        Quaternion qq = new Quaternion(
                this.w*q.getW() - this.x*q.getX() - this.y*q.getY() - this.z*q.getZ(),
                this.w*q.getX() + this.x*q.getW() + this.y*q.getZ() - this.z*q.getY(),
                this.w*q.getY() - this.x*q.getZ() + this.y*q.getW() + this.z*q.getX(),
                this.w*q.getZ() + this.x*q.getY() - this.y*q.getX() + this.z*q.getW());
        return qq;
    }
    public Quaternion multiply(double d) {return new Quaternion(w*d, x*d, y*d, z*d);}
    public Quaternion divide(double d) {return new Quaternion(w/d, x/d, y/d, z/d);}
    public double norm() {return Math.sqrt(w*w + x*x + y*y + z*z);}
    public double norms() {return w*w + x*x + y*y + z*z;}
    public void normalize() {
        w /= norm();
        x /= norm();
        y /= norm();
        z /= norm();
    }
    public Quaternion conjugate() {return new Quaternion(w, -x, -y, -z);}
    public Quaternion reciprocal() {return conjugate().divide(norms());}
    
    public double getW() {return w;}
    public double getX() {return x;}
    public double getY() {return y;}
    public double getZ() {return z;}
    
    public void setW(double w) {this.w = w;}
    public void setX(double x) {this.x = x;}
    public void setY(double y) {this.y = y;}
    public void setZ(double z) {this.z = z;}
    
    public double addW(double w) {this.w += w; return this.w;}
    public double addX(double x) {this.x += x; return this.x;}
    public double addY(double y) {this.y += y; return this.y;}
    public double addZ(double z) {this.z += z; return this.z;}
    
    @Override
    public String toString() {return "Q(" + w + ", " + x + ", " + y + ", " + z + ")";}
    /*
    public static void main(String[] args) {
        
        Quaternion q1 = new Quaternion(0,1,0,-1);
        Quaternion q2 = new Quaternion(0,-1,1,0);
        Quaternion q3 = new Quaternion(0,0,-1,1);
        Quaternion q4 = new Quaternion(0,1,1,1);
        
        System.out.println(q1);
        System.out.println(q2);
        System.out.println(q3);
        
        System.out.println(q1.multiply(q4));
        System.out.println(q2.multiply(q4));
        System.out.println(q3.multiply(q4));
        System.out.println(q1.add(q2).add(q3).multiply(q4));
        
        
    }
    */
    
}