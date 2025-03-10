
// 1️⃣ Eager Initialization:
// Creates the instance at class loading time (wastes memory if not used).
// Best when object is lightweight and always needed.
class EagerSingleton {
    private static final EagerSingleton instance = new EagerSingleton();

    private EagerSingleton() { System.out.println("Eager Singleton Created!"); }

    public static EagerSingleton getInstance() { return instance; }
}

// 2️⃣ Lazy Initialization (Not Thread-Safe):
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

// 3️⃣ Thread-Safe Singleton (Synchronized Method):
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

// 4️⃣ Double-Checked Locking (Best Practice):
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

// 5️⃣ Bill Pugh Singleton (Static Inner Class):
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

// 6️⃣ Enum Singleton:
// Best for preventing serialization and reflection attacks.
// Ensures only one instance, even in a multi-threaded environment.
enum EnumSingleton {
    INSTANCE;
    
    EnumSingleton() { System.out.println("Enum Singleton Created!"); }

    public void showMessage() {
        System.out.println("Enum Singleton Method Called!");
    }
}

// Main Class to Test Singletons
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
