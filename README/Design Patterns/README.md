
# Design Patterns

## Creational Design Patterns

### Singleton
```
  // Eager Initialization:
  // Creates the instance at class loading time (wastes memory if not used).
  // Best when object is lightweight and always needed.
  class EagerSingleton {
      private static final EagerSingleton instance = new EagerSingleton();

      private EagerSingleton() { System.out.println("Eager Singleton Created!"); }

      public static EagerSingleton getInstance() { return instance; }
  }

  // Lazy Initialization (Not Thread-Safe):
  // Instance is created only when first requested.
  // NOT thread-safe (may create multiple instances in multi-threaded environments).
  class LazySingleton {
      private static LazySingleton instance;

      private LazySingleton() { System.out.println("Lazy Singleton Created!"); }

      public static LazySingleton getInstance() {
          if (instance == null) {
              instance = new LazySingleton();
          }
          return instance;
      }
  }

  // Thread-Safe Singleton (Synchronized Method):
  // Uses synchronized to prevent multiple instance creation.
  // Slower performance due to locking on every call.
  class ThreadSafeSingleton {
      private static ThreadSafeSingleton instance;

      private ThreadSafeSingleton() { System.out.println("Thread-Safe Singleton Created!"); }

      public static synchronized ThreadSafeSingleton getInstance() {
          if (instance == null) {
              instance = new ThreadSafeSingleton();
          }
          return instance;
      }
  }

  // Double-Checked Locking (Best Practice):
  // Uses volatile and synchronized to ensure thread safety only when needed.
  // Most efficient thread-safe approach.
  class DoubleCheckedSingleton {
      private static volatile DoubleCheckedSingleton instance;

      private DoubleCheckedSingleton() { System.out.println("Double-Checked Singleton Created!"); }

      public static DoubleCheckedSingleton getInstance() {
          if (instance == null) {  // First check
              synchronized (DoubleCheckedSingleton.class) {
                  if (instance == null) {  // Second check
                      instance = new DoubleCheckedSingleton();
                  }
              }
          }
          return instance;
      }
  }

  // Bill Pugh Singleton (Static Inner Class):
  // Uses a static inner class to hold the instance.
  // Instance is created only when getInstance() is called.
  // Recommended for simplicity and performance.
  class BillPughSingleton {
      private BillPughSingleton() { System.out.println("Bill Pugh Singleton Created!"); }

      private static class SingletonHelper {
          private static final BillPughSingleton INSTANCE = new BillPughSingleton();
      }

      public static BillPughSingleton getInstance() {
          return SingletonHelper.INSTANCE;
      }
  }

  // Enum Singleton:
  // Best for preventing serialization and reflection attacks.
  // Ensures only one instance, even in a multi-threaded environment.
  enum EnumSingleton {
      INSTANCE;
      
      EnumSingleton() { System.out.println("Enum Singleton Created!"); }

      public void showMessage() {
          System.out.println("Enum Singleton Method Called!");
      }
  }

  public class Main {
      public static void main(String[] args) {
          System.out.println("Testing Different Singleton Implementations:\n");

          // Testing all Singleton Implementations
          EagerSingleton eager = EagerSingleton.getInstance();
          LazySingleton lazy = LazySingleton.getInstance();
          ThreadSafeSingleton threadSafe = ThreadSafeSingleton.getInstance();
          DoubleCheckedSingleton doubleChecked = DoubleCheckedSingleton.getInstance();
          BillPughSingleton billPugh = BillPughSingleton.getInstance();
          EnumSingleton enumInstance = EnumSingleton.INSTANCE;

          // Enum Singleton Example
          enumInstance.showMessage();
      }
  }

```

### Factory Method
```
  interface Vehicle {
      void drive();
  }

  class Car implements Vehicle {
      @Override
      public void drive() {
          System.out.println("Driving a Car");
      }
  }

  class Bike implements Vehicle {
      @Override
      public void drive() {
          System.out.println("Riding a Bike");
      }
  }

  class VehicleFactory {
      public static Vehicle getVehicle(String type) {
          if (type.equalsIgnoreCase("Car")) {
              return new Car();
          } else if (type.equalsIgnoreCase("Bike")) {
              return new Bike();
          }
          return null;
      }
  }

  public class Main {
      public static void main(String[] args) {
          // Using the Factory to create objects
          Vehicle car = VehicleFactory.getVehicle("Car");
          Vehicle bike = VehicleFactory.getVehicle("Bike");

          // Calling methods on created objects
          if (car != null) car.drive();
          if (bike != null) bike.drive();
      }
  }
```

