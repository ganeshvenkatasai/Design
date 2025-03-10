import java.util.ArrayList;
import java.util.List;

// Observer interface
interface Observer {
    void update(String message);
}

// Subject (Observable) interface
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers(String message);
}

// Concrete Subject
class NewsAgency implements Subject {
    private List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.update(message);
        }
    }

    public void publishNews(String news) {
        System.out.println("News Published: " + news);
        notifyObservers(news);
    }
}

// Concrete Observer 1
class EmailSubscriber implements Observer {
    private String name;

    public EmailSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received an email update: " + message);
    }
}

// Concrete Observer 2
class MobileSubscriber implements Observer {
    private String name;

    public MobileSubscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(String message) {
        System.out.println(name + " received a mobile notification: " + message);
    }
}

// Main class to test Observer pattern
public class Main {
    public static void main(String[] args) {
        NewsAgency agency = new NewsAgency();

        Observer user1 = new EmailSubscriber("Alice");
        Observer user2 = new MobileSubscriber("Bob");

        agency.addObserver(user1);
        agency.addObserver(user2);

        agency.publishNews("Breaking News: Observer Pattern in Java!");
        agency.publishNews("Sports Update: Local Team Wins Championship!");

        agency.removeObserver(user1); // Unsubscribing Alice

        agency.publishNews("Tech News: Java 22 Released!");
    }
}
