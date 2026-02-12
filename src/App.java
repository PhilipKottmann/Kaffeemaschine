import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        Spinner spinner = new Spinner();
        // writing to a txt-file:
        CoffeeCounterRepository coffeeCounter = new FileCoffeeCounterRepository("coffeeCounter.txt");
        // writing to a database:
        //CoffeeCounterRepository coffeeCounter = new DatabaseCoffeeCounterRepository(connection);

        WaterContainer water = new WaterContainer("Wasserbehälter", 2000);
        BeanContainer beans = new BeanContainer("Bohnenbehälter", 500);
        MilkContainer milk = new MilkContainer("Milchbehälter", 400);
        WasteContainer waste = new WasteContainer("Satzbehälter", 250);

        CoffeeMaker maker = new CoffeeMaker(water, beans, milk, waste);

        // instantiating recipe and sorting it in alphabetical order ascending
        RecipeRepository repo = new RecipeRepository();
        List<String> recipeNames = new ArrayList<>(repo.getRecipes().keySet());
        Collections.sort(recipeNames);

        MainMenu mainMenu = new MainMenu(keyboard, recipeNames);

        MaintenanceMenu maintenanceMenu = new MaintenanceMenu(keyboard, water, beans, milk, waste, maker, spinner, coffeeCounter);

        ErrorHandler errorHandler = new ErrorHandler(keyboard, water, beans, milk, waste, maker);

        CoffeeMachine coffeeMachine = new CoffeeMachine(coffeeCounter, mainMenu, maintenanceMenu,
                errorHandler, maker, spinner, repo);
        coffeeMachine.start();
    }
}
