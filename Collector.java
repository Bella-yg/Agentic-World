import java.util.Random;



public class Collector extends Agent {



    private int foodCollected;



    public Collector(int row, int col) {

        super(row, col, 'C');

        foodCollected = 0;

    }



    @Override

    public void act(World world) {



        int[][] directions = {

                {-1,0},

                {1,0},

                {0,-1},

                {0,1}

        };



        for (int[] d : directions) {



            int nr = row + d[0];

            int nc = col + d[1];



            if (world.hasFood(nr, nc)) {



                move(world, nr, nc);

                world.removeFood(nr, nc);

                foodCollected++;



                return;

            }

        }



        Random rand = world.getRandom();



        for (int tries = 0; tries < 4; tries++) {



            int dir = rand.nextInt(4);



            int nr = row;

            int nc = col;



            if (dir == 0)

                nr--;

            else if (dir == 1)

                nr++;

            else if (dir == 2)

                nc--;

            else

                nc++;



            if (world.isValidLocation(nr, nc)

                    && !world.isWall(nr, nc)

                    && !world.hasAgent(nr, nc)) {



                move(world, nr, nc);

                return;

            }

        }

    }



    public int getFoodCollected() {

        return foodCollected;

    }

}