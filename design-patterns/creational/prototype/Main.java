import java.util.HashMap;
import java.util.Map;

// Prototype Interface
interface Prototype {
    Prototype clone();
}

// Concrete Class implementing Prototype
class Book implements Prototype {
    private String title;
    private String author;

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
    }

    public void showBook() {
        System.out.println("Book: " + title + " by " + author);
    }

    @Override
    public Prototype clone() {
        return new Book(this.title, this.author);
    }
}

// Prototype Registry (for caching objects)
class PrototypeRegistry {
    private Map<String, Prototype> prototypes = new HashMap<>();

    public void addPrototype(String key, Prototype prototype) {
        prototypes.put(key, prototype);
    }

    public Prototype getPrototype(String key) {
        return prototypes.get(key).clone();
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        PrototypeRegistry registry = new PrototypeRegistry();

        // Create a Book prototype and register it
        Book book1 = new Book("Design Patterns", "GoF");
        registry.addPrototype("DesignPatternBook", book1);

        // Cloning the prototype
        Book clonedBook = (Book) registry.getPrototype("DesignPatternBook");
        clonedBook.showBook();  // Output: Book: Design Patterns by GoF
    }
}
