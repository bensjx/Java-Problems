/*
 * Name		: 
 * Matric No.		: 
 * Plab Account	:
 */

import java.util.*;

public class Sudoku {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int[][] ary = new int[N*N][N*N];
        String quit = "NO";
        for (int i = 0; i < N*N; i++){
            for (int g = 0; g < N*N; g++){
                ary[i][g] = 0;
                ary[i][g] = sc.nextInt();
            }
        }
        for (int i = 0; i < N*N; i++){
            int[] temp = new int[N*N];
            for (int g = 0; g < N*N; g++){
                temp[g] = ary[g][i];
            }
            if (!Check(temp,N)){
                System.out.println("INVALID");
                return;
            }
        }
        for (int i = 0; i < N*N; i++){
            int[] temp = new int[N*N];
            for (int g = 0; g < N*N; g++){
                temp[g] = ary[i][g];
            }
            if (!Check(temp,N)){
                System.out.println("INVALID");
                return;
            }
        }
        for (int x = 0; x < N*N; x+=N){
            for (int y = 0; y < N*N; y+=N){
                int[] temp = new int[N*N];
                int Count = 0;
                for (int i = x; i < x+N; i++){
                    for (int g = y; g < y+N; g++){
                        temp[Count++] = ary[i][g];
                    }
                }
                if (!Check(temp,N)){
                    System.out.println("INVALID");
                    return;
                }
            }
        }
        System.out.println("VALID");
    }

    public static boolean Check(int[] line,int n){
        int[] testArray = new int[n*n];
        for (int i = 0; i < n*n; i++){
            testArray[i] = i+1;
        }
        Arrays.sort(line);
        if (Arrays.equals(testArray,line)){
            return true;
        }
        return false;
    }
}