### Abstract Factory
```
  interface Car {
      void drive();
  }

  interface Bike {
      void ride();
  }

  class Sedan implements Car {
      @Override
      public void drive() {
          System.out.println("Driving a Sedan");
      }
  }

  class SUV implements Car {
      @Override
      public void drive() {
          System.out.println("Driving an SUV");
      }
  }

  class SportsBike implements Bike {
      @Override
      public void ride() {
          System.out.println("Riding a Sports Bike");
      }
  }

  class CruiserBike implements Bike {
      @Override
      public void ride() {
          System.out.println("Riding a Cruiser Bike");
      }
  }

  interface VehicleFactory {
      Car createCar();
      Bike createBike();
  }

  class LuxuryVehicleFactory implements VehicleFactory {
      @Override
      public Car createCar() {
          return new SUV(); // Luxury car
      }

      @Override
      public Bike createBike() {
          return new SportsBike(); // Luxury bike
      }
  }

  class EconomyVehicleFactory implements VehicleFactory {
      @Override
      public Car createCar() {
          return new Sedan(); // Economy car
      }

      @Override
      public Bike createBike() {
          return new CruiserBike(); // Economy bike
      }
  }

  class FactoryProducer {
      public static VehicleFactory getFactory(String type) {
          if (type.equalsIgnoreCase("Luxury")) {
              return new LuxuryVehicleFactory();
          } else if (type.equalsIgnoreCase("Economy")) {
              return new EconomyVehicleFactory();
          }
          return null;
      }
  }

  public class Main {
      public static void main(String[] args) {
          VehicleFactory luxuryFactory = FactoryProducer.getFactory("Luxury");
          Car luxuryCar = luxuryFactory.createCar();
          Bike luxuryBike = luxuryFactory.createBike();

          VehicleFactory economyFactory = FactoryProducer.getFactory("Economy");
          Car economyCar = economyFactory.createCar();
          Bike economyBike = economyFactory.createBike();

          luxuryCar.drive();
          luxuryBike.ride();
          economyCar.drive(); 
          economyBike.ride(); 
      }
  }
```

### Builder
```
  class Car {
      private String engine;
      private int wheels;
      private boolean sunroof;

      private Car(CarBuilder builder) {
          this.engine = builder.engine;
          this.wheels = builder.wheels;
          this.sunroof = builder.sunroof;
      }

      public void showCar() {
          System.out.println("Car with Engine: " + engine + ", Wheels: " + wheels + ", Sunroof: " + sunroof);
      }

      public static class CarBuilder {
          private String engine;
          private int wheels;
          private boolean sunroof;

          public CarBuilder(String engine, int wheels) {
              this.engine = engine;
              this.wheels = wheels;
          }

          public CarBuilder setSunroof(boolean sunroof) {
              this.sunroof = sunroof;
              return this;
          }

          public Car build() {
              return new Car(this);
          }
      }
  }

  public class Main {
      public static void main(String[] args) {
          Car car1 = new Car.CarBuilder("V8", 4).setSunroof(true).build();
          Car car2 = new Car.CarBuilder("Electric", 4).setSunroof(false).build();

          car1.showCar();
          car2.showCar();
      }
  }
```

### Prototype
```
  interface Prototype {
      Prototype clone();
  }

  class Book implements Prototype {
      private String title;
      private String author;

      public Book(String title, String author) {
          this.title = title;
          this.author = author;
      }

      public void showBook() {
          System.out.println("Book: " + title + " by " + author);
      }

      @Override
      public Prototype clone() {
          return new Book(this.title, this.author);
      }
  }

  class PrototypeRegistry {
      private Map<String, Prototype> prototypes = new HashMap<>();

      public void addPrototype(String key, Prototype prototype) {
          prototypes.put(key, prototype);
      }

      public Prototype getPrototype(String key) {
          return prototypes.get(key).clone();
      }
  }

  public class Main {
      public static void main(String[] args) {
          PrototypeRegistry registry = new PrototypeRegistry();

          Book book1 = new Book("Design Patterns", "GoF");
          registry.addPrototype("DesignPatternBook", book1);

          Book clonedBook = (Book) registry.getPrototype("DesignPatternBook");
          clonedBook.showBook();
      }
  }
```

