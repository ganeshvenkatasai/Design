# OOPS

## Abstraction
```
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
```

## Encapsulation
```
  public class Encapsulation{

      private String name;
      public static void main(String[] args){
          Encapsulation encapsulation = new Encapsulation();
          encapsulation.setName("Ganesh");
          String myName = encapsulation.getName();
          System.out.println(myName);
      }

      public void setName(String name) {
          this.name = name;
      }

      public String getName(){
          return this.name;
      }
  }
```

## Inheritance
```
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
```

## Polymorphism 
```


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
        compileTimePolymorphism.add(1 ,2);
        compileTimePolymorphism.add(1 ,2, 3);
    }
}
```