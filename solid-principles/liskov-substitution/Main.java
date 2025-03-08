// Subclasses should be substitutable in Base classes


class Rectangle {

    protected int width;
    protected int height;

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void getWidth() {
        System.out.println(this.width);
    }

    public void getHeight() {
        System.out.println(this.height);
    }

}

class Square extends Rectangle {

    @Override
    public void setWidth(int width) {
        this.width = this.height = width;
    }

    @Override
    public void setHeight(int height) {
        this.height = this.width = height;
    }
}

abstract class Animal {
    abstract void eat();
}

class Tiger extends Animal {
    @Override
    public void eat() {
        System.out.println("Tiger is eating");
    }
}

class Penguin extends Animal {
    @Override
    public void eat() {
        System.out.println("Penguin is eating");
    }

    public void fly() {
        System.out.println("Penguin is flying");
    }
}

public class Main {
    public static void main(String[] args) {

        // Bad example, as square modifies both width and height
        Rectangle rectangle = new Rectangle();
        rectangle.setWidth(10);
        rectangle.setHeight(20);
        rectangle.getWidth();
        rectangle.getHeight();

        Rectangle square = new Square();
        square.setWidth(10);
        square.setHeight(20);
        square.getWidth();
        square.getHeight();

        // We should use subclasses (inheritance) when there is a true "is-a" relationship between the base class and the subclass
        Tiger tiger = new Tiger();
        tiger.eat();
        Penguin penguin = new Penguin();
        penguin.eat();
        penguin.fly();

    }
}
