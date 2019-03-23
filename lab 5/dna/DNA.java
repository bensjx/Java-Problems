/**
 *	Name	  : 
 *	Matric no.:
 */

import java.util.*;

public class DNA {

    public void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        String dna = sc.next();
        HashMap<String,ArrayList<Integer>> dnaMap = new
            HashMap<String,ArrayList<Integer>>();
        generate(dna,dnaMap,N);
        int Q = sc.nextInt();
        for (int q = 0; q < Q; q++){
            System.out.println(count(sc.next(),dnaMap,N,dna));
        }
    }

    public void generate(String dna,HashMap<String,ArrayList<Integer>>
            dnaMap,int N) {
        for (int i = 0; i < N; i++){
            String concerned = Character.toString(dna.charAt(i));
            if (!dnaMap.containsKey(concerned)){
                ArrayList<Integer> idx = new ArrayList<Integer>();
                idx.add(i);
                dnaMap.put(concerned,idx);
            } else {
                ArrayList<Integer> idx = dnaMap.get(concerned);
                idx.add(i);
                dnaMap.put(concerned,idx);
            }
        }
    }

    public int count(String subDNA,HashMap<String,ArrayList<Integer>>
            dnaMap, int N,String dna) {
        int count = 0;
        ArrayList<Integer> startingIdx =
            dnaMap.get(Character.toString(subDNA.charAt(0)));
        if (startingIdx != null){
            for (int i = 0; i < startingIdx.size(); i++){
                int idx = startingIdx.get(i);
                if (idx + subDNA.length() < dna.length()+1){
                    if
                        (dna.substring(idx, idx + subDNA.length()).equals(subDNA)){
                            count++;
                        }
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        DNA dna = new DNA();
        dna.run();
    }
}
