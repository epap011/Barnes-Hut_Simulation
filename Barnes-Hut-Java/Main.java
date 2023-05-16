public class Main {
    public static void main(String[] args) {
        
        NBodySimulation nBodySimulation = new NBodySimulation();

        if(args.length != 3) {
            System.out.println("Usage: java Main <number of iterations> <input file> <output file>");
            System.exit(1);
        }

        int iterations = Integer.parseInt(args[0]);
        double dt = 10;
        
        try {
            long start = System.currentTimeMillis();
            nBodySimulation.createBurnesHutTreeFromFile(args[1]);
            for(int i = 0; i < iterations; i++) {
                nBodySimulation.simulate(dt);
            }
            long finish = System.currentTimeMillis();
            System.out.println("Time: " + (finish-start) + "ms");
            nBodySimulation.printParticlesToFile(args[2]);
        } catch (Exception e) {
            System.out.println(e);
        }
    }
}