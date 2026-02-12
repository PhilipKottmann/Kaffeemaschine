import java.io.FileNotFoundException;
import java.io.IOException;

public interface CoffeeCounterRepository {
    public void incrementCoffeeCount();
    public int getCoffeeCount();
}
