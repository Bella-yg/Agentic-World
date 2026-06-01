public class Main {



    public static void main(String[] args) {



        long seed = 42L;



        World world = new World(15, 15, seed);



        world.addAgent(new Explorer(1, 1));

        world.addAgent(new Explorer(10, 2));



        world.addAgent(new Collector(5, 5));

        world.addAgent(new Collector(8, 10));



        world.addAgent(new Predator(12, 12));



        world.spawnFood(20);



        world.runSimulation(50);

    }

}