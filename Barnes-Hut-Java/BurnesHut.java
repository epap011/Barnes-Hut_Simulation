import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

public class BurnesHut {
    private ArrayList<Body> bodies;
    private double universeSize;

    public ArrayList<Body> getBodies() {return bodies;}
    public double getUniverseSize()    {return universeSize;}

    public void setBodies(ArrayList<Body> bodies)    {this.bodies = bodies;}
    public void setUniverseSize(double universeSize) {this.universeSize = universeSize;}

    public BurnesHut() {bodies = new ArrayList<Body>();}

    public void createBodiesFromFile(String fileName) throws Exception {
        File file;
        Scanner reader;

        try {
            file   = new File(fileName);
            reader = new Scanner(file);
        } catch (Exception e) {
            throw new Exception("File not found");
        }

        int numberOfBodies = Integer.parseInt(reader.nextLine());
        int universeSize   = Integer.parseInt(reader.nextLine());

        while (reader.hasNextLine()) {
            String line   = reader.nextLine();
            String[] data = line.split(" ");
            
            double x    = Double.parseDouble(data[0]);
            double y    = Double.parseDouble(data[1]);
            double vx   = Double.parseDouble(data[2]);
            double vy   = Double.parseDouble(data[3]);
            double mass = Double.parseDouble(data[4]);
            String name = data[5];

            Body body = new Body(x, y, vx, vy, mass, name);
            this.bodies.add(body);
        }
    }

    public void printBodies() {
        System.out.println("Name\tx\ty\tvx\tvy\tmass");
        for (Body body : bodies) {
            System.out.println(body.getName() + "\t" + body.getX() + "\t" + body.getY() + "\t" + body.getVx() + "\t" + body.getVy() + "\t" + body.getMass());
        }
    }
}