import java.util.ArrayList;
import java.util.Scanner;

public class GameController {

    private final int VERTICAL = 1;
    private final int HORIZONTAL = 2;

    private String[] shipNames = new String[]{"Mayflower", "USS Constitution", "Titanic", "Spirit", "Odyssey"};

    private ArrayList<Ship> allShips = new ArrayList<>();

    private Scanner playerInput = new Scanner(System.in);

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

    private void initializeGame() {
        while (this.allShips.size() < 3) {
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

    private ArrayList<Integer> takePlayerGuess() {
        String playerGuess = this.playerInput.nextLine();
        while (playerGuess.split(" ").length != 2) {
            System.out.println("Incorrect notation of ship coordinates. Correct example: '2 2' or '1 9'");
            playerGuess = this.playerInput.nextLine();
        }

        int x_guess = Integer.parseInt(playerGuess.split(" ")[0]);
        int y_guess = Integer.parseInt(playerGuess.split(" ")[1]);

        ArrayList<Integer> parsedPlayerGuess = new ArrayList<>();
        parsedPlayerGuess.add(x_guess);
        parsedPlayerGuess.add(y_guess);

        return parsedPlayerGuess;
    }

    private void processPlayerGuess(ArrayList<Integer> playerGuess) {
        boolean hasHit = false;
        for (Ship ship : this.allShips) {
            for (ArrayList<Integer> shipCoordinates : ship.getPosition()) {
                if (playerGuess.equals(shipCoordinates)) {
                    System.out.println("Ship has been hit!");

                    ArrayList<ArrayList<Integer>> updatedShipPosition = ship.getPosition();
                    updatedShipPosition.remove(playerGuess);
                    ship.setPosition(updatedShipPosition);

                    if(updatedShipPosition.isEmpty()) {
                        this.allShips.remove(ship);
                        System.out.println("Ship with name: " + ship.getName() + " has been destroyed!");
                    }
                    hasHit = true;
                    break;
                }
            }
            if(hasHit) break;
        }
        if (!hasHit) System.out.println("Shot did not hit any ship");
    }

    public void playGame() {
        int playerGuessCounter = 0;
        this.initializeGame();

        while(!this.allShips.isEmpty()) {
            ArrayList<Integer> playerGuess = takePlayerGuess();
            processPlayerGuess(playerGuess);
            playerGuessCounter++;
        }
        System.out.println("Congratulation! You have won in " + playerGuessCounter + " turns!");
    }

    // getters/setters
    public ArrayList<Ship> getAllShips() {
        return allShips;
    }

    public void setAllShips(ArrayList<Ship> allShips) {
        this.allShips = allShips;
    }
}

