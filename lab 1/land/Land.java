/**
 * name	     : 
 * matric no.: 
 */

import java.util.*;

class Grid {

    // declare the member field
    private int size$;
    private int[][] grid$;
    private int trees$;
    // declare the constructor

    public Grid(int startsize, int[][] startgrid,int starttrees){
        size$ = startsize;
        grid$ = startgrid;
        trees$ = starttrees;
    }
    /**
     *	   checkNoTree   : to check whether the (size x size) square with upper-left coordinate 
     *                     (x, y) contains a tree
     *	   Pre-condition : an upper left coordinate (x,y) and integer
     *	   size
     *	   Post-condition: true if there is no tree, false if there is a
     *	   tree
     */
    public boolean checkNoTree(int x, int y, int size) {
        // implementation
        for (int i = x; i<size+x; i++){
            for (int g = y; g<size+y; g++){
                if (grid$[i][g] == 1){
                    return false;
                }
            }
        }
        return true;
    }



    /**
     *	   checkValidSize: to check whether it is possible to find a (size x size) square that contains 
     *                     no tree
     *	   Pre-condition :
     *	   Post-condition: false if there is no
     */
    public boolean checkValidSize(int size) {
        for (int row = 0; row <=size$-size; row++){
            for (int col = 0; col <=size$-size; col++){
                if(checkNoTree(row,col,size)){
                    return true;
                }
            }
        }
        return false;
    }



    /** 
     *	   solve         : use this method to find the largest size of a square with no trees
     *	   Pre-condition :
     *	   Post-condition:
     */
    public int solve() {
        int results = 0;
        if (trees$ == 0){
            return size$;
        }
        for (int i = 1; i < size$+1; i++){
            if(checkValidSize(i) == true){
                results = i;
            }
        }
        return results;
    }

}

class Land {

    public static void main(String[] args) {

        // declare the necessary variables
        int size;
        int numtrees;
        int x;
        int y;
        int[][] freshgrid;

        // create new object from class Result

        // declare a Scanner object to read input
        Scanner sc = new Scanner(System.in);

        // read input and process them accordingly
        size = sc.nextInt();
        numtrees = sc.nextInt(); 
        freshgrid = new int[size][size];
        while (sc.hasNext()){
            x = sc.nextInt();
            y = sc.nextInt();
            freshgrid[x-1][y-1] = 1;
        }
        Grid _grid = new Grid(size,freshgrid,numtrees);
        System.out.println(_grid.solve());
    }
}
