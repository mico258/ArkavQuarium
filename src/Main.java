import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        try {
            Aquarium aquarium = new Aquarium("D:\\Kuliah\\Semester 6\\OOP\\Java\\ArkavQuarium\\src\\draw\\guppy1.png");
            aquarium.buildFrame();

            aquarium.setContent();
            aquarium.startAquarium();

        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
