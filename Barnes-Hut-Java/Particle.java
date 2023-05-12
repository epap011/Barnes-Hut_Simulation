public class Particle {
    private double x;
    private double y;
    private double vx;
    private double vy;
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
    this.x    = x;
    this.y    = y;
    this.vx   = vx;
    this.vy   = vy;
    this.mass = mass;
    this.name = name;
    }
}