import java.util.*;

public class CoffeeMachine {

    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        Spinner spinner = new Spinner();
        FileCoffeeCounterRepository coffeeCounter = new FileCoffeeCounterRepository("coffeeCounter.txt");

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

        do {
            MainMenuResult menuResult = mainMenu.askUser();

            if(menuResult.getOption() == MainMenuOption.MAINTENANCE){
                //maintenanceMenu(keyboard,water, beans, milk, waste, maker);
                maintenanceMenu.open();
                continue;
            }

            if(menuResult.getOption() == MainMenuOption.EXIT){
                spinner.run("Beende Programm", 2);
                break; // Exit program
            }

            if(menuResult.getOption() == MainMenuOption.COFFEE_SELECTED){
                String selectedName = menuResult.getCoffeeName();

                Recipe coffeeChoice = repo.getRecipe(selectedName); // Recipe fetched according to the chosen coffee
                Coffee order = new Coffee(coffeeChoice); // Order placed => new instance of Coffee created

                ResourceCheckResult result = maker.brew(order); // Object for interpreting of failures

                if(result != ResourceCheckResult.OK){
                    // boolean fixed = handleError(result, keyboard, water, beans, milk, waste, maker);
                    boolean fixed = errorHandler.handleError(result);

                    if(fixed){
                        result = maker.brew(order);
                    }
                }
                if(result == ResourceCheckResult.OK){
                    String prefix = "Bereite zu";
                    spinner.run(prefix,3);
                    System.out.println("Bitte Getränk entnehmen.");
                    System.out.println();
                    coffeeCounter.incrementCoffeeCount();
                }
            }
        } while(true);
    }
}
