/**
 * Name			: 
 * Matric No.	: 
 */
import java.util.*;

public class Love {

    private int solve(Queue<String> letter,HashMap<String,Integer>
    original){
        int numDistinct = 0;
        int numLetter = 0;
        int minNumLetter = Integer.MAX_VALUE;
        Queue<String> temp = new LinkedList<String>();
        HashMap<String,Integer> tracker = new HashMap<String,Integer>();
        while (!letter.isEmpty()){
            String check = letter.poll();
            if (!tracker.containsKey(check)){
                tracker.put(check,1);
                numDistinct += 1;
            } else {
                tracker.put(check,tracker.get(check)+1);
            }
            numLetter += check.length();
            temp.offer(check);
            if(numDistinct == original.size()) {
                boolean adjusted = false;
                while (adjusted == false){
                    String word = temp.peek();
                    if (tracker.get(word)>1){
                        temp.poll();
                        tracker.put(word,tracker.get(word)-1);
                        numLetter -= word.length();
                    } else {
                        adjusted = true;
                    }
                }
                if (numLetter < minNumLetter){
                    minNumLetter = numLetter;
                }
            }
        }
        return minNumLetter;
    }


    public void run() {
        Scanner sc = new Scanner(System.in);
        Queue<String> letter = new LinkedList<String>();
        HashMap<String,Integer> original = new
        HashMap<String,Integer>();
        int N = sc.nextInt();
        for (int n = 0; n < N; n++){
            String word = sc.next();
            original.put(word,1);
            letter.offer(word);
        }
        System.out.println(solve(letter,original));
    }

    public static void main(String[] args) {
        Love myLove = new Love();
        myLove.run();
    }

}
