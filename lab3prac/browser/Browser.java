
/**
*	Name		: 
*	Matric No.	: 
*/

import java.util.*;

public class Browser {
	
    public void newtab(LinkedList<String> web,Curr curr){
        web.add(curr.getCurr()+1,"http://www.comp.nus.edu.sg");
        System.out.println(web.get(curr.getCurr()));
    }
    
    public void closetab(LinkedList<String> web, Curr curr){
        if (curr.getCurr() == web.size()-1){
            web.remove(curr.getCurr());
            curr.set(web.size()-1);
        } else {
            web.remove(curr.getCurr());
        }
        System.out.println(web.get(curr.getCurr()));
    }

    public void nexttab(LinkedList<String> web, Curr curr){
        if (curr.getCurr() == web.size()-1){
            System.out.println(web.get(curr.getCurr()));
            return;
        } else {
            curr.set(curr.getCurr()+1);
            System.out.println(web.get(curr.getCurr()));
        }
    }

    public void prevtab(LinkedList<String> web, Curr curr){
        if (curr.getCurr() == 0){
            System.out.println(web.get(curr.getCurr()));
            return;
        } else {
            curr.set(curr.getCurr()-1);
            System.out.println(web.get(curr.getCurr()));
        }
    }

    public void openhere(LinkedList<String> web, String url, Curr curr){
        web.remove(curr.getCurr());
        web.add(curr.getCurr(),url);
        System.out.println(web.get(curr.getCurr()));
    }
    
    public void opennew(LinkedList<String> web, String url, Curr curr){
        web.add(curr.getCurr()+1,url);
        System.out.println(web.get(curr.getCurr()));
    }
    
    public void run(){
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        LinkedList<String> web = new LinkedList<String>();
        web.add("http://www.comp.nus.edu.sg");
        Curr curr = new Curr(0);
        for (int i = 0; i < N; i++){
            String qtype = sc.next();
            if (qtype.equals("NEWTAB")){
                newtab(web,curr);
            }
            else if (qtype.equals("CLOSETAB")){
                closetab(web,curr);
            }
            else if (qtype.equals("NEXTTAB")){
                nexttab(web,curr);
            }
            else if (qtype.equals("PREVTAB")){
                prevtab(web,curr);
            }
            else if (qtype.equals("OPENHERE")){
                openhere(web,sc.next(),curr);
            }
            else if (qtype.equals("OPENNEW")){
                opennew(web,sc.next(),curr);
            }
        }
    }

    public static void main(String[] args) {
        Browser browser = new Browser();
        browser.run();
	}
}

class Curr{
    private int _curr;

    public Curr(int curr){
        _curr = curr;
    }
    
    public int getCurr(){
        return _curr;
    }
    
    public void set(int x){
        _curr = x;
    }
}

class ListNode<E> {
	protected E element;
	protected ListNode<E> next;

	/* constructors */
	public ListNode(E item) {
		element = item;
		next = null;
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
