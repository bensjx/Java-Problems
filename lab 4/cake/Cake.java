/**
 */

import java.util.*;

public class Cake {

    private int calculate(Queue<Slice> currCake){
        Queue<Slice> tempCake = new LinkedList<Slice>();
        tempCake.addAll(currCake);
        int results = 0;
        while (!tempCake.isEmpty()){
            results += tempCake.poll().getChoc();
        }
        return results;
    }

    private int solve(int tolerance, Queue<Slice> cake){
        Queue<Slice> currCake = new LinkedList<Slice>();
        int maxChoc = 0;
        int currChoc = 0;
        int currRasin = 0;
        while (!cake.isEmpty()){
            //add remaining choc if you hit the limit
            if (currRasin == tolerance){
                while(!cake.isEmpty() &&cake.peek().getType().equals("C")){
                    Slice tempSliceC = cake.poll();
                    currCake.offer(tempSliceC);
                    currChoc += tempSliceC.getChoc();
                }
                if (currChoc >= maxChoc){
                    maxChoc = currChoc;
                }
            }
            if (!cake.isEmpty()){
                Slice tempSlice = cake.poll();
                currCake.offer(tempSlice);
                currChoc += tempSlice.getChoc();
                if (tempSlice.getType().equals("R")){
                    currRasin++;
                }
            }
            //add new rasin slice, remove the first rasin slice
            if (currRasin > tolerance){
                if (currCake.peek().getType().equals("R")){
                    int tempChoc = currCake.poll().getChoc();
                    currRasin -= 1;
                    currChoc -= tempChoc;
                } else {
                    while (currCake.peek().getType().equals("C")){
                        currChoc -= currCake.poll().getChoc();
                    }
                    currChoc -= currCake.poll().getChoc(); //remove the first "R"
                    currRasin -= 1;
                }
            }
        }
        if (cake.isEmpty() && calculate(currCake) >= maxChoc){
            maxChoc = calculate(currCake);
        }
        return maxChoc;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        int blocks = sc.nextInt();
        int tolerance = sc.nextInt();
        Queue<Slice> cake = new LinkedList<Slice>();
        for (int i = 0; i < blocks; i++){
            cake.offer(new Slice(sc.next(),sc.nextInt()));
        }
        int results = solve(tolerance,cake);
        System.out.println(results);
    }

    public static void main(String[] args) {
        Cake c = new Cake();
        c.run();
    }
}
class Slice{
    private String _type;
    private int _choc;

    public Slice(String type, int choc){
        _type = type;
        _choc = choc;
    }

    public String getType(){
        return _type;
    }

    public int getChoc(){
        return _choc;
    }
}
