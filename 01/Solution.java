
/**
 * The class <b>Solution</b> is used
 * to store a (partial) solution to the game
 *
 * @ GVJ, Hrithik, Soorya all from uOttawa
 */
public class Solution {


    // Your variables here
    boolean [][] solHolder;
    int iRow = 0;
    int iCol = 0;
    boolean ready = false;
    int [][] selHolder;
    private int width;
    private int height;

    /**
     * Constructor. Creates an instance of Solution 
     * for a board of size <b>widthxheight</b>. That 
     * solution does not have any board position
     * value explicitly specified yet.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     */
    public Solution(int width, int height) {

        //Your code here
        this.width = width;
        this.height = height;
        solHolder = new boolean [height][width];
        selHolder = new int [height][width];
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                selHolder[i][j] = 0;
        }
    }

   /**
     * Constructor. Creates an instance of Solution 
     * wich is a deep copy of the instance received
     * as parameter. 
     *
     * @param other
     *  Instance of solution to deep-copy
     */
     public Solution(Solution other) {

        //Your code here
        this.width = other.width;
        this.height = other.height;
        this.iRow = other.iRow;
        this.iCol = other.iCol;


        this.solHolder = new boolean [height][width];
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                this.solHolder[i][j] = other.solHolder[i][j];
        }

        this.selHolder = new int[height][width];
        for (int i = 0; i < height; i++)
        {
            for (int j = 0; j < width; j++)
                this.selHolder[i][j] = other.selHolder[i][j];
        }

    }


    /**
     * returns <b>true</b> if and only the parameter 
     * <b>other</b> is referencing an instance of a 
     * Solution which is the ``same'' as  this 
     * instance of Solution (its board as the same
     * values and it is completed to the same degree)
     *
     * @param other
     *  referenced object to compare
     */

    public boolean equals(Object other){

        //Your code here
        if (other == null || other.getClass() != this.getClass()) {
            return false;
        }

        Solution another;
        another = (Solution) other;

        for (int i = 0; i < this.solHolder.length; i++)
        {
            for (int j = 0; j < this.solHolder[i].length; j++)
            {
                if (this.solHolder[i][j] != another.solHolder[i][j])
                    return false;
            } 
        }
        return true;
    }


    /** 
    * returns <b>true</b> if the solution 
    * has been entirely specified
    *
    * @return
    * true if the solution is fully specified
    */
    public boolean isReady(){

        //Your code here
        return ready;
    }

    /** 
    * specifies the ``next'' value of the 
    * solution. 
    * The first call to setNext specifies 
    * the value of the board location (1,1), 
    * the second call specifies the value
    *  of the board location (1,2) etc. 
    *
    * If <b>setNext</b> is called more times 
    * than there are positions on the board, 
    * an error message is printed out and the 
    * call is ignored.
    *
    * @param nextValue
    *  the boolean value of the next position
    *  of the solution
    */
    public void setNext(boolean nextValue) {

        //Your code here
        if (ready == false)
        {
            if (iRow < solHolder.length)
            {
                if (iCol < solHolder[iRow].length)
                {
                    solHolder[iRow][iCol] = nextValue;
                    if (nextValue == true)
                    {

                        selHolder[iRow][iCol]++;

                        if (iRow > 0)
                            selHolder[iRow-1][iCol]++;
                        if (iRow < solHolder.length - 1)
                            selHolder[iRow+1][iCol]++;
                        if (iCol > 0)
                            selHolder[iRow][iCol-1]++;
                        if (iCol < solHolder[iRow].length - 1)
                            selHolder[iRow][iCol+1]++; 
                    }

                    iCol++;
                    if (iCol == solHolder[iRow].length)
                    {
                        iCol = 0;
                        iRow++;
                    }
                }
            }
        }
        else
            System.out.println ("Solution is already ready.");
        
        if (iRow == solHolder.length)
        {
            ready = true;
        }
    }
    
    /**
    * returns <b>true</b> if the solution is completely 
    * specified and is indeed working, that is, if it 
    * will bring a board of the specified dimensions 
    * from being  entirely ``off'' to being  entirely 
    * ``on''.
    *
    * @return
    *  true if the solution is completely specified
    * and works
    */
    public boolean isSuccessful(){

        //Your code here
        for (int i = 0; i < selHolder.length; i++)
        {
            for (int j = 0; j < selHolder[i].length; j++)
            {
                if (selHolder[i][j] % 2 == 0)
                    return false;
            } 
        }
        return true;
    }


    /**
     * returns a string representation of the solution
     *
     * @return
     *      the string representation
     */
    public String toString() {
 
        //Your code here
        String toreturn = "[[";
        for (int i = 0; i < solHolder.length; i++)
        {
            for (int j = 0; j < solHolder[i].length; j++)
            {
                toreturn += solHolder[i][j];
               // toreturn += selHolder[i][j];
                if (j != solHolder[i].length-1)
                    toreturn += ", ";
            } 
            if (i != solHolder.length-1)
                toreturn += "],\n";
        }
        toreturn += "]]";

        return toreturn;
    }

}
