package aquarium;

import javax.imageio.ImageIO;
import javax.swing.*;

import animal.Guppy;
import animal.Piranha;
import animal.Snail;
import droppable.Food;
import droppable.Koin;
import linkedlist.LinkedList;
import position.Coordinate;

import static animal.Fish.GUPPY_PRICE;
import static animal.Fish.PIRANHA_PRICE;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;



/**
 *Class Aquarium.
 *
 * @author Mico
 *
 */

public class Aquarium extends JPanel {

    /**
     *
     * Harga Dasar Egg.
     *
     */

    private static final int BASE_EGG_PRICE = 50;
    /**
     * Lebar Layar.
     *
     */
    public static final int SCREEN_WIDTH = 640;
    /**
     * Tinggi Layar.
     *
     */

    public static final int SCREEN_HEIGHT = 480;

    /**
     * Status Jika Kalah.
     *
     */
    public static final int LOSE = 0;
    /**
     * Status Jika Menang.
     *
     */
    public static final int WIN = 1;
    /**
     * Status Saat Load Game.
     *
     */
    public static final int LOAD = 0;
    /**
     * Status Saat Play New Game.
     *
     */
    public static final int NEW = 1;
    /**
     * Status Saat Load Game.
     *
     */
    public static final int HOME = 0;
    /**
     * Status Saat Bermain Game.
     *
     */
    public static final int PLAY = 1;
    /**
     * Status Saat Game Berakhir.
     *
     */
    public static final int FINISH = 2;

    /**
     * Alamat Gambar.
     *
     */
    private final String CURRENT_IMAGE =
            "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\guppy1.png";
    /**
     * Alamat Gambar BackGround.
     *
     */
    private final String BACKGROUND_IMAGE =
            "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\Aquarium1.jpg";
    /**
     * Alamat Gambar Bar.
     *
     */
    private final String BAR_IMAGE =
            "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\bar.jpg";
    /**
     * Alamat Gambar Menang.
     *
     */
    private final String WIN_IMAGE =
            "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\win.png";
    /**
     * Alamat Gambar Kalah.
     *
     */
    private final String LOSE_IMAGE =
            "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\lose.jpg";


    /**
     * List Guppy.
     *
     */
    private linkedlist.LinkedList<Guppy> listGuppy;
    /**
     * List Piranha.
     *
     */
    private LinkedList<Piranha> listPiranha;
    /**
     * List Food.
     *
     */
    private linkedlist.LinkedList<Food> listFood;
    /**
     * List Koin.
     *
     */
    private linkedlist.LinkedList<Koin> listKoin;

    /**
     * List Siput.
     *
     */
    private Snail snail;
    /**
     * Stage Telur.
     *
     */
    private int telur;
    /**
     * Total Uang.
     *
     */
    private int duit;

    /**
     * Buffer gambar.
     *
     */
    private BufferedImage defaultImage;
    /**
     * HashMap Gambar.
     *
     */
    private Map<String, BufferedImage> images;
    /**
     * Status dalam integer.
     *
     */
    private int status;
    /**
     * Gambar untuk kemenangan/kekalahan.
     *
     */
    private String res;
    /**
     * Kategory pemain.
     *
     */
    private int playCategory = -1;

    /**
     * fps game.
     *
     */
    private long fps;

    /**
     * Frame Program.
     *
     */

    private JFrame jFrame;

    /**
     * Constructor Aquarium.
     * @param defaultObjectImagePath .
     *
     */

    public Aquarium(String defaultObjectImagePath) throws IOException {
        this.defaultImage = ImageIO.read(new File(defaultObjectImagePath));
        this.images = new HashMap<>();

        this.fps = 1000000000L / 128L;

        listGuppy = new LinkedList<Guppy>();
        listPiranha = new LinkedList<Piranha>();
        listFood = new LinkedList<Food>();
        listKoin = new LinkedList<Koin>();
        snail = new Snail(SCREEN_WIDTH / 2, 400);
        playCategory = -1;
    }

    /**
     * Get Image
     * @param path
     *
     * @return BufferedImage.
     */
    private BufferedImage readImage(String path) {
        BufferedImage newImage = this.images.get(path);
        if (newImage == null) {
            try {
                newImage = ImageIO.read(new File(path));
            } catch (IOException e) {
                newImage = defaultImage;
            }
            this.images.put(path, newImage);
        }
        return newImage;
    }

    /**
     * method start.
     *
     *
     */
    public void startAquarium() {
        status = HOME;

        while (playCategory == -1) {
            System.out.println(playCategory);
        }
        playAquarium();
    }

