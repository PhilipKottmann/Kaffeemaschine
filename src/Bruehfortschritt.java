/**
 * @author: Philip Kottmann
 * @Datum: 27.3.2025
 * @Inhalt: Fortschrittsanzeige mit importiertem Modul
 */

import me.tongfei.progressbar.ProgressBar;

public class Bruehfortschritt {
    public Bruehfortschritt(double bruehzeit, boolean milchschaum){
        double pausenberechnung = (50 * bruehzeit); // Pause [ms]
        long pause = Math.round(pausenberechnung);
        if(milchschaum){
            pause += 25;
        }
        try (ProgressBar pb = new ProgressBar("Zubereitung", 100)) {
            for (int i = 0; i < 100; i++) {
                // Fortschritt um 1 erhöhen
                pb.step();

                // Simuliert eine Aufgabe durch eine kleine Pause
                try {
                    Thread.sleep(pause); // Pause [ms]
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
