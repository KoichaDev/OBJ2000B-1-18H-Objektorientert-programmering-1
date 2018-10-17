package oppgave.pkg1.shuffle.array;

import java.util.*;

/*
 * Programming exercise 13.2, s.557: Skriv ein metode som reknar ut 
 * (og returnerer) gjennomsnittet av ein ArrayList< Integer>.
*/

public class App {

    public static void main(String[] args) {
        
        ArrayList <Number> list = new ArrayList();
        
        shuffle(list);
        
        System.out.println("Shuffle: " + list);
        
     
        
       // List<Integer> list2 = convertLinkedList(list);
       LinkedList<Integer> list2 = new LinkedList(list);
       
       finneMedian(list, list2);      
       
       System.out.println("ArrayList: " + list);
       System.out.println("LinkedList: " + list2);
    }
    
    public static void shuffle(ArrayList<Number> list) {
        
        for(int i = 0; i < 10; i++) {
            list.add(i);
            Collections.shuffle(list);
        }
    }    
    
    /*
     * Prøv å skrive om metoden slik at den også kan ta imot LinkedList< Integer>.
    */
    
    public static ArrayList <Integer> convertLinkedList(ArrayList <Integer> list) {
        ArrayList <Integer> newList = list;
        return newList;
    }
    
    /*
     * Lag ein metode som finn medianen av ein tabell, der innhaldet er objekt som implementerer Comparable. 
     *  Bruk sorteringsmetode i Java-biblioteket.
    */
    
    public static ArrayList<Number> finneMedian(ArrayList <Number> list, LinkedList<Integer> list2) {
        ArrayList<Number> listArray = list;
        LinkedList<Integer> listLinked = list2;
        
        int median = 2;
        
        for(int i = 0; i < listArray.size(); i++) {
            listArray.get(i);
        }
        for(int i = 0; i < listLinked.size(); i++) {
            listLinked.get(i);
        }
       
        return listArray;
    }
}
