public class Particle {
    private double x;
    private double y;
    private double vx;
    private double vy;
    private double Fx;
    private double Fy;
    private double mass;
    private String name;

    public Double getX   (){return this.x;   }
    public Double getY   (){return this.y;   }
    public Double getVx  (){return this.vx;  }
    public Double getVy  (){return this.vy;  }
    public Double getMass(){return this.mass;}
    public String getName(){return this.name;}

    public void setX   (Double x)   {this.x    = x;   }
    public void setY   (Double y)   {this.y    = y;   }
    public void setVx  (Double vx)  {this.vx   = vx;  }
    public void setVy  (Double vy)  {this.vy   = vy;  }
    public void setMass(Double mass){this.mass = mass;}
    public void setName(String name){this.name = name;}

    public Particle(double x, double y, double vx, double vy, double mass, String name) {
        this.name = name;
        this.x    = x;
        this.y    = y;
        this.vx   = vx;
        this.vy   = vy;
        this.mass = mass;
        this.Fx   = 0;
        this.Fy   = 0;
    }

    public double distanceToParticle(Particle p) {
        double dx = this.x-p.x;
        double dy = this.y-p.y;
        double d  = Math.sqrt(dx*dx+dy*dy);
        return d;
    }

    public void applyForce(Particle b) {
        double dx  = b.x - this.x;
        double dy  = b.y - this.y;
        double dis = Math.sqrt(dx*dx + dy*dy);
        double G   = 6.67e-11;
        double F   = (G * this.mass * b.mass) / (dis*dis);
        this.Fx    = F * dx / dis;
        this.Fy    = F * dy / dis;
    }

    public void updatePositionAndVelocity(double dt) {
        this.x  += dt * this.vx;
        this.y  += dt * this.vy;
        this.vx += dt * this.Fx / this.mass;
        this.vy += dt * this.Fy / this.mass;
    }
}