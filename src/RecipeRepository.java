import java.util.HashMap;
import java.util.Map;

public class RecipeRepository {

    private final Map<String, Recipe> recipes = new HashMap<>();

    public RecipeRepository(){
        loadRecipeList();
    }

    private void loadRecipeList(){
        recipes.put("Espresso", new Recipe("Espresso", 100, 15, 0, 0, 2.00));
        recipes.put("Cafe Creme", new Recipe("Cafe Creme", 250, 30, 0, 0, 3.00));
        recipes.put("Cappuccino", new Recipe("Cappuccino", 200, 15, 0, 30, 3.80));
        recipes.put("Latte Macchiato", new Recipe("Latte Macchiato", 250, 15, 15, 20, 4.20));
        recipes.put("Wiener Melange", new Recipe("Wiener Melange", 200, 25, 10, 0, 3.50));
    }

    public Recipe getRecipe(String name){
        return recipes.get(name);
    }

    public Map<String, Recipe> getRecipes() {
        return recipes;
    }
}
