import java.util.*;

public class CoffeeMachine {
    private final CoffeeCounterRepository coffeeCounter;
    private final MainMenu mainMenu;
    private final MaintenanceMenu maintenanceMenu;
    private final ErrorHandler errorHandler;
    private final CoffeeMaker maker;
    private final Spinner spinner;
    private final RecipeRepository repo;

    public CoffeeMachine(CoffeeCounterRepository coffeeCounter, MainMenu mainMenu, MaintenanceMenu maintenanceMenu, ErrorHandler errorHandler, CoffeeMaker maker, Spinner spinner, RecipeRepository recipe){
        this.coffeeCounter = coffeeCounter;
        this.mainMenu = mainMenu;
        this.maintenanceMenu = maintenanceMenu;
        this.errorHandler = errorHandler;
        this.maker = maker;
        this.spinner = spinner;
        this.repo = recipe;
    }

    public void start(){
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
