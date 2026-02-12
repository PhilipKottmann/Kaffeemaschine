// currently a placeholder for the DB connection

public class DatabaseCoffeeCounterRepository implements CoffeeCounterRepository {
    private String databaseName;
    private String hostName;
    private String userName;
    private String password;

    public DatabaseCoffeeCounterRepository(String databaseName, String hostName, String userName, String password) {
        this.databaseName = databaseName;
        this.hostName = hostName;
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void incrementCoffeeCount() {

    }

    @Override
    public int getCoffeeCount() {
        return 42;
    }
}
