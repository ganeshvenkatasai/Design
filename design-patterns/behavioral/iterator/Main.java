

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
