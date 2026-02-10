import java.util.Scanner;

public class MaintenanceMenu {
    private Scanner keyboard;
    private WaterContainer water;
    private BeanContainer beans;
    private MilkContainer milk;
    private WasteContainer waste;
    private CoffeeMaker maker;
    private Spinner spinner;

    public MaintenanceMenu(Scanner keyboard, WaterContainer water, BeanContainer beans, MilkContainer milk, WasteContainer waste, CoffeeMaker maker, Spinner spinner) {
        this.keyboard = keyboard;
        this.water = water;
        this.beans = beans;
        this.milk = milk;
        this.waste = waste;
        this.maker = maker;
        this.spinner = spinner;
    }

    public void open(){
        boolean menuReturn = true;

        while(menuReturn){
            System.out.println("+++ Wartungsmenü +++");
            System.out.println("0: Zurück zum Hauptmenü");
            System.out.println("1: Wasser auffüllen");
            System.out.println("2: Bohnen auffüllen");
            System.out.println("3: Milch auffüllen");
            System.out.println("4: Satzbehälter leeren");
            System.out.println("5: Füllstände anzeigen");

            System.out.print("Bitte Auswahl treffen: ");
            int choice = keyboard.nextInt();
            switch(choice){
                case 0:
                    menuReturn = false;
                    break;
                case 1:
                    water.fill();
                    spinner.run("Fülle Wasser auf", 2);
                    System.out.println(water.getName() + " aufgefüllt");
                    break;
                case 2:
                    beans.fill();
                    spinner.run("Fülle Bohnen auf", 2);
                    System.out.println(beans.getName() + " aufgefüllt");
                    break;
                case 3:
                    milk.fill();
                    spinner.run("Fülle Milch auf", 2);
                    System.out.println(milk.getName() + " aufgefüllt");
                    break;
                case 4:
                    waste.empty();
                    spinner.run("Leere Satzbehälter", 2);
                    System.out.println(waste.getName() + " geleert");
                    break;
                case 5:
                    System.out.println("Wasser Füllstand: " + maker.getWaterLevel() + " ml");
                    System.out.println("Bohnen Füllstand: " + maker.getBeanLevel() + " g");
                    System.out.println("Milch Füllstand: " + maker.getMilkLevel() + " ml");
                    System.out.println("Satzbehälter Füllstand: " + maker.getWasteLevel() + " g");
                    break;
                default:
                    System.out.println("Fehler im Wartungsmenü");
            }
        }
    }
}
