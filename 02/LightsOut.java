import java.util.ArrayList;
import java.util.Queue;


/*
 * The class <b>LightsOut</b> is the
 * class that implements the method to
 * computs solutions of the Lights Out game.
 * It contains the main of our application.
 *
 * @author GVJ, Hrithik, Soorya at uOttawa
 */

public class LightsOut {

    // Your variables here
    static ArrayListSolutionQueue partialSolutions; 
    //static ArrayList<Solution> solutions = new ArrayList<>();

    /**
     * The method <b>solve</b> finds all the 
     * solutions to the <b>Lights Out</b> game 
     * for an initially completely ``off'' board 
     * of size <b>widthxheight</b>, using a  
     * Breadth-First Search algorithm. 
     *
     * It returns an <b>ArrayList&lt;Solution&gt;</b> 
     * containing all the valid solutions to the 
     * problem.
     *
     * During the computation of the solution, the 
     * method prints out a message each time a new 
     * solution  is found, along with the total time 
     * it took (in milliseconds) to find that solution.
     *
     * @param width
     *  the width of the board
     * @param height
     *  the height of the board
     * @return
     *  an instance of <b>ArrayList&lt;Solution&gt;</b>
     * containing all the solutions
     */
    public static ArrayList<Solution> solve(int width, int height){

        //Your code here
        partialSolutions = new ArrayListSolutionQueue();
        ArrayList<Solution> solutions = new ArrayList<>();

        partialSolutions.enqueue(new Solution(width, height));

        while (!(partialSolutions.isEmpty()))
        {
            Solution current = partialSolutions.dequeue();

       /*     try
                {
                Thread.sleep (1000);
                }
            catch (InterruptedException m)
                {
                ;
                }
        */
            System.out.println(current);

            if (current.isReady() && current.isSuccessful())
            {
                solutions.add(partialSolutions.dequeue());
                System.out.println ("New solution found "+ System.nanoTime());
            }
            else if (!(current.isReady()))
            {
                if (current.stillPossible(true))
                
                {
                Solution current1 = new Solution (current);
                current1.setNext(true);
                System.out.println(current1);                    
                partialSolutions.enqueue(current1);
                }
                
                if (current.stillPossible(false))
                
                {

                Solution current2 = new Solution (current);
                current2.setNext(false);
                System.out.println(current2);
                partialSolutions.enqueue(current2);

                }
            }
        }
        return solutions;        
    }

    /**
     * <b>main</b> method  calls the method <b>solve</b> 
     * and then prints out the number of solutions found,
     * as well as the details of each solution.
     *
     * The <b>width</b> and <b>height</b> used by the 
     * main are passed as runtime parameters to
     * the program. If no runtime parameters are passed 
     * to the program, or if the parameters are incorrect,
     * then the default values are used.
     *
     * @param args
     *  Strings array of runtime parameters
     */
    public static void main(String[] args) {

        StudentInfo.display();
        int width, height;

        //Your code here

        if (args.length == 2)
        {
            width = Integer.parseInt(args[0]);
            height = Integer.parseInt(args[1]);
        }
        else
        {
            width = 3;
            height = 3;
        }


        ArrayList<Solution> solutions = solve (width, height);

        System.out.println ("The number of solutions is: "+solutions.size());
        for (int i = 0; i < solutions.size(); i++)
        {
            System.out.println ("Solution "+(i+1));
            System.out.println (solutions.get(i));
        }

    }
}