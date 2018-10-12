package abstractcop_demo;

public class App {

    public static void main(String[] args) {
        Camera cam1 = new Camera();
        
        cam1.setId(5);
    
        Car car1 = new Car();
        car1.setId(4);
        car1.run();
        
        // Doing this, you can't instantiate (Create new Object) Machine
        // Machine machine1 = new Machine();
        
        
    }
    
}
