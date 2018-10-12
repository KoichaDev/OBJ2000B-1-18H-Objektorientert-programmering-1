package abstractcop_demo;

// Machine is the class Hierachy

public abstract class Machine {
    
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    // Using the abstract, you can supply a lot inside a method
    
    public abstract void start();    
    public abstract void doStuff();
    public abstract void shutDown();
    
    // Adding the abstract method inside the run() method as a supply
    public void run() {
        start();
        doStuff();
        shutDown();
    }
}
