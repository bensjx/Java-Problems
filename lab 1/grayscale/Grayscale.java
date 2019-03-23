/*
 * Name       :  
 * Matric No. : 
 * Plab Acct. :
 */

import java.util.*;

public class Grayscale {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int H = sc.nextInt();
        int W = sc.nextInt();
        //Scanning input file into 6 different blocks
        int[][] red1 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                red1[row][col] = sc.nextInt();
            }
        }
        int[][] green1 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                green1[row][col] = sc.nextInt();
            }
        }
        int[][] blue1 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                blue1[row][col] = sc.nextInt();
            }
        }
        int[][] red2 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                red2[row][col] = sc.nextInt();
            }
        }
        int[][] green2 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                green2[row][col] = sc.nextInt();
            }
        }
        int[][] blue2 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                blue2[row][col] = sc.nextInt();
            }
        }
        //Skeleton gray 1 and gray 2
        int[][] gray1 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                gray1[row][col] = 0; //initialize
            }
        }
        int[][] gray2 = new int[H][W];
        for (int row = 0; row<H; row++){
            for (int col = 0; col<W; col++){
                gray2[row][col] = 0; //initialize
            }
        }
        //Coverting RGB to grayscale
        ToGray(gray1,red1,green1,blue1);
        ToGray(gray2,red2,green2,blue2);
        System.out.println(Transpose(gray1,gray2));
    }
    /*Pre-condition: 4 Arrays of blocks -- red,green,blue,gray(each are arrays
     * with pixel values. Gray has a starting value of 0)
     *Post-condition: An array of 1 block -- grayscale(with average of all RGB
     pixels)
     */
    private static int[][] ToGray(int[][] gray,int[][] r,int[][] g,int[][] b){
        int Height = gray.length; //max limit of block(row)
        int Width = gray[0].length; //max limit of block(column)
        /*By obtaining 4 numbers -> row start(rs), row end(re), column
         * start(cs),
         * column end(ce), we can set up a "perimeter" in which all values
         * in the perimeter is being use to calculate the average value
         * for grayscale pixel
         */
        for (int row = 0; row<Height; row++){
            for (int col = 0; col<Width; col++){
                //Since it is a 3 by 3 perimeter, we -1 from the origin
                int rs = row-1;
                if (rs < 0){
                    rs = 0;
                }
                // Since it is a 3 by 3 perimeter, we +1 from the origin
                // If index exceed the block, we set it to the end of
                // the block
                int re = row+1;
                if (re >= Height){
                    re = Height-1;
                }
                int cs = col-1;
                if (cs < 0){
                    cs = 0;
                }
                int ce = col+1;
                if (ce >= Width){
                    ce = Width-1;
                }
                /*For every pixel in gray, we calculate the average of
                 * RGB values. We obtain the sum of pixel values and
                 * divide it by 3(RGB) and the perimeter in which the
                 * values are taken from.
                 *Type casting is necessary to round down the number to
                 * an integer
                 */
                gray[row][col] = (int) ((SumOfBlock(r,rs,re,cs,ce) +
                            SumOfBlock(g,rs,re,cs,ce) +
                            SumOfBlock(b,rs,re,cs,ce))/(3*(re-rs+1)*(ce-cs+1)));
            }
        }
        return gray;
    }
    /*Pre-condition: An array of block (R/G/B) and the 4 perimeters (rs,re,cs,ce)
     *Post-condition: An integer that specify the total sum of pixels
     *that are being used to calculate the average
     */
    private static int SumOfBlock(int[][] block, int rowstart, int
            rowend, int colstart, int colend){
        int results = 0;
        for (int row = rowstart; row < rowend+1; row++){
            for (int col = colstart; col< colend+1; col++){
                results += block[row][col];
            }
        }
        return results;
    }
    /*Pre-condition: 2 arrays(gray 1 and gray 2)
     *Post-condition: An integer with the sum of absolute difference in
     *pixels of the 2 arrays
     */
    private static int AbsoluteDiff(int[][] array1, int[][] array2){
        int results = 0;
        for (int row = 0; row < array1.length; row++){
            for (int col = 0; col < array1[0].length ; col++){
                results += 
                    Math.abs(array1[row][col]-array2[row][col]);
            }
        }
        return results; 
    }
    /*Pre-condition: 2 arrays(gray 1 and gray 2)
     *Post-condition: An integer with the minimum sum of absolute
     * difference that is calculated with the correct transposed array
     */
    private static int Transpose(int[][] array1, int[][] array2){
        //maximum possible minimum absolute difference of 2 arrays
        int minsumdiff = 255*array1.length*array1[0].length;
        for (int i = -255; i < 256; i++){
            //temp array to house pixel values from array 1 + transposed
            //value
            int[][] temp = new int[array1.length][array1[0].length];
            for (int row = 0; row < array1.length; row++){
                for (int col = 0; col < array1[0].length; col++){
                    temp[row][col] = array1[row][col] + i;
                    //Ensuring that 0 < pixel value < 255
                    if (temp[row][col] > 255){
                        temp[row][col] = 255;
                    }
                    if (temp[row][col] < 0){
                        temp[row][col] = 0;
                    }
                }
            }
            //Check if this transposed value produces the minimum
            //absolute difference
            if (AbsoluteDiff(temp, array2) < minsumdiff){
                minsumdiff = AbsoluteDiff(temp,array2);
            }
        }
        //Repeat for array 2 since only 1 array can be transposed,
        //meaning either array can be transposed
        for (int i = -255; i < 256; i++){
            int[][] temp2 = new int[array2.length][array2[0].length];
            for (int row = 0; row < array2.length; row++){
                for (int col = 0; col < array2[0].length; col++){
                    temp2[row][col] = array2[row][col] + i;
                    if (temp2[row][col] > 255){
                        temp2[row][col] = 255;
                    }
                    if (temp2[row][col] < 0){
                        temp2[row][col] = 0;
                    }
                }
            }
            if (AbsoluteDiff(temp2, array1) < minsumdiff){
                minsumdiff = AbsoluteDiff(temp2,array1);
            }
        }
        return minsumdiff;
    }
}
