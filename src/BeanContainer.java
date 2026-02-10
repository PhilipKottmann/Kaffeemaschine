public class BeanContainer extends Container{

    public BeanContainer(String name, int capacity) {
        super(name, capacity);
        setCurrentLevel(capacity/2);
    }
}
