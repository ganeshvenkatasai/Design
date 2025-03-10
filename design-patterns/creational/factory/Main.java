// Step 1: Create an interface for Products
interface Vehicle {
    void drive();
}

// Step 2: Create Concrete Classes implementing the Vehicle interface
class Car implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Driving a Car");
    }
}

class Bike implements Vehicle {
    @Override
    public void drive() {
        System.out.println("Riding a Bike");
    }
}

// Step 3: Create a Factory Class to generate objects of Concrete Classes
class VehicleFactory {
    public static Vehicle getVehicle(String type) {
        if (type.equalsIgnoreCase("Car")) {
            return new Car();
        } else if (type.equalsIgnoreCase("Bike")) {
            return new Bike();
        }
        return null;
    }
}

// Step 4: Main Class to Test Factory Pattern
public class Main {
    public static void main(String[] args) {
        // Using the Factory to create objects
        Vehicle car = VehicleFactory.getVehicle("Car");
        Vehicle bike = VehicleFactory.getVehicle("Bike");

        // Calling methods on created objects
        if (car != null) car.drive();
        if (bike != null) bike.drive();
    }
}
