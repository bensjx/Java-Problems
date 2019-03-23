/**
 * Name 		:
 * Matric No. 	:
 * PLab Acct. 	:
 */

import java.util.*;

public class ColouredBalls {

    private int find(Queue<Ball> currBall,int col){
        Iterator<Ball> ite = currBall.iterator();
        int count = 0;
        while (ite.hasNext()){
            if (ite.next().getColour() == col){
                count++;
            }
        }
        return count;
    }

    private int solver(Queue<Ball> mainBall, int reqBalls){
        Queue<Ball> currBall = new LinkedList<Ball>();
        int cost = Integer.MAX_VALUE;
        int currCost = 0;
        while (!mainBall.isEmpty()){
            if (reqBalls == 0){
                while //rm duplicated first ball to min cost
                    (find(currBall,currBall.peek().getColour())
                    > 1){
                        Ball rmBall = currBall.poll();
                        currCost -= rmBall.getCost();
                    }
                if (currCost <= cost){
                    cost = currCost;
                }
                Ball tempBall = currBall.poll();
                currCost -= tempBall.getCost();
                reqBalls += 1;
            }
            if (reqBalls >0){ //still need more balls
                Ball tempBall = mainBall.poll();
                if (find(currBall,tempBall.getColour()) == 0){
                    reqBalls -= 1;
                }
                currBall.offer(tempBall);
                currCost += tempBall.getCost();
                if (reqBalls == 0 && currCost <= cost){
                    cost = currCost;
                }
            }
            if (reqBalls <0){
                Ball rmBall = currBall.poll();
                currCost -= rmBall.getCost();
                reqBalls+= 1;
            }
        }
        return cost;
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        int numBalls = sc.nextInt();
        int reqBalls = sc.nextInt();
        Queue<Ball> mainBall = new LinkedList<Ball>();
        for (int i = 0; i < numBalls; i++){
            mainBall.offer(new Ball(sc.nextInt(),sc.nextInt()));
        }
        System.out.println(solver(mainBall,reqBalls));
    }

    public static void main(String[] args) {
        ColouredBalls colouredBalls = new ColouredBalls();
        colouredBalls.run();
    }
}

class Ball{
    private int _cost;
    private int _colour;

    public Ball(int cost, int colour){
        _cost = cost;
        _colour = colour;
    }
    
    public int getCost(){
        return _cost;
    }
    
    public int getColour(){
        return _colour;
    }
}
