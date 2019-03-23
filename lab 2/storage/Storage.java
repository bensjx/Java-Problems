/**
 * Name         : 
 * Matric No.   : 
 * Plab Acct.   :
 */

import java.util.*;
public class Storage {

    public void Purchase(String name, int value, ArrayList<Box> box,
            Box hand){
        if (!hand.isFull()){
            Item createitem = new Item(name,value,0);
            hand.addItem(createitem);
            System.out.println("item "+name+" is now being held");
        }else{
            for (int i = 0; i < box.size(); i++){
                if (!box.get(i).isFull()){
                    Item createitem = new Item(name,value,i);
                    box.get(i).addItem(createitem);
                    System.out.println("item "+name+" has been " +
                            "deposited to box " +(i+1));
                    break;
                }
            }
        }
    }

    public void Deposit(String name,Box hand, ArrayList<Box> box){
        Item tempitem = new Item("",0,0);
        boolean present = false;
        for (int b = 0; b < box.size(); b++){
            int a = box.get(b).getItem().size();
            for (int c = 0; c < a; c++){
                if (box.get(b).getItem().get(c).getName().equals(name)){
                    System.out.println("item "+name+" is already in "+           
                            "storage");
                    return;
                }
            }
        }
        for (int x = 0; x < hand.getItem().size(); x++){
            if (hand.getItem().get(x).getName().equals(name)){
                tempitem = hand.getItem().get(x);
                present = true;
                break;
            }
        }
        if (present == false){
            System.out.println("item "+name+" does not exist");
            return;
        }
        for (int i = 0; i < box.size(); i++){
            if (!box.get(i).isFull()){
                box.get(i).addItem(tempitem);
                hand.removeItem(tempitem);
                tempitem.changeLoc(i);
                System.out.println("item "+name+" has been deposited to"
                        +" box "+(i+1));
                break;
            }
        }
    }

    public void Withdraw(String name, ArrayList<Box> box,
            Box hand){
        for (int i = 0; i < hand.getItem().size(); i++){
            if (hand.getItem().get(i).getName().equals(name)){
                System.out.println("item "+name+" is already being"
                        +" held");
                return;
            }
        }
        for (int b = 0; b < box.size(); b++){
            int a = box.get(b).getItem().size();
            for (int c = 0; c < a; c++){
                if (box.get(b).getItem().get(c).getName().equals(name)){
                    if(hand.isFull()){
                        System.out.println("cannot hold any more"+
                                " items");
                        return;
                    }else{
                        Item tempitem = box.get(b).getItem().get(c);
                        box.get(b).removeItem(tempitem);
                        tempitem.changeLoc(0);
                        hand.addItem(tempitem);
                        System.out.println("item "+name+" has been "+
                                "withdrawn");
                        return;
                    }
                }
            }
        }
        System.out.println("item "+name+" does not exist");
    }

    public void Location(String name,Box hand,
            ArrayList<Box> box){
        for (int i = 0; i < hand.getItem().size(); i++){
            if (hand.getItem().get(i).getName().equals(name)){
                System.out.println("item "+name+" is being held");
                return;
            }
        }
        for (int x = 0; x < box.size(); x++){
            for (int y = 0; y<box.get(x).getItem().size();y++){
                if (box.get(x).getItem().get(y).getName().equals(name)){
                    System.out.println("item "+name+" is in box "+(x+1));
                    return;
                }
            }
        }
        System.out.println("item "+name+" does not exist");
    }

    public void Valuable(Box hand, ArrayList<Box> box){
        int highest = 0;
        ArrayList<String> names = new ArrayList<String>();
        for (int i = 0; i < hand.getItem().size(); i++){
            if (hand.getItem().get(i).getValue()>highest){
                highest = hand.getItem().get(i).getValue();
            }
        }
        for (int x = 0; x < box.size(); x++){
            for (int y = 0; y<box.get(x).getItem().size();y++){
                if (box.get(x).getItem().get(y).getValue() > highest){
                    highest = box.get(x).getItem().get(y).getValue();
                }
            }
        }
        for (int j = 0; j < hand.getItem().size(); j++){
            if (hand.getItem().get(j).getValue() == highest){
                names.add(hand.getItem().get(j).getName());
            }
        }
        for (int k = 0; k < box.size(); k++){
            for (int l = 0; l<box.get(k).getItem().size();l++){
                if (box.get(k).getItem().get(l).getValue() == highest){
                    names.add(box.get(k).getItem().get(l).getName());
                }
            }
        }
        Collections.sort(names);
        System.out.println(names.get(0));
    }

    public void run() {
        // treat this as your "main" method
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int S = sc.nextInt();
        int K = sc.nextInt();
        int Q = sc.nextInt();
        Box _hand = new Box(0,S,new ArrayList<Item>());
        ArrayList<Box> _box = new ArrayList<Box>();
        for (int i = 1; i < N+1; i++){
            Box tempbox = new Box(i,K,new ArrayList<Item>());
            _box.add(tempbox);
        }
        for (int x = 0; x < Q; x++){
            String qtype = sc.next();
            if (qtype.equals("purchase")){
                String name = sc.next();
                int value = sc.nextInt();
                Purchase(name,value,_box,_hand);
            }else if(qtype.equals("deposit")){
                String name = sc.next();
                Deposit(name,_hand,_box);
            }else if(qtype.equals("withdraw")){
                String name = sc.next();
                Withdraw(name,_box,_hand);
            }else if(qtype.equals("location")){
                String name = sc.next();
                Location(name,_hand,_box);
            }else if(qtype.equals("valuable")){
                Valuable(_hand,_box);
            }
        }
    }

    public static void main(String[] args) {
        Storage myStorageSystem = new Storage();
        myStorageSystem.run();
    }

}

class Box {
    // define appropriate attributes, constructor, and methods
    private int _capacity;
    private int _number;
    private ArrayList<Item> _item;

    public Box(int num, int cap, ArrayList<Item> item){
        _number = num;
        _capacity = cap;
        _item = item;
    }
    public int getNumber(){return _number;}
    public int getCapacity(){return _capacity;}
    public ArrayList<Item> getItem(){return _item;}
    public void addItem(Item item){
        _item.add(item);
    }
    public void removeItem(Item item){
        _item.remove(item);
    }
    public boolean isFull(){
        if (_capacity == _item.size()){
            return true;
        }else{return false;}
    }
}

class Item {
    // define appropriate attributes, constructor, and methods
    private String _name;
    private int _value;
    private int _loc;

    public Item(String name, int value, int loc){
        _name = name;
        _value = value;
        _loc = loc;
    }
    public int getValue(){return _value;}
    public int getLoc(){return _loc;}
    public String getName(){return _name;}
    public void changeLoc(int y){_loc = y;}
}
