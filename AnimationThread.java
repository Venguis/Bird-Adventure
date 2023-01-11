public class AnimationThread extends Thread {

    public void run() {
        while (true) {
            wait(1);
            if (!MyFrame.isTalking()) {
                for (int col = 0; col < Game.getMap().length; col++) {
                    for (int row = 0; row < Game.getMap()[col].length; row++) {
                        if (Game.getMap()[row][col].getType() == 52) {
                            Game.getMap()[row][col] = new Interactable(53);
                        } else if (Game.getMap()[row][col].getType() == 53) {
                            Game.getMap()[row][col] = new Interactable(52);
                        }
                        if (Game.getMap()[row][col].getType() == 54) {
                            Game.getMap()[row][col] = new Interactable(55);
                        } else if (Game.getMap()[row][col].getType() == 55) {
                            Game.getMap()[row][col] = new Interactable(54);
                        } 
                        if (Game.getMap()[row][col].getType() == 61) {
                            Game.getMap()[row][col] = new Interactable(62);
                        } else if (Game.getMap()[row][col].getType() == 62) {
                            Game.getMap()[row][col] = new Interactable(61);
                        } 
                        if (Game.getMap()[row][col].getType() == 11) {
                            Game.getMap()[row][col] = new Environment(12);
                            Game.getMap()[row][col].setWalking();
                        } else if (Game.getMap()[row][col].getType() == 12) {
                            Game.getMap()[row][col] = new Environment(13);
                            Game.getMap()[row][col].setWalking();
                        } else if (Game.getMap()[row][col].getType() == 13) {
                            Game.getMap()[row][col] = new Environment(14);
                            Game.getMap()[row][col].setWalking();
                        } else if (Game.getMap()[row][col].getType() == 14) {
                            Game.getMap()[row][col] = new Environment(11);
                            Game.getMap()[row][col].setWalking();
                        }
                        if (Game.getMap()[row][col].getType() == 15) {
                            Game.getMap()[row][col] = new Environment(16);
                            if (!MyFrame.isBoatObtained()) {
                                Game.getMap()[row][col].setWalking();
                            } else {
                                Game.getMap()[row][col].setWalkable(true);
                            }
                        } else if (Game.getMap()[row][col].getType() == 16) {
                            Game.getMap()[row][col] = new Environment(17);
                            if (!MyFrame.isBoatObtained()) {
                                Game.getMap()[row][col].setWalking();
                            } else {
                                Game.getMap()[row][col].setWalkable(true);
                            }
                        } else if (Game.getMap()[row][col].getType() == 17) {
                            Game.getMap()[row][col] = new Environment(18);
                            if (!MyFrame.isBoatObtained()) {
                                Game.getMap()[row][col].setWalking();
                            } else {
                                Game.getMap()[row][col].setWalkable(true);
                            }
                        } else if (Game.getMap()[row][col].getType() == 18) {
                            Game.getMap()[row][col] = new Environment(15);
                            if (!MyFrame.isBoatObtained()) {
                                Game.getMap()[row][col].setWalking();
                            } else {
                                Game.getMap()[row][col].setWalkable(true);
                            }
                        }
                    }
                }
            }
            MyFrame.p1.repaint();
        }
    }

    public void wait(int n) {
        try {
            Thread.sleep(n * 1000);
        } catch (Exception e) {
        }
    }
}