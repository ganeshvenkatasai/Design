
abstract class Animal {
    abstract void sound();
    public void sleep() {
        System.out.println("Animal is sleeping");
    }
}

class Dog extends Animal{
    @Override
    void sound() {
        System.out.println("Bow Bow");
    }

    @Override
    public void sleep() {
        System.out.println("Dog is Sleeping");
    }

}

class Cat extends Animal{
    @Override
    void sound() {
        System.out.println("Meow Meow");
    }
}



public class Abstraction {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();
        dog.sound();
        dog.sleep();
        cat.sound();
        cat.sleep();
    }    
}
