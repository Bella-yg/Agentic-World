import java.util.ArrayList;

import java.util.Random;



public class World {



    private char[][] grid;

    private ArrayList<Agent> agents;



    private int rows;

    private int cols;



    private Random rand;



    public World(int rows, int cols, long seed) {



        this.rows = rows;

        this.cols = cols;



        grid = new char[rows][cols];

        agents = new ArrayList<>();



        rand = new Random(seed);



        fillEmpty();

        createWalls();

    }



    private void fillEmpty() {



        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                grid[r][c] = '.';

            }

        }

    }



    private void createWalls() {



        for (int r = 2; r < 13; r++) {

            grid[r][7] = '#';

        }



        for (int c = 3; c < 11; c++) {

            grid[6][c] = '#';

        }

    }



    public void addAgent(Agent a) {

        agents.add(a);

    }



    public ArrayList<Agent> getAgents() {

        return new ArrayList<>(agents);

    }



    public Random getRandom() {

        return rand;

    }



    public boolean isValidLocation(int r, int c) {

        return r >= 0 && r < rows && c >= 0 && c < cols;

    }



    public boolean isWall(int r, int c) {

        return grid[r][c] == '#';

    }



    public boolean hasFood(int r, int c) {



        if (!isValidLocation(r, c))

            return false;



        return grid[r][c] == 'F';

    }



    public boolean hasAgent(int r, int c) {

        return getAgentAt(r, c) != null;

    }



    public Agent getAgentAt(int r, int c) {



        for (Agent a : agents) {



            if (a.getRow() == r

                    && a.getCol() == c) {



                return a;

            }

        }



        return null;

    }



    public void removeAgent(Agent a) {

        agents.remove(a);

    }



    public void removeFood(int r, int c) {

        grid[r][c] = '.';

    }



    public void spawnFood(int amount) {



        int count = 0;



        while (count < amount) {



            int r = rand.nextInt(rows);

            int c = rand.nextInt(cols);



            if (grid[r][c] == '.'

                    && !hasAgent(r, c)) {



                grid[r][c] = 'F';

                count++;

            }

        }

    }



    public void runSimulation(int turns) {



        for (int turn = 1; turn <= turns; turn++) {



            System.out.println("\nTURN " + turn);



            ArrayList<Agent> snapshot =

                    new ArrayList<>(agents);



            for (Agent a : snapshot) {



                if (agents.contains(a)) {

                    a.act(this);

                }

            }



            displayWorld();

        }

    }



    public void displayWorld() {



        char[][] display =

                new char[rows][cols];



        for (int r = 0; r < rows; r++) {

            for (int c = 0; c < cols; c++) {

                display[r][c] = grid[r][c];

            }

        }



        for (Agent a : agents) {
            display[a.getRow()][a.getCol()] = a.getSymbol();
        }
        for (int r = 0; r < rows; r++){
            for (int c = 0; c < cols; c++){
            System.out.print(display[r][c] + " ");
            }
        }
        System.out.println();
    }
}