/**
 * Name          :
 * Matric number : 
 */

import java.util.*;

public class Chemistry {

    /*//solving with postfix
      private static int postfix(String postfixed){
      Stack<Integer> solver = new Stack<Integer>();
      for (int i = 0; i < postfixed.length(); i++){
      Character charac = postfixed.charAt(i);
      if (Character.isDigit(charac)){ //if is operand
      solver.push(Character.getNumericValue(charac));            
      } else { //if is operator
      int arg1 = solver.pop();
      int arg2 = solver.pop();
      int tempresult = 0;
      tempresult = convertedSolver(arg1,arg2,charac);
      solver.push(tempresult);
      }
      }
      return solver.pop();
      }

    //convert infix to postfix
    private static String convert(String compound,Stack<Elements> table){
    String postfixed = ""; //the result
    Stack<Character> storage = new Stack<Character>(); //storage only contain
    //operators
    for (int i = 0; i < compound.length(); i++){
    Character ch = compound.charAt(i);
    if (Character.isLetter(ch) || Character.isDigit(ch)){
    // if ch is operand
    postfixed = postfixed + search(table,ch);
    } else if (ch == '('){
    storage.push(ch);
    } else if (ch == ')'){
    while (storage.peek() != '('){
    postfixed = postfixed + storage.pop();
    }
    storage.pop(); //remove the '('
    } else if(!Character.isLetter(ch) && !Character.isDigit(ch)){
    //if ch is operator, compare with top of stack. put
    //HIGHER precedence into the postfixed
    while (!storage.isEmpty() && storage.peek() != '('
    && precedence(ch) <= precedence(storage.peek())){
    postfixed = postfixed + storage.pop();
    }
    storage.push(ch);
    }
    }
    while (!storage.isEmpty()){
    //add the remaining things in storage to postfixed
    postfixed = postfixed + storage.pop();
    }
    return postfixed;
    }*/

    private static int solve(Stack<Elements> table, String compound){
        Stack<Integer> calculator = new Stack<Integer>();
        calculator.push(-1); //starting the new session
        for (int i = 0; i < compound.length(); i++){
            Character ch = compound.charAt(i);
            if (ch == '('){
                calculator.push(-1); //new session
            } else if (ch == ')'){
                int result = 0;
                int topStack = calculator.pop();
                while (topStack != -1 && !calculator.isEmpty()){
                    result  += topStack;
                    topStack = calculator.pop();
                }
                calculator.push(result);
            } else if (Character.isDigit(ch)){
                int topStack = calculator.pop();
                topStack *= Character.getNumericValue(ch);
                calculator.push(topStack);
            } else { //ch is an element
                int mass = search(table,ch);
                calculator.push(mass);
            }
        }
        if (calculator.size() > 2){
            int result = 0;
            int topStack = calculator.pop();
            while (topStack != -1 && !calculator.isEmpty()){
                result  += topStack;
                topStack = calculator.pop();
            }
            calculator.push(result);
        }
        return calculator.peek();
    }

    //to search for the mass given the element
    private static int search(Stack<Elements> table, char element){
        Stack<Elements> temp = new Stack<Elements>(); //so that we do not edit the original
        //stack
        temp.addAll(table); //stack extends vector so can use this
        //can use an iterator too
        while (!temp.isEmpty()){
            if (temp.peek().getElement() == element){
                return temp.peek().getMass();
            } else {
                temp.pop();
            }
        }
        return element; //return element if element is a number
    }

    public static void main(String[] args) {
        String compound  = ""; //to store the main compound
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        Stack<Elements> table = new Stack<Elements>(); //the periodic table to store masses
        for (int n = 0; n < N; n++){
            table.push(new Elements(sc.next().charAt(0),sc.nextInt()));
        }
        sc.nextLine(); //scanner ended with sc.nextInt()
        compound = sc.nextLine();
        int answer = solve(table,compound);
        System.out.println(answer);
    }
}

class Elements{
    private char _element;
    private int _mass = 0;

    public Elements(char element, int mass){
        _element = element;
        _mass = mass;
    }

    public char getElement(){
        return _element;    
    }

    public int getMass(){
        return _mass;
    }
}
