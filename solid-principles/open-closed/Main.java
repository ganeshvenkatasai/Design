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