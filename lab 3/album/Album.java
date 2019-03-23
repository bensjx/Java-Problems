/**
 * Name         : 
 * Matric No.   : 
 * PLab Acct.   :
 */

import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.NoSuchElementException;
// Check with a LabTA before you decide to import anything else...
// Using other Collection classes and arrays might result in 0 marks

public class Album {

    // define your own attributes, constructor, and methods here
    
    /* pre condition: position must be lower than the number of photos
     * post condition: insert photo into the desired album
     */
    private void Insert(String albumid, int position, int
            photoid,LinkedList<Integer> mainalbum,LinkedList<Undo>
            action){
        if (position > mainalbum.size()){
            System.out.println("Invalid position, album " + albumid 
                    + " only has " + mainalbum.size() + " photos.");
            return;
        } else {
            mainalbum.add(position,photoid);
            // action is used to keep track of changes
            action.add(new Undo("I",position,0)); 
            System.out.println("Photo " + photoid +
                    " inserted after position " + position + 
                    " of album " + albumid + ".");
        }

    }
    
    /* pre condition: position should be smaller than the number of
     * photos. Position cannot be 0.
     * post condition: the photo is deleted from the album at position
     */
    private void Delete(String albumid, int position,LinkedList<Integer>
            mainalbum,LinkedList<Undo> action){
        if (position > mainalbum.size()){
            System.out.println("Invalid position, album " + albumid 
                    + " only has " + mainalbum.size() + " photos.");
            return;
        } else if (position == 0){
            System.out.println("Invalid position 0.");
            return;
        } else {
            int temp = mainalbum.get(position-1);
            mainalbum.remove(position-1);
            action.add(new Undo("D",position,temp));
            System.out.println("Photo deleted from position " + 
                    position + " of album " + albumid + ".");
        }
    }
    /* pre condition: -
     * post condition: print out all the items in the album
     */
    private void Preview(String albumid,LinkedList<Integer> mainalbum){
        if (mainalbum.size() == 0){
            System.out.println("Album " + albumid + ": [].");
            return;
        } else {
            System.out.print("Album " + albumid + ": [" + mainalbum.get(0));
            for (int i = 1; i < mainalbum.size(); i++){
                System.out.print(", " + mainalbum.get(i));
            }
            System.out.println("].");
        }
    }
    /* pre condition: action list must not be empty
     * post condition: album is reverted back by 1 move. Action last
     * move is deleted.
     */
    private void _Undo(String albumid,LinkedList<Integer>
            mainalbum,LinkedList<Undo> action){
        if (action.size() == 0){
            System.out.println("No changes in album " + albumid + 
                    " to undo.");
        } else {
            Undo lastmove = action.get(action.size()-1);
            int pos = lastmove.getPosition();
            //I means insert. If last move is insert, we delete it
            if (lastmove.getAction().equals("I")){
                mainalbum.remove(pos);
                action.remove(lastmove);
                System.out.println("Album " + albumid +
                " has been undone.");
                return;
            } else if (lastmove.getAction().equals("D")){
                int removedPhoto = lastmove.getPhoto();
                mainalbum.add(pos-1,removedPhoto);
                action.remove(lastmove);
                System.out.println("Album " + albumid + 
                " has been undone.");
                return;
            }
        }
    }
    
    /* pre condition: -
     * post condition: print the number of non duplicated photos
     */
    private void Count(LinkedList<Integer> A, LinkedList<Integer> B){
        // a new link list to store non duplicate photos
        LinkedList<Integer> distinct = new LinkedList<Integer>();
        for (int i = 0; i < A.size(); i++){
            if (!distinct.contains(A.get(i))){
                distinct.add(A.get(i));
            }
        }
        for (int x = 0; x < B.size(); x++){
            if (!distinct.contains(B.get(x))){
                distinct.add(B.get(x));
            }
        }

        System.out.println("Number of distinct photos: " +
                distinct.size() + ".");
    }

