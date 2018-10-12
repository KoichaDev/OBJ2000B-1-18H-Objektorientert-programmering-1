package derekbananas_interface_abstract_demo;

public class Veichle implements Driveable {

    int numOfWheels = 2;
    double theSpeed = 0;
    
    int carStrength = 0;
    
     public Veichle(int wheels, double speed) {
        this.numOfWheels = wheels;
        this.theSpeed = speed;
    }   
    
    @Override
    public int getWheel() {
        return this.numOfWheels;
    }

    @Override
    public void setWheel(int numWheels) {
        this.numOfWheels = numWheels;
    }

    @Override
    public double getSpeed() {
        return this.theSpeed;
        
    }

    @Override
    public void setSpeed(double speed) {
        this.theSpeed = speed;
    }

   
    
    
}
