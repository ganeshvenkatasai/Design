// Subsystem 1: Amplifier
class Amplifier {
    void turnOn() {
        System.out.println("Amplifier is ON");
    }

    void turnOff() {
        System.out.println("Amplifier is OFF");
    }
}

// Subsystem 2: DVD Player
class DVDPlayer {
    void playMovie(String movie) {
        System.out.println("Playing movie: " + movie);
    }

    void stopMovie() {
        System.out.println("Stopping movie");
    }
}

// Subsystem 3: Projector
class Projector {
    void turnOn() {
        System.out.println("Projector is ON");
    }

    void turnOff() {
        System.out.println("Projector is OFF");
    }
}

// Subsystem 4: Lights
class Lights {
    void dim() {
        System.out.println("Lights dimmed");
    }

    void turnOn() {
        System.out.println("Lights turned ON");
    }
}

// Facade Class: Simplifies complex operations
class HomeTheaterFacade {
    private Amplifier amp;
    private DVDPlayer dvd;
    private Projector projector;
    private Lights lights;

    public HomeTheaterFacade() {
        this.amp = new Amplifier();
        this.dvd = new DVDPlayer();
        this.projector = new Projector();
        this.lights = new Lights();
    }

    public void watchMovie(String movie) {
        System.out.println("\nStarting Movie Night...");
        lights.dim();
        amp.turnOn();
        projector.turnOn();
        dvd.playMovie(movie);
    }

    public void stopMovie() {
        System.out.println("\nShutting down Home Theater...");
        dvd.stopMovie();
        projector.turnOff();
        amp.turnOff();
        lights.turnOn();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        HomeTheaterFacade homeTheater = new HomeTheaterFacade();
        
        // Start watching a movie
        homeTheater.watchMovie("Inception");

        // Stop the movie
        homeTheater.stopMovie();
    }
}
