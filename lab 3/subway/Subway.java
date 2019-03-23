/**
 * Name			: 
 * Matric. No	: 
 * PLab Acct.	:
 */

import java.util.*;

public class Subway {

    private void Time(String start, String end, DoublyLinkedList<String>
            stations){
        int clock;
        int anticlock;
        int startpos = stations.get(start);
        int endpos = stations.get(end);
        clock = Math.abs(startpos - endpos);
        anticlock = Math.abs(stations.size()-(clock));
        if (clock == 0){
            System.out.println(0);
        }else if (clock <= anticlock){
            int results = 2*clock + clock-1;
            System.out.println(results);
        } else {
            int results = 2*anticlock + anticlock-1;
            System.out.println(results);
        }
    }

    private void Build(String curr, String newstn,
            DoublyLinkedList<String> stations){
        ListNode<String> track = new ListNode<String>("");
        int loc = stations.get(curr);
        ListNode<String> current = stations.getHead();
        for (int i = 0; i < loc; i++){
            current = current.getNext();
        }
        stations.addAfter(current,newstn);
        System.out.println("station " + newstn + " has been built");
    }

    private void Path(String start, String end, DoublyLinkedList<String>
            stations){
        int clock;
        int anticlock;
        int startpos = stations.get(start);
        int endpos = stations.get(end);
        clock = Math.abs(startpos-endpos);
        anticlock = Math.abs(stations.size()-(clock));
        if (stations.get(startpos) == stations.get(endpos)){
            System.out.println(stations.get(startpos).getElement());
            return;
        } else {
            if (clock < anticlock){
                System.out.print(stations.get(startpos).getElement());
                if (startpos < endpos){
                    for (int i = (startpos+1); i < endpos; i++){
                        System.out.print(" " + stations.get(i).getElement());
                    }
                }
                else {
                    for (int i = (startpos-1); i > endpos; i--){
                        System.out.print(" " + stations.get(i).getElement());
                    }
                }
                System.out.println(" " + stations.get(endpos).getElement());
            } else if(clock == anticlock){
                if (startpos > endpos){
                    System.out.print(stations.get(startpos).getElement());
                    int count = startpos + 1;
                    for (int i =0; i < anticlock-1; i++){
                        if (count == stations.size()){
                            count = 0;
                        }
                        System.out.print(" " +
                                stations.get(count).getElement());
                        count++;
                    }
                    System.out.println(" " +
                            stations.get(endpos).getElement());
                }
                else {
                    System.out.print(stations.get(startpos).getElement());
                    for (int i = (startpos+1); i < endpos; i++){
                        System.out.print(" " + stations.get(i).getElement());
                    }
                    System.out.println(" " + stations.get(endpos).getElement());
                }
            }else {
                    System.out.print(stations.get(startpos).getElement());
                    if (startpos < endpos){
                        int count = startpos - 1;
                        for (int i =0; i < anticlock-1; i++){
                            if (count < 0){
                                count = stations.size()-1;    
                            }
                            System.out.print(" " + stations.get(count).getElement());    
                            count--;
                        }
                    } else {
                        int count = startpos + 1;
                        for (int i =0; i < anticlock-1; i++){
                            if (count == stations.size()){
                                count = 0;
                            }
                            System.out.print(" " +
                                    stations.get(count).getElement());
                            count++;
                        }
                    }
                    System.out.println(" " +
                            stations.get(endpos).getElement());
                }
            }
        }

        private void Print(String s, DoublyLinkedList<String> stations){
            if (stations.size() == 1){
                System.out.println(stations.get(0).getElement());
                return;
            } else {
                int start = stations.get(s);
                System.out.print(stations.get(start).getElement());
                for (ListNode<String> n = stations.get(start).getNext(); n !=
                        stations.get(start).getPrev();n = n.getNext()){
                    System.out.print(" " + n.getElement());
                }
                System.out.println(" " + stations.get(start).getPrev().getElement());
            }
        }
        public void run() {
            Scanner sc = new Scanner(System.in);
            int N = sc.nextInt();
            DoublyLinkedList<String> stations = new
                DoublyLinkedList<String>();
            for (int n = 0; n < N; n++){
                stations.addLast(sc.next());
            }
            int Q = sc.nextInt();
            for (int q = 0; q < Q; q++){
                String qtype = sc.next();
                if (qtype.equals("TIME")){
                    Time(sc.next(),sc.next(),stations);
                } else if (qtype.equals("BUILD")){
                    Build(sc.next(),sc.next(),stations);
                } else if (qtype.equals("PATH")){
                    Path(sc.next(),sc.next(),stations);
                } else if (qtype.equals("PRINT")){
                    Print(sc.next(),stations);
                }
            }
        }

