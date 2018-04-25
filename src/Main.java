import aquarium.Aquarium;

import java.io.IOException;

/**
 *main Class .
 */

public class Main {

    /**
     *class thread .
     */
    public static class GameThread extends Thread {

        /**
         *method Run .
         */
        public void run() {
            try {
                Aquarium aquarium = new Aquarium(
                        "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\guppy1.png");
                aquarium.buildFrame();

                aquarium.setContent();
                aquarium.startAquarium();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     *method Main .
     * @param args .
     */
    public static void main(String[] args) {

        Thread thread = new GameThread();
        thread.start();



    }
}
