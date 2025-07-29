import java.lang.reflect.Array;
import java.util.ArrayList;

public class GameController {

    private final int VERTICAL = 1;
    private final int HORIZONTAL = 2;

    private String[] shipNames = new String[]{"Mayflower", "USS Constitution", "Titanic", "Spirit", "Odyssey"};

    private ArrayList<Ship> allShips = new ArrayList<>();

    public GameController() {}

    private ArrayList<Integer> generatePosition(int x, int y) {
        ArrayList<Integer> pos = new ArrayList<>();
        pos.add(x);
        pos.add(y);
        return pos;
    }

    public Ship generateShip() {

        ArrayList<ArrayList<Integer>> shipPositions = new ArrayList<>();

        int direction = (int)(Math.random() * 2 + 1);

        if (direction == VERTICAL) {
            int x_coord = (int)(Math.random() * 10 + 1);
            int y_coord = (int)(Math.random() * 8 + 1);

            for(int i = 0; i < 3; i++) {
                shipPositions.add(generatePosition(x_coord, y_coord + i));
            }
        } else {
            int x_coord = (int)(Math.random() * 8 + 1);
            int y_coord = (int)(Math.random() * 10 + 1);
            for(int i = 0; i < 3; i++) {
                shipPositions.add(generatePosition(x_coord + i, y_coord));
            }
        }
        String shipName = this.shipNames[(int)(Math.random() * 4)];
        return new Ship(shipName, shipPositions);
    }

    public void initializeGame() {
        while (this.allShips.size() < 10) {
            Ship newShip = generateShip();
            boolean isSpawnable = true;

            //TODO refactor
            for (ArrayList<Integer> pos: newShip.getPosition()) {
                for (Ship existingShip:this.allShips) {
                    for (ArrayList<Integer> takenPos: existingShip.getPosition()) {
                        if (pos.equals(takenPos)) {
                            System.out.println("This position is taken, ship cannot be spawned: " + pos +  " "+ takenPos);
                            isSpawnable = false;
                        }
                    }
                }
            }
            if (isSpawnable) this.allShips.add(newShip);
        }

        System.out.println("All ships where generated");
        for (Ship ship : this.allShips) {
            System.out.println(ship.getPosition());
        }
    }

    public ArrayList<Ship> getAllShips() {
        return allShips;
    }

    public void setAllShips(ArrayList<Ship> allShips) {
        this.allShips = allShips;
    }
}

