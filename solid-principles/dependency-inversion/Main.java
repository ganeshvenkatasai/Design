
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
