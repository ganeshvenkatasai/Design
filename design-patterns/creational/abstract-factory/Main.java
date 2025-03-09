// Step 1: Create Product Interfaces
interface Car {
    void drive();
}

interface Bike {
    void ride();
}

// Step 2: Create Concrete Car Implementations
class Sedan implements Car {
    @Override
    public void drive() {
        System.out.println("Driving a Sedan");
    }
}

class SUV implements Car {
    @Override
    public void drive() {
        System.out.println("Driving an SUV");
    }
}

// Step 3: Create Concrete Bike Implementations
class SportsBike implements Bike {
    @Override
    public void ride() {
        System.out.println("Riding a Sports Bike");
    }
}

class CruiserBike implements Bike {
    @Override
    public void ride() {
        System.out.println("Riding a Cruiser Bike");
    }
}

// Step 4: Create Abstract Factory Interface
interface VehicleFactory {
    Car createCar();
    Bike createBike();
}

// Step 5: Create Concrete Factories for Different Vehicle Types
class LuxuryVehicleFactory implements VehicleFactory {
    @Override
    public Car createCar() {
        return new SUV(); // Luxury car
    }

    @Override
    public Bike createBike() {
        return new SportsBike(); // Luxury bike
    }
}

class EconomyVehicleFactory implements VehicleFactory {
    @Override
    public Car createCar() {
        return new Sedan(); // Economy car
    }

    @Override
    public Bike createBike() {
        return new CruiserBike(); // Economy bike
    }
}

// Step 6: Factory Producer (to get the correct factory)
class FactoryProducer {
    public static VehicleFactory getFactory(String type) {
        if (type.equalsIgnoreCase("Luxury")) {
            return new LuxuryVehicleFactory();
        } else if (type.equalsIgnoreCase("Economy")) {
            return new EconomyVehicleFactory();
        }
        return null;
    }
}

// Step 7: Main Class to Test Abstract Factory Pattern
public class Main {
    public static void main(String[] args) {
        // Get Luxury Vehicle Factory
        VehicleFactory luxuryFactory = FactoryProducer.getFactory("Luxury");
        Car luxuryCar = luxuryFactory.createCar();
        Bike luxuryBike = luxuryFactory.createBike();

        // Get Economy Vehicle Factory
        VehicleFactory economyFactory = FactoryProducer.getFactory("Economy");
        Car economyCar = economyFactory.createCar();
        Bike economyBike = economyFactory.createBike();

        // Use the created objects
        luxuryCar.drive();
        luxuryBike.ride();
        economyCar.drive(); 
        economyBike.ride(); 
    }
}
