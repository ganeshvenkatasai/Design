

class Parent {
    public void run() {
        System.out.println("Parent");
    }
}

class RunTimePolymorphism extends Parent{
    @Override
    public void run() {
        System.out.println("RunTimePolymorphism");
    }
}

class CompileTimePolymorphism {
    public void add(int x, int y) {
        System.out.println("Sum : " + (x + y));
    }

    public void add(int x, int y, int z) {
        System.out.println("Sum : " + (x + y + z));
    }

}

public class Polymorphism {
    public static void main(String[] args){
        Parent runTimePolymorphism = new Parent();
        runTimePolymorphism.run();

        runTimePolymorphism = new RunTimePolymorphism();
        runTimePolymorphism.run();

        CompileTimePolymorphism compileTimePolymorphism = new CompileTimePolymorphism();
        compileTimePolymorphism.add(1,2, 0);
        compileTimePolymorphism.add(1,2, 3);
    }
}