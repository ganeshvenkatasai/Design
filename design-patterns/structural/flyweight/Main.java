import java.util.*;

// Flyweight: Shared tree properties (intrinsic state)
class TreeType {
    private String name;
    private String color;
    private String texture;

    public TreeType(String name, String color, String texture) {
        this.name = name;
        this.color = color;
        this.texture = texture;
    }

    public void draw(int x, int y) {
        System.out.println("Drawing tree of type: " + name + ", " + color + ", " + texture + " at (" + x + ", " + y + ")");
    }
}

// Flyweight Factory: Manages shared TreeType objects
class TreeFactory {
    private static final Map<String, TreeType> treeTypes = new HashMap<>();

    public static TreeType getTreeType(String name, String color, String texture) {
        String key = name + "_" + color + "_" + texture;
        if (!treeTypes.containsKey(key)) {
            treeTypes.put(key, new TreeType(name, color, texture));
            System.out.println("Creating new TreeType: " + key);
        }
        return treeTypes.get(key);
    }
}

// Context: Unique properties (extrinsic state)
class Tree {
    private int x, y;
    private TreeType type;

    public Tree(int x, int y, TreeType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {
        type.draw(x, y);
    }
}

// Main Class
public class Main {
    public static void main(String[] args) {
        List<Tree> forest = new ArrayList<>();

        // Creating trees with shared TreeType objects
        forest.add(new Tree(10, 20, TreeFactory.getTreeType("Oak", "Green", "Rough")));
        forest.add(new Tree(15, 25, TreeFactory.getTreeType("Oak", "Green", "Rough")));
        forest.add(new Tree(30, 50, TreeFactory.getTreeType("Pine", "Dark Green", "Smooth")));
        forest.add(new Tree(50, 80, TreeFactory.getTreeType("Oak", "Green", "Rough")));
        forest.add(new Tree(70, 90, TreeFactory.getTreeType("Pine", "Dark Green", "Smooth")));

        // Drawing all trees
        for (Tree tree : forest) {
            tree.draw();
        }
    }
}
