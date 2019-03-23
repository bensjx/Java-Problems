/**
 * Name         : 
 * Matric No.   : 
 * PLab Acct.   :
 */

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;
// Check with a LabTA before you decide to import anything else...
//  Using other Collection classes and arrays might result in 0 marks

public class Printer {

    private void pushResults(User user,Time currTime,Stack<String>
            results){
        Queue<Integer> suJob = user.getJob();
        int tempSize = suJob.size();
        int startTime = user.getTime();
        currTime.setTime(Math.max(startTime,currTime.getTime()));
        results.push("COVER " + currTime.getTime() + " " +
                user.getName() + " " +
                tempSize);
        while (!suJob.isEmpty()){
            results.push("PHOTO " + suJob.poll());
        }
        currTime.setTime(currTime.getTime()+ tempSize + 1);
    }

    private Stack<String> print(Queue<User> standard, Queue<User> premium){
        Stack<String> results = new Stack<String>(); //main answer
        Time currTime = new Time(0);
        while (!standard.isEmpty() || !premium.isEmpty()){
            if (standard.isEmpty()){
                while (!premium.isEmpty()){
                    User premiumUser = premium.poll();
                    pushResults(premiumUser,currTime,results);
                }
            } else if (premium.isEmpty()){
                while(!standard.isEmpty()){
                    User standardUser = standard.poll();
                    pushResults(standardUser,currTime,results);
                }
            } else {
                if (premium.peek().getTime() <= currTime.getTime()){
                    User premiumUser = premium.poll();
                    pushResults(premiumUser,currTime,results);
                } else if (standard.peek().getTime() <=
                        currTime.getTime()){
                    User standardUser = standard.poll();
                    pushResults(standardUser,currTime,results);
                } else {
                    if (premium.peek().getTime() <
                            standard.peek().getTime()){
                        User premiumUser = premium.poll();
                        pushResults(premiumUser,currTime,results);
                    } else {
                        User standardUser = standard.poll();
                        pushResults(standardUser,currTime,results);
                    }
                }
            }
        }
        return results;
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(); //num of users
        Queue<User> standard = new LinkedList<User>(); //main list
        Queue<User> premium = new LinkedList<User>(); //main list
        for (int n = 0; n < N; n++){
            int time = sc.nextInt();
            String name = sc.next();
            int pages = sc.nextInt();
            Queue<Integer> jobs = new LinkedList<Integer>();
            for (int p = 0; p < pages; p++){
                jobs.offer(sc.nextInt());
            }
            if (name.equals("proftan")){
                premium.offer(new User(time,name,jobs));
            } else {
                standard.offer(new User(time,name,jobs));
            }
        }
        Stack<String> results;
        results = print(standard,premium);
        while (!results.isEmpty()){
            System.out.println(results.pop());
        }
    }
    public static void main(String[] args) {
        Printer newPrinter = new Printer();
        newPrinter.run();
    }
}

class Time{
    private int _time;

    public Time(int time){
        _time = time;
    }

    public int getTime(){
        return _time;
    }

    public void setTime(int x){
        _time = x;
    }
}

class User{
    private int _time;
    private String _name;
    private Queue<Integer> _job;

    public User(int time, String name, Queue<Integer> job){
        _time = time;
        _name = name;
        _job = job;
    }

    public int getTime(){
        return _time;
    }

    public String getName(){
        return _name;
    }

    public Queue<Integer> getJob(){
        return _job;
    }
}
