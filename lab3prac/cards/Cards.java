/**
 * Name         :
 * Matric No    : 
 * Plab Acct.   :
 *
 */

import java.util.*;

public class Cards {
    
    private void swap(int a, int b, int c, int d, LinkedList<Human>
    maincard){
        LinkedList<Human> swapped = new LinkedList<Human>();
        LinkedList<Human> first = new LinkedList<Human>();
        LinkedList<Human> second = new LinkedList<Human>();
        for (int i = 0; i < maincard.size(); i++){
            if (i+1 >= a && i+1 <= b){
                first.add(maincard.get(i));
            }
            if (i+1 >= c && i+1 <= d){
                second.add(maincard.get(i));
            }
        }
        int count = 0;
        while (count < maincard.size()){
            if (count+1 == a){
                swapped.addAll(second);
            } else if (count+1 == c){
                swapped.addAll(first);
            } else if ((count+1)<a || count+1 > b && count+1 < c ||
            count+1 > d) {
                swapped.add(maincard.get(count));
            }
            count++;
        }
        maincard.clear();
        maincard.addAll(swapped);
        System.out.println("swap has been performed");
    }
    
    private void shuffle(LinkedList<Human> maincard){
        LinkedList<Human> first = new LinkedList<Human>();
        LinkedList<Human> second = new LinkedList<Human>();
        LinkedList<Human> shuffled = new LinkedList<Human>();
        if (maincard.size() % 2 == 1){ //odd
            for (int f = 0; f < (maincard.size()/2)+1; f++){
                first.add(maincard.get(f));
            }
            for (int s = (maincard.size()/2)+1; s < maincard.size();
            s++){
                second.add(maincard.get(s));
            }
            int count = 0;
            while (count < second.size()){
                shuffled.add(first.get(count));
                shuffled.add(second.get(count));
                count ++;
            }
            shuffled.add(first.get(first.size()-1));
            maincard.clear();
            maincard.addAll(shuffled);
            System.out.println("shuffle has been performed");
        } else { //even
            for (int f = 0; f < (maincard.size()/2); f++){
                first.add(maincard.get(f));
            }
            for (int s = (maincard.size()/2); s < maincard.size();
            s++){
                second.add(maincard.get(s));
            }
            int count = 0;
            while (count < second.size()){
                shuffled.add(first.get(count));
                shuffled.add(second.get(count));
                count ++;
            }
            maincard.clear();
            maincard.addAll(shuffled);
            System.out.println("shuffle has been performed");
        }
    }

    private void details(int idx, LinkedList<Human> maincard){
        Human human = maincard.get(idx-1);
        System.out.println(human.getName() + " " + human.getAge());
    }

    private void position(String name, LinkedList<Human> maincard){
        ListIterator<Human> ite = maincard.listIterator(0);
        boolean present = false;
        int count = 1;
        while (ite.hasNext()){
            if (ite.next().getName().equals(name)){
                present = true;
                break;
            } else {
                count++;
            }
        }
        if (present == true){
            System.out.println(count);
        } else {
            System.out.println(name + " is not present");
        }
    }
    
    private void print(LinkedList<Human> maincard){
        if (maincard.size() == 1){
            System.out.println(maincard.get(0).getName());
        } else {
            System.out.print(maincard.get(0).getName());
            for (int i = 1; i < maincard.size() - 1; i++){
                System.out.print(" " + maincard.get(i).getName());
            }
            System.out.println(" " + maincard.get(maincard.size()-1).getName());
        }
    }
   
    public void run() {
        Scanner sc = new Scanner(System.in);
        LinkedList<Human> maincard = new LinkedList<Human>();
        int N = sc.nextInt();
        for (int i = 0; i < N; i++){
            maincard.add(new Human(sc.next(),sc.nextInt()));
        }
        int Q = sc.nextInt();
        for (int x = 0; x < Q; x++){
            String qtype = sc.next();
            if (qtype.equals("swap")){
                swap(sc.nextInt(),sc.nextInt(),sc.nextInt(),sc.nextInt(),maincard);
            } else if (qtype.equals("details")){
                details(sc.nextInt(),maincard);
            } else if (qtype.equals("position")){
                position(sc.next(),maincard);
            } else if (qtype.equals("shuffle")){
                shuffle(maincard);
            } else if (qtype.equals("print")){
                print(maincard);
            }
        }
    }
    
