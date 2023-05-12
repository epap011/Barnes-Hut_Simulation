public class Main {
    public static void main(String[] args) {
        
        NBodySimulation nBodySimulation = new NBodySimulation();
        
        try {
            nBodySimulation.createBurnesHutTreeFromFile("test.txt");
            nBodySimulation.printBurnesHutTree();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}