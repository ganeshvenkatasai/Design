// State interface
interface DocumentState {
    void handleRequest();
}

// Concrete State: Draft
class DraftState implements DocumentState {
    @Override
    public void handleRequest() {
        System.out.println("Document is in Draft mode.");
    }
}

// Concrete State: Review
class ReviewState implements DocumentState {
    @Override
    public void handleRequest() {
        System.out.println("Document is in Review mode.");
    }
}

// Concrete State: Published
class PublishedState implements DocumentState {
    @Override
    public void handleRequest() {
        System.out.println("Document is Published.");
    }
}

// Context class: Document
class Document {
    private DocumentState state;

    public Document() {
        // Default state
        this.state = new DraftState();
    }

    public void setState(DocumentState state) {
        this.state = state;
    }

    public void applyState() {
        state.handleRequest();
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        Document document = new Document();

        document.applyState(); // Default state: Draft

        document.setState(new ReviewState());
        document.applyState(); // Transition to Review

        document.setState(new PublishedState());
        document.applyState(); // Transition to Published
    }
}
