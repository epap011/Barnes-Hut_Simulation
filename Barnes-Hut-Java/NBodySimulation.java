import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class NBodySimulation {
    private double universeSize;
    private BurnesHutTreeNode treeRootNode;

    public NBodySimulation() {}

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
        }
    }
}

class BurnesHutTreeNode {
    private Particle particle;
    private ArrayList<BurnesHutNode> children;
    private double centerOfMass;

    public BurnesHutNode(Particle particle) {
        this.particle = particle;
        this.children = new ArrayList<BurnesHutNode>(4);
        centerOfMass  = -1;
    }
}