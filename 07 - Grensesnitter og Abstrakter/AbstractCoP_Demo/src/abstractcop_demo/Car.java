package abstractcop_demo;

public class Car extends Machine {

    @Override
    public void start() {
        System.out.println("Starting Car.");
    }

    @Override
    public void doStuff() {
        System.out.println("Do Stuff in a car");
    }

    @Override
    public void shutDown() {
        System.out.println("Shutdown Car.");
    }
    
    
    
}