## Structural Design Patterns

### Adapter
```
  interface TypeCCharger {
      void chargeWithTypeC();
  }

  class MicroUSBCharger {
      public void chargeWithMicroUSB() {
          System.out.println("Charging with Micro-USB Charger...");
      }
  }

  class TypeCAdapter implements TypeCCharger {
      private MicroUSBCharger microUSBCharger;

      public TypeCAdapter(MicroUSBCharger microUSBCharger) {
          this.microUSBCharger = microUSBCharger;
      }

      @Override
      public void chargeWithTypeC() {
          System.out.println("Using Adapter: Converting Type-C to Micro-USB...");
          microUSBCharger.chargeWithMicroUSB();
      }
  }

  class Mobile {
      private TypeCCharger charger;

      public Mobile(TypeCCharger charger) {
          this.charger = charger;
      }

      public void charge() {
          charger.chargeWithTypeC();
      }
  }

  public class Main {
      public static void main(String[] args) {
          MicroUSBCharger microUSBCharger = new MicroUSBCharger();

          TypeCCharger adapter = new TypeCAdapter(microUSBCharger);

          Mobile mobile = new Mobile(adapter);

          mobile.charge();
      }
  }
```

### Bridge
```
  interface Device {
      void turnOn();
      void turnOff();
      void setVolume(int percent);
  }

  class TV implements Device {
      @Override
      public void turnOn() {
          System.out.println("TV is turned ON");
      }

      @Override
      public void turnOff() {
          System.out.println("TV is turned OFF");
      }

      @Override
      public void setVolume(int percent) {
          System.out.println("TV volume set to " + percent + "%");
      }
  }

  class Radio implements Device {
      @Override
      public void turnOn() {
          System.out.println("Radio is turned ON");
      }

      @Override
      public void turnOff() {
          System.out.println("Radio is turned OFF");
      }

      @Override
      public void setVolume(int percent) {
          System.out.println("Radio volume set to " + percent + "%");
      }
  }

  abstract class RemoteControl {
      protected Device device;

      public RemoteControl(Device device) {
          this.device = device;
      }

      public void turnOn() {
          device.turnOn();
      }

      public void turnOff() {
          device.turnOff();
      }

      public abstract void increaseVolume();
  }

  class BasicRemote extends RemoteControl {
      public BasicRemote(Device device) {
          super(device);
      }

      @Override
      public void increaseVolume() {
          System.out.println("Basic Remote: Increasing volume");
          device.setVolume(50); 
      }
  }

  class AdvancedRemote extends RemoteControl {
      public AdvancedRemote(Device device) {
          super(device);
      }

      @Override
      public void increaseVolume() {
          System.out.println("Advanced Remote: Increasing volume");
          device.setVolume(75);
      }

      public void mute() {
          System.out.println("Advanced Remote: Muting device");
          device.setVolume(0);
      }
  }

  public class Main {
      public static void main(String[] args) {
          Device tv = new TV();
          RemoteControl basicRemote = new BasicRemote(tv);

          System.out.println("Using Basic Remote with TV:");
          basicRemote.turnOn();
          basicRemote.increaseVolume();
          basicRemote.turnOff();

          Device radio = new Radio();
          AdvancedRemote advancedRemote = new AdvancedRemote(radio);

          System.out.println("Using Advanced Remote with Radio:");
          advancedRemote.turnOn();
          advancedRemote.increaseVolume();
          advancedRemote.mute();
          advancedRemote.turnOff();
      }
  }
```

### Composite
```
  interface FileSystemComponent {
      void showDetails();
  }

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

  public class Main {
      public static void main(String[] args) {
          File file1 = new File("Document.txt");
          File file2 = new File("Photo.jpg");
          File file3 = new File("Music.mp3");

          Folder folder1 = new Folder("MyFolder");
          folder1.addComponent(file1);
          folder1.addComponent(file2);

          Folder rootFolder = new Folder("Root");
          rootFolder.addComponent(folder1);
          rootFolder.addComponent(file3);

          rootFolder.showDetails();
      }
  }
```