    public static void main(String[] args) {
        Cards myCards = new Cards();
        myCards.run();
    }
}

class Human{
    private String _name;
    private int _age;

    public Human(String name, int age){
        _name = name;
        _age = age;
    }
    
    public String getName(){
        return _name;
    }
    
    public int getAge(){
        return _age;
    }
}
class TailedLinkedList<E> {

    // Data attributes
    private ListNode<E> head;
    private ListNode<E> tail;
    private int num_nodes;

    public TailedLinkedList() {
        this.head = null;
        this.tail = null;
        this.num_nodes = 0;
    }

    // Return true if list is empty; otherwise return false.
    public boolean isEmpty() {
        return (num_nodes == 0);
    }

    // Return number of nodes in list.
    public int size() {
        return num_nodes;
    }

    // Return value in the first node.
    public E getFirst() throws NoSuchElementException {
        if (head == null)
            throw new NoSuchElementException("can't get from an empty list");
        else
            return head.getElement();
    }

    // Return true if list contains item, otherwise return false.
    public boolean contains(E item) {
        for (ListNode<E> n = head; n != null; n = n.getNext())
            if (n.getElement().equals(item))
                return true;

        return false;
    }



    // Add item to front of list.
    public void addFirst(E item) {
        head = new ListNode<E>(item, head);
        num_nodes++;
        if (num_nodes == 1) tail = head;
    }

    // Return reference to first node.
    public ListNode<E> getHead() {
        return head;
    }

    // Return reference to last node of list.
    public ListNode<E> getTail() {
        return tail;
    }

    // Add item to end of list.
    public void addLast(E item) {
        if (head != null) {
            tail.setNext(new ListNode<E>(item));
            tail = tail.getNext();
        } else {
            tail = new ListNode<E>(item);
            head = tail;
        }
        num_nodes++;
    }

    // Remove node after node referenced by current
    public E removeAfter(ListNode<E> current) throws NoSuchElementException {
        E temp;
        if (current != null) {
            ListNode<E> nextPtr = current.getNext();
            if (nextPtr != null) {
                temp = nextPtr.getElement();
                current.setNext(nextPtr.getNext());
                num_nodes--;
                if (nextPtr.getNext() == null) // last node is removed
                    tail = current;
                return temp;
            } else
                throw new NoSuchElementException("No next node to remove");
        } else { // if current is null, we want to remove head
            if (head != null) {
                temp = head.getElement();
                head = head.getNext();
                num_nodes--;
                if (head == null)
                    tail = null;
                return temp;
            } else
                throw new NoSuchElementException("No next node to remove");
        }
    }

    // Remove first node of list.
    public E removeFirst() throws NoSuchElementException {
        return removeAfter(null);
    }

    // Remove item from list
    public E remove(E item) throws NoSuchElementException {
        ListNode<E> current = head;
        if (current == null) {
            throw new NoSuchElementException("No node to remove");
        }
        if (current.getElement().equals(item)) {
            return removeAfter(null);
        }
        while (current.getNext().getElement() != null) {
            if (current.getNext().getElement().equals(item)) {
                return removeAfter(current);
            }
            current = current.getNext();
        }
        throw new NoSuchElementException("No node to remove");
    }
}

class ListNode<E> {
    protected E element;
    protected ListNode<E> next;

    /* constructors */
    public ListNode(E item) {
        this.element = item;
        this.next = null;
    }

    public ListNode(E item, ListNode<E> n) {
        element = item;
        next = n;
    }

    /* get the next ListNode */
    public ListNode<E> getNext() {
        return this.next;
    }

    /* get the element of the ListNode */
    public E getElement() {
        return this.element;
    }

    public void setNext(ListNode<E> item) {
        this.next = item;
    }

    public void setElement(E item) {
        this.element = item;
    }
}

