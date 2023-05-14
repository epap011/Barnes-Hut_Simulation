import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class NBodySimulation {
    private int numberOfParticles;
    private double universeSize;
    private BHTreeNode treeRootNode;

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
        this.universeSize      = Integer.parseInt(reader.nextLine());
        this.treeRootNode      = new BHTreeNode(new Quad(0, 0, this.universeSize));

        while (reader.hasNextLine()) {
            String line   = reader.nextLine();
            String[] data = line.split(" ");
            
            double x    = Double.parseDouble(data[0]);
            double y    = Double.parseDouble(data[1]);
            double vx   = Double.parseDouble(data[2]);
            double vy   = Double.parseDouble(data[3]);
            double mass = Double.parseDouble(data[4]);
            String name = data[5];

            Particle particle = new Particle(x, y, vx, vy, mass, name);
            treeRootNode.insertParticle(particle);
        }
    }
}

class BHTreeNode {
    private Particle particle;
    private Quad quad;
    private BHTreeNode NW, NE, SW, SE;

    public BHTreeNode(double centerX, double centerY, double length) {   
        this.NW = null; this.NE = null; 
        this.SW = null; this.SE = null;
        
        this.centerX = centerX;
        this.centerY = centerY;
        this.length  = length;
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

        }

        else {
            
        }
    }

    private boolean isExternalNode() {
        return (NW == null && NE == null && SW == null && SE == null);
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
}