    private void run() {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        LinkedList<Integer> A = new LinkedList<Integer>();
        LinkedList<Integer> B = new LinkedList<Integer>();
        LinkedList<Undo> actionA = new LinkedList<Undo>();
        LinkedList<Undo> actionB = new LinkedList<Undo>();
        for (int i = 0; i < N; i++){
            String qtype = sc.next();
            if (qtype.equals("INSERT")){
                String albumid = sc.next();
                if (albumid.equals("A")){
                    Insert(albumid,sc.nextInt(),sc.nextInt(),A,actionA);
                } else { //it is guaranteed albumID can only be A or B
                    Insert(albumid,sc.nextInt(),sc.nextInt(),B,actionB);
                }
            }
            if (qtype.equals("DELETE")){
                String albumid = sc.next();
                if (albumid.equals("A")){
                    Delete(albumid,sc.nextInt(),A,actionA);
                } else { //it is guaranteed albumID can only be A or B
                    Delete(albumid,sc.nextInt(),B,actionB);
                }
            }
            if (qtype.equals("PREVIEW")){
                String albumid = sc.next();
                if (albumid.equals("A")){
                    Preview(albumid,A);
                } else { //it is guaranteed albumID can only be A or B
                    Preview(albumid,B);
                }
            }
            if (qtype.equals("UNDO")){
                String albumid = sc.next();
                if (albumid.equals("A")){
                    _Undo(albumid,A,actionA);
                } else { //it is guaranteed albumID can only be A or B
                    _Undo(albumid,B,actionB);
                }
            }
            if (qtype.equals("COUNT")){
                Count(A,B);
            }
        }
    }

    public static void main(String[] args) {
        Album newAlbum = new Album();
        newAlbum.run();
    }
}

// Class to store 3 parameters to facilitate method Undo
class Undo{
    private String _action; // "I" or "D" stands for increase and
                            // decrease
    private int _position; // indicare which position to remove or add
                           // to
    private int _removedPhoto; // Only used for delete. To restore the
                               // deleted phoTo

    public Undo(String action, int position,int removedPhoto){
        _action = action;
        _position = position;
        _removedPhoto = removedPhoto;
    }

    public String getAction(){
        return _action;
    }

    public int getPosition(){
        return _position;
    }

    public int getPhoto(){
        return _removedPhoto;
    }
}


/* List node for ExtendedLinkedList */
/* You may modify this! */
/* Add comments for any methods you have added */
class ListNode <E> {
    protected E element;
    protected ListNode <E> next;

    /* constructors */
    public ListNode(E item) { element = item; next = null; }
    public ListNode(E item, ListNode <E> n) { element = item; next=n;}

    /* get the next ListNode */
    public ListNode <E> getNext() {
        return this.next;
    }

    /* get the element of the ListNode */
    public E getElement() {
        return this.element;
    }
}

/* ExtendedLinkedList discussed in Lectue 5B */
/* You may modify this! */
/* Add comments for any methods you have added */
class ExtendedLinkedList <E> {
    private ListNode <E> head = null;
    private int num_nodes = 0;

    public boolean isEmpty() { return (num_nodes == 0); }
    public int size() { return num_nodes; }
    public E getFirst() throws NoSuchElementException {
        if (head == null) 
            throw new NoSuchElementException("can't get from an empty list");
        else return head.getElement();
    }
    public boolean contains(E item) {
        for (ListNode <E> n = head; n!= null; n=n.next)
            if (n.getElement().equals(item)) return true;
        return false;
    }

    public void addFirst(E item) {
        head = new ListNode <E> (item, head);
        num_nodes++;
    }

    public E removeFirst() throws NoSuchElementException {
        ListNode <E> ln;
        if (head == null) 
            throw new NoSuchElementException("can't remove from empty list");
        else { 
            ln = head;
            head = head.next;
            num_nodes--;
            return ln.element;
        }
    }
    public ListNode <E> getFirstPtr() { 
        return head; 
    }

    public void addAfter(ListNode <E> current, E item) {
        if (current != null) { 
            current.next = new ListNode <E> (item, current.next);
        } else {
            head = new ListNode <E> (item, head);
        }
        num_nodes++;
    }

    public E removeAfter(ListNode <E> current) throws NoSuchElementException {
        E temp;
        if (current != null) {
            if (current.next != null) {// current is not pointing to last node
                temp = current.next.element;
                current.next = current.next.next;
                num_nodes--;  return temp;
            } else throw new NoSuchElementException("No next node to remove");
        } else { // if current is null, assume we want to remove head
            if (head != null) {
                temp = head.element;
                head = head.next; 
                num_nodes--;  return temp;
            } else throw new NoSuchElementException("No next node to remove");
        }
    }
    public void print() {
        ListNode <E> ln = head;
        System.out.print("List is: " + ln.element);
        for (int i=1; i < num_nodes; i++) {
            ln = ln.next;
            System.out.print(", " + ln.element);}
            System.out.println(".");
    }
}
