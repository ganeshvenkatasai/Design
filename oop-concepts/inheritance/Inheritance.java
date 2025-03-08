

class Parent {
    public void parentMethod() {
        System.out.println("I am Parent Method");
    }
}

class Child extends Parent {

}

public class Inheritance {
    public static void main(String[] args){
        Child child = new Child();
        child.parentMethod();
    }
}
