package oppgave6;

import java.util.*;

public class SortedList {

    LinkedList<Person> list;

    public SortedList(){
        list = new LinkedList<>();
    }
    
    // Oppretter adde metode med objekt Person inne i parameteren
    public void add(Person ny){
        //Iterator kjører gjennom elementen
        Iterator<Person> itp = list.iterator();
        int posisjon = 0;
        while (itp.hasNext()){
            Person gml = itp.next();
            if (ny.compareTo(gml) < 0) { //ny skal inn FØR gml i lista?
                list.add(posisjon, ny);
                return;
            }
            posisjon++;
        }
        // Gjennom hele lista - legg inn bakerst:
        list.add(ny);
    }

    @Override
    public String toString(){
        return list.toString();
    }

}   // Slutt class SortedList
