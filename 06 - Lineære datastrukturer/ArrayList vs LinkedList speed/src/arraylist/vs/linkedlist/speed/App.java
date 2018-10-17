package arraylist.vs.linkedlist.speed;

/*
* Bytt ut ArrayList med LinkedList i disse programmene. 
* Fungerer alt like bra? Raskere? langsommere? Hvordan måle det?
*/

import java.util.*;

// If you want to add/remove an item anywhere on the list in the list
// Like the beginning on the middle. Use LinkedList

public class App {

    public static void main(String[] args) {
        
        /*
        * ArrayList manage arrays internally.
        * [0][1][2][3][4][5] etc. = Transversing the list is very fast
        */
        
        List <Integer> arrayList = new ArrayList();
        
        /*
        * LinkedList consist of elements where each elements has a referene to
        * the previous an the next elements
        * [0] -> [1] -> [2] -> [3] etc... 
        *     <-     <-     <-
        */
        
        List  <Integer> linkedList = new LinkedList();
        
        doTimings("ArrayList: ", arrayList);
        doTimings("LinkedList", linkedList);
        
    }
    
    private static void doTimings(String type, List <Integer> list) {
        // 1E5 = 10 exponential 5 = 100 000
         for(int i = 0; i < 1E5; i++) {
             list.add(i);
         }
         
         // We want to time how long it's going to take to add items
         long start = System.currentTimeMillis();
         
         
         
         /*
         // (Optional Test 1)  Add Items at the end of the list         
         for(int i = 0; i < 1E5; i++) {
            // This is slower than the ArrayList
             list.add(i);
         }*/
         
         
         // (Optional Test2) Add items elsewhere in list
         for(int i = 0; i < 1E5; i++) {
             // This faster than the ArrayList
             list.add(0, i); // Adding items in the beginning of the list
         }
         
         long end = System.currentTimeMillis();
         
         System.out.println("Time Taken: " + (end - start) + " ms for " + type);
    }
    
}
