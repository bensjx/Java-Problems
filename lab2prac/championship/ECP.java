import java.util.*;

public class ECP {

    public void Win(String winname, String losename,
            HashMap<String,Championship> champ, HashMap<String,Team>
            team){
        Team winteam = team.get(winname);
        Team loseteam = team.get(losename);
        if (!winteam.getChamp().equals(loseteam.getChamp())){
            System.out.println("invalid matching");
        } else {
            winteam.win();
            winteam.increaseWin();
            winteam.increaseMatches();
            loseteam.increaseMatches();
            System.out.println(winteam.getChamp());
        }
    }

    public void Draw(String nameone, String nametwo, 
            HashMap<String,Championship> champ, HashMap<String,Team>
            team){
        Team teamone = team.get(nameone);
        Team teamtwo = team.get(nametwo);
        if (!teamone.getChamp().equals(teamtwo.getChamp())){
            System.out.println("invalid matching");
        } else {
            teamone.draw();
            teamtwo.draw();
            teamone.increaseMatches();
            teamtwo.increaseMatches();
            System.out.println(teamone.getChamp());
        }
    }

    public void Top(String champname,HashMap<String,Championship> champ){
        Championship league = champ.get(champname);
        HashMap<String,Team> teams = league.getTeam();
        String topname = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        int topscore = 0;
        int topwin = 0;
        for (Team team: teams.values()){
            if (team.getScore() > topscore){
                topscore = team.getScore();
                topname = team.getName();
                topwin = team.getWin();
            } else if (team.getScore() == topscore){
                if (team.getWin() > topwin){
                    topwin = team.getWin();
                    topname = team.getName();
                } else if (team.getWin() == topwin){
                    if (team.getName().compareTo(topname) < 0){
                        topname = team.getName();
                    }
                }
            }
        }
        System.out.println(topname);
    }
    public void Max(HashMap<String,Team> team){
        String topname = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        int maxscore = 0;
        for (Team teams: team.values()){
            if (teams.getMatches() > maxscore){
                maxscore = teams.getMatches();
                topname = teams.getName();
            }else if (teams.getMatches() == maxscore){
                if (teams.getName().compareTo(topname) < 0){
                    topname = teams.getName();
                }
            }
        }
        System.out.println(topname);
    }

    public void Final(String champname, HashMap<String,Championship>
            championship){
        Championship league = championship.get(champname);
        HashMap<String,Team> teams = league.getTeam();
        String topname = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        String topname2 = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        String topname3 = "zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz";
        int topscore = 0;
        int topscore2 = 0;
        int topscore3 = 0;
        int topwin = 0;
        int topwin2 = 0;
        int topwin3 = 0;
        for (Team team: teams.values()){
            if (team.getScore() > topscore){
                topscore = team.getScore();
                topname = team.getName();
                topwin = team.getWin();
            } else if (team.getScore() == topscore){
                if (team.getWin() > topwin){
                    topwin = team.getWin();
                    topname = team.getName();
                } else if (team.getWin() == topwin){
                    if (team.getName().compareTo(topname) < 0){
                        topname = team.getName();
                    }
                }
            }
        }
        for (Team team: teams.values()){
            if (!team.getName().equals(topname)){
                if (team.getScore() > topscore2){
                    topscore2 = team.getScore();
                    topname2 = team.getName();
                    topwin2 = team.getWin();
                } else if (team.getScore() == topscore2){
                    if (team.getWin() > topwin2){
                        topwin2 = team.getWin();
                        topname2 = team.getName();
                    } else if (team.getWin() == topwin2){
                        if (team.getName().compareTo(topname2) < 0){
                            topname2 = team.getName();
                        }
                    }
                }
            }
        }
        for (Team team: teams.values()){
            if (!team.getName().equals(topname) &&
                    !team.getName().equals(topname2)){
                if (team.getScore() > topscore3){
                    topscore3 = team.getScore();
                    topname3 = team.getName();
                    topwin3 = team.getWin();
                } else if (team.getScore() == topscore3){
                    if (team.getWin() > topwin3){
                        topwin3 = team.getWin();
                        topname3 = team.getName();
                    } else if (team.getWin() == topwin3){
                        if (team.getName().compareTo(topname3) < 0){
                            topname3 = team.getName();
                        }
                    }
                }
            }
        }
        if (topname3.equals("")){
            System.out.println(topname + " " + topname2);
        } else if (topname2.equals("")){
            System.out.println(topname);
        } else {
            System.out.println(topname + " " + topname2 + " " +
                    topname3);
        }
    }


    public void run(){
        Scanner sc = new Scanner(System.in);
        HashMap<String,Championship> _champ = new
            HashMap<String,Championship>();
        HashMap<String,Team> _team = new HashMap<String,Team>();
        int C = sc.nextInt();
        int T = sc.nextInt();
        int Q = sc.nextInt();
        for (int c = 0; c < C; c++){
            String champname = sc.next();
            _champ.put(champname,new Championship(champname,new
                        HashMap<String,Team>()));
        }
        for (int t = 0; t < T; t++){
            String teamname = sc.next();
            String champname = sc.next();
            Team newteam = new Team(teamname,champname,0,0,0);
            _team.put(teamname,newteam);
            for (Championship champs: _champ.values()){
                if (champs.getName().equals(champname)){
                    champs.addTeam(teamname,newteam);
                }
            }
        }
        for (int q = 0; q < Q; q++){
            String qtype = sc.next();
            if (qtype.equals("win")){
                Win(sc.next(),sc.next(),_champ,_team);
            } else if (qtype.equals("draw")){
                Draw(sc.next(),sc.next(),_champ,_team);
            } else if (qtype.equals("top")){
                Top(sc.next(),_champ);
            } else if (qtype.equals("max")){
                Max(_team);
            } else if (qtype.equals("final")){
                Final(sc.next(),_champ);
            }
        }
    }

    public static void main(String[] args) {
        //define your main method here
        ECP myecp = new ECP();
        myecp.run();
    }
}

class Championship {
    //define the appropriate attributes, constructor, and methods here
    private String _name;
    private HashMap<String,Team> _teams;

    public Championship(String name, HashMap<String,Team> teams){
        _name = name;
        _teams = teams;
    }

    public String getName(){
        return _name;
    }

    public HashMap<String,Team> getTeam(){
        return _teams;
    }

    public void addTeam(String teamname,Team team){
        _teams.put(teamname,team);
    }
}

class Team {
    //define the appropriate attributes, constructor, and methods here
    private String _name;
    private String _championship;
    private int _score;
    private int _matches;
    private int _win;

    public Team(String name, String championship, int score, int
            matches, int win){
        _name = name;
        _championship = championship;
        _score = score;
        _matches = matches;
        _win = win;
    }

    public String getName(){
        return _name;
    }

    public String getChamp(){
        return _championship;
    }

    public int getScore(){
        return _score;
    }

    public int getMatches(){
        return _matches;
    }

    public int getWin(){
        return _win;
    }

    public void win(){
        _score += 3;
    }

    public void draw(){
        _score += 1;
    }

    public void increaseWin(){
        _win += 1;
    }

    public void increaseMatches(){
        _matches += 1;
    }
}
