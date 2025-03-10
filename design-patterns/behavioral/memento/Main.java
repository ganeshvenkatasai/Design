import java.util.Stack;

// Memento Interface to hide details
interface IMemento {}

// Concrete Memento storing text
class Memento implements IMemento {
    private final String text;

    public Memento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

// Originator Interface for saving/restoring state
interface Originator {
    IMemento save();
    void restore(IMemento memento);
}

// Concrete Originator: TextEditor
class TextEditor implements Originator {
    private String text;

    public TextEditor() {
        text = "";
    }

    public void type(String words) {
        text += words;
    }

    public String getText() {
        return text;
    }

    @Override
    public IMemento save() {
        return new Memento(text);
    }

    @Override
    public void restore(IMemento memento) {
        if (memento instanceof Memento) {
            this.text = ((Memento) memento).getText();
        }
    }
}

// Caretaker stores Memento history
class Caretaker {
    private Stack<IMemento> history = new Stack<>();

    public void save(IMemento memento) {
        if (memento != null) {
            history.push(memento);
        }
    }

    public IMemento undo() {
        if (!history.isEmpty()) {
            history.pop(); // Remove the current state (last saved)
        }
        return history.isEmpty() ? null : history.peek(); // Return the previous state
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        editor.type("Hi ");
        caretaker.save(editor.save()); // Save state 1: "Hi "

        editor.type("Ganesh");
        caretaker.save(editor.save()); // Save state 2: "Hi Ganesh"

        System.out.println("Current Text: " + editor.getText()); // Output: Hi Ganesh

        IMemento previousState = caretaker.undo();
        if (previousState != null) {
            editor.restore(previousState);
        }
        System.out.println("After Undo: " + editor.getText()); // Output: Hi 

        editor.type("Sai");
        caretaker.save(editor.save()); // Save state 3: "Hi Sai"

        System.out.println("Final Text: " + editor.getText()); // Output: Hi Sai
    }
}
