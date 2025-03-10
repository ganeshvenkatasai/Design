// Step 1: Create a Product Class
class Car {
    private String engine;
    private int wheels;
    private boolean sunroof;

    // Private Constructor to enforce object creation through Builder
    private Car(CarBuilder builder) {
        this.engine = builder.engine;
        this.wheels = builder.wheels;
        this.sunroof = builder.sunroof;
    }

    public void showCar() {
        System.out.println("Car with Engine: " + engine + ", Wheels: " + wheels + ", Sunroof: " + sunroof);
    }

    // Step 2: Create the Static Builder Class
    public static class CarBuilder {
        private String engine;
        private int wheels;
        private boolean sunroof;

        public CarBuilder(String engine, int wheels) {
            this.engine = engine;
            this.wheels = wheels;
        }

        public CarBuilder setSunroof(boolean sunroof) {
            this.sunroof = sunroof;
            return this;
        }

        public Car build() {
            return new Car(this);
        }
    }
}

// Step 3: Main Class to Test Builder Pattern
public class Main {
    public static void main(String[] args) {
        // Using Builder to create Car objects
        Car car1 = new Car.CarBuilder("V8", 4).setSunroof(true).build();
        Car car2 = new Car.CarBuilder("Electric", 4).setSunroof(false).build();

        car1.showCar();  // Output: Car with Engine: V8, Wheels: 4, Sunroof: true
        car2.showCar();  // Output: Car with Engine: Electric, Wheels: 4, Sunroof: false
    }
}
