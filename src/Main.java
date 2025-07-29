import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        GameController gc = new GameController();
        for(int i = 0; i < 10; i++) {
            gc.initializeGame();
            gc.setAllShips(new ArrayList<>());
        }
    }
}