### Decorator
```
  interface Coffee {
      String getDescription();
      double getCost();
  }

  class SimpleCoffee implements Coffee {
      @Override
      public String getDescription() {
          return "Simple Coffee";
      }

      @Override
      public double getCost() {
          return 5.0;
      }
  }

  abstract class CoffeeDecorator implements Coffee {
      protected Coffee coffee;

      public CoffeeDecorator(Coffee coffee) {
          this.coffee = coffee;
      }

      @Override
      public String getDescription() {
          return coffee.getDescription();
      }

      @Override
      public double getCost() {
          return coffee.getCost();
      }
  }

  class MilkDecorator extends CoffeeDecorator {
      public MilkDecorator(Coffee coffee) {
          super(coffee);
      }

      @Override
      public String getDescription() {
          return coffee.getDescription() + ", Milk";
      }

      @Override
      public double getCost() {
          return coffee.getCost() + 2.0;  // Adding milk costs $2
      }
  }

  class SugarDecorator extends CoffeeDecorator {
      public SugarDecorator(Coffee coffee) {
          super(coffee);
      }

      @Override
      public String getDescription() {
          return coffee.getDescription() + ", Sugar";
      }

      @Override
      public double getCost() {
          return coffee.getCost() + 1.0;
      }
  }

  // Step 5: Main Class
  public class Main {
      public static void main(String[] args) {
          Coffee coffee = new SimpleCoffee();
          System.out.println(coffee.getDescription() + " => $" + coffee.getCost());

          coffee = new MilkDecorator(coffee);
          System.out.println(coffee.getDescription() + " => $" + coffee.getCost());

          coffee = new SugarDecorator(coffee);
          System.out.println(coffee.getDescription() + " => $" + coffee.getCost());
      }
  }
```

### Facade
```
  class Amplifier {
      void turnOn() {
          System.out.println("Amplifier is ON");
      }

      void turnOff() {
          System.out.println("Amplifier is OFF");
      }
  }

  class DVDPlayer {
      void playMovie(String movie) {
          System.out.println("Playing movie: " + movie);
      }

      void stopMovie() {
          System.out.println("Stopping movie");
      }
  }

  class Projector {
      void turnOn() {
          System.out.println("Projector is ON");
      }

      void turnOff() {
          System.out.println("Projector is OFF");
      }
  }

  class Lights {
      void dim() {
          System.out.println("Lights dimmed");
      }

      void turnOn() {
          System.out.println("Lights turned ON");
      }
  }

  class HomeTheaterFacade {
      private Amplifier amp;
      private DVDPlayer dvd;
      private Projector projector;
      private Lights lights;

      public HomeTheaterFacade() {
          this.amp = new Amplifier();
          this.dvd = new DVDPlayer();
          this.projector = new Projector();
          this.lights = new Lights();
      }

      public void watchMovie(String movie) {
          System.out.println("\nStarting Movie Night...");
          lights.dim();
          amp.turnOn();
          projector.turnOn();
          dvd.playMovie(movie);
      }

      public void stopMovie() {
          System.out.println("\nShutting down Home Theater...");
          dvd.stopMovie();
          projector.turnOff();
          amp.turnOff();
          lights.turnOn();
      }
  }

  public class Main {
      public static void main(String[] args) {
          HomeTheaterFacade homeTheater = new HomeTheaterFacade();
          
          homeTheater.watchMovie("Inception");

          homeTheater.stopMovie();
      }
  }
```

### Flyweight
```
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

  public class Main {
      public static void main(String[] args) {
          List<Tree> forest = new ArrayList<>();

          forest.add(new Tree(10, 20, TreeFactory.getTreeType("Oak", "Green", "Rough")));
          forest.add(new Tree(15, 25, TreeFactory.getTreeType("Oak", "Green", "Rough")));
          forest.add(new Tree(30, 50, TreeFactory.getTreeType("Pine", "Dark Green", "Smooth")));
          forest.add(new Tree(50, 80, TreeFactory.getTreeType("Oak", "Green", "Rough")));
          forest.add(new Tree(70, 90, TreeFactory.getTreeType("Pine", "Dark Green", "Smooth")));

          for (Tree tree : forest) {
              tree.draw();
          }
      }
  }
```

