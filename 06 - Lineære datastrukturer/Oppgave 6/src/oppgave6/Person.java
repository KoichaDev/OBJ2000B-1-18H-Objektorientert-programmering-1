package oppgave6;

public class Person implements Comparable<Person>{

    String navn;

    public Person(String navn){
        this.navn = navn;
    }

    @Override
    public String toString(){
        return navn;
    }

    @Override
    public int compareTo(Person p){
        return navn.compareTo(p.navn);
        // Eller prøv: return navn.length() - p.navn.length();
    }

}   // Slutt class Person
