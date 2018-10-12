package exercise_11_14;

import java.util.*;

public class UnionArrayList {

    public static void main(String[] args) {
        
        ArrayList <Integer> list = new ArrayList();
        ArrayList <Integer> list2 = new ArrayList();
        
        for(int i = 1; i <= 3; i++) {
            list.add(randomNumbers(1,4));
            list2.add(randomNumbers(5, 10));
        }
        System.out.println("List: " + list);
        System.out.println("List2: " + list2);
        
        ArrayList <Integer> list3 = union(list, list2);
        
        for(int i = 0; i < list3.size(); i++) {
            list3.get(i);
        }
        System.out.println("List3: " + list3);
    }
    
    public static ArrayList<Integer> union(ArrayList<Integer> list, ArrayList<Integer> list2) {
        
        ArrayList <Integer> unionList = new ArrayList();
        
        for(int i = 0; i < list.size(); i++) {
                unionList.add(list.get(i));
        }
            
        for(int i = 0; i < list2.size(); i++) {
          unionList.add(list2.get(i));   
        }
        Collections.sort(unionList);
        return unionList;
    }
    
     public static int randomNumbers(int min, int max) {
        int range = (max - min) + 1;
        return (int) (Math.random() * range) + min;
    }
    
}
