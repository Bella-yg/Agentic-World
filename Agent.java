public abstract class Agent {



    protected int row;

    protected int col;

    protected char symbol;



    public Agent(int row, int col, char symbol) {

        this.row = row;

        this.col = col;

        this.symbol = symbol;

    }



    public abstract void act(World world);



    public int getRow() {

        return row;

    }



    public int getCol() {

        return col;

    }



    public char getSymbol() {

        return symbol;

    }



    public void move(World world, int newRow, int newCol) {



        if (world.isValidLocation(newRow, newCol)

                && !world.isWall(newRow, newCol)

                && !world.hasAgent(newRow, newCol)) {



            row = newRow;

            col = newCol;

        }

    }

}