// Abstract class defining the template method
abstract class DataProcessor {
    
    // Template method defining the steps of the algorithm
    public final void process() {
        readData();
        processData();
        saveData();
    }

    // Step 1: Read data (implemented by subclasses)
    protected abstract void readData();

    // Step 2: Process data (common for all)
    private void processData() {
        System.out.println("Processing data...");
    }

    // Step 3: Save data (implemented by subclasses)
    protected abstract void saveData();
}

// Concrete class for CSV data processing
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

// Concrete class for JSON data processing
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

// Main class
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
