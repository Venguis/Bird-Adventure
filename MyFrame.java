import java.awt.*;
import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
// import java.util.Arrays;

public class MyFrame extends JFrame {
    public static CenterPanel p1 = new CenterPanel();
    public static Graphics g = p1.getGraphics();
    private static Color backgroundColor;
    private static boolean isTalking = true;
    private static int[] talkCounters = new int[10];
    private static int textCounter = 2;
    private static String[] textInfo = { "Omniscient Narrator", "3",
            "Once, you were a crippled fledgling, unable to MOVE or even INTERACT. \nFortunately for you, a majestic voice imparted upon you great guidance:\nUse the ARROW KEYS to MOVE and SPACE to INTERACT.\n\nWhile regrettably you remained mute, \nyou nevertheless learned that INTERACT signalled others to continue talking!\nYou could even talk to others multiple times! Incredible!",
            "With this new knowledge, you became emboldened by the flame of ambition.\nThe world would bow at your talons, given your newfound powers!\nAnd thus, your journey began...\n\n\n\nNote for playtesting: use ESCAPE to fly if you can't solve puzzles." };
    // this line of code is very long - faber
    // Awfully convenient for the author of story
    private static boolean boatObtained;
    private static boolean strawberry;
    private static int torchCounter;
    private static boolean torchObtained;
    private static boolean cave;
    private static boolean cheats;

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }

    public MyFrame() {
        cheats = false;
        setSize(WIDTH, HEIGHT);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        strawberry = false;
        boatObtained = false;
        torchCounter = 0;
        KeyListener l = new KeyListener() {
            public void keyPressed(KeyEvent e) {
                int key = e.getKeyCode();
                switch (key) {
                    // 18 by 11
                    case KeyEvent.VK_LEFT:
                        if (!isTalking()) {
                            Game.setFacing(3);
                            if (Game.tileFacingWalkable()) {
                                Game.setxMap(Game.getxMap() - 1);
                                if (torchCounter > 0) {
                                    torchCounter--;
                                }
                            }
                            repaint();
                        } else {

                        }
                        break;
                    case KeyEvent.VK_RIGHT:
                        if (!isTalking()) {
                            Game.setFacing(1);
                            if (Game.tileFacingWalkable()) {
                                Game.setxMap(Game.getxMap() + 1);
                                if (torchCounter > 0) {
                                    torchCounter--;
                                }
                            }
                            repaint();
                        } else {

                        }
                        break;
                    case KeyEvent.VK_UP:
                        if (!isTalking()) {
                            Game.setFacing(0);
                            if (Game.tileFacingWalkable()) {
                                Game.setyMap(Game.getyMap() - 1);
                                if (torchCounter > 0) {
                                    torchCounter--;
                                }
                            }
                            repaint();
                        } else {

                        }
                        break;
                    case KeyEvent.VK_DOWN:
                        if (!isTalking()) {
                            Game.setFacing(2);
                            if (Game.tileFacingWalkable()) {
                                Game.setyMap(Game.getyMap() + 1);
                                if (torchCounter > 0) {
                                    torchCounter--;
                                }
                            }
                            repaint();
                        } else {

                        }
                        break;
                    case KeyEvent.VK_SPACE:
                        if (!isTalking()) {
                            Game.tileFacing().interact();
                            if (torchCounter > 0) {
                                torchCounter--;
                            }
                            repaint();
                        } else {
                            // talking stuff
                            if (textCounter == Integer.parseInt(textInfo[1])) {
                                if (textInfo[textCounter].equals(
                                    "With this new knowledge, you became emboldened by the flame of ambition.\nThe world would bow at your talons, given your newfound powers!\nAnd thus, your journey began...\n\n\n\nNote for playtesting: use ESCAPE to fly if you can't solve puzzles.")) {
                                    Game.loadMap("Map1.txt", 2, 36, new Color(128, 255, 128));
                                    p1.setBackground(backgroundColor);
                                }
                                if (textInfo[textCounter].equals("oh. finally.")) {
                                    strawberry = true;
                                }
                                if (textInfo[textCounter].equals("You've laid your talons on a well-made torch.")) {
                                    torchObtained = true;
                                }
                                textCounter = 2;
                                isTalking = false;
                            } else {
                                textCounter++;
                            }
                            repaint();
                        }
                        break;
                    case KeyEvent.VK_ESCAPE:
                        // // Game.loadMap("Map3.txt", 2, 36, new Color(128, 255, 128));
                        // Game.loadMap("Map2.txt", 18, 24, Color.black);
                        // p1.setBackground(backgroundColor);
                        if (!cheats) {
                            for (int col = 0; col < Game.getMap().length; col++) {
                                for (int row = 0; row < Game.getMap()[col].length; row++) {
                                    Game.getMap()[row][col].setWalkable(true);
                                }
                            }
                            cheats = true;
                        } else {
                            for (int col = 0; col < Game.getMap().length; col++) {
                                for (int row = 0; row < Game.getMap()[col].length; row++) {
                                    Game.getMap()[row][col].setWalking();
                                }
                            }
                            cheats = false;
                        }
                        break;
                }
            }
            // other keylistener methods
            // region

            public void keyTyped(KeyEvent e) {

            }

            public void keyReleased(KeyEvent e) {

            }
            // endregion
        };
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.addKeyListener(l);
        this.setContentPane(p1);
        p1.setBackground(backgroundColor);
        p1.setSize(1207, 600);
        try {
            getContentPane().add(p1, BorderLayout.PAGE_START);
        } catch (Exception e) {
        }
    }

    public static class CenterPanel extends JPanel {

        // @Override
        public void paintComponent(Graphics g) {
            int t;
            super.paintComponent(g);

            // 18 x 11
            for (int col = Game.getxMap(); col < Game.getxMap() + 18; col++) {
                for (int row = Game.getyMap(); row < Game.getyMap() + 11; row++) {
                    t = Game.getMap()[col][row].getType();
                    if (!isTalking) {
                        paintType(g, t, col, row);
                        if (col == Game.getxMap() + 8 && row == Game.getyMap() + 5) {
                            paintType(g, 50, col, row);
                        }
                    } else {
                        paintType(g, t, col, row);
                        if (col == Game.getxMap() + 8 && row == Game.getyMap() + 5) {
                            paintType(g, 50, col, row);
                        }
                        if (col == Game.getxMap() + 17 && row == Game.getyMap() + 10) {
                            paintType(g, t, col, row);
                            paintType(g, 99, 0, 0);
                            paintType(g, 98, 0, 0);
                        }
                    }
                }
            }
            if (torchCounter > 0) {
                g.setColor(new Color(201, 201, 199));
                g.setFont(new Font("Monospaced", Font.BOLD, 25));
                g.drawString(torchCounter + "", 50, 50);
            }
        }

        /**
         * 0 nothing
         * 1 grass
         * 2 rock
         * 3 tree
         * 4 flowers
         * 5 big grass
         * 6 big grass flowers
         * 7 is crushed flowers
         * 10 boulder
         * 11-18 is river
         * 19 is bridge
         * 20 cave background
         * 21 cave pebbles
         * 22 right, 23 left, 24 top, 25 bot
         * 26 tright, 27 bright, 28 tleft, 29 bleft
         * 50 player
         * 51 boulder
         * 52 53 helper
         * 54 55 slime
         * 56 sign 1
         * 57 58 lever 1
         * 59 sign 2
         * 60 strawberry
         * 61 62 fox
         * 63 64 lever 2
         * 65 66 sign 3 4
         * 67 boat tree
         * 98 99 text
         * 
         * @param g
         * @param t   type
         * @param row
         * @param col
         */
        public void paintType(Graphics g, int t, int row, int col) {
            int x = (row - Game.getxMap()) * 71 + 71;
            int y = (col - Game.getyMap()) * 60 + 60;
            switch (t) {
                default:
                case 0:
                    // background
                    g.setColor(backgroundColor);
                    g.fillRect(x - 71, y - 60, 71, 60);
                    break;
                case 1:
                    // grass
                    g.setColor(new Color(27, 202, 8));
                    g.fillRect(x - 55, y - 45, 4, 4);
                    g.fillRect(x - 40, y - 20, 4, 4);
                    g.fillRect(x - 35, y - 40, 4, 4);
                    g.fillRect(x - 22, y - 35, 4, 4);
                    break;
                case 2:
                    // rock
                    int[] xPoints = { x - 71, x - 60, x - 55, x - 35, x - 20, x - 15, x };
                    int[] yPoints = { y, y - 30, y - 20, y - 60, y - 25, y - 35, y };
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints, yPoints, 7);
                    g.setColor(Color.gray);
                    g.fillPolygon(xPoints, yPoints, 7);
                    break;
                case 3:
                case 67:
                    // tree
                    int[] xPoints16 = { x - 35, x - 50, x - 43, x - 55, x - 40, x - 40, x - 30, x - 30, x - 15, x - 27,
                            x - 20 };
                    int[] yPoints16 = { y - 60, y - 35, y - 35, y - 15, y - 15, y, y, y - 15, y - 15, y - 35, y - 35 };
                    g.setColor(new Color(85, 135, 81));
                    g.fillPolygon(xPoints16, yPoints16, 11);
                    g.setColor(new Color(166, 103, 103));
                    g.fillRect(x - 40, y - 15, 10, 15);
                    break;
                case 4:
                    // flowers
                    g.setColor(new Color(27, 202, 8));
                    g.fillRect(x - 55, y - 45, 4, 4);
                    g.fillRect(x - 40, y - 20, 4, 4);
                    g.fillRect(x - 22, y - 35, 4, 4);

                    g.setColor(Color.white);
                    g.fillOval(x - 58, y - 30, 9, 9);
                    g.fillOval(x - 44, y - 30, 9, 9);
                    g.fillOval(x - 51, y - 37, 9, 9);
                    g.fillOval(x - 51, y - 23, 9, 9);
                    g.setColor(Color.pink);
                    g.fillOval(x - 51, y - 30, 9, 9);

                    g.setColor(Color.white);
                    g.fillOval(x - 38, y - 45, 8, 8);
                    g.fillOval(x - 25, y - 45, 8, 8);
                    g.fillOval(x - 31, y - 52, 8, 8);
                    g.fillOval(x - 31, y - 38, 8, 8);
                    g.setColor(Color.pink);
                    g.fillOval(x - 31, y - 45, 8, 8);
                    break;
                case 5:
                    // big grass
                    // region
                    g.setColor(new Color(28, 174, 12));

                    g.fillRect(x - 48, y - 40, 4, 4);
                    g.fillRect(x - 44, y - 44, 4, 4);
                    g.fillRect(x - 40, y - 40, 4, 4);
                    g.fillRect(x - 36, y - 44, 4, 4);
                    g.fillRect(x - 32, y - 40, 4, 4);

                    g.fillRect(x - 58, y - 25, 4, 4);
                    g.fillRect(x - 54, y - 29, 4, 4);
                    g.fillRect(x - 50, y - 25, 4, 4);
                    g.fillRect(x - 46, y - 29, 4, 4);
                    g.fillRect(x - 42, y - 25, 4, 4);

                    g.fillRect(x - 53, y - 10, 4, 4);
                    g.fillRect(x - 49, y - 14, 4, 4);
                    g.fillRect(x - 45, y - 10, 4, 4);
                    g.fillRect(x - 41, y - 14, 4, 4);
                    g.fillRect(x - 37, y - 10, 4, 4);

                    g.fillRect(x - 26, y - 20, 4, 4);
                    g.fillRect(x - 22, y - 24, 4, 4);
                    g.fillRect(x - 18, y - 20, 4, 4);
                    g.fillRect(x - 14, y - 24, 4, 4);
                    g.fillRect(x - 10, y - 20, 4, 4);

                    g.fillRect(x - 27, y - 50, 4, 4);
                    g.fillRect(x - 23, y - 54, 4, 4);
                    g.fillRect(x - 19, y - 50, 4, 4);
                    g.fillRect(x - 15, y - 54, 4, 4);
                    g.fillRect(x - 11, y - 50, 4, 4);

                    g.fillRect(x - 22, y - 35, 4, 4);
                    g.fillRect(x - 18, y - 39, 4, 4);
                    g.fillRect(x - 14, y - 35, 4, 4);
                    g.fillRect(x - 10, y - 39, 4, 4);
                    g.fillRect(x - 6, y - 35, 4, 4);
                    // endregion
                    break;
                case 6:
                    // big grass flowers
                    paintType(g, 5, row, col);
                    paintType(g, 4, row, col);
                    break;
                case 7:
                    // crushed flowers
                    g.setColor(new Color(27, 202, 8));
                    g.fillRect(x - 55, y - 45, 4, 4);
                    g.fillRect(x - 40, y - 20, 4, 4);
                    g.fillRect(x - 22, y - 35, 4, 4);

                    g.setColor(Color.white);
                    g.fillOval(x - 9, y - 9, 9, 9);
                    g.fillOval(x - 20, y - 50, 9, 9);
                    g.fillOval(x - 51, y - 20, 9, 9);
                    g.fillOval(x - 70, y - 40, 9, 9);
                    g.setColor(Color.pink);
                    g.fillOval(x - 25, y - 30, 9, 9);
                    break;
                case 8:
                    // crushed flowers big grass
                    paintType(g, 5, row, col);
                    g.setColor(Color.white);
                    g.fillOval(x - 9, y - 9, 9, 9);
                    g.fillOval(x - 20, y - 50, 9, 9);
                    g.fillOval(x - 51, y - 20, 9, 9);
                    g.fillOval(x - 70, y - 40, 9, 9);
                    g.setColor(Color.pink);
                    g.fillOval(x - 25, y - 30, 9, 9);
                    break;
                case 9:
                    // bigger grass
                    // region
                    g.setColor(new Color(28, 174, 12));

                    g.fillRect(x - 48, y - 40, 4, 4);
                    g.fillRect(x - 44, y - 44, 4, 4);
                    g.fillRect(x - 40, y - 40, 4, 4);
                    g.fillRect(x - 36, y - 44, 4, 4);
                    g.fillRect(x - 32, y - 40, 4, 4);

                    g.fillRect(x - 58, y - 25, 4, 4);
                    g.fillRect(x - 54, y - 29, 4, 4);
                    g.fillRect(x - 50, y - 25, 4, 4);
                    g.fillRect(x - 46, y - 29, 4, 4);
                    g.fillRect(x - 42, y - 25, 4, 4);

                    g.fillRect(x - 53, y - 10, 4, 4);
                    g.fillRect(x - 49, y - 14, 4, 4);
                    g.fillRect(x - 45, y - 10, 4, 4);
                    g.fillRect(x - 41, y - 14, 4, 4);
                    g.fillRect(x - 37, y - 10, 4, 4);

                    g.fillRect(x - 26, y - 20, 4, 4);
                    g.fillRect(x - 22, y - 24, 4, 4);
                    g.fillRect(x - 18, y - 20, 4, 4);
                    g.fillRect(x - 14, y - 24, 4, 4);
                    g.fillRect(x - 10, y - 20, 4, 4);

                    g.fillRect(x - 27, y - 48, 4, 4);
                    g.fillRect(x - 23, y - 52, 4, 4);
                    g.fillRect(x - 19, y - 48, 4, 4);
                    g.fillRect(x - 15, y - 52, 4, 4);
                    g.fillRect(x - 11, y - 48, 4, 4);

                    g.fillRect(x - 22, y - 35, 4, 4);
                    g.fillRect(x - 18, y - 39, 4, 4);
                    g.fillRect(x - 14, y - 35, 4, 4);
                    g.fillRect(x - 10, y - 39, 4, 4);
                    g.fillRect(x - 6, y - 35, 4, 4);

                    g.fillRect(x - 68, y - 50, 4, 4);
                    g.fillRect(x - 64, y - 54, 4, 4);
                    g.fillRect(x - 60, y - 50, 4, 4);
                    g.fillRect(x - 56, y - 54, 4, 4);
                    g.fillRect(x - 52, y - 50, 4, 4);

                    g.fillRect(x - 46, y - 53, 4, 4);
                    g.fillRect(x - 42, y - 57, 4, 4);
                    g.fillRect(x - 38, y - 53, 4, 4);
                    g.fillRect(x - 34, y - 57, 4, 4);
                    g.fillRect(x - 30, y - 53, 4, 4);

                    g.fillRect(x - 36, y - 28, 4, 4);
                    g.fillRect(x - 32, y - 32, 4, 4);
                    g.fillRect(x - 28, y - 28, 4, 4);
                    g.fillRect(x - 24, y - 32, 4, 4);
                    g.fillRect(x - 20, y - 28, 4, 4);

                    g.fillRect(x - 15, y - 5, 4, 4);
                    g.fillRect(x - 11, y - 9, 4, 4);
                    g.fillRect(x - 7, y - 5, 4, 4);
                    g.fillRect(x - 3, y - 9, 4, 4);
                    g.fillRect(x, y - 5, 5, 4);

                    g.fillRect(x - 13, y - 50, 4, 4);
                    g.fillRect(x - 9, y - 54, 4, 4);
                    g.fillRect(x - 5, y - 50, 4, 4);
                    g.fillRect(x - 1, y - 54, 4, 4);
                    // g.fillRect(x - 6, y - 50, 4, 4);
                    // endregion
                    break;
                case 10:
                    // river boulder
                    g.setColor(new Color(201, 201, 199));
                    int[] xPoints23 = { x - 40, x - 30, x - 20, x - 20, x - 30, x - 40, x - 50, x - 50 };
                    int[] yPoints23 = { y - 45, y - 45, y - 35, y - 25, y - 15, y - 15, y - 25, y - 35 };
                    g.fillPolygon(xPoints23, yPoints23, 8);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints23, yPoints23, 8);
                    break;
                case 11:
                    // river
                    // region
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 65, y - 55, x - 50, y - 55);
                    g.drawLine(x - 53, y - 40, x - 38, y - 40);
                    g.drawLine(x - 10, y - 30, x, y - 30);
                    g.drawLine(x - 71, y - 30, x - 66, y - 30);
                    g.drawLine(x - 30, y - 20, x - 15, y - 20);
                    g.drawLine(x - 49, y - 10, x - 36, y - 10);
                    paintType(g, 10, row, col);
                    break;
                case 12:
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 40, y - 55, x - 25, y - 55);
                    g.drawLine(x - 38, y - 40, x - 23, y - 40);
                    g.drawLine(x - 70, y - 30, x - 55, y - 30);
                    g.drawLine(x - 15, y - 20, x, y - 20);
                    g.drawLine(x - 36, y - 10, x - 21, y - 10);
                    paintType(g, 10, row, col);
                    break;
                case 13:
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 25, y - 55, x - 10, y - 55);
                    g.drawLine(x - 23, y - 40, x - 8, y - 40);
                    g.drawLine(x - 55, y - 30, x - 40, y - 30);
                    g.drawLine(x - 71, y - 20, x - 55, y - 20);
                    g.drawLine(x - 21, y - 10, x - 6, y - 10);
                    paintType(g, 10, row, col);
                    break;
                case 14:
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 10, y - 55, x, y - 55);
                    g.drawLine(x - 71, y - 55, x - 66, y - 55);
                    g.drawLine(x - 8, y - 40, x, y - 40);
                    g.drawLine(x - 71, y - 40, x - 63, y - 40);
                    g.drawLine(x - 40, y - 30, x - 25, y - 30);
                    g.drawLine(x - 55, y - 20, x - 40, y - 20);
                    g.drawLine(x - 6, y - 10, x, y - 10);
                    g.drawLine(x - 71, y - 10, x - 60, y - 10);
                    paintType(g, 10, row, col);
                    break;
                case 15:
                    // river
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 65, y - 55, x - 50, y - 55);
                    g.drawLine(x - 53, y - 40, x - 38, y - 40);
                    g.drawLine(x - 10, y - 30, x, y - 30);
                    g.drawLine(x - 71, y - 30, x - 66, y - 30);
                    g.drawLine(x - 30, y - 20, x - 15, y - 20);
                    g.drawLine(x - 49, y - 10, x - 36, y - 10);
                    break;
                case 16:
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 40, y - 55, x - 25, y - 55);
                    g.drawLine(x - 38, y - 40, x - 23, y - 40);
                    g.drawLine(x - 70, y - 30, x - 55, y - 30);
                    g.drawLine(x - 15, y - 20, x, y - 20);
                    g.drawLine(x - 36, y - 10, x - 21, y - 10);
                    break;
                case 17:
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 25, y - 55, x - 10, y - 55);
                    g.drawLine(x - 23, y - 40, x - 8, y - 40);
                    g.drawLine(x - 55, y - 30, x - 40, y - 30);
                    g.drawLine(x - 71, y - 20, x - 55, y - 20);
                    g.drawLine(x - 21, y - 10, x - 6, y - 10);
                    break;
                case 18:
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(15, 94, 156));
                    g.drawLine(x - 10, y - 55, x, y - 55);
                    g.drawLine(x - 71, y - 55, x - 66, y - 55);
                    g.drawLine(x - 8, y - 40, x, y - 40);
                    g.drawLine(x - 71, y - 40, x - 63, y - 40);
                    g.drawLine(x - 40, y - 30, x - 25, y - 30);
                    g.drawLine(x - 55, y - 20, x - 40, y - 20);
                    g.drawLine(x - 6, y - 10, x, y - 10);
                    g.drawLine(x - 71, y - 10, x - 60, y - 10);
                    break;
                // endregion
                case 19:
                    // bridge
                    g.setColor(new Color(28, 163, 236));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    g.setColor(new Color(178, 98, 71));
                    g.fillRect(x - 65, y - 60, 60, 60);
                    g.fillRect(x - 65, y - 65, 5, 5);
                    g.fillRect(x - 10, y - 65, 5, 5);
                    g.fillRect(x - 65, y, 5, 5);
                    g.fillRect(x - 10, y, 5, 5);
                    g.setColor(Color.black);
                    g.drawRect(x - 65, y - 65, 5, 5);
                    g.drawRect(x - 10, y - 65, 5, 5);
                    g.drawRect(x - 65, y, 5, 5);
                    g.drawRect(x - 10, y, 5, 5);
                    g.drawRect(x - 65, y - 10, 60, 10);
                    g.drawRect(x - 65, y - 20, 60, 10);
                    g.drawRect(x - 65, y - 30, 60, 10);
                    g.drawRect(x - 65, y - 40, 60, 10);
                    g.drawRect(x - 65, y - 50, 60, 10);
                    g.drawRect(x - 65, y - 60, 60, 10);
                    break;
                case 20:
                    // cave background
                    g.setColor(new Color(77, 37, 15));
                    g.fillRect(x - 71, y - 60, 71, 60);
                    break;
                case 21:
                    // cave pebbles
                    paintType(g, 20, row, col);
                    g.setColor(new Color(181, 176, 161));
                    g.fillRect(x - 63, y - 34, 4, 4);
                    g.fillRect(x - 47, y - 22, 4, 4);
                    g.fillRect(x - 30, y - 7, 4, 4);
                    g.fillRect(x - 22, y - 38, 4, 4);
                    g.fillRect(x - 10, y - 50, 4, 4);
                    break;
                case 22:
                    // wall right
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 50, y - 60, 50, 60);
                    g.setColor(Color.black);
                    // g.drawLine(x-50, y-60, x-50, y);
                    break;
                case 23:
                    // wall left
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 60, 50, 60);
                    g.setColor(Color.black);
                    // g.drawLine(x-71, y-60, x-71, y);
                    break;
                case 24:
                    // wall top
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 60, 71, 50);
                    g.setColor(Color.black);
                    // g.drawLine(x-71, y-10, x, y-10);
                    break;
                case 25:
                    // wall bottom
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 50, 71, 50);
                    g.setColor(Color.black);
                    // g.drawLine(x-71, y-50, x, y-50);
                    break;
                case 26:
                    // wall topright
                    paintType(g, 20, row, col);
                    paintType(g, 24, row, col);
                    paintType(g, 22, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 60, 71, 49);
                    break;
                case 27:
                    // wall bottomright
                    paintType(g, 20, row, col);
                    paintType(g, 25, row, col);
                    paintType(g, 22, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 49, 71, 50);
                    break;
                case 28:
                    // wall topleft
                    paintType(g, 20, row, col);
                    paintType(g, 24, row, col);
                    paintType(g, 23, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 60, 71, 49);
                    break;
                case 29:
                    // wall bottomleft
                    paintType(g, 20, row, col);
                    paintType(g, 25, row, col);
                    paintType(g, 23, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 49, 71, 49);
                    break;
                case 30:
                    // wall top right s
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 50, y - 60, 50, 50);
                    g.setColor(Color.black);
                    // g.drawLine(x-50, y-60, x-50, y);
                    break;
                case 31:
                    // wall top left s
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 60, 50, 50);
                    g.setColor(Color.black);
                    // g.drawLine(x-71, y-60, x-71, y);
                    break;
                case 32:
                    // wall bottom right s
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 50, y - 50, 50, 50);
                    g.setColor(Color.black);
                    // g.drawLine(x-71, y-10, x, y-10);
                    break;
                case 33:
                    // wall bottom left s
                    paintType(g, 20, row, col);
                    g.setColor(new Color(58, 27, 9));
                    g.fillRect(x - 71, y - 50, 50, 50);
                    g.setColor(Color.black);
                    // g.drawLine(x-71, y-50, x, y-50);
                    break;
                case 50:
                    // player
                    if (cave && torchObtained) {
                        if (torchCounter > 0) {
                            int[] xPoints36 = { x - 14, x - 16, x - 20, x - 19, x - 17 };
                            int[] yPoints36 = { y - 27, y - 37, y - 31, y - 29, y - 25 };
                            g.setColor(new Color(226, 88, 34));
                            g.fillPolygon(xPoints36, yPoints36, 5);
                        }
                        int[] xPoints37 = { x - 19, x - 17, x - 26, x - 28 };
                        int[] yPoints37 = { y - 29, y - 25, y - 20, y - 23 };
                        g.setColor(new Color(164, 116, 73));
                        g.fillPolygon(xPoints37, yPoints37, 4);
                        g.setColor(Color.black);
                        g.drawPolygon(xPoints37, yPoints37, 4);
                    }
                    switch (Game.getFacing()) {
                        case 0:
                            // up
                            // head
                            g.setColor(Color.white);
                            g.fillOval(x - 50, y - 57, 25, 25);
                            g.setColor(Color.black);
                            g.drawOval(x - 50, y - 57, 25, 25);
                            // eyes
                            g.setColor(Color.black);
                            g.drawOval(x - 35, y - 51, 2, 3);
                            g.fillOval(x - 35, y - 51, 2, 3);
                            // beak
                            g.setColor(Color.white);
                            int[] xPoints1 = { x - 27, x - 20, x - 31 };
                            int[] yPoints1 = { y - 49, y - 60, y - 54 };
                            g.fillPolygon(xPoints1, yPoints1, 3);
                            g.setColor(Color.black);
                            g.drawLine(x - 27, y - 49, x - 20, y - 60);
                            g.drawLine(x - 31, y - 54, x - 20, y - 60);
                            // cloak fill
                            g.setColor(new Color(115, 19, 19));
                            int[] xPoints2 = { x - 52, x - 45, x - 31, x - 22 };
                            int[] yPoints2 = { y, y - 33, y - 34, y };
                            g.fillPolygon(xPoints2, yPoints2, 4);
                            // cloak outline
                            // g.setColor(new Color(103, 17, 17));
                            g.setColor(Color.black);
                            g.drawLine(x - 45, y - 33, x - 52, y);
                            g.drawLine(x - 39, y - 31, x - 43, y);
                            g.drawLine(x - 35, y - 33, x - 32, y);
                            g.drawLine(x - 31, y - 34, x - 22, y);
                            g.drawLine(x - 52, y, x - 22, y);
                            break;
                        case 1:
                            // right
                            // head
                            g.setColor(Color.white);
                            g.fillOval(x - 50, y - 57, 25, 25);
                            g.setColor(Color.black);
                            g.drawOval(x - 50, y - 57, 25, 25);
                            // eyes
                            g.setColor(Color.black);
                            g.drawOval(x - 35, y - 48, 2, 3);
                            g.fillOval(x - 35, y - 48, 2, 3);
                            // beak
                            g.setColor(Color.white);
                            int[] xPoints3 = { x - 27, x - 15, x - 27 };
                            int[] yPoints3 = { y - 41, y - 44, y - 47 };
                            g.fillPolygon(xPoints3, yPoints3, 3);
                            g.setColor(Color.black);
                            g.drawLine(x - 25, y - 41, x - 15, y - 44);
                            g.drawLine(x - 25, y - 47, x - 15, y - 44);
                            // cloak fill
                            g.setColor(new Color(115, 19, 19));
                            int[] xPoints4 = { x - 52, x - 45, x - 31, x - 22 };
                            int[] yPoints4 = { y, y - 33, y - 34, y };
                            g.fillPolygon(xPoints4, yPoints4, 4);
                            // cloak outline
                            // g.setColor(new Color(103, 17, 17));
                            g.setColor(Color.black);
                            g.drawLine(x - 45, y - 33, x - 52, y);
                            g.drawLine(x - 39, y - 31, x - 43, y);
                            g.drawLine(x - 35, y - 33, x - 32, y);
                            g.drawLine(x - 31, y - 34, x - 22, y);
                            g.drawLine(x - 52, y, x - 22, y);
                            break;
                        case 2:
                            // down
                            // head
                            g.setColor(Color.white);
                            g.fillOval(x - 50, y - 57, 25, 25);
                            g.setColor(Color.black);
                            g.drawOval(x - 50, y - 57, 25, 25);
                            // eyes
                            g.setColor(Color.black);
                            g.fillOval(x - 33, y - 42, 3, 3);
                            g.fillOval(x - 43, y - 42, 3, 3);
                            // cloak fill
                            g.setColor(new Color(115, 19, 19));
                            int[] xPoints5 = { x - 52, x - 45, x - 31, x - 22 };
                            int[] yPoints5 = { y, y - 33, y - 34, y };
                            g.fillPolygon(xPoints5, yPoints5, 4);
                            // cloak outline
                            // g.setColor(new Color(103, 17, 17));
                            g.setColor(Color.black);
                            g.drawLine(x - 45, y - 33, x - 52, y);
                            g.drawLine(x - 39, y - 31, x - 43, y);
                            g.drawLine(x - 35, y - 33, x - 32, y);
                            g.drawLine(x - 31, y - 34, x - 22, y);
                            g.drawLine(x - 52, y, x - 22, y);
                            // beak
                            g.setColor(Color.white);
                            int x1Beak = 40;
                            int y1Beak = 33;
                            int x2Beak = 34;
                            int y2Beak = 33;
                            int x3Beak = 37;
                            int y3Beak = 17;
                            int[] xPoints6 = { x - x1Beak, x - x3Beak, x - x2Beak };
                            int[] yPoints6 = { y - y1Beak, y - y3Beak, y - y2Beak };
                            g.fillPolygon(xPoints6, yPoints6, 3);
                            g.setColor(Color.black);
                            g.drawLine(x - x1Beak, y - y1Beak, x - x3Beak, y - y3Beak);
                            g.drawLine(x - x2Beak, y - y2Beak, x - x3Beak, y - y3Beak);
                            break;
                        case 3:
                            // left
                            // head
                            g.setColor(Color.white);
                            g.fillOval(x - 50, y - 57, 25, 25);
                            g.setColor(Color.black);
                            g.drawOval(x - 50, y - 57, 25, 25);
                            // eyes
                            g.setColor(Color.black);
                            g.drawOval(x - 42, y - 48, 2, 3);
                            g.fillOval(x - 42, y - 48, 2, 3);
                            // beak
                            g.setColor(Color.white);
                            int[] xPoints7 = { x - 48, x - 58, x - 48 };
                            int[] yPoints7 = { y - 41, y - 44, y - 47 };
                            g.fillPolygon(xPoints7, yPoints7, 3);
                            g.setColor(Color.black);
                            g.drawLine(x - 50, y - 41, x - 60, y - 44);
                            g.drawLine(x - 50, y - 47, x - 60, y - 44);
                            // cloak fill
                            g.setColor(new Color(115, 19, 19));
                            int[] xPoints9 = { x - 52, x - 45, x - 31, x - 22 };
                            int[] yPoints9 = { y, y - 33, y - 34, y };
                            g.fillPolygon(xPoints9, yPoints9, 4);
                            // cloak outline
                            // g.setColor(new Color(103, 17, 17));
                            g.setColor(Color.black);
                            g.drawLine(x - 45, y - 33, x - 52, y);
                            g.drawLine(x - 39, y - 31, x - 43, y);
                            g.drawLine(x - 35, y - 33, x - 32, y);
                            g.drawLine(x - 31, y - 34, x - 22, y);
                            g.drawLine(x - 52, y, x - 22, y);
                            break;
                    }
                    int h = Game.getMap()[Game.getxMap() + 8][Game.getyMap() + 5].getType();
                    if (boatObtained && h > 14 && h < 19) {
                        g.setColor(new Color(102, 51, 0));
                        int[] xPoints24 = { x - 70, x - 55, x - 16, x - 1 };
                        int[] yPoints24 = { y - 25, y, y, y - 25 };
                        g.fillPolygon(xPoints24, yPoints24, 4);
                        g.setColor(Color.black);
                        g.drawPolygon(xPoints24, yPoints24, 4);
                    }
                    break;
                case 51:
                    // boulder
                    g.setColor(Color.black);
                    g.fillOval(x - 65, y - 60, 60, 60);
                    g.setColor(Color.gray);
                    g.fillOval(x - 64, y - 59, 58, 58);
                    break;
                case 52:
                    // helper
                    // region
                    // wings
                    // old pastel blue color new Color(129, 248, 252)
                    g.setColor(new Color(220, 210, 175));
                    int[] xPoints8 = { x - 65, x - 55, x - 65, x - 44, x - 26, x - 5, x - 15, x - 5, x - 30, x - 40 };
                    int[] yPoints8 = { y - 55, y - 30, y - 5, y - 23, y - 23, y - 5, y - 30, y - 55, y - 40, y - 40 };
                    g.fillPolygon(xPoints8, yPoints8, 10);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints8, yPoints8, 10);

                    // fake eyes
                    g.setColor(Color.black);
                    g.fillOval(x - 58, y - 25, 10, 10);
                    g.fillOval(x - 20, y - 25, 10, 10);

                    g.fillOval(x - 58, y - 46, 10, 10);
                    g.fillOval(x - 21, y - 46, 10, 10);

                    g.setColor(Color.yellow);
                    g.fillOval(x - 56, y - 23, 6, 6);
                    g.fillOval(x - 18, y - 23, 6, 6);

                    g.fillOval(x - 56, y - 44, 6, 6);
                    g.fillOval(x - 19, y - 44, 6, 6);

                    // coat
                    g.setColor(new Color(220, 210, 175));
                    int[] xPoints9 = { x - 45, x - 35, x - 25 };
                    int[] yPoints9 = { y - 15, y - 55, y - 15 };
                    g.fillPolygon(xPoints9, yPoints9, 3);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints9, yPoints9, 3);

                    // sleeve
                    xPoints9[0] = x - 37;
                    xPoints9[1] = x - 35;
                    xPoints9[2] = x - 33;
                    yPoints9[0] = y - 45;
                    yPoints9[1] = y - 20;
                    yPoints9[2] = y - 45;
                    g.setColor(Color.yellow);
                    g.fillPolygon(xPoints9, yPoints9, 3);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints9, yPoints9, 3);

                    // headtip
                    int[] xPoints10 = { x - 37, x - 35, x - 33, x - 35 };
                    int[] yPoints10 = { y - 55, y - 60, y - 55, y - 50 };
                    g.setColor(Color.yellow);
                    g.fillPolygon(xPoints10, yPoints10, 4);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints10, yPoints10, 4);

                    // head
                    // old purple new Color(205, 87, 255)
                    int[] xPoints11 = { x - 45, x - 38, x - 35, x - 32, x - 25, x - 35 };
                    int[] yPoints11 = { y - 50, y - 56, y - 52, y - 56, y - 50, y - 40 };
                    g.setColor(new Color(220, 210, 175));
                    g.fillPolygon(xPoints11, yPoints11, 6);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints11, yPoints11, 6);

                    // feet
                    g.setColor(Color.black);
                    g.drawLine(x - 42, y - 15, x - 42, y - 7);
                    g.drawLine(x - 42, y - 7, x - 46, y - 4);
                    g.drawLine(x - 42, y - 7, x - 38, y - 4);
                    g.drawLine(x - 42, y - 7, x - 39, y - 8);

                    g.drawLine(x - 28, y - 15, x - 28, y - 7);
                    g.drawLine(x - 28, y - 7, x - 32, y - 4);
                    g.drawLine(x - 28, y - 7, x - 24, y - 4);
                    g.drawLine(x - 28, y - 7, x - 25, y - 8);

                    // eyes
                    // g.setColor(Color.yellow);
                    // g.fillOval(x - 40, y - 52, 4, 4);
                    // g.fillOval(x - 33, y - 52, 4, 4);
                    g.setColor(Color.black);
                    g.fillOval(x - 39, y - 51, 2, 2);
                    g.fillOval(x - 32, y - 51, 2, 2);

                    // mouth
                    g.setColor(Color.black);
                    g.drawLine(x - 39, y - 47, x - 35, y - 44);
                    g.drawLine(x - 35, y - 44, x - 31, y - 47);
                    break;
                case 53:
                    // helperTwo

                    // wings
                    // old pastel blue color new Color(129, 248, 252)
                    g.setColor(new Color(220, 210, 175));
                    int[] xPoints12 = { x - 65, x - 55, x - 65, x - 44, x - 26, x - 5, x - 15, x - 5, x - 30, x - 40 };
                    int[] yPoints12 = { y - 55, y - 30, y - 5, y - 23, y - 23, y - 5, y - 30, y - 55, y - 40, y - 40 };
                    g.fillPolygon(xPoints12, yPoints12, 10);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints12, yPoints12, 10);

                    // fake eyes
                    g.setColor(Color.black);
                    g.fillOval(x - 58, y - 25, 10, 10);
                    g.fillOval(x - 20, y - 25, 10, 10);

                    g.fillOval(x - 58, y - 46, 10, 10);
                    g.fillOval(x - 21, y - 46, 10, 10);

                    g.setColor(Color.yellow);
                    g.fillOval(x - 56, y - 23, 6, 6);
                    g.fillOval(x - 18, y - 23, 6, 6);

                    g.fillOval(x - 56, y - 44, 6, 6);
                    g.fillOval(x - 19, y - 44, 6, 6);

                    // coat
                    g.setColor(new Color(220, 210, 175));
                    int[] xPoints13 = { x - 45, x - 35, x - 25 };
                    int[] yPoints13 = { y - 15, y - 55, y - 15 };
                    g.fillPolygon(xPoints13, yPoints13, 3);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints13, yPoints13, 3);

                    // sleeve
                    xPoints13[0] = x - 37;
                    xPoints13[1] = x - 35;
                    xPoints13[2] = x - 33;
                    yPoints13[0] = y - 45;
                    yPoints13[1] = y - 20;
                    yPoints13[2] = y - 45;
                    g.setColor(Color.yellow);
                    g.fillPolygon(xPoints13, yPoints13, 3);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints13, yPoints13, 3);

                    // feet
                    g.setColor(Color.black);
                    g.drawLine(x - 42, y - 15, x - 42, y - 7);
                    g.drawLine(x - 42, y - 7, x - 46, y - 4);
                    g.drawLine(x - 42, y - 7, x - 38, y - 4);
                    g.drawLine(x - 42, y - 7, x - 39, y - 8);

                    g.drawLine(x - 28, y - 15, x - 28, y - 7);
                    g.drawLine(x - 28, y - 7, x - 32, y - 4);
                    g.drawLine(x - 28, y - 7, x - 24, y - 4);
                    g.drawLine(x - 28, y - 7, x - 25, y - 8);

                    // head
                    // old purple new Color(205, 87, 255)
                    int[] xPoints15 = { x - 45, x - 38, x - 35, x - 32, x - 25, x - 35 };
                    int[] yPoints15 = { y - 47, y - 53, y - 49, y - 53, y - 47, y - 37 };
                    g.setColor(new Color(220, 210, 175));
                    g.fillPolygon(xPoints15, yPoints15, 6);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints15, yPoints15, 6);

                    // headtip
                    int[] xPoints14 = { x - 37, x - 35, x - 33, x - 35 };
                    int[] yPoints14 = { y - 52, y - 57, y - 52, y - 47 };
                    g.setColor(Color.yellow);
                    g.fillPolygon(xPoints14, yPoints14, 4);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints14, yPoints14, 4);

                    // eyes
                    g.setColor(Color.black);
                    g.fillOval(x - 39, y - 48, 2, 2);
                    g.fillOval(x - 32, y - 48, 2, 2);
                    // g.setColor(Color.yellow);
                    // g.fillOval(x - 40, y - 52, 4, 4);
                    // g.fillOval(x - 33, y - 52, 4, 4);

                    // mouth
                    g.setColor(Color.black);
                    g.drawLine(x - 39, y - 44, x - 35, y - 41);
                    g.drawLine(x - 35, y - 41, x - 31, y - 44);
                    // endregion
                    break;
                case 54:
                    // depressed slime
                    // region
                    // body
                    if (strawberry) {
                        g.setColor(new Color(200, 63, 73));
                    } else {
                        g.setColor(new Color(0, 162, 255));
                    }
                    g.fillOval(x - 65, y - 40, 60, 35);
                    g.setColor(Color.black);
                    g.drawOval(x - 65, y - 40, 60, 35);

                    // left eye
                    int[] xPoints17 = { x - 55, x - 40, x - 37, x - 52 };
                    int[] yPoints17 = { y - 24, y - 30, y - 28, y - 22 };
                    g.fillPolygon(xPoints17, yPoints17, 4);

                    // right eye
                    int[] xPoints18 = { x - 32, x - 17, x - 14, x - 29 };
                    int[] yPoints18 = { y - 28, y - 22, y - 24, y - 30, };
                    g.fillPolygon(xPoints18, yPoints18, 4);

                    // mouth
                    g.drawArc(x - 42, y - 15, 20, 5, 45, 135);
                    break;
                case 55:
                    // slime 2
                    // body
                    if (strawberry) {
                        g.setColor(new Color(200, 63, 73));
                    } else {
                        g.setColor(new Color(0, 162, 255));
                    }
                    g.fillOval(x - 65, y - 40 + 5, 60, 30);
                    g.setColor(Color.black);
                    g.drawOval(x - 65, y - 40 + 5, 60, 30);

                    // left eye
                    int[] xPoints19 = { x - 55, x - 40, x - 37, x - 52 };
                    int[] yPoints19 = { y - 24 + 3, y - 30 + 3, y - 28 + 3, y - 22 + 3 };
                    g.fillPolygon(xPoints19, yPoints19, 4);

                    // right eye
                    int[] xPoints20 = { x - 32, x - 17, x - 14, x - 29 };
                    int[] yPoints20 = { y - 28 + 3, y - 22 + 3, y - 24 + 3, y - 30 + 3 };
                    g.fillPolygon(xPoints20, yPoints20, 4);

                    // mouth
                    g.drawArc(x - 42, y - 15 + 3, 20, 5, 45, 135);
                    // endregion
                    break;
                case 56:
                    // sign 1
                    g.setColor(new Color(222, 184, 135));
                    g.fillRect(x - 39, y - 20, 8, 10);
                    g.fillRect(x - 60, y - 60, 50, 40);
                    // if (strawberry) {
                    // g.setColor(Color.yellow);
                    // } else {
                    g.setColor(Color.black);
                    // }
                    g.drawRect(x - 39, y - 20, 8, 10);
                    g.drawRect(x - 60, y - 60, 50, 40);
                    g.drawLine(x - 50, y - 40, x - 20, y - 40);
                    g.drawLine(x - 50, y - 30, x - 20, y - 30);
                    g.drawLine(x - 50, y - 50, x - 20, y - 50);
                    break;
                case 57:
                    // lever
                    g.setColor(new Color(77, 171, 218));
                    g.fillRect(x - 55, y - 45, 40, 30);
                    g.setColor(Color.black);
                    g.drawRect(x - 55, y - 45, 40, 30);
                    g.drawRect(x - 42, y - 34, 15, 7);

                    g.setColor(new Color(255, 255, 255));
                    g.drawLine(x - 35, y - 30, x - 50, y - 50);
                    g.setColor(Color.red);
                    g.fillOval(x - 53, y - 53, 5, 5);
                    break;
                case 58:
                    // lever2
                    g.setColor(new Color(77, 171, 218));
                    g.fillRect(x - 55, y - 45, 40, 30);
                    g.setColor(Color.black);
                    g.drawRect(x - 55, y - 45, 40, 30);
                    g.drawRect(x - 42, y - 34, 15, 7);

                    g.setColor(new Color(255, 255, 255));
                    g.drawLine(x - 35, y - 30, x - 20, y - 50);
                    g.setColor(Color.red);
                    g.fillOval(x - 22, y - 53, 5, 5);
                    break;
                case 59:
                    // sign 2
                    g.setColor(new Color(222, 184, 135));
                    g.fillRect(x - 39, y - 20, 8, 10);
                    g.fillRect(x - 60, y - 60, 50, 40);
                    g.setColor(Color.black);
                    g.drawRect(x - 39, y - 20, 8, 10);
                    g.drawRect(x - 60, y - 60, 50, 40);
                    g.drawLine(x - 50, y - 40, x - 20, y - 40);
                    g.drawLine(x - 50, y - 30, x - 20, y - 30);
                    g.drawLine(x - 50, y - 50, x - 20, y - 50);
                    break;
                case 60:
                    // strawberry
                    int[] xPoints21 = { x - 50, x - 50, x - 35, x - 20, x - 20 };
                    int[] yPoints21 = { y - 45, y - 20, y, y - 20, y - 45 };
                    g.setColor(new Color(200, 63, 73));
                    g.fillPolygon(xPoints21, yPoints21, 5);
                    g.setColor(new Color(17, 240, 132));
                    int[] xPoints22 = { x - 55, x - 15, x - 33, x - 35, x - 37 };
                    int[] yPoints22 = { y - 45, y - 45, y - 50, y - 60, y - 50 };
                    g.fillPolygon(xPoints22, yPoints22, 5);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints21, yPoints21, 5);
                    g.drawPolygon(xPoints22, yPoints22, 5);
                    break;
                case 61:
                    // friendly fox
                    // region
                    // tail
                    g.setColor(new Color(255, 125, 25));
                    int[] xPoints26 = { x - 30, x - 8, x - 9, x - 23 };
                    int[] yPoints26 = { y - 3, y - 20, y - 38, y - 30 };
                    g.fillPolygon(xPoints26, yPoints26, 4);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints26, yPoints26, 4);

                    // body
                    g.setColor(new Color(255, 125, 25));
                    int[] xPoints25 = { x - 42, x - 48, x - 22, x - 28 };
                    int[] yPoints25 = { y - 30, y, y, y - 30 };
                    g.fillPolygon(xPoints25, yPoints25, 4);
                    g.setColor(Color.white);
                    int[] xPoints27 = { x - 37, x - 43, x - 27, x - 33 };
                    int[] yPoints27 = { y - 30, y - 5, y - 5, y - 30 };
                    g.fillPolygon(xPoints27, yPoints27, 4);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints25, yPoints25, 4);
                    g.drawPolygon(xPoints27, yPoints27, 4);

                    // head
                    g.setColor(new Color(255, 125, 25));
                    int[] xPoints24 = { x - 55, x - 45, x - 25, x - 15, x - 18, x - 16, x - 54, x - 52 };
                    int[] yPoints24 = { y - 57 + 3, y - 50 + 3, y - 50 + 3, y - 57 + 3, y - 35 + 3, y - 30 + 3,
                            y - 30 + 3, y - 35 + 3 };
                    g.fillPolygon(xPoints24, yPoints24, 8);

                    // mouth
                    g.setColor(Color.white);
                    int[] xPoints28 = { x - 35, x - 44, x - 26 };
                    int[] yPoints28 = { y - 35 + 3, y - 30 + 3, y - 30 + 3 };
                    g.fillPolygon(xPoints28, yPoints28, 3);

                    // black outlines + nose and eyes
                    // eyes
                    g.setColor(Color.black);
                    g.drawLine(x - 35, y - 33 + 3, x - 35, y - 36 + 3);
                    g.drawLine(x - 37, y - 36 + 3, x - 33, y - 36 + 3);
                    g.drawLine(x - 43, y - 38 + 3, x - 43, y - 42 + 3);
                    g.drawLine(x - 27, y - 38 + 3, x - 27, y - 42 + 3);
                    g.drawPolygon(xPoints24, yPoints24, 8);
                    g.drawPolygon(xPoints28, yPoints28, 3);
                    break;
                case 62:
                    // friendly fox 2
                    // tail
                    g.setColor(new Color(255, 125, 25));
                    int[] xPoints29 = { x - 30, x - 8, x - 9, x - 23 };
                    int[] yPoints29 = { y - 3, y - 20, y - 38, y - 30 };
                    g.fillPolygon(xPoints29, yPoints29, 4);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints29, yPoints29, 4);

                    // body
                    g.setColor(new Color(255, 125, 25));
                    int[] xPoints30 = { x - 42, x - 48, x - 22, x - 28 };
                    int[] yPoints30 = { y - 30, y, y, y - 30 };
                    g.fillPolygon(xPoints30, yPoints30, 4);
                    g.setColor(Color.white);
                    int[] xPoints31 = { x - 37, x - 43, x - 27, x - 33 };
                    int[] yPoints31 = { y - 30, y - 5, y - 5, y - 30 };
                    g.fillPolygon(xPoints31, yPoints31, 4);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints30, yPoints30, 4);
                    g.drawPolygon(xPoints31, yPoints31, 4);

                    // head
                    g.setColor(new Color(255, 125, 25));
                    int[] xPoints32 = { x - 55, x - 45, x - 25, x - 15, x - 18, x - 16, x - 54, x - 52 };
                    int[] yPoints32 = { y - 57, y - 50, y - 50, y - 57, y - 35, y - 30, y - 30, y - 35 };
                    g.fillPolygon(xPoints32, yPoints32, 8);

                    // mouth
                    g.setColor(Color.white);
                    int[] xPoints33 = { x - 35, x - 44, x - 26 };
                    int[] yPoints33 = { y - 35, y - 30, y - 30 };
                    g.fillPolygon(xPoints33, yPoints33, 3);

                    // black outlines + nose and eyes
                    // eyes
                    g.setColor(Color.black);
                    g.drawLine(x - 35, y - 33, x - 35, y - 36);
                    g.drawLine(x - 37, y - 36, x - 33, y - 36);
                    g.drawLine(x - 43, y - 38, x - 43, y - 42);
                    g.drawLine(x - 27, y - 38, x - 27, y - 42);
                    g.drawPolygon(xPoints32, yPoints32, 8);
                    g.drawPolygon(xPoints33, yPoints33, 3);
                    // endregion
                    break;
                case 63:
                    // lever3
                    g.setColor(new Color(77, 171, 218));
                    g.fillRect(x - 55, y - 45, 40, 30);
                    g.setColor(Color.black);
                    g.drawRect(x - 55, y - 45, 40, 30);
                    g.drawRect(x - 42, y - 34, 15, 7);

                    g.setColor(new Color(255, 255, 255));
                    g.drawLine(x - 35, y - 30, x - 50, y - 50);
                    g.setColor(Color.red);
                    g.fillOval(x - 53, y - 53, 5, 5);
                    break;
                case 64:
                    // lever4
                    g.setColor(new Color(77, 171, 218));
                    g.fillRect(x - 55, y - 45, 40, 30);
                    g.setColor(Color.black);
                    g.drawRect(x - 55, y - 45, 40, 30);
                    g.drawRect(x - 42, y - 34, 15, 7);

                    g.setColor(new Color(255, 255, 255));
                    g.drawLine(x - 35, y - 30, x - 20, y - 50);
                    g.setColor(Color.red);
                    g.fillOval(x - 22, y - 53, 5, 5);
                    break;
                case 65:
                case 66:
                    // signs 3 4
                    paintType(g, 59, row, col);
                    break;
                case 68:
                    // cave entrance
                    int[] xPoints34 = { x - 71, x - 71, x - 48, x - 25, x, x };
                    int[] yPoints34 = { y, y - 40, y - 60, y - 60, y - 40, y };
                    int[] xPoints35 = { x - 71 + 12, x - 71 + 12, x - 48, x - 25, x - 12, x - 12 };
                    int[] yPoints35 = { y, y - 40, y - 60 + 10, y - 60 + 10, y - 40, y };
                    g.setColor(Color.gray);
                    g.fillPolygon(xPoints34, yPoints34, 6);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints34, yPoints34, 6);
                    g.fillPolygon(xPoints35, yPoints35, 6);
                    break;
                case 69:
                    // cracked boulder
                    paintType(g, 20, row, col);
                    g.setColor(Color.black);
                    g.fillOval(x - 65, y - 60, 60, 60);
                    g.setColor(Color.gray);
                    g.fillOval(x - 64, y - 59, 58, 58);
                    g.setColor(Color.black);
                    g.drawLine(x - 38, y - 59, x - 30, y - 50);
                    g.drawLine(x - 30, y - 50, x - 45, y - 41);
                    g.drawLine(x - 45, y - 41, x - 28, y - 32);
                    g.drawLine(x - 28, y - 32, x - 37, y - 23);
                    break;
                case 70:
                    // empty brazier
                    paintType(g, 20, row, col);
                    int[] xPoints38 = { x - 45, x - 40, x - 40, x - 45, x - 25, x - 30, x - 30, x - 25 };
                    int[] yPoints38 = { y - 30, y - 20, y - 10, y, y, y - 10, y - 20, y - 30 };
                    g.setColor(new Color(171, 164, 150));
                    g.fillPolygon(xPoints38, yPoints38, 8);
                    g.setColor(Color.black);
                    g.drawPolygon(xPoints38, yPoints38, 8);
                    break;
                case 71:
                    // full brazier
                    paintType(g, 20, row, col);
                    paintType(g, 70, row, col);
                    g.setColor(new Color(226, 88, 34));
                    int[] xPoints39 = { x - 39, x - 43, x - 35, x - 27, x - 31 };
                    int[] yPoints39 = { y - 30, y - 37, y - 50, y - 37, y - 30 };
                    g.fillPolygon(xPoints39, yPoints39, 5);
                    break;
                case 72:
                    // cave boulder
                    paintType(g, 20, row, col);
                    paintType(g, 51, row, col);
                    break;
                case 73:
                    // lever
                    paintType(g, 20, row, col);
                    g.setColor(Color.red);
                    g.fillRect(x - 55, y - 45, 40, 30);
                    g.setColor(Color.black);
                    g.drawRect(x - 55, y - 45, 40, 30);
                    g.drawRect(x - 42, y - 34, 15, 7);

                    g.setColor(new Color(255, 255, 255));
                    g.drawLine(x - 35, y - 30, x - 50, y - 50);
                    g.setColor(new Color(77, 171, 218));
                    g.fillOval(x - 53, y - 53, 5, 5);
                    break;
                case 74:
                    // lever2
                    paintType(g, 20, row, col);
                    g.setColor(Color.red);
                    g.fillRect(x - 55, y - 45, 40, 30);
                    g.setColor(Color.black);
                    g.drawRect(x - 55, y - 45, 40, 30);
                    g.drawRect(x - 42, y - 34, 15, 7);

                    g.setColor(new Color(255, 255, 255));
                    g.drawLine(x - 35, y - 30, x - 20, y - 50);
                    g.setColor(new Color(77, 171, 218));
                    g.fillOval(x - 22, y - 53, 5, 5);
                    break;
                case 75:
                    // lever wall
                    paintType(g, 20, row, col);
                    g.setColor(Color.black);
                    g.fillOval(x - 65, y - 60, 60, 60);
                    g.setColor(Color.red);
                    g.fillOval(x - 64, y - 59, 58, 58);
                    break;
                case 76:
                    // next puzzle cave
                    paintType(g, 20, row, col);
                    paintType(g, 68, row, col);
                    break;
                case 98:
                    // text
                    switch (textInfo[0]) {
                        case "Omniscient Narrator":
                            g.setColor(Color.black);
                            g.setFont(new Font("Monospaced", Font.BOLD, 25));
                            break;
                        case "MAD MOTH":
                            g.setColor(Color.yellow);
                            g.setFont(new Font("DialogInput", Font.BOLD, 35));
                            break;
                        case "Sign":
                            g.setColor(new Color(198, 145, 123));
                            g.setFont(new Font("TimesRoman", Font.BOLD, 30));
                            break;
                        case "sad slime":
                            g.setColor(new Color(0, 0, 255));
                            g.setFont(new Font("SansSerif", Font.ITALIC, 20));
                            break;
                        case "less sad slime":
                            g.setColor(new Color(255, 0, 0));
                            g.setFont(new Font("SansSerif", Font.ITALIC, 20));
                            break;
                        case "Friendly Fox":
                            g.setColor(new Color(255, 125, 25));
                            g.setFont(new Font("Helvetica", Font.BOLD, 30));
                            break;
                        default:
                            g.setColor(Color.black);
                            g.setFont(new Font("SansSerif", Font.PLAIN, 20));
                            break;
                    }
                    g.drawString(textInfo[0] + ":", 55, 400 + g.getFont().getSize() - 25);
                    drawStringLines(textInfo[textCounter], 55, 400 + g.getFont().getSize() + 20, g);
                    break;
                case 99:
                    // text box
                    g.setColor(Color.black);
                    g.fillRect(39, 371, 1200, 250);
                    g.setColor(new Color(210, 210, 210));
                    g.fillRect(49, 381, 1180, 230);
                    break;
            }
            // g.setColor(Color.black);
            // g.drawRect(x - 71, y - 60, 71, 60);
        }

        public void drawStringLines(String str, int x, int y, Graphics g) {
            if (str.indexOf("\n") == -1) {
                g.drawString(str, x, y);
            } else {
                String[] temp = str.split("\n");
                // System.out.println(Arrays.toString(temp));
                for (int i = 0; i < temp.length; i++) {
                    g.drawString(temp[i], x, y + i * g.getFont().getSize());
                }
            }
        }
    }

    public static boolean isCave() {
        return cave;
    }

    public static void setCave(boolean cave) {
        MyFrame.cave = cave;
    }

    public static boolean isTorchObtained() {
        return torchObtained;
    }

    public static void setTorchObtained(boolean torchObtained) {
        MyFrame.torchObtained = torchObtained;
    }

    public static int getTextCounter() {
        return textCounter;
    }

    public static void setTextCounter(int textCounter) {
        MyFrame.textCounter = textCounter;
    }

    public static Color getBackgroundColor() {
        return backgroundColor;
    }

    public static void setBackgroundColor(Color backgroundColor) {
        MyFrame.backgroundColor = backgroundColor;
    }

    public static boolean isTalking() {
        return isTalking;
    }

    public static void setTalking(boolean isTalking) {
        MyFrame.isTalking = isTalking;
    }

    public static String[] getTextInfo() {
        return textInfo;
    }

    public static void setTextInfo(String[] textInfo) {
        MyFrame.textInfo = textInfo;
    }

    public static int getTorchCounter() {
        return torchCounter;
    }

    public static void setTorchCounter(int torchCounter) {
        MyFrame.torchCounter = torchCounter;
    }

    public static int[] getTalkCounters() {
        return talkCounters;
    }

    public static void setTalkCounters(int[] talkCounters) {
        MyFrame.talkCounters = talkCounters;
    }

    public static boolean isStrawberry() {
        return strawberry;
    }

    public static void setStrawberry(boolean strawberry) {
        MyFrame.strawberry = strawberry;
    }

    public static boolean isBoatObtained() {
        return boatObtained;
    }

    public static void setBoatObtained(boolean boatObtained) {
        MyFrame.boatObtained = boatObtained;
    }
}