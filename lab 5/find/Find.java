/**
 *	name	  :
 *	matric no.:
 */

import java.util.*;

class Result {

    public void generate(String dna, HashMap<String,ArrayList<Integer>> dnaMap,int
            N) {
        for (int f = 0; f < N; f++){
            String concerned = Character.toString(dna.charAt(f));
            ArrayList<Integer> indexes = new ArrayList<Integer>();
            if (!dnaMap.containsKey(concerned)){
                indexes.add(f);
                dnaMap.put(concerned,indexes);
            } else {
                ArrayList<Integer> indexExist = dnaMap.get(concerned);  
                indexExist.add(f);
                dnaMap.put(concerned,indexExist);
            }
        }
    }

    public boolean find(String subDNA, HashMap<String,ArrayList<Integer>>
            fullDNA,String stringDNA) {
        ArrayList<Integer> startingIdx =
            fullDNA.get(Character.toString(subDNA.charAt(0)));
        if (startingIdx == null){
            return false;
        }
        for (int i = 0; i < startingIdx.size(); i++){
            int idx = startingIdx.get(i);
            if (idx + subDNA.length() < stringDNA.length()+1){
                if
                    (stringDNA.substring(idx,idx+subDNA.length()).equals(subDNA)){
                        return true;
                    }
            }
        }
        return false;
    }

    public void run(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        HashMap<String,ArrayList<Integer>> firstDNA = new
            HashMap<String,ArrayList<Integer>>();
        HashMap<String,ArrayList<Integer>> secondDNA = new
            HashMap<String,ArrayList<Integer>>();
        String first = sc.next();
        String second = sc.next();
        generate(first,firstDNA,N);
        generate(second,secondDNA,N);
        int Q = sc.nextInt();
        for (int i = 0; i < Q; i++){
            String subDNA = sc.next();
            if (find(subDNA,firstDNA,first) &&
                    find(subDNA,secondDNA,second)){
                System.out.println(3);
            } else if (find(subDNA,secondDNA,second)){
                System.out.println(2);
            } else if (find(subDNA,firstDNA,first)){
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}

class Find {

    public static void main(String[] args) {
        Result res = new Result();
        res.run();
    }
}
