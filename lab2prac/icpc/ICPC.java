/**
 * Name         :
 * Matric No.   : 
 * PLab Acct.   :
 */

import java.util.*;

public class ICPC {

    // define your own attributes, constructor, and methods here
    private void Submit(String teamname, int problem, int time, String
            verdict, HashMap<Integer,Problem> mainproblem, HashMap<String, Team>
            mainteam){
        Team team = mainteam.get(teamname);
        Problem problems = mainproblem.get(problem);
        for (int i = 0; i < problems.getTeams().size();i++){
            if (problems.getTeams().get(i).getName().equals(teamname)){
                System.out.println("problem already solved");
                return;
            }
        }
        if (verdict.equals("AC")){
            int temp = team.getAttempts(problem);
            team.increasePenalty(problem,time);
            team.increaseNumsolved();
            problems.updateTeam(team);
            if (problems.getSolved() == 0){
                problems.updateTime(time);
            }
            System.out.println(teamname+ " " + verdict + " " + problem +
                    " " + temp);
            return;
        }
        else if (verdict.equals("TLE") || verdict.equals("MLE") ||
                verdict.equals("WA") || verdict.equals("RTE")){
            int temp = team.getAttempts(problem);
            team.increaseAttempts(problem);
            System.out.println(teamname+ " " + verdict + " " + problem +
                    " " + temp);
            return;
        }
    }

    private void Details(String teamname, HashMap<String,Team> mainteam){
        Team team = mainteam.get(teamname);
        System.out.println(teamname + " " + team.getNumsolved() + " " +
                team.getPenalty());
    }

    private void First(int num, HashMap<Integer,Problem>
            mainproblem){
        Problem problem = mainproblem.get(num);
        int time = problem.getSolved();
        ArrayList<Team> teamsolved = problem.getTeams();
        if (time == 0){
            System.out.println("problem " + num + " has not been " + 
                    "solved");
            return;
        }else {
            String name = teamsolved.get(0).getName();
            System.out.println(name + " " + time);
        }
    }

    private void Unsolved(HashMap<Integer,Problem> mainproblem){
        ArrayList<Integer> list = new ArrayList<Integer>();
        for (Problem problems: mainproblem.values()){
            if (problems.getSolved() == 0){
                list.add(problems.getNumber());
            }
        }
        if (list.size() == 0){
            System.out.println("all problems have been solved");
        }else{
            for (int i = 0; i < list.size()-1; i++){
                System.out.print(list.get(i) + " ");
            }
            System.out.println(list.get(list.size()-1));
        }
    }

    private void Top(HashMap<String,Team> mainteam){
        String teamname = "";
        int submissions = 0;
        int penalty = 999999;
        for (Team teams: mainteam.values()){
            if (teams.getNumsolved() > submissions){
                teamname = teams.getName();
                submissions = teams.getNumsolved();
                penalty = teams.getPenalty();
            } else if(teams.getNumsolved() == submissions){
                if (teams.getPenalty() < penalty){
                    teamname = teams.getName();
                    penalty = teams.getPenalty();
                } else if(teams.getPenalty() == penalty){
                    if (teams.getName().compareTo(teamname) < 0){
                        teamname = teams.getName();
                    }
                }
            }
        }
        Team team = mainteam.get(teamname);
        System.out.println(teamname + " " + team.getNumsolved() + " " +
                team.getPenalty());
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int P = sc.nextInt();
        int Q = sc.nextInt();
        HashMap<Integer,Problem> _problem = new
            HashMap<Integer,Problem>();
        HashMap<String,Team> _team = new HashMap<String,Team>();
        for (int i = 0; i < N; i++){
            String teamname = sc.next();
            ArrayList<Integer> temp = new ArrayList<Integer>();
            for (int ii = 0; ii < P; ii++){
                temp.add(0);
            }
            _team.put(teamname,new Team(teamname,0,temp,0));
        }
        for (int x = 1; x < P+1; x++){
            _problem.put(x,new Problem(x,0,new ArrayList<Team>(),0));
        }
        for (int y = 0; y < Q; y++){
            String qtype = sc.next();
            if (qtype.equals("SUBMIT")){
                Submit(sc.next(),sc.nextInt(),sc.nextInt(),sc.next(),_problem,_team);
            } else if (qtype.equals("DETAILS")){
                Details(sc.next(),_team);
            } else if (qtype.equals("FIRST")){;
                First(sc.nextInt(),_problem);
            } else if (qtype.equals("UNSOLVED")){
                Unsolved(_problem);
            } else if (qtype.equals("TOP")){
                Top(_team);
            }
        }
    }

    public static void main(String[] args) {
        ICPC competition = new ICPC();
        competition.run();
    }
}

class Problem {
    // define your own attributes, constructor, and methods here
    private int _number;
    private int _timeSolved;
    private ArrayList<Team> _teamsSolved;
    private int _submissions;

    public Problem(int number, int timesolved, ArrayList<Team>
            teamssolved,int submissions){
        _number = number;
        _timeSolved = timesolved;
        _teamsSolved = teamssolved;
        _submissions = submissions;
    }

    public int getNumber(){
        return _number;
    }
    public int getSolved(){
        return _timeSolved;
    }
    public ArrayList<Team> getTeams(){
        return _teamsSolved;
    }
    public int getSubmissions(){
        return _submissions;
    }
    public void updateTime(int x){
        _timeSolved += x;
    }
    public void updateTeam(Team team){
        _teamsSolved.add(team);
    }
    public void increaseSubmissions(){
        _submissions += 1;
    }
}

class Team {
    // define your own attributes, constructor, and methods here
    private String _name;
    private int _penalty;
    private ArrayList<Integer> _attempts;
    private int _numSolved;

    public Team(String name, int penalty, ArrayList<Integer> attempts, int numsolved){
        _name = name;
        _penalty = penalty;
        _attempts = attempts;
        _numSolved = numsolved;
    }
    public String getName(){
        return _name;
    }
    public int getPenalty(){
        return _penalty;    
    }
    public int getAttempts(int loc){
        return _attempts.get(loc-1);
    }
    public int getNumsolved(){
        return  _numSolved;
    }
    public void increaseAttempts(int x){
        int temp = _attempts.get(x-1);
        _attempts.set(x-1,temp+1);
    }
    public void increaseNumsolved(){
        _numSolved++;
    }
    public void increasePenalty(int p,int x){
        _penalty += _attempts.get(p-1)*20 + x;
        _attempts.set(p-1,0);
    }
}
