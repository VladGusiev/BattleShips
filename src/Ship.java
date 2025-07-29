import java.util.ArrayList;

public class Ship {
    private String name;
    private ArrayList<ArrayList<Integer>> positions;

    public Ship(String name, ArrayList<ArrayList<Integer>> pos) {
        this.name = name;
        this.positions = pos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<ArrayList<Integer>> getPosition() {
        return positions;
    }

    public void setPosition(ArrayList<ArrayList<Integer>> position) {
        this.positions = position;
    }
}
