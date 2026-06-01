import java.util.Random;



public class Explorer extends Agent {



    private int direction;



    public Explorer(int row, int col) {

        super(row, col, 'E');

        direction = 0;

    }



    @Override

    public void act(World world) {



        Random rand = world.getRandom();



        for (int tries = 0; tries < 4; tries++) {



            int newRow = row;

            int newCol = col;



            if (direction == 0)

                newRow--;

            else if (direction == 1)

                newRow++;

            else if (direction == 2)

                newCol--;

            else

                newCol++;



            if (world.isValidLocation(newRow, newCol)

                    && !world.isWall(newRow, newCol)

                    && !world.hasAgent(newRow, newCol)) {



                move(world, newRow, newCol);

                return;

            }



            direction = rand.nextInt(4);

        }

    }

}