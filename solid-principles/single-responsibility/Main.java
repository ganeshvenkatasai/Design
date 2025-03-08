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
