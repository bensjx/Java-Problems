/**
 * Name : 
 * Matric. No :
 * PLab Acct. :
 */

import java.util.*;

public class ClassPhoto {

    public void Arrive(String name, int height, LinkedList<Student>
            mainlist){
        if (mainlist.size() == 0){
            mainlist.add(new Student(name,height));
            System.out.println(name + " added at " + 1);
        } else {
            for (int i = 0; i < mainlist.size(); i++){
                if (mainlist.get(i).getHeight() <= height){
                    mainlist.add(i,new Student(name,height));
                    System.out.println(name + " added at " + 
                            (i+1));
                    return;
                }
            }
            mainlist.add(new Student(name,height));
            System.out.println(name + " added at " +
                    (mainlist.size()));
        }
    }

    public void Leave(String name, LinkedList<Student>
            mainlist){
        int pos = Integer.MIN_VALUE;
        for (int i = 0; i < mainlist.size(); i++){
            if (mainlist.get(i).getName().equals(name)){
                pos = i;
                break;
            }
        }
        if (pos == Integer.MIN_VALUE){
            System.out.println("No student with name " + name);
            return; 
        } else {
            mainlist.remove(pos);
            System.out.println(name + " has left position " + (pos+1));
        }
    }

    public void Shortest(int position, LinkedList<Student> mainlist){
        // not allowed to use arraylist
        ArrayList<Integer> heights = new ArrayList<Integer>();
        for (int i = 0; i < mainlist.size(); i++){
            if (!heights.contains(mainlist.get(i).getHeight())){
                heights.add(mainlist.get(i).getHeight());
            }
        }
        if (heights.size() < position){
            System.out.println("No such student");
            return;
        } else {
            int height = heights.get(heights.size()-position);
            ArrayList<String> names = new ArrayList<String>();
            for (int x = 0; x < mainlist.size(); x++){
                if (mainlist.get(x).getHeight() == height){
                    names.add(mainlist.get(x).getName());
                }
            }
            for (int y = 0; y < names.size()-1; y++){
                System.out.print(names.get(y) + " ");
            }
            System.out.println(names.get(names.size()-1));
        }
    }

    public void Count(int LH, int HH, LinkedList<Student> mainlist){
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < mainlist.size(); i++){
            if (mainlist.get(i).getHeight() >= LH &&
                    mainlist.get(i).getHeight() <= HH){
                names.add(mainlist.get(i).getName());
            }
        }
        System.out.println(names.size());
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int Q = sc.nextInt();
        LinkedList<Student> mainlist = new LinkedList<Student>();
        for (int i = 0; i < Q; i++){
            String qtype = sc.next();
            if (qtype.equals("arrive")){
                Arrive(sc.next(),sc.nextInt(),mainlist);
            } else if (qtype.equals("leave")){
                Leave(sc.next(),mainlist);
            } else if (qtype.equals("shortest")){
                Shortest(sc.nextInt(),mainlist);
            } else if (qtype.equals("count")){
                Count(sc.nextInt(),sc.nextInt(),mainlist);
            } else {
                System.out.println("No such command");
            }
        }
    }

    public static void main(String[] args) {
        ClassPhoto newClassPhoto = new ClassPhoto();
        newClassPhoto.run();
    }
}

class Student{
    private String _name;
    private int _height;

    public Student(String name, int height){
        _name = name;
        _height = height;
    }

    public String getName(){
        return _name;
    }

    public int getHeight(){
        return _height;
    }
}
