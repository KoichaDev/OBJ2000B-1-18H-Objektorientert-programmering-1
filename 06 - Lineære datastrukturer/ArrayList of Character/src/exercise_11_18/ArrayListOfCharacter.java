package exercise_11_18;

import java.util.*;

public class ArrayListOfCharacter {

    public static void main(String[] args) {
        
        ArrayList<Integer> smallInt = new ArrayList();
        ArrayList<Integer> bigInt = new ArrayList();
        
        ArrayList<Character> smallChar = new ArrayList();
        ArrayList<Character> bigChar = new ArrayList();
        
        
        int small = 0;
        int big = 0;
        
        // From a to z
        for(small = 97; small < 123; small++) {
            smallInt.add(small);
        }
        
        // From A to Z
        for (big = 65; big < 91; big++) {
            bigInt.add(big);
        }
        
        // converting from smallInt to characters 
        for(int characters : smallInt) {
            smallChar.add(Character.valueOf((char) characters)); 
        }
        
        // converting from bigInt to characters 
        for(int characters : bigInt) {
            bigChar.add(Character.valueOf((char) characters));
        }
        
        System.out.println("Numbers: " + smallInt);
        System.out.println("Small. Char: " + smallChar);
        System.out.println("Big Char: " + bigChar);
    }
    
}
