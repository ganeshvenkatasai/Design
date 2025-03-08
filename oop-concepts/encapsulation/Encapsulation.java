

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