import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;
import java.io.FileWriter;

public class NBodySimulation {
    private int numberOfParticles;
    private double universeSize;
    private BHTreeNode treeRootNode;
    private ArrayList<Particle> particles;

    public NBodySimulation() { }

    public void createBurnesHutTreeFromFile(String fileName) throws Exception {
        File file;
        Scanner reader;

        try {
            file   = new File(fileName);
            reader = new Scanner(file);
        } catch (Exception e) {
            throw new Exception("File not found");
        }

        this.numberOfParticles = Integer.parseInt(reader.nextLine());
        this.universeSize      = Double.parseDouble(reader.nextLine());
        this.particles         = new ArrayList<Particle>();

        while (reader.hasNextLine()) {
            String line   = reader.nextLine();
            String[] data = line.split(" ");

            if(line.equals("")) break;

            double x    = Double.parseDouble(data[0]);
            double y    = Double.parseDouble(data[1]);
            double vx   = Double.parseDouble(data[2]);
            double vy   = Double.parseDouble(data[3]);
            double mass = Double.parseDouble(data[4]);
            String name = data[5];

            Particle particle = new Particle(x, y, vx, vy, mass, name);
            particles.add(particle);
        }
    }

    public void simulate(double dt) {
        this.treeRootNode = new BHTreeNode(new Quad(0, 0, this.universeSize));
        for (Particle particle : particles) {
            treeRootNode.insertParticle(particle);
        }

        for (Particle particle : particles) {
            treeRootNode.updateNetForce(particle);
            particle.updatePositionAndVelocity(dt);
        }
    }

    public void printParticlesToFile(String fileName) throws Exception {
        try {
            FileWriter myWriter = new FileWriter(fileName);
            myWriter.write(this.numberOfParticles + "\n");
            myWriter.write(this.universeSize + "\n");
            for (Particle particle : particles) {
                myWriter.write(particle.getX() + " " + particle.getY() + " " + particle.getVx() + " " + particle.getVy() + " " + particle.getMass() + " " + particle.getName() + "\n");
            }
            myWriter.close();
        } catch (Exception e) {
            throw new Exception("File not found");
        }
    }

    public void printParticle() {
        for (Particle particle : particles) {
            System.out.println(particle.getX() + " " + particle.getY() + " " + particle.getVx() + " " + particle.getVy() + " " + particle.getMass());
        }
    }
}

class BHTreeNode {
    private Particle particle;
    private Quad quad;
    private BHTreeNode NW, NE, SW, SE;

    public BHTreeNode(Quad quad) {   
        this.NW = null; this.NE = null; 
        this.SW = null; this.SE = null;
        
        this.quad = quad;
    }

    public void insertParticle(Particle particle) {

        // If this node does not contain a particle, put the new particle here. 
        if(this.particle == null) {
            this.particle = particle;
            return;
        }

        //If this node is an internal node, update it's center-of-mass and total mass. 
        //Recursively insert the new particle b in the appropriate quadrant. 
        if(!isExternalNode()) {
            // Update center-of-mass and total mass.
            this.particle.setX((this.particle.getX() * this.particle.getMass() + particle.getX() * particle.getMass()) / (this.particle.getMass() + particle.getMass()));
            this.particle.setY((this.particle.getY() * this.particle.getMass() + particle.getY() * particle.getMass()) / (this.particle.getMass() + particle.getMass()));
            this.particle.setMass(this.particle.getMass() + particle.getMass());
            findAppropriateQuadForParticle(particle);
        }

        else {
            double centerX = this.getQuad().getCenterX();
            double centerY = this.getQuad().getCenterY();
            double length  = this.getQuad().getLength();

            NW = new BHTreeNode(new Quad(centerX - length/4, centerY + length/4, length/2));
            NE = new BHTreeNode(new Quad(centerX + length/4, centerY + length/4, length/2));
            SW = new BHTreeNode(new Quad(centerX - length/4, centerY - length/4, length/2));
            SE = new BHTreeNode(new Quad(centerX + length/4, centerY - length/4, length/2));

            findAppropriateQuadForParticle(this.particle);
            findAppropriateQuadForParticle(particle);
        }
    }

    public void updateNetForce(Particle particle) {

        if(this.particle == null || this.particle == particle) return;

        if(isExternalNode()) this.particle.applyForce(particle);

        else {
            double s = this.getQuad().getLength();
            double d = this.particle.distanceToParticle(particle);
            double ratio = s/d;
            if(ratio < 0.5) {
                particle.applyForce(this.particle);
            }
            else {
                NW.updateNetForce(particle);
                NE.updateNetForce(particle);
                SW.updateNetForce(particle);
                SE.updateNetForce(particle);
            }
        }
    }

    public void findAppropriateQuadForParticle(Particle particle) {
        double x = particle.getX();
        double y = particle.getY();

        if(NW.getQuad().containsPoint(x, y)) NW.insertParticle(particle);
        else 
        if(NE.getQuad().containsPoint(x, y)) NE.insertParticle(particle);
        else 
        if(SW.getQuad().containsPoint(x, y)) SW.insertParticle(particle);
        else 
        if(SE.getQuad().containsPoint(x, y)) SE.insertParticle(particle);
    }

    private boolean isExternalNode() {
        return (NW == null && NE == null && SW == null && SE == null);
    }

    public Quad getQuad() {
        return this.quad;
    }
}

class Quad {
    private double centerX, centerY;
    private double length;

    public Quad(double xmid, double ymid, double length) {
        this.centerX = xmid;
        this.centerY = ymid;
        this.length  = length;
    }

    public boolean containsPoint(double x, double y) {
        return (x >= this.centerX - this.length/2 && 
                x <= this.centerX + this.length/2 && 
                y >= this.centerY - this.length/2 && 
                y <= this.centerY + this.length/2);   
    }

    public double getCenterX() {return this.centerX;}

    public double getCenterY() {return this.centerY;}

    public double getLength()  {return this.length;}
}