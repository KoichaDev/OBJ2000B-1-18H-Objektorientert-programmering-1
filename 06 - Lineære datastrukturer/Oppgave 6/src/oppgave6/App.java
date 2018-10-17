/*
 * Lag en klasse SortedList med en LinkedList som instansvariabel. Den skal holde objekter sortert. 
 * Objektene må da kunne sammenlignes, de må implementere Comparable. 
 * Mer om det på forelesning.
 */
package oppgave6;

public class App {

    public static void main(String[] args) {
        SortedList liste = new SortedList();
        
        liste.add(new Person("Ole2"));
        liste.add(new Person("Per"));
        liste.add(new Person("Kåre"));
        liste.add(new Person("Jens"));
        liste.add(new Person("Petter"));
        liste.add(new Person("Kristian"));
        liste.add(new Person("Frode"));
        liste.add(new Person("Ole1"));
        
        System.out.println(liste);

    }

}
