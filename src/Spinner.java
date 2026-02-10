import java.util.concurrent.TimeUnit;

public class Spinner {
    private String prefix;
    private int cycles;

    public void run(String prefix, int cycles){
        String[] spinner = {"|", "/", "-", "\\"};
        String clearLine = " ".repeat(prefix.length() + 2);

        for(int c = 0; c < cycles; c++){
            for(int i = 0; i < spinner.length; i++){
                System.out.print(prefix + " " + spinner[i]);
                System.out.print("\r");
                try {
                    TimeUnit.MILLISECONDS.sleep(250);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        System.out.print(clearLine);
        System.out.print("\r");
    }
}
