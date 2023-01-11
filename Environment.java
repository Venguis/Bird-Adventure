
public class Environment extends Unit {
    private boolean walkable;

    public Environment(int type) {
        super(type);
    }

    public void setWalking() {
        super.setWalking();
        switch (type) {
            case 00:
            case 02:
            case 03:
            case 11:
            case 12:
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
            case 30:
            case 31:
            case 32:
            case 33:
            case 71:
            walkable = false;
            break;
            case 01:
            case 04:
            case 05:
            case 06:
            case 07:
            case 9:
            case 10:
            case 19:
            case 20:
            case 21:
            default:
                walkable = true;
                break;
        }
    }

    public boolean isWalkable() {
        super.isWalkable();
        return walkable;
    }

    public void setWalkable(boolean walkable) {
        super.setWalkable(walkable);
        this.walkable = walkable;
    }
}