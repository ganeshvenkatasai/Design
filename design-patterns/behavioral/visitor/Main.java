// Step 1: Define the Element interface
interface FileElement {
    void accept(FileVisitor visitor);
}

// Step 2: Concrete Elements (TextFile and ImageFile)
class TextFile implements FileElement {
    private String name;

    public TextFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

class ImageFile implements FileElement {
    private String name;

    public ImageFile(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(FileVisitor visitor) {
        visitor.visit(this);
    }
}

// Step 3: Define the Visitor interface
interface FileVisitor {
    void visit(TextFile textFile);
    void visit(ImageFile imageFile);
}

// Step 4: Concrete Visitors (Compression and Encryption)
class CompressionVisitor implements FileVisitor {
    @Override
    public void visit(TextFile textFile) {
        System.out.println("Compressing text file: " + textFile.getName());
    }

    @Override
    public void visit(ImageFile imageFile) {
        System.out.println("Compressing image file: " + imageFile.getName());
    }
}

class EncryptionVisitor implements FileVisitor {
    @Override
    public void visit(TextFile textFile) {
        System.out.println("Encrypting text file: " + textFile.getName());
    }

    @Override
    public void visit(ImageFile imageFile) {
        System.out.println("Encrypting image file: " + imageFile.getName());
    }
}

// Step 5: Main class
public class Main {
    public static void main(String[] args) {
        FileElement textFile = new TextFile("document.txt");
        FileElement imageFile = new ImageFile("photo.png");

        FileVisitor compression = new CompressionVisitor();
        FileVisitor encryption = new EncryptionVisitor();

        // Apply visitors to files
        textFile.accept(compression);
        textFile.accept(encryption);

        System.out.println("-----------------");

        imageFile.accept(compression);
        imageFile.accept(encryption);
    }
}
