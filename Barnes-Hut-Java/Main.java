public class Main {
    public static void main(String[] args) {
        BurnesHut burnesHut = new BurnesHut();
        try {
            burnesHut.createBodiesFromFile("test.txt");
            burnesHut.printBodies();
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}