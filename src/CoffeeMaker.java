public class CoffeeMaker {

    private WaterContainer waterContainer;
    private BeanContainer beanContainer;
    private MilkContainer milkContainer;
    private WasteContainer wasteContainer;

    public CoffeeMaker(WaterContainer waterContainer, BeanContainer beanContainer,
                       MilkContainer milkContainer, WasteContainer wasteContainer) {
        this.waterContainer = waterContainer;
        this.beanContainer = beanContainer;
        this.milkContainer = milkContainer;
        this.wasteContainer = wasteContainer;
    }

    private ResourceCheckResult hasEnoughResources(Recipe recipe){
        if(!waterContainer.hasEnoughContent(recipe.getWaterAmount())) return ResourceCheckResult.NO_WATER;
        if(!beanContainer.hasEnoughContent(recipe.getBeanAmount())) return ResourceCheckResult.NO_BEANS;
        if(!milkContainer.hasEnoughContent(recipe.getMilkAmount() + recipe.getMilkFoamAmount())) return ResourceCheckResult.NO_MILK;
        if(wasteContainer.isFull()) return ResourceCheckResult.WASTE_FULL;

        return ResourceCheckResult.OK;
    }

    public ResourceCheckResult brew(Coffee coffee){
        Recipe recipe = coffee.getRecipe();
        ResourceCheckResult result = hasEnoughResources(recipe);

        if(result != ResourceCheckResult.OK){
            return result;
        }
        waterContainer.consume(recipe.getWaterAmount());
        beanContainer.consume(recipe.getBeanAmount());
        if((recipe.getMilkAmount() + recipe.getMilkFoamAmount()) > 0){
            milkContainer.consume(recipe.getMilkAmount() + recipe.getMilkFoamAmount());
        }
        wasteContainer.add(recipe.getBeanAmount());

        System.out.println("Auswahl: " + recipe.getName());
        System.out.println("Wasser: " + recipe.getWaterAmount() + " ml");
        System.out.println("Bohnen: " + recipe.getBeanAmount()+ " g");
        System.out.println("Milch: " + recipe.getMilkAmount() + " ml");
        System.out.println("Milchschaum: " + recipe.getMilkFoamAmount() + " ml");
        System.out.println("Preis: € " + recipe.getPrice());

        return ResourceCheckResult.OK;
    }

    public int getWaterLevel(){
        return waterContainer.getCurrentLevel();
    }

    public int getBeanLevel(){
        return beanContainer.getCurrentLevel();
    }

    public int getMilkLevel(){
        return milkContainer.getCurrentLevel();
    }

    public int getWasteLevel(){
        return wasteContainer.getCurrentLevel();
    }

}