    /**
     * method play.
     *
     *
     */
    public void playAquarium() {
        status = PLAY;
        long lastFrameStart = System.nanoTime();
        long now;

        if (playCategory == LOAD) {
            readFile();
        } else {
            initDefault();
        }

        while (true) {
            now = System.nanoTime();

            if ((now - lastFrameStart) >= fps) {
                syncAll();

                if (telur == 3) {
                    setResult(WIN);
                    break;
                } else {
                    if (listKoin.size() == 0 && listGuppy.size() == 0
                            && listPiranha.size() == 0 && duit < GUPPY_PRICE) {
                        setResult(LOSE);
                        break;
                    }
                }

                jFrame.invalidate();
                jFrame.validate();
                jFrame.repaint();
                lastFrameStart = now;
            }
        }
    }

    /**
     * method setResult.
     *
     *@param result .
     *
     */
    public void setResult(int result) {
        status = FINISH;
        res = WIN == result ? WIN_IMAGE : LOSE_IMAGE;
        jFrame.invalidate();
        jFrame.validate();
        jFrame.repaint();
    }

    /**
     * method sync.
     *
     *
     */
    public void syncAll() {
        int i = 0;
        while (i < listFood.size()) {
            listFood.get(i).moveGeneral(listFood.get(i).getX(), 400);

            if (listFood.get(i).getY() > 400) {
                listFood.remove(i);
            } else {
                i++;
            }
        }

        i = 0;
        while (i < listKoin.size()) {
            if (!listKoin.get(i).beetweenY(400, 2)) {
                listKoin.get(i).moveGeneral(listKoin.get(i).getX(), 400);
            }
            i++;
        }

        i = 0;
        while (i < listGuppy.size()) {
            int j = listGuppy.get(i).synchronize(listKoin);

            if (j == 0) {
                listGuppy.remove(i);
            } else {
                if (j == 1) {
                    //lapar
                    listGuppy.get(i).eat(listFood);
                } else {
                    // ga lapar
                    listGuppy.get(i).randomMove();
                }
                i++;
            }
        }

        i = 0;
        while (i < listPiranha.size()) {
            int j = listPiranha.get(i).synchronize(listKoin);
            if (j == 0) {
                listPiranha.remove(i);
            } else {
                if (j == 1) {
                    //laper
                    listPiranha.get(i).eat(listGuppy, listKoin);
                } else {
                    //ga laper
                    listPiranha.get(i).randomMove();
                }
                i++;
            }
        }

        int dapat = snail.eat(listKoin);
        duit += dapat;
    }

    /**
     * method buildFrame.
     *
     *
     */
    public void buildFrame() {
        jFrame = new JFrame();
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        jFrame.setVisible(true);
    }

