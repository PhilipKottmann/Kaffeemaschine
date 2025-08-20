public class Fuellstand {
    private int minimum = 0;
    private int maximum = 0;
    private String name = "";

    // Konstruktor
    public Fuellstand(String name, int maximum) {
        setName(name);
        setMaximum(maximum);
    }

    // Getter
    public int getMinimum() {
        return minimum;
    }

    public int getMaximum() {
        return maximum;
    }

    public String getName() {
        return name;
    }

    // Setter
    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public void setMaximum(int maximum) {
        this.maximum = maximum;
    }

    public void setName(String name) {
        this.name = name;
    }

    // eigene Methoden
    private void fuellstandErhoehen(){

    }

    private void fuellstandVerrringern(){

    }

    public void fuellstandAnzeigen(){
        //System.out.println("Das ist der Füllstand des " + behaelter.getClass());
        System.out.println("Das ist der Füllstand des " + getName());
        System.out.println("Der maximale Füllstand beträgt: " + getMaximum());
    }
}
