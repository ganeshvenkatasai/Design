
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
