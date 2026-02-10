public class Recipe {

    private final String name;
    private final int waterAmount;
    private final int beanAmount;
    private final int milkAmount;
    private final int milkFoamAmount;
    private final double price;

    public Recipe(String name, int waterAmount, int beanAmount, int milkAmount, int milkFoamAmount, double price){
        this.name = name;
        this.waterAmount = waterAmount;
        this.beanAmount = beanAmount;
        this. milkAmount = milkAmount;
        this.milkFoamAmount = milkFoamAmount;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getWaterAmount() {
        return waterAmount;
    }

    public int getBeanAmount() {
        return beanAmount;
    }

    public int getMilkAmount() {
        return milkAmount;
    }

    public int getMilkFoamAmount() {
        return milkFoamAmount;
    }

    public double getPrice() {
        return price;
    }
}
