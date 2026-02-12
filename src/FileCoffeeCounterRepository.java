import java.io.*;

public class FileCoffeeCounterRepository implements CoffeeCounterRepository{
    private String filePath;
    private File file;

    public FileCoffeeCounterRepository(String filePath){
        this.filePath = filePath;
        this.file = new File(filePath);
        //System.out.println(file.getAbsolutePath());
    }

    @Override
    public void incrementCoffeeCount() {
        // int currentCoffeeCount = getCoffeeCount();
        // int newCoffeeCount = currentCoffeeCount + 1;
        int newCoffeeCount = getCoffeeCount() + 1;

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file))){
            writer.write(String.valueOf(newCoffeeCount));
        }catch (IOException e){
            System.out.println("Fehler beim Schreiben der Kaffeeanzahl");
        }
    }

    @Override
    public int getCoffeeCount(){
        String line = null;

        // empty file => return 0
        if(!file.exists()){
            return 0;
        }

        // try reading the file
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            return 0;
        }

        // in case line is empty ("null") => return 0
        if(line == null){
            return 0;
        }

        // parsing result of "line" to an integer for further processing
        try{
            return Integer.parseInt(line);
        }catch (NumberFormatException nfe){
            return 0;
        }
    }
}
