public abstract class Container {
    private final String name;
    private int capacity;
    private int currentLevel;

    // Constructor
    public Container(String name, int capacity) {
        this.name = name;
        setCapacity(capacity);
    }

    // own methods
    public void fill(){
        setCurrentLevel(getCapacity());
    }

    public void consume(int amount){
        if(hasEnoughContent(amount)){
            setCurrentLevel(getCurrentLevel() - amount);
        }else{
            throw new IllegalArgumentException("Not enough content in container");
        }
    }

    public boolean isEmpty(){
        return getCurrentLevel() <= 0;
    }

    public boolean hasEnoughContent(int amount){
        return getCurrentLevel() >= amount; // if-else-statement simplified
    }

    public boolean isFull(){
        return getCurrentLevel() == getCapacity();
    }

    public void add(int amount){ // e.g Waste-container adds waste instead of removing it
        if((getCurrentLevel() + amount) <= getCapacity()){
            setCurrentLevel(getCurrentLevel() + amount);
        }else{
            throw new IllegalArgumentException("Container has not enough remaining capacity");
        }
    }

    public void empty(){
        setCurrentLevel(0);
    }


    // getter- and setter-methods
    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(int currentLevel) {
        this.currentLevel = currentLevel;
    }
}
