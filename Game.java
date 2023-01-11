import java.awt.Color;
import java.io.File;
import java.util.Scanner;
// import java.util.Arrays;

//unused imports
// region
// import java.io.FileWriter;
// import java.awt.*;
// import javax.swing.*;
// import javax.swing.JFrame;
// import javax.swing.JTextField;
// endregion

public class Game {

    private static Unit[][] map;
    private static int xMap;
    private static int yMap;
    private static int facing;
    private boolean interact;
    private static MyFrame m;
    private AnimationThread animator;

    public void play() {
        map = new Unit[50][50];
        animator = new AnimationThread();
        loadMap("Map0.txt", 0, 0, Color.black);
        facing = 1;
        interact = false;
        // System.out.println(Arrays.deepToString(map));
        setM(new MyFrame());
        animator.start();
    }

    public static MyFrame getM() {
        return m;
    }

    public static void setM(MyFrame m) {
        Game.m = m;
    }

    public static void loadMap(String mapFile, int xMapNew, int yMapNew, Color background) {
        try {
            File myFile = new File(mapFile);
            Scanner sc = new Scanner(myFile);
            sc.useDelimiter("	");
            int type;
            for (int col = 0; col < map.length; col++) {
                for (int row = 0; row < map[col].length; row++) {
                    try {
                        type = Integer.parseInt(sc.next().substring(0, 2));
                        if (type < 50) {
                            map[row][col] = new Environment(type);
                        } else {
                            map[row][col] = new Interactable(type);
                            map[row][col].setRow(row);
                            map[row][col].setCol(col);
                        }
                        map[row][col].setWalking();
                    } catch (Exception e) {

                    }
                }
            }
            xMap = xMapNew;
            yMap = yMapNew;
            MyFrame.setBackgroundColor(background);
            sc.close();
        } catch (Exception e) {
            // System.out.println("Error: " + e);
        }
    }

    public static Unit tileFacing() {
        switch (facing) {
            // row == Game.getxMap()+8 && col == Game.getyMap()+5
            case 0:
                return map[Game.getxMap() + 8][Game.getyMap() + 4];
            case 1:
                return map[Game.getxMap() + 9][Game.getyMap() + 5];
            case 2:
                return map[Game.getxMap() + 8][Game.getyMap() + 6];
            case 3:
                return map[Game.getxMap() + 7][Game.getyMap() + 5];
        }
        return map[Game.getxMap() + 8][Game.getyMap() + 5];
    }

    public static boolean tileFacingWalkable() {
        if (tileFacing().isWalkable()) {
            return true;
        }
        return false;
    }

    public boolean isInteract() {
        return interact;
    }

    public void setInteract(boolean interact) {
        this.interact = interact;
    }

    public static int getxMap() {
        return xMap;
    }

    public static int getyMap() {
        return yMap;
    }

    public static void setxMap(int xMap1) {
        xMap = xMap1;
    }

    public static void setyMap(int yMap1) {
        yMap = yMap1;
    }

    public static Unit[][] getMap() {
        return map;
    }

    public static void setMap(Unit[][] Map1) {
        map = Map1;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }

    public static int getFacing() {
        return facing;
    }

    public static int setFacing(int facing1) {
        int temp = facing;
        facing = facing1;
        return temp;
    }
}