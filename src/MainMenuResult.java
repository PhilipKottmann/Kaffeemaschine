public class MainMenuResult{
    private MainMenuOption option;
    private String coffeeName;

    public MainMenuResult(MainMenuOption option, String coffeeName) {
        this.option = option;
        this.coffeeName = coffeeName;
    }

    public MainMenuOption getOption() {
        return option;
    }

    public String getCoffeeName() {
        return coffeeName;
    }
}