    /**
     * method setContent.
     *
     *
     */
    public void setContent() {
        jFrame.setContentPane(this);
        jFrame.getContentPane().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println(e.getLocationOnScreen().getX() + ", " + e.getLocationOnScreen().getY());
                double mouseX = e.getLocationOnScreen().getX();
                double mouseY = e.getLocationOnScreen().getY();

                if (status == PLAY) {
                    mouseY -= 35;
                    int i = 0;
                    while (i < listKoin.size()) {
                        if (listKoin.get(i).beetweenX(mouseX, 20)
                                && listKoin.get(i).beetweenY(mouseY, 20)) {
                            break;
                        }
                        i++;
                    }

                    if (i != listKoin.size()) {
                        duit += listKoin.get(i).getValue();
                        listKoin.remove(i);
                    } else if (mouseX > 581 - 30 && mouseX < 581 + 30
                            && mouseY > 55 - 10 && mouseY < 55 + 10) {
                        saveFile();
                    } else if (mouseX > 330 - 30 && mouseX < 330 + 30
                            && mouseY > 32 - 30 && mouseY < 32 + 30) {
                        if (duit >= GUPPY_PRICE) {
                            duit -= GUPPY_PRICE;
                            double aa;
                            double bb;
                            Random r = new Random();
                            aa = 100 + (300 - 100) * r.nextDouble();
                            bb = 100 + (200 - 100) * r.nextDouble();
                            if (aa < 20 && bb < 20) {
                                aa = SCREEN_WIDTH / 2;
                                bb = SCREEN_HEIGHT / 2;
                            }
                            Guppy g = new Guppy(aa, bb);
                            listGuppy.add(g);
                        }
                    } else if (mouseX > 395 - 30 && mouseX < 395 + 30
                            && mouseY > 32 - 30 && mouseY < 32 + 30) {
                        if (duit >= PIRANHA_PRICE) {
                            duit -= PIRANHA_PRICE;
                            double aa;
                            double bb;
                            Random r = new Random();
                            aa = 100 + (300 - 100) * r.nextDouble();
                            bb = 100 + (200 - 100) * r.nextDouble();
                            if (aa < 20 && bb < 20) {
                                aa = SCREEN_WIDTH / 2;
                                bb = SCREEN_HEIGHT / 2;
                            }
                            Piranha p = new Piranha(aa, bb);
                            listPiranha.add(p);
                        }
                    } else if (mouseX > 482 - 30 && mouseX < 482 + 30
                            && mouseY > 32 - 30 && mouseY < 32 + 30) {
                        if (duit >= BASE_EGG_PRICE * (telur + 1)) {
                            telur++;
                            duit -= BASE_EGG_PRICE * telur;
                        }
                    } else if (mouseY > 80 && mouseY < 400) {
                        if (duit - 2 >= 0) {
                            duit -= 2;
                            Food ff = new Food(mouseX, mouseY);
                            listFood.add(ff);
                        }
                    }
                } else if (status == HOME) {
                    if (mouseX > 330 - 100 && mouseX < 330 + 100
                            && mouseY > 240 - 40 && mouseY < 240 + 40) {
                        playCategory = NEW;
                    } else if (mouseX > 330 - 100 && mouseX < 330 + 100
                            && mouseY > 340 - 40 && mouseY < 340 + 40) {
                        playCategory = LOAD;
                    }
                }

            }
        });
    }

    /**
     * method save.
     *
     *
     */
    public void saveFile() {
        String fileName;

        //duit
        fileName = "duit.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            bufferedWriter.write(duit + "");

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

        //telur
        fileName = "telur.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            bufferedWriter.write(telur + "");

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

        //guppy
        fileName = "guppy.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < listGuppy.size(); i++) {
                Guppy g = listGuppy.get(i);
                bufferedWriter.write(g.getX() + " " + g.getY() + " " + g.getSpeed() + " " + g.getLookAt()
                        + " " + g.getLifetime() + " " + g.getStillFull() + " " + g.getCountingDead() + " "
                        + g.getCoordinate().getX() + " " + g.getCoordinate().getY() + " " + g.getPhase() + " "
                        + g.getTotalEatenFood() + " " + g.getKoinValue());
                bufferedWriter.newLine();
            }

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

        //piranha
        fileName = "piranha.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < listPiranha.size(); i++) {
                Piranha g = listPiranha.get(i);
                bufferedWriter.write(g.getX() + " " + g.getY() + " " + g.getSpeed() + " " + g.getLookAt() + " "
                        + g.getLifetime() + " " + g.getStillFull() + " " + g.getCountingDead() + " "
                        + g.getCoordinate().getX() + " " + g.getCoordinate().getY());
                bufferedWriter.newLine();
            }

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

        //snail
        fileName = "snail.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            Snail g = snail;
            bufferedWriter.write(g.getX() + " " + g.getY() + " " + g.getSpeed() + " " + g.getLookAt());

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

        //Food
        fileName = "Food.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < listFood.size(); i++) {
                Food g = listFood.get(i);
                bufferedWriter.write(g.getX() + " " + g.getY() + " " + g.getSpeed());
                bufferedWriter.newLine();
            }

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

        //Koin
        fileName = "Koin.txt";
        try {
            // Assume default encoding.
            FileWriter fileWriter =
                    new FileWriter(fileName);
            // Always wrap FileWriter in BufferedWriter.
            BufferedWriter bufferedWriter =
                    new BufferedWriter(fileWriter);

            for (int i = 0; i < listKoin.size(); i++) {
                Koin g = listKoin.get(i);
                bufferedWriter.write(g.getX() + " " + g.getY() + " " + g.getSpeed() + " " + g.getValue());
                bufferedWriter.newLine();
            }

            // Always close files.
            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Error writing to file '" + fileName + "'");
        }

    }

    /**
     * method load.
     *
     *
     */
    public void readFile() {
        String fileName;
        String line;

        //duit
        fileName = "duit.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                duit = Integer.parseInt(line);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        //telur
        fileName = "telur.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                telur = Integer.parseInt(line);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        //guppy
        fileName = "guppy.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(" ");
                Guppy g = new Guppy(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
                g.setSpeed(Double.parseDouble(data[2]));
                g.setLookAt(Integer.parseInt(data[3]));
                g.setLifetime(Integer.parseInt(data[4]));
                g.setStillFull(Integer.parseInt(data[5]));
                g.setCountingDead(Integer.parseInt(data[6]));
                g.setCoordinate(new Coordinate(Double.parseDouble(data[7]), Double.parseDouble(data[8])));
                g.setPhase(Integer.parseInt(data[9]));
                g.setTotalEatenFood(Integer.parseInt(data[10]));
                g.setKoinValue(Integer.parseInt(data[11]));
                listGuppy.add(g);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        //piranha
        fileName = "piranha.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(" ");
                Piranha g = new Piranha(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
                g.setSpeed(Double.parseDouble(data[2]));
                g.setLookAt(Integer.parseInt(data[3]));
                g.setLifetime(Integer.parseInt(data[4]));
                g.setStillFull(Integer.parseInt(data[5]));
                g.setCountingDead(Integer.parseInt(data[6]));
                g.setCoordinate(new Coordinate(Double.parseDouble(data[7]), Double.parseDouble(data[8])));
                listPiranha.add(g);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        //Food
        fileName = "Food.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(" ");
                Food g = new Food(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
                g.setSpeed(Double.parseDouble(data[2]));
                listFood.add(g);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        //Koin
        fileName = "Koin.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(" ");
                Koin g = new Koin(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Integer.parseInt(data[3]));
                g.setSpeed(Double.parseDouble(data[2]));
                listKoin.add(g);
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }

        //snail
        fileName = "snail.txt";
        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader =
                    new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(" ");
                Snail g = new Snail(Double.parseDouble(data[0]), Double.parseDouble(data[1]));
                g.setSpeed(Double.parseDouble(data[2]));
                g.setLookAt(Integer.parseInt(data[3]));
                snail = g;
            }

            // Always close files.
            bufferedReader.close();
        } catch (FileNotFoundException ex) {
            System.out.println(
                    "Unable to open file '" + fileName + "'");
        } catch (IOException ex) {
            System.out.println(
                    "Error reading file '"
                            + fileName + "'");
        }
    }

    /**
     * method init.
     *
     *
     */
    public void initDefault() {
        duit = 10;
        telur = 0;
        listGuppy.add(new Guppy(SCREEN_WIDTH / 2, SCREEN_HEIGHT / 2));
    }

    @Override
    /**
     * method paint.
     *
     *
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        g.setColor(Color.WHITE);
        g.drawImage(readImage(BACKGROUND_IMAGE), 0, 0, null);

        if (status == HOME) {
            g.drawImage(readImage(
                    "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\button_start.png"), SCREEN_WIDTH / 2 - 45, SCREEN_HEIGHT / 2 - 50, null);
            g.drawImage(readImage(
                    "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\button_load.png"), SCREEN_WIDTH / 2 - 45, SCREEN_HEIGHT / 2 + 50, null);
            return;
        }

        if (status == FINISH) {
            setBackground(Color.WHITE);
            g.setColor(Color.WHITE);

            g.drawImage(readImage(res), 0, 0, null);
        }

        g.drawImage(readImage(BAR_IMAGE), 0, 0, null);

        //save
        g.drawString("SAVE", 560, 20);

        //beli telor
        String telorImg = "C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\";
        telorImg += String.valueOf(telur + 1) + ".jpg";
        g.drawImage(readImage(telorImg), 450, 5, null);
        String hargaTelor = "Rp. " + BASE_EGG_PRICE * (telur + 1);
        g.drawString(hargaTelor, 445, 59);

        //beli guppy
        g.drawImage(readImage("C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\guppy1.png"),
                240, -35, null);
        g.drawString("Rp. 5", 305, 59);

        //beli piranha
        g.drawImage(readImage("C:\\Users\\ASUS ROG\\Desktop\\ArkavQuarium\\src\\draw\\rsz_piranha.png"),
                375, 6, null);
        g.drawString("Rp. 20", 375, 59);

        //uang
        String uang = "Rp. " + duit;
        g.drawString(uang, 565, 54);

        //guppy
        for (int i = 0; i < listGuppy.size(); i++) {
            g.drawImage(readImage(listGuppy.get(i).getImagePath()),
                    (int) Math.floor(listGuppy.get(i).getX() - 20),
                    (int) Math.floor(listGuppy.get(i).getY()), null);
        }

        //pirana
        for (int i = 0; i < listPiranha.size(); i++) {
            g.drawImage(readImage(listPiranha.get(i).getImagePath()),
                    (int) Math.floor(listPiranha.get(i).getX() - 20),
                    (int) Math.floor(listPiranha.get(i).getY()), null);
        }

        //Koin
        for (int i = 0; i < listKoin.size(); i++) {
            g.drawImage(readImage(listKoin.get(i).getImagePath()),
                    (int) Math.floor(listKoin.get(i).getX() - 20),
                    (int) Math.floor(listKoin.get(i).getY()), null);
        }

        //fish food
        for (int i = 0; i < listFood.size(); i++) {
            g.drawImage(readImage(listFood.get(i).getImagePath()),
                    (int) Math.floor(listFood.get(i).getX() - 20),
                    (int) Math.floor(listFood.get(i).getY()), null);
        }

        //snail
        g.drawImage(readImage(snail.getImagePath()),
                (int) Math.floor(snail.getX()),
                (int) Math.floor(snail.getY()) - 30, null);

    }

}
