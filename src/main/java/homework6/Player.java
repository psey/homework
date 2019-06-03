package homework6;

import java.util.ArrayList;

public class Player {
    public String name;
    public ArrayList<Ship> listOfShip = new ArrayList<>();
    public char battlefield[][] = new char[10][10];
    public char enemyBattlefield[][] = new char[10][10];

    Player() {

    }

    Player(String name) {
        this.name = name;
    }
}

