import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class NBodySimulation {
    private double universeSize;
    private BurnesHutTreeNode treeRootNode;

    public NBodySimulation() {
        Particle god = new Particle(0, 0, 0, 0, 0, "God");
        this.treeRootNode = new BurnesHutTreeNode(god);
        this.universeSize = 0;
    }

    public void createBurnesHutTreeFromFile(String fileName) throws Exception {
        File file;
        Scanner reader;

        try {
            file   = new File(fileName);
            reader = new Scanner(file);
        } catch (Exception e) {
            throw new Exception("File not found");
        }

        int numberOfParticles = Integer.parseInt(reader.nextLine());
        int universeSize      = Integer.parseInt(reader.nextLine());

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
            
            if(treeRootNode == NULL) treeRootNode = new BurnesHutTreeNode(particle, universeSize/2, universeSize/2, universeSize, universeSize);
            treeRootNode.insertParticle(particle);
        }
    }
}

class BurnesHutTreeNode {
    private Particle particle;
    private BurnesHutTreeNode NW, NE, SW, SE;
    private double centerX, centerY;
    private double height, width;
    private double centerOfMass;
    private double totalMass;

    public BurnesHutNode(Particle particle, double centerX, double centerY, double height, double width) {
        this.particle = particle;
        
        this.NW = null; this.NE = null; 
        this.SW = null; this.SE = null;
        
        this.centerOfMass = 0;
        this.totalMass    = 0;
        this.height  = height;
        this.width   = width;
        this.centerX = centerX;
        this.centerY = centerY;
    }

    public void insertParticle(Particle particle) {
        
    }

    private boolean isParticleFarAway(Particle particle, BurnesHutTreeNode node) {
        
    }
}