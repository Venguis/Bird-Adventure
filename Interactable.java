import java.awt.Color;

public class Interactable extends Unit {
    private boolean walkable;
    private int row;
    private int col;
    private Unit temp = new Environment(5);
    private static boolean strawberryObtained = false;

    public Interactable(int type) {
        super(type);
        walkable = false;
    }

    public void interact() {
        String[] textInfo = new String[20];
        switch (type - 50) {
            case 0:
                // player
                break;
            case 22:
            case 1:
                // boulder
                switch (Game.getFacing()) {
                    case 0:
                        if (Game.getMap()[row][col - 1].isWalkable()) {
                            if (temp.getType() == 6) {
                                Game.getMap()[row][col] = new Environment(8);
                            } else {
                                if (MyFrame.isCave()) {
                                    temp = new Environment(21);
                                }
                                Game.getMap()[row][col] = temp;
                            }
                            Game.getMap()[row][col].setWalking();
                            temp = Game.getMap()[row][col - 1];
                            Game.getMap()[row][col - 1] = this;
                            col--;
                        }
                        break;
                    case 1:
                        if (Game.getMap()[row + 1][col].isWalkable()) {
                            if (temp.getType() == 6) {
                                Game.getMap()[row][col] = new Environment(8);
                            } else {
                                if (MyFrame.isCave()) {
                                    temp = new Environment(21);
                                }
                                Game.getMap()[row][col] = temp;
                            }
                            Game.getMap()[row][col].setWalking();
                            temp = Game.getMap()[row + 1][col];
                            Game.getMap()[row + 1][col] = this;
                            row++;
                        }
                        break;
                    case 2:
                        if (Game.getMap()[row][col + 1].isWalkable()) {
                            if (temp.getType() == 6) {
                                Game.getMap()[row][col] = new Environment(8);
                            } else {
                                if (MyFrame.isCave()) {
                                    temp = new Environment(21);
                                }
                                Game.getMap()[row][col] = temp;
                            }
                            Game.getMap()[row][col].setWalking();
                            temp = Game.getMap()[row][col + 1];
                            Game.getMap()[row][col + 1] = this;
                            col++;
                        }
                        break;
                    case 3:
                        if (Game.getMap()[row - 1][col].isWalkable()) {
                            if (temp.getType() == 6) {
                                Game.getMap()[row][col] = new Environment(8);
                            } else {
                                if (MyFrame.isCave()) {
                                    temp = new Environment(21);
                                }
                                Game.getMap()[row][col] = temp;
                            }
                            Game.getMap()[row][col].setWalking();
                            temp = Game.getMap()[row - 1][col];
                            Game.getMap()[row - 1][col] = this;
                            row--;
                        }
                        break;
                }
                break;
            case 2:
            case 3:
                MyFrame.setTalking(true);
                textInfo[0] = "MAD MOTH";
                if (!strawberryObtained) {
                    switch (MyFrame.getTalkCounters()[0]) {
                        case 0:
                            textInfo[1] = "2";
                            textInfo[2] = "HAHAHA! ANOTHER LITTLE WANDERER COMES STUMBLING ABOUT!" +
                                    "\nWELCOME, WELCOME TO OUR WONDEROUS AND WONDERFUL HOME, \nFICTIONAL SETTING 253,387!\nI HEAR OUR CREATOR NAMED US HIMSELF!";
                            break;
                        case 1:
                            textInfo[1] = "3";
                            textInfo[2] = "YOU MAY BE ASKING, \n\"HANDSOME SIR! WHY, OH WHY, IS YOUR SPEECH SUCH AN \nIRRITATING SHADE OF YELLOW?\"\nTHIS IS AN EXCELLENT QUESTION, \nAND I CAN ONLY RESPOND...";
                            textInfo[3] = "MY CREATOR WAS LIKELY A \nBAD GRAPHIC DESIGNER! HAHAHAHAHA!";
                            break;
                        case 3:
                            textInfo[0] = "Omniscient Narrator";
                            textInfo[1] = "2";
                            textInfo[2] = "Take a hint - they don't have any more dialogue. Move along now.";
                            break;
                        default:
                            textInfo[1] = "2";
                            textInfo[2] = "NOW SCURRY OFF, LITTLE BIRDY! \nIF YOU'RE LOOKING FOR SOMETHING TO DO, \nI HEAR SAD SLIME UP NORTH WOULD LOOOOOVE\nSOME ASSISTANCE!\nHAHAHAHAHAHAHAHAHAHAHAAHAHAHAAHAHAAHA!";
                            break;
                    }
                    MyFrame.getTalkCounters()[0]++;
                } else {

                }
                MyFrame.setTextInfo(textInfo);
                break;
            case 4:
            case 5:
                MyFrame.setTalking(true);
                textInfo[0] = "sad slime";
                if (!strawberryObtained) {
                    switch (MyFrame.getTalkCounters()[1]) {
                        case 0:
                            textInfo[1] = "3";
                            textInfo[2] = "another one to come bother me... that moth never gets tired, does he? *sigh*";
                            textInfo[3] = "well, since you're here, why don't you find me some strawberries?\ni'm tired of being so blue all the time, and i think they might help.";
                            break;
                        default:
                            textInfo[1] = "2";
                            textInfo[2] = "now leave me alone...";
                            break;
                    }
                    MyFrame.getTalkCounters()[1]++;
                } else {
                    switch (MyFrame.getTalkCounters()[4]) {
                        case 0:
                            textInfo[1] = "3";
                            textInfo[2] = "oh. finally.";
                            textInfo[3] = "well, since you've been a little helpful, i'll let you in on a little secret. \nthat moth is hiding his little boat in the tree behind him. \njust shake it a few times and it'll come right out.";
                            break;
                        default:
                            textInfo[0] = "less sad slime";
                            textInfo[1] = "2";
                            textInfo[2] = "that moth is hiding his little boat in the tree behind him. \njust shake it a few times and it'll come right out.";
                            break;
                    }
                    MyFrame.getTalkCounters()[4]++;
                }
                MyFrame.setTextInfo(textInfo);
                break;
            case 6:
                MyFrame.setTalking(true);
                textInfo[0] = "Sign";
                switch (MyFrame.getTalkCounters()[2]) {
                    case 2:
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "Hurry up along your little journey. This sign has nothing new to say.";
                        break;
                    default:
                        textInfo[0] = "Sign";
                        textInfo[1] = "2";
                        textInfo[2] = "Ahead is my DASTARDLY boulder puzzle of INGENIUS design!!!! BEWARE!!!!!!!!\n    -Puzzler Porpoise\n\nP.S. I left a lever for you to reset because my puzzle is too HEINOUS to be solved in one try! \nHEHEHE!";
                        break;
                }
                MyFrame.getTalkCounters()[2]++;
                MyFrame.setTextInfo(textInfo);
                break;
            case 7:
                // first reset lever
                Game.loadMap("Map1.txt", 2, 25, new Color(128, 255, 128));
                Game.getMap()[row][col] = new Interactable(58);
                MyFrame.p1.repaint();
                break;
            case 8:
                Game.loadMap("Map1.txt", 2, 25, new Color(128, 255, 128));
                Game.getMap()[row][col] = new Interactable(57);
                MyFrame.p1.repaint();
                break;
            case 9:
                MyFrame.setTalking(true);
                textInfo[0] = "Sign";
                switch (MyFrame.getTalkCounters()[3]) {
                    case 2:
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "Why are you still talking to a sign?";
                        break;
                    default:
                        textInfo[0] = "Sign";
                        textInfo[1] = "2";
                        textInfo[2] = "Sure, you may have defeated my first and EASIEST puzzle, but... \nyou had to crush FLOWERS to do it!!! \nWho's the real LOSER now????? HEHEHE!\n    -Puzzler Porpoise";
                        break;
                }
                MyFrame.getTalkCounters()[3]++;
                MyFrame.setTextInfo(textInfo);
                break;
            case 10:
                MyFrame.setTalking(true);
                textInfo[0] = "Omniscient Narrator";
                switch (MyFrame.getTalkCounters()[8]) {
                    case 0:
                        textInfo[1] = "2";
                        textInfo[2] = "You obtained an enormous strawberry.";
                        break;
                    default:
                        textInfo[1] = "2";
                        textInfo[2] = "You obtained another enormous strawberry?";
                        break;
                }
                MyFrame.setTextInfo(textInfo);
                strawberryObtained = true;
                MyFrame.getTalkCounters()[8]++;
                Game.getMap()[row][col] = new Environment(05);
                Game.getMap()[row][col].setWalking();
                break;
            case 11:
            case 12:
                MyFrame.setTalking(true);
                textInfo[0] = "Friendly Fox";
                switch (MyFrame.getTalkCounters()[7]) {
                    case 0:
                        textInfo[1] = "3";
                        textInfo[2] = "Heya friend, nice job getting this far! \nI know those puzzles were brutal, congrats on getting through them!";
                        textInfo[3] = "Hate to be the bearer of bad news, but it's not over yet. \nPuzzler Porpoise left some nasty torch puzzles and didn't even leave you a torch! \nDon't worry though, I found one for you that you can use.";
                        break;
                    case 1:
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "You've laid your talons on a well-made torch.";
                        break;
                    case 2:
                        textInfo[1] = "2";
                        textInfo[2] = "The puzzles aren't too difficult to understand: just light up your torch and light up the lantern. \nTricky part is doing that without your torch burning out. \nI know you've got it though, I'll be waiting for you on the other side!";
                        break;
                    default:
                        textInfo[0] = "Friendly Fox";
                        textInfo[1] = "2";
                        textInfo[2] = "Good luck!";
                        break;
                }
                MyFrame.getTalkCounters()[7]++;
                MyFrame.setTextInfo(textInfo);
                break;
            case 13:
                // second reset lever
                Game.loadMap("Map1.txt", 7, 7, new Color(128, 255, 128));
                Game.getMap()[row][col] = new Interactable(64);
                Game.getMap()[10][25] = new Environment(05);
                Game.getMap()[10][27] = new Environment(06);
                Game.getMap()[10][25].setWalking();
                Game.getMap()[10][27].setWalking();
                MyFrame.p1.repaint();
                break;
            case 14:
                // lever 2?
                Game.loadMap("Map1.txt", 7, 7, new Color(128, 255, 128));
                Game.getMap()[row][col] = new Interactable(63);
                Game.getMap()[row][col].setWalking();
                MyFrame.p1.repaint();
                break;
            case 15:
                MyFrame.setTalking(true);
                textInfo[0] = "Sign";
                switch (MyFrame.getTalkCounters()[3]) {
                    case 2:
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "Play the game.";
                        break;
                    default:
                        textInfo[0] = "Sign";
                        textInfo[1] = "2";
                        textInfo[2] = "Beware! My most odious puzzle yet!!! HEHEHE!\n    -Puzzler Porpoise";
                        break;
                }
                MyFrame.getTalkCounters()[3]++;
                MyFrame.setTextInfo(textInfo);
                break;
            case 16:
                MyFrame.setTalking(true);
                textInfo[0] = "Sign";
                switch (MyFrame.getTalkCounters()[3]) {
                    case 2:
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "Do you think talking to a sign multiple times is funny?";
                        break;
                    default:
                        textInfo[0] = "Sign";
                        textInfo[1] = "2";
                        textInfo[2] = "Grrrrr...\nJust you wait... \nyou'll find even more atrocious puzzles by the river cave...\n    -Puzzler Porpoise";
                        break;
                }
                MyFrame.getTalkCounters()[3]++;
                MyFrame.setTextInfo(textInfo);
                break;
            case 17:
                switch (MyFrame.getTalkCounters()[6]) {
                    case 0:
                        MyFrame.setTalking(true);
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "You hear rustling.";
                        MyFrame.setTextInfo(textInfo);
                        break;
                    case 2:
                        MyFrame.setTalking(true);
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "You acquired a shoddy boat.";
                        MyFrame.setTextInfo(textInfo);
                        MyFrame.setBoatObtained(true);
                        break;
                    case 3:
                        MyFrame.setTalking(true);
                        textInfo[0] = "Omniscient Narrator";
                        textInfo[1] = "2";
                        textInfo[2] = "It is strange a boat could be hidden in a tree, \nbut you decide not to question it.";
                        MyFrame.setTextInfo(textInfo);
                        MyFrame.setBoatObtained(true);
                        break;
                    default:
                        break;
                }
                MyFrame.getTalkCounters()[6]++;
                break;
            case 18:
                MyFrame.p1.removeAll();
                Game.loadMap("Map2.txt", 18, 24, Color.black);
                MyFrame.p1.setBackground(Color.black);
                MyFrame.p1.repaint();
                MyFrame.setCave(true);
                break;
            case 19:
                Game.getMap()[row][col] = new Environment(21);
                Game.getMap()[row][col].setWalking();
                break;
            case 20:
                if (MyFrame.getTorchCounter() > 0) {
                    Game.getMap()[row][col] = new Environment(71);
                    Game.getMap()[row][col].setWalking();
                    Game.getMap()[34][32] = new Interactable(76);
                    Game.getMap()[34][32].setWalking();
                    Game.getMap()[33][32] = new Environment(21);
                    Game.getMap()[33][32].setWalking();
                    Game.getMap()[32][32] = new Environment(21);
                    Game.getMap()[32][32].setWalking();
                }
                // unlock a door
                break;
            case 21:
                Game.loadMap("Map2.txt", 18, 24, Color.black);
                MyFrame.p1.setBackground(Color.black);
                MyFrame.setTorchCounter(25);
                MyFrame.setTalking(true);
                textInfo[0] = "Omniscient Narrator";
                textInfo[1] = "2";
                textInfo[2] = "You've lit your torch for 24 actions.";
                MyFrame.setTextInfo(textInfo);
                MyFrame.p1.repaint();
                break;
            case 23:
                // 32, 30
                Game.getMap()[row][col] = new Interactable(74);
                Game.getMap()[31][30] = new Environment(21);
                Game.getMap()[31][30].setWalking();
                Game.getMap()[row][col].setWalking();
                MyFrame.p1.repaint();
                break;
            case 24:
                Game.getMap()[row][col] = new Interactable(73);
                Game.getMap()[row][col].setWalking();
                MyFrame.p1.repaint();
                break;
            case 26:
                Game.loadMap("Map2.txt", 30, 1, Color.black);
                MyFrame.p1.setBackground(Color.black);
                MyFrame.p1.repaint();
                break;
        }
    }

    public void setWalking() {
        super.setWalking();
        walkable = false;
    }

    public boolean isWalkable() {
        super.isWalkable();
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        super.setWalkable(walkable);
        this.walkable = walkable;
    }

    public int getRow() {
        super.getRow();
        return row;
    }

    public void setRow(int row) {
        super.setRow(row);
        this.row = row;
    }

    public int getCol() {
        super.getCol();
        return col;
    }

    public void setCol(int col) {
        super.setCol(col);
        this.col = col;
    }

    public static boolean isStrawberryObtained() {
        return strawberryObtained;
    }

    public static void setStrawberryObtained(boolean strawberryObtained) {
        Interactable.strawberryObtained = strawberryObtained;
    }

    public static void main(String[] args) {
        Game g = new Game();
        g.play();
    }
}