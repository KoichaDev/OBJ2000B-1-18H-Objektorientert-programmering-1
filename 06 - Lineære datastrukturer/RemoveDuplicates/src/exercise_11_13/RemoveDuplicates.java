package exercise_11_13;

import java.util.*;

public class RemoveDuplicates {

    public static void main(String[] args) {
        
        
        //Creating an arraylist
        ArrayList <Integer> list = new ArrayList();
        
        // Generating list from 0 to 10 to the arrayList
        for(int i = 0; i < 10; i++) {
            list.add(randomNumbers(1, 10));
        }
        System.out.println("Numbers: " + list);
        
        // removing the elements of the duplicated ArrayList
        removeDuplicates(list);
        
        for(int i = 0; i < list.size(); i++) {
            list.get(i);
        }
        System.out.println("Removed: " + list);
        
        
        
    }

    public static void removeDuplicates(ArrayList<Integer> list) {
        for(int i = 0; i < list.size(); i++) {
            for(int j = i + 1; j < list.size(); j++) {
                if(list.get(i) == list.get(j)) {
                    list.remove(j);
                }
            }
        }
    }

    public static int randomNumbers(int min, int max) {
        int range = (max - min) + 1;

        return (int) (Math.random() * range) + min;
    }

}
