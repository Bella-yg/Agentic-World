import java.util.ArrayList;

import java.util.Random;



public class Predator extends Agent {



    private int kills;



    public Predator(int row, int col) {

        super(row, col, 'P');

        kills = 0;

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



            Agent target = world.getAgentAt(nr, nc);



            if (target != null && !(target instanceof Predator)) {



                world.removeAgent(target);



                row = nr;

                col = nc;



                kills++;

                return;

            }

        }



        Agent nearest = null;

        int bestDistance = Integer.MAX_VALUE;



        for (Agent a : world.getAgents()) {



            if (a == this)

                continue;



            if (a instanceof Predator)

                continue;



            int distance =

                    Math.abs(a.getRow() - row)

                            + Math.abs(a.getCol() - col);



            if (distance < bestDistance) {



                bestDistance = distance;

                nearest = a;

            }

        }



        if (nearest != null) {



            int dr =

                    Integer.compare(nearest.getRow(), row);



            int dc =

                    Integer.compare(nearest.getCol(), col);



            int nr = row + dr;

            int nc = col;



            if (world.isValidLocation(nr, nc)

                    && !world.isWall(nr, nc)

                    && !world.hasAgent(nr, nc)) {



                row = nr;

                col = nc;

                return;

            }



            nr = row;

            nc = col + dc;



            if (world.isValidLocation(nr, nc)

                    && !world.isWall(nr, nc)

                    && !world.hasAgent(nr, nc)) {



                row = nr;

                col = nc;

                return;

            }

        }



        Random rand = world.getRandom();



        for (int i = 0; i < 4; i++) {



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



                row = nr;

                col = nc;

                return;

            }

        }

    }



    public int getKills() {

        return kills;

    }

}