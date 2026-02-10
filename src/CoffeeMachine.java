import java.util.*;

public class CoffeeMachine {

    public static boolean handleError(ResourceCheckResult result, Scanner keyboard,
                                      WaterContainer water, BeanContainer beans,
                                      MilkContainer milk, WasteContainer waste, CoffeeMaker maker){

        switch (result){
            case NO_WATER:
                System.out.print("Nicht genügend Wasser! Wasser auffüllen (j/n)? ");
                if(keyboard.nextLine().equals("j")){
                    water.fill();
                    return true;
                }else {
                    return false;
                }
            case NO_BEANS:
                System.out.print("Nicht genügend Bohnen! Bohnen auffüllen (j/n)? ");
                if(keyboard.nextLine().equals("j")){
                    beans.fill();
                    return true;
                }else {
                    return false;
                }
            case NO_MILK:
                System.out.print("Nicht genügend Milch! Milch auffüllen (j/n)? ");
                if(keyboard.nextLine().equals("j")){
                    milk.fill();
                    return true;
                }else {
                    return false;
                }
            case WASTE_FULL:
                System.out.print("Satzbehälter voll! Satzbehälter leeren (j/n)? ");
                if(keyboard.nextLine().equals("j")){
                    waste.empty();
                    return true;
                }else {
                    return false;
                }
            case DESCALING_REQUIRED:
                System.out.print("Bitte Maschine entkalken!");
                break;
            case CLEANING_MILKSYSTEM_REQUIRED:
                System.out.print("Bitte Milchsystem reinigen!");
                break;
            default: return false;
        } return false;
    }

    public static void main(String[] args){

        Scanner keyboard = new Scanner(System.in);
        Spinner spinner = new Spinner();

        WaterContainer water = new WaterContainer("Wasserbehälter", 2000);
        BeanContainer beans = new BeanContainer("Bohnenbehälter", 500);
        MilkContainer milk = new MilkContainer("Milchbehälter", 400);
        WasteContainer waste = new WasteContainer("Satzbehälter", 250);

        CoffeeMaker maker = new CoffeeMaker(water, beans, milk, waste);

        RecipeRepository repo = new RecipeRepository();
        List<String> recipeNames = new ArrayList<>(repo.getRecipes().keySet());
        Collections.sort(recipeNames);

        MainMenu mainMenu = new MainMenu(keyboard, recipeNames);
        MaintenanceMenu maintenanceMenu = new MaintenanceMenu(keyboard, water, beans, milk, waste, maker, spinner);

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
                    boolean fixed = handleError(result, keyboard, water, beans, milk, waste, maker);

                    if(fixed){
                        result = maker.brew(order);
                    }
                }
                if(result == ResourceCheckResult.OK){
                    String prefix = "Bereite zu";
                    spinner.run(prefix,3);
                    System.out.println("Bitte Getränk entnehmen.");
                    System.out.println();
                }
            }
        } while(true);
    }
}
