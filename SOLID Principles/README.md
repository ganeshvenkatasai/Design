# SOLID Principles

## Single Responsibility Principle
```
  // A class should have only one reason to change


  // Bad Example
  class Person{
      private String fileName;

      public void saveFile() {
          System.out.println(fileName + " file is saved");
      }
  }


  // Good Example
  class User{

      private String name;

      public void setName(String name) {
          this.name = name;
      }

      public void getName() {
          System.out.println("My name is " + name);
      }
  }


  public class Main {

      public static void main(String[] args) {
          User user = new User();
          user.setName("Ganesh");
          user.getName();
      }
  }
```

## Open Closed Principle
```
  // A class should be open for extension and cloased for modification

  abstract class Payment{
      abstract void pay();
  }

  class Gpay extends Payment {
      @Override
      void pay() {
          System.out.println("Payment with Gpay");
      }
  }

  class Phonepe extends Payment {
      @Override
      void pay() {
          System.out.println("Payment with Phonepe");
      }
  }

  public class Main {

      public static void main(String[] args) {
          Payment gpay = new Gpay();
          Payment phonepe = new Phonepe();
          gpay.pay();
          phonepe.pay();
      }
  }
```

## Liskov Substitution Principle
```
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
```

## Interface Segregation Principle
```
  // Bad Example
  interface Printer {
      public void print();
      public void scan();
  }

  class BadPrinter implements Printer {
      @Override
      public void print() {
          System.out.println("Bad Printer is Printing");
      }

      @Override
      public void scan() {
          /* No Need */
          System.out.println("Bad Printer unable to scan");
      }
  }


  // Good Example

  interface Printable {
      public void print();
  }

  interface Scannable {
      public void scan();
  }

  class GoodPrinter implements Printable {
      @Override
      public void print() {
          System.out.println("Good Printer is Printing");
      }

  }


  public class Main {
      public static void main(String[] args) {
          Printer badPrinter = new BadPrinter();
          badPrinter.print();
          badPrinter.scan();

          Printable goodPrinter = new GoodPrinter();
          goodPrinter.print();
      }
  }
```

## Dependency Inversion Principle
```
  // Bad Example
  class MySql {
      public void connect() {
          System.out.println("Connected to Bad MySql Database");
      }
  }

  // Good Example
  interface Database {
      public void connect();
  }

  class MySqlDatabase implements Database {
      @Override
      public void connect(){
          System.out.println("Connected to Good MySql Database");
      }
  }

  class PostgresDatabase implements Database {
      @Override
      public void connect(){
          System.out.println("Connected to Good Postgres Database");
      }
  }


  public class Main {
      public static void main(String[] args) {
          MySql mySql = new MySql();
          mySql.connect();

          Database mySqlDatabase = new MySqlDatabase();
          Database postgresDatabase = new PostgresDatabase();
          mySqlDatabase.connect();
          postgresDatabase.connect();
      }
  }
```