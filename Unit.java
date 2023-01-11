
public class Unit {
    protected int type;

    public Unit(int type) {
        this.type = type;
    }

    public String toString() {
        return type + "";
    }

    public void setWalking() {

    }

    public boolean isWalkable() {
        return false;
    }

    public void setWalkable(boolean walkable) {
    }

    public void interact() {

    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getRow() {
        return -1;
    }

    public void setRow(int row) {
    }

    public int getCol() {
        return -1;
    }

    public void setCol(int col) {
    }
}