### Proxy
```
  interface Image {
      void display();
  }

  class RealImage implements Image {
      private String filename;

      public RealImage(String filename) {
          this.filename = filename;
          loadImageFromDisk();
      }

      private void loadImageFromDisk() {
          System.out.println("Loading image: " + filename);
      }

      @Override
      public void display() {
          System.out.println("Displaying image: " + filename);
      }
  }

  class ProxyImage implements Image {
      private String filename;
      private RealImage realImage;

      public ProxyImage(String filename) {
          this.filename = filename;
      }

      @Override
      public void display() {
          if (realImage == null) {
              realImage = new RealImage(filename);
          }
          realImage.display();
      }
  }

  public class Main {
      public static void main(String[] args) {
          Image image1 = new ProxyImage("photo1.jpg");
          Image image2 = new ProxyImage("photo2.jpg");
          image1.display();
          image1.display();
          image2.display();
      }
  }
```

## Behavioral Design Patterns

### Chain of Responsibility
```
  abstract class LeaveHandler {
      protected LeaveHandler nextHandler;

      abstract void handleRequest(int days);

      void setNextHandler(LeaveHandler nextHandler) {
          this.nextHandler = nextHandler;
      }
  }

  class TeamLead extends LeaveHandler{
      @Override
      void handleRequest(int days) {
          if (days < 3) {
              System.out.println("Team Lead approved " + days + " days Leave");
          } else {
              nextHandler.handleRequest(days);
          }
      }
  }

  class Manager extends LeaveHandler{
      @Override
      void handleRequest(int days) {
          if (days < 8) {
              System.out.println("Manager approved " + days + " days Leave");
          } else {
              nextHandler.handleRequest(days);
          }
      }
  }

  class CEO extends LeaveHandler{
      @Override
      void handleRequest(int days) {
          System.out.println("CEO approved " + days + " days Leave");
      }
  }


  public class Main {
      public static void main(String[] args) {
          LeaveHandler teamLead = new TeamLead();
          LeaveHandler manager = new Manager();
          LeaveHandler ceo = new CEO();

          teamLead.setNextHandler(manager);
          manager.setNextHandler(ceo);

          teamLead.handleRequest(2);
          teamLead.handleRequest(7);
          teamLead.handleRequest(30);

      }
  }
```

### Command
```
  class TV {

      private int volume;

      public void turnOn() {
          System.out.println("TV is ON");
      }

      public void turnOff() {
          System.out.println("TV is OFF");
      }

      public void volumeUp() {
          volume++;
          System.out.println("Volume is increased to : " + volume);
      }

      public void volumeDown() {
          volume--;
          System.out.println("Volume is decreased to : " + volume);
      }
  }


  interface Command  {
      void execute();
  }


  class TurnOnCommand implements Command {

      private TV tv;

      public TurnOnCommand(TV tv) {
          this.tv = tv;
      }

      @Override
      public void execute() {
          tv.turnOn();
      }
  }

  class TurnOffCommand implements Command {

      private TV tv;

      public TurnOffCommand(TV tv) {
          this.tv = tv;
      }

      @Override
      public void execute() {
          tv.turnOff();
      }
  }

  class VolumeUpCommand implements Command {

      private TV tv;

      public VolumeUpCommand(TV tv) {
          this.tv = tv;
      }

      @Override
      public void execute() {
          tv.volumeUp();
      }
  }

  class VolumeDownCommand implements Command {

      private TV tv;

      public VolumeDownCommand(TV tv) {
          this.tv = tv;
      }

      @Override
      public void execute() {
          tv.volumeDown();
      }
  }


  class RemoteControl {

      private Command command;

      public void setCommand(Command command) {
          this.command = command;
      }

      public void pressButton() {
          command.execute();
      }
  }



  public class Main {
      public static void main(String[] args) {
          TV tv = new TV();
          Command turnOn = new TurnOnCommand(tv);
          Command turnOff = new TurnOffCommand(tv);
          Command volumeUpCommand = new VolumeUpCommand(tv);
          Command volumeDownCommand = new VolumeDownCommand(tv);
          RemoteControl remoteControl = new RemoteControl();

          remoteControl.setCommand(turnOn);
          remoteControl.pressButton();

          remoteControl.setCommand(volumeUpCommand);
          remoteControl.pressButton();

          remoteControl.setCommand(volumeDownCommand);
          remoteControl.pressButton();

          remoteControl.setCommand(turnOff);
          remoteControl.pressButton();

      }
  }
```

### Interpreter
```
  interface Expression {
      int interpret();
  }

  class NumberExpression implements Expression {

      private int number;

      public NumberExpression(int number) {
          this.number = number;
      }

      @Override
      public int interpret() {
          return number;
      }
  }

  class AdditionExpression implements Expression {
      private Expression leftExpression;
      private Expression rightExpression;

      public AdditionExpression(Expression leftExpression, Expression rightExpression) {
          this.leftExpression = leftExpression;
          this.rightExpression = rightExpression;
      }

      @Override
      public int interpret(){
          return leftExpression.interpret() + rightExpression.interpret();
      }
  }

  class SubtractionExpression implements Expression {
      private Expression leftExpression;
      private Expression rightExpression;

      public SubtractionExpression(Expression leftExpression, Expression rightExpression) {
          this.leftExpression = leftExpression;
          this.rightExpression = rightExpression;
      }

      @Override
      public int interpret(){
          return leftExpression.interpret() - rightExpression.interpret();
      }
  }

  class MultiplicatonExpression implements Expression {
      private Expression leftExpression;
      private Expression rightExpression;

      public MultiplicatonExpression(Expression leftExpression, Expression rightExpression) {
          this.leftExpression = leftExpression;
          this.rightExpression = rightExpression;
      }

      @Override
      public int interpret(){
          return leftExpression.interpret() * rightExpression.interpret();
      }
  }

  public class Main {
      public static void main(String[] args) {
          Expression additionExpression = new AdditionExpression(new NumberExpression(10), new NumberExpression(20));
          Expression subtractionExpression = new SubtractionExpression(additionExpression, new NumberExpression(5));
          Expression multiplicationExpression= new MultiplicatonExpression(subtractionExpression, new NumberExpression(2));
          int result = multiplicationExpression.interpret();
          System.out.println(result);
      }
  }
```

### Iterator
```
  class Book {
      private String name;

      public Book(String name) {
          this.name = name;
      }

      public void setBook(String name) {
          this.name = name;
      }

      public String getBook() {
          return this.name;
      }
  }

  interface Iterator {
      boolean hasNext();
      Object next();
  }

  class BookIterator implements Iterator {

      private int count;
      private int index;
      private Book[] books;

      public BookIterator(Book[] books, int count){
          this.books = books;
          this.count = count;
          this.index = 0;
      }

      @Override
      public boolean hasNext() {
          if (index < count) {
              return true;
          } else {
              return false;
          }
      }

      @Override
      public Object next(){
          return books[index++];
      }
  }

  interface BookCollection {
      Iterator createIterator();
  }

  class Library implements BookCollection{

      private int count;
      private int capacity;
      private Book[] books;

      public Library(int capacity) {
          count = 0;
          this.capacity = capacity;
          books = new Book[capacity];
      }

      public void addBook(Book book) {
          if (count < capacity) {
              books[count] = book;
              count++;
          } else {
              System.out.println("Library is fully occupied");
          }
      }

      @Override
      public Iterator createIterator() {
          return new BookIterator(books, count);
      }

  }

  public class Main {
      public static void main(String[] args) {
          Library library = new Library(5);
          library.addBook(new Book("Fountain Head"));
          library.addBook(new Book("Atlas Shrugged"));
          library.addBook(new Book("Naa Istam"));
          Iterator iterator = library.createIterator();
          while (iterator.hasNext()) {
              Book book = (Book) iterator.next();
              System.out.println(book.getBook());
          } 
      }
  }
```

### Mediator
```
  interface ChatMediator {
      void sendMessage(String message, User sender);
      void addUser(User user);
  }

  class ChatRoom implements ChatMediator {

      List<User> users = new ArrayList<>();

      @Override
      public void sendMessage(String message, User sender) {
          for (User user : users) {
              if (user != sender) {
                  user.receive(message);
              }
          }
      }

      @Override
      public void addUser(User user) {
          users.add(user);
      }
  }

  abstract class User {

      protected ChatMediator chatMediator;
      protected String name;

      public User(ChatMediator chatMediator, String name) {
          this.chatMediator = chatMediator;
          this.name = name;
      }

      public abstract void send(String message);
      public abstract void receive(String message);

  }

  class ChatUser extends User {
      public ChatUser(ChatMediator chatMediator, String name) {
          super(chatMediator, name);
      }

      @Override
      public void send(String message) {
          System.out.println(name + " send : " + message);
          chatMediator.sendMessage(message, this);
      }

      @Override
      public void receive(String message) {
          System.out.println(name + " receives : " + message);
      }

  }

  public class Main {
      public static void main(String[] args) {
          ChatMediator chatroom = new ChatRoom();
          User user1 = new ChatUser(chatroom,"Prabhas");
          User user2 = new ChatUser(chatroom,"Mahesh");
          User user3 = new ChatUser(chatroom,"Allu Arjun");

          chatroom.addUser(user1);
          chatroom.addUser(user2);
          chatroom.addUser(user3);

          user1.send("Hello Everyone");

      }
  }
```

### Memento
```
interface IMemento {}

class Memento implements IMemento {
    private final String text;

    public Memento(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }
}

interface Originator {
    IMemento save();
    void restore(IMemento memento);
}

class TextEditor implements Originator {
    private String text;

    public TextEditor() {
        text = "";
    }

    public void type(String words) {
        text += words;
    }

    public String getText() {
        return text;
    }

    @Override
    public IMemento save() {
        return new Memento(text);
    }

    @Override
    public void restore(IMemento memento) {
        if (memento instanceof Memento) {
            this.text = ((Memento) memento).getText();
        }
    }
}

class Caretaker {
    private Stack<IMemento> history = new Stack<>();

    public void save(IMemento memento) {
        if (memento != null) {
            history.push(memento);
        }
    }

    public IMemento undo() {
        if (!history.isEmpty()) {
            history.pop(); // Remove the current state (last saved)
        }
        return history.isEmpty() ? null : history.peek();
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor();
        Caretaker caretaker = new Caretaker();

        editor.type("Hi ");
        caretaker.save(editor.save());

        editor.type("Ganesh");
        caretaker.save(editor.save());
        System.out.println("Current Text: " + editor.getText());

        IMemento previousState = caretaker.undo();
        if (previousState != null) {
            editor.restore(previousState);
        }
        System.out.println("After Undo: " + editor.getText());

        editor.type("Sai");
        caretaker.save(editor.save());

        System.out.println("Final Text: " + editor.getText());
    }
}

```
### Observer
```
  interface Observer {
      void update(String message);
  }

  interface Subject {
      void addObserver(Observer observer);
      void removeObserver(Observer observer);
      void notifyObservers(String message);
  }

  class NewsAgency implements Subject {
      private List<Observer> observers = new ArrayList<>();

      @Override
      public void addObserver(Observer observer) {
          observers.add(observer);
      }

      @Override
      public void removeObserver(Observer observer) {
          observers.remove(observer);
      }

      @Override
      public void notifyObservers(String message) {
          for (Observer observer : observers) {
              observer.update(message);
          }
      }

      public void publishNews(String news) {
          System.out.println("News Published: " + news);
          notifyObservers(news);
      }
  }

  class EmailSubscriber implements Observer {
      private String name;

      public EmailSubscriber(String name) {
          this.name = name;
      }

      @Override
      public void update(String message) {
          System.out.println(name + " received an email update: " + message);
      }
  }

  class MobileSubscriber implements Observer {
      private String name;

      public MobileSubscriber(String name) {
          this.name = name;
      }

      @Override
      public void update(String message) {
          System.out.println(name + " received a mobile notification: " + message);
      }
  }

  public class Main {
      public static void main(String[] args) {
          NewsAgency agency = new NewsAgency();

          Observer user1 = new EmailSubscriber("Alice");
          Observer user2 = new MobileSubscriber("Bob");

          agency.addObserver(user1);
          agency.addObserver(user2);

          agency.publishNews("Breaking News: Observer Pattern in Java!");
          agency.publishNews("Sports Update: Local Team Wins Championship!");

          agency.removeObserver(user1); // Unsubscribing Alice

          agency.publishNews("Tech News: Java 22 Released!");
      }
  }
```

### State
```
  interface DocumentState {
      void handleRequest();
  }

  class DraftState implements DocumentState {
      @Override
      public void handleRequest() {
          System.out.println("Document is in Draft mode.");
      }
  }

  class ReviewState implements DocumentState {
      @Override
      public void handleRequest() {
          System.out.println("Document is in Review mode.");
      }
  }

  class PublishedState implements DocumentState {
      @Override
      public void handleRequest() {
          System.out.println("Document is Published.");
      }
  }

  class Document {
      private DocumentState state;

      public Document() {
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

          document.applyState();

          document.setState(new ReviewState());
          document.applyState();

          document.setState(new PublishedState());
          document.applyState();
      }
  }
```

### Strategy
```
  interface PaymentStrategy {
      void pay(int amount);
  }

  class CreditCardPayment implements PaymentStrategy {
      private String cardNumber;

      public CreditCardPayment(String cardNumber) {
          this.cardNumber = cardNumber;
      }

      @Override
      public void pay(int amount) {
          System.out.println("Paid $" + amount + " using Credit Card (Card No: " + cardNumber + ").");
      }
  }

  class PayPalPayment implements PaymentStrategy {
      private String email;

      public PayPalPayment(String email) {
          this.email = email;
      }

      @Override
      public void pay(int amount) {
          System.out.println("Paid $" + amount + " using PayPal (Email: " + email + ").");
      }
  }

  class BitcoinPayment implements PaymentStrategy {
      private String walletAddress;

      public BitcoinPayment(String walletAddress) {
          this.walletAddress = walletAddress;
      }

      @Override
      public void pay(int amount) {
          System.out.println("Paid $" + amount + " using Bitcoin (Wallet: " + walletAddress + ").");
      }
  }

  class ShoppingCart {
      private PaymentStrategy paymentStrategy;

      public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
          this.paymentStrategy = paymentStrategy;
      }

      public void checkout(int amount) {
          if (paymentStrategy == null) {
              System.out.println("No payment method selected!");
          } else {
              paymentStrategy.pay(amount);
          }
      }
  }

  public class Main {
      public static void main(String[] args) {
          ShoppingCart cart = new ShoppingCart();

          cart.setPaymentStrategy(new CreditCardPayment("1234-5678-9876-5432"));
          cart.checkout(100);

          cart.setPaymentStrategy(new PayPalPayment("user@example.com"));
          cart.checkout(50);

          cart.setPaymentStrategy(new BitcoinPayment("1A2b3C4d5E6f7G"));
          cart.checkout(200);
      }
  }
```

### Template Method
```
  abstract class DataProcessor {
      
      public final void process() {
          readData();
          processData();
          saveData();
      }

      protected abstract void readData();

      private void processData() {
          System.out.println("Processing data...");
      }

      protected abstract void saveData();
  }

  class CsvDataProcessor extends DataProcessor {
      @Override
      protected void readData() {
          System.out.println("Reading CSV data...");
      }

      @Override
      protected void saveData() {
          System.out.println("Saving processed CSV data...");
      }
  }

  class JsonDataProcessor extends DataProcessor {
      @Override
      protected void readData() {
          System.out.println("Reading JSON data...");
      }

      @Override
      protected void saveData() {
          System.out.println("Saving processed JSON data...");
      }
  }

  public class Main {
      public static void main(String[] args) {
          // Process CSV Data
          DataProcessor csvProcessor = new CsvDataProcessor();
          csvProcessor.process();

          System.out.println("----------------------");

          // Process JSON Data
          DataProcessor jsonProcessor = new JsonDataProcessor();
          jsonProcessor.process();
      }
  }
```
### Visitor
```
  interface FileElement {
      void accept(FileVisitor visitor);
  }

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

  interface FileVisitor {
      void visit(TextFile textFile);
      void visit(ImageFile imageFile);
  }

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

  public class Main {
      public static void main(String[] args) {
          FileElement textFile = new TextFile("document.txt");
          FileElement imageFile = new ImageFile("photo.png");

          FileVisitor compression = new CompressionVisitor();
          FileVisitor encryption = new EncryptionVisitor();

          textFile.accept(compression);
          textFile.accept(encryption);

          imageFile.accept(compression);
          imageFile.accept(encryption);
      }
  }
```

