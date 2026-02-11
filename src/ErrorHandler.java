import java.util.Scanner;

public class ErrorHandler {
    private Scanner keyboard;
    private WaterContainer water;
    private BeanContainer beans;
    private MilkContainer milk;
    private WasteContainer waste;
    private CoffeeMaker maker;

    public ErrorHandler(Scanner keyboard, WaterContainer water, BeanContainer beans, MilkContainer milk, WasteContainer waste, CoffeeMaker maker) {
        this.keyboard = keyboard;
        this.water = water;
        this.beans = beans;
        this.milk = milk;
        this.waste = waste;
        this.maker = maker;
    }

    public boolean handleError(ResourceCheckResult errorType){

        switch (errorType){
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
}