        public static void main(String[] args) {
            Subway newSubwayNetwork = new Subway();
            newSubwayNetwork.run();
        }
    }

    class DoublyLinkedList<E> {

        //Data attributes
        private ListNode<E> head;
        private ListNode<E> tail;
        private int size;

        public DoublyLinkedList() {
            this.head = null;
            this.tail = null;
            this.size = 0;
        }

        public int get(E o){
            int pos = 0;
            ListNode<E> curr = head;
            while (curr != null){
                if (curr.getElement().equals(o)){
                    return pos;
                }
                pos++;
                curr = curr.getNext();
            }
            return -1;
        }

        public ListNode<E> get(int idx){
            ListNode<E> curr = head;
            if (idx < 0 || idx >= size){
                throw new IndexOutOfBoundsException();
            } else {
                for (int i = 0; i < idx; i++){
                    curr = curr.getNext();
                }
                return curr;
            }
        }

        //add element after this current element
        public void addAfter(ListNode<E> current, E item){
            ListNode<E> temp = new ListNode<E>(item); //create the LN to
            //be inserted
            if (current!= null){
                if (current != tail){
                    current.getNext().setPrev(temp);
                    temp.setNext(current.getNext());
                    current.setNext(temp);
                    temp.setPrev(current);
                } else { //edge case. if is tail, no need to get
                    //curr.next.prev,meaning temp will become the tail
                    temp.setPrev(current);
                    current.setNext(temp);
                    tail = temp;
                }
            } else {//if curr is null, means the DLL is empty. So we create
                //a new DLL with head and tail pointing to temp
                head = temp;
                tail = temp;
            }
            head.setPrev(tail);
            tail.setNext(head);
            size++;
            // to complete the circular loop just for this problem
        }

        //add element to the tail of the list
        public void addLast(E item){
            ListNode<E> temp = new ListNode<E>(item);
            if (size == 0){
                head = temp;
                tail = temp;
            } else {
                tail.setNext(temp);
                temp.setPrev(tail);
                tail = temp;
            }
            head.setPrev(tail);
            tail.setNext(head);
            size++;
        }
        // returns the size of the linked list
        public int size() {
            return this.size;
        }

        // returns true if the list is empty, false otherwise
        public boolean isEmpty() {
            return this.size == 0;
        }

        // adds the specified element to the beginning of the list
        public void addFirst(E element) {
            ListNode<E> newNode = new ListNode<E>(element);

            if (size == 0) {
                this.head = newNode;
                this.tail = this.head;
            } else {
                ListNode<E> oldHead = this.head;
                this.head = newNode;
                newNode.setNext(oldHead);
                oldHead.setPrev(newNode);
            }

            this.size++;
        }

        // retrieves the first element of the list
        public E getFirst() throws NoSuchElementException {
            if (head == null) {
                throw new NoSuchElementException("Cannot get from an empty list");
            } else {
                return head.getElement();
            }
        }

        // returns true if the list contains the element, false otherwise
        public boolean contains(E element) {
            for (ListNode<E> current = head; current != null; current = current.getNext()) {
                if (current.getElement().equals(element)) {
                    return true;
                }
            }

            return false;
        }

        // removes the first element in the list
        public E removeFirst() throws NoSuchElementException {
            if (head == null) {
                throw new NoSuchElementException("Cannot remove from an empty list");
            } else {
                ListNode<E> currentHead = head;
                head = head.getNext();
                if (head == null) {
                    tail = null;
                } else {
                    head.setPrev(null);
                }
                this.size--;
                return currentHead.getElement();
            }
        }

        // Returns reference to first node.
        public ListNode<E> getHead() {
            return this.head;
        }

        // Returns reference to last node of list.
        public ListNode<E> getTail() {
            return this.tail;
        }

    }

    class ListNode<E> {
        private E element;
        private ListNode<E> next;
        private ListNode<E> prev;

        public ListNode(E newElement) {
            this.element = newElement;
            this.next = null;
            this.prev = null;
        }

        public void setElement(E newElement) {
            this.element = newElement;
        }

        public E getElement() {
            return this.element;
        }

        public void setPrev(ListNode<E> previous) {
            this.prev = previous;
        }

        public void setNext(ListNode<E> next) {
            this.next = next;
        }

        public ListNode<E> getNext() {
            return next;
        }

        public ListNode<E> getPrev() {
            return prev;
        }
    }
