import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private Scanner keyboard;
    private List<String> recipeNames;

    public MainMenu(Scanner keyboard, List<String> recipeNames) {
        this.keyboard = keyboard;
        this.recipeNames = recipeNames;
    }

    public MainMenuResult askUser(){
        do {
            // Starting point of the main menu:
            System.out.println("+++ Hauptmenü +++");
            System.out.println("0: Wartungsmenü");
            for(int i = 0; i < recipeNames.size(); i++){
                System.out.println((i + 1) + ": " + recipeNames.get(i));
            }
            System.out.println("99: Beenden");
            // User input for choosing an option (coffee, maintenance etc.):
            System.out.print("Bitte gewünschte Option wählen: ");
            int choice = (keyboard.nextInt() - 1);
            keyboard.nextLine(); // Buffer wiped after calling nextInt()

            // Checking for exit-option:
            if(choice == 98){
                return new MainMenuResult(MainMenuOption.EXIT, null);
            }
            // Error handling:
            if(choice < -1 || choice >= recipeNames.size()){
                System.out.println("Fehlerhafte Eingabe. Bitte eine Ganzzahl im gültigen Bereich eingeben!");
                continue;
            }
            // choosing the Maintenance-menu:
            if(choice == -1){
                return new MainMenuResult(MainMenuOption.MAINTENANCE, null);
            }
            // choosing a valid coffee:
            if(choice >= 0 && choice < recipeNames.size()){
                String coffeeName = recipeNames.get(choice);
                return new MainMenuResult(MainMenuOption.COFFEE_SELECTED, coffeeName);
            }
        } while (true);
    }
}
