import java.util.ArrayList;
import java.util.List;

// Step 1: Component Interface (Common for Files and Folders)
interface FileSystemComponent {
    void showDetails();
}

// Step 2: Leaf Class (File)
class File implements FileSystemComponent {
    private String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }
}

// Step 3: Composite Class (Folder)
class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }
}

// Step 4: Main Class
public class Main {
    public static void main(String[] args) {
        // Creating files
        File file1 = new File("Document.txt");
        File file2 = new File("Photo.jpg");
        File file3 = new File("Music.mp3");

        // Creating a folder and adding files to it
        Folder folder1 = new Folder("MyFolder");
        folder1.addComponent(file1);
        folder1.addComponent(file2);

        // Creating another folder and adding a file and subfolder
        Folder rootFolder = new Folder("Root");
        rootFolder.addComponent(folder1);
        rootFolder.addComponent(file3);

        // Displaying the structure
        rootFolder.showDetails();
    }
}
