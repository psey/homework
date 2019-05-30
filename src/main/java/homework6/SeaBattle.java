package homework6;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SeaBattle {
    static char battlefield[][];
    static int battlefieldSize = 10;
    static ArrayList<Integer> arrayOfShips = new ArrayList<Integer>(Arrays.asList(4, 3, 3, 2, 2, 2, 1, 1, 1, 1));
    static ArrayList<Ship> listOfShip = new ArrayList<>();
    static char[] rowName = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J'};
    static char shipSign = '@';
    static char areaCloseToShip = '*';
    static char successShoot = 'X';
    static char shootOver = '0';
    static int hitCol, hitRow;


    public static void main(String[] args) throws IOException {
        // Генерим поле для игры
        // сюда всталю метод с Шип инфо

        battlefieldInit();
        for (int i = 0; i < arrayOfShips.size(); i++) {

            while (true) {
                Ship ship = shipGeneration(arrayOfShips.get(i));
                ship.uniqueSymbol = (char) (i + 'a');
                if (isPlaceable(ship)) {
                    placeShip(ship);
                    listOfShip.add(ship);
                    break;
                }
            }

        }

        printBattlefield();


        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (listOfShip.size() != 0) {
            enterShipPosition(reader);
            System.out.println(hitCol + " col");
            System.out.println(hitRow + " row");
            System.out.println(battlefield[hitRow][hitCol]);
            char c = battlefield[hitRow][hitCol];
            for (int i = 0; i < listOfShip.size(); i++) {
                if (listOfShip.get(i).uniqueSymbol == c) {
                    System.out.println("Попал");
                    battlefield[hitRow][hitCol] = successShoot;
                    listOfShip.get(i).hits--;

                    if (listOfShip.get(i).hits == 0) {
                        System.out.println("Убил!");
                        battlefield[hitRow][hitCol] = successShoot;
                        listOfShip.remove(listOfShip.get(i));
                    }
                    break;
                } else {
                    System.out.println("мимо");
                    battlefield[hitRow][hitCol] = shootOver;

                }
            }


            printBattlefield();

        }
    }


    public static void placeShip(Ship ship) {
        placeRect(ship.row - 1, ship.column - 1, ship.height + 2, ship.width + 2, areaCloseToShip);
        placeRect(ship.row, ship.column, ship.height, ship.width, ship.uniqueSymbol);

    }

    //размещаем прямоугольник. корабль или звездочки.
    public static void placeRect(int row, int col, int height, int width, char sign) {
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                int r = row + i;
                int c = col + j;
                if (r >= 0 && r <= battlefield.length - 1 && c >= 0 && c <= battlefield.length - 1) {
                    battlefield[r][c] = sign;
                } else {
                    continue;
                }
            }
        }
    }

    // влазит ли корабль на поле. Не ставим ли мы его на корабль или на пространство вокруг корабля
    public static boolean isPlaceable(Ship ship) {
        if (ship.column + ship.width > battlefieldSize - 1) {
            //     System.out.println(ship.column + " col ");
            return false;
        }
        if (ship.row + ship.height > battlefieldSize - 1) {
            //     System.out.println(ship.row + "row");
            return false;
        }
        for (int i = 0; i < ship.height; i++) {
            for (int j = 0; j < ship.width; j++) {
                int r = ship.row + i;
                int c = ship.column + j;
                if (battlefield[r][c] == shipSign || battlefield[r][c] == areaCloseToShip) {
                    return false;
                }
            }
        }
        //      System.out.println(" Row " + ship.row + " Column " + ship.column + " Width " + ship.width + " Height " + ship.height );
        return true;
    }

    // генерим корабль заданого размера
    public static Ship shipGeneration(int shipSize) {
        Ship ship = new Ship();
        Random random = new Random();
        ship.row = random.nextInt(battlefieldSize);
        ship.column = random.nextInt(battlefieldSize);
        ship.hits = shipSize;
        //   ship.uniqueSymbol = (char)(random.nextInt(10000) + 'A');

        int horizontal = random.nextInt(2);
        if (horizontal == 0) {
            ship.width = 1;
            ship.height = shipSize;

        } else {
            ship.width = shipSize;
            ship.height = 1;
        }

        return ship;
    }

    // надо для проверки ввода человеком
    public static boolean columnIsValid(int col) {
        return col <= battlefieldSize;
    }

    public static boolean rowIsValid(char firstElem) {
        for (int i = 0; i < rowName.length; i++) {
            if (rowName[i] == firstElem) {
                return true;
            }
        }
        return false;
    }

    public static void enterShipPosition(BufferedReader reader) throws IOException {

        System.out.println("Введите координаты в формате РядСтолбец(C9)");

        boolean isElementNotSet = true;
        while (isElementNotSet) {
            String elem = reader.readLine().toUpperCase();
            if (elem.length() > 3 || elem.length() < 2) {
                System.out.println("Что-то Вы вводите не то. Пример ввода: H6");
            } else if (columnIsValid(Integer.parseInt(elem.substring(1))) && rowIsValid(elem.charAt(0))) {
                hitCol = Integer.parseInt(elem.substring(1)) - 1;
                switch (elem.charAt(0)) {
                    case 'A':
                        hitRow = 0;
                        break;
                    case 'B':
                        hitRow = 1;
                        break;
                    case 'C':
                        hitRow = 2;
                        break;
                    case 'D':
                        hitRow = 3;
                        break;
                    case 'E':
                        hitRow = 4;
                        break;
                    case 'F':
                        hitRow = 5;
                        break;
                    case 'G':
                        hitRow = 6;
                        break;
                    case 'H':
                        hitRow = 7;
                        break;
                    case 'I':
                        hitRow = 8;
                        break;
                    case 'J':
                        hitRow = 9;
                        break;
                    default:
                        System.out.println("Something went wrong");

                }

                System.out.println("Ты молодец!");
                isElementNotSet = false;
            } else {
                System.out.println(" иди нахер. это сложная для тебя игра");
            }

        }
        //  return elem;

    }

    public static void battlefieldInit() {
        battlefield = new char[battlefieldSize][battlefieldSize];
        for (int i = 0; i < battlefield.length; i++) {
            for (int j = 0; j < battlefield[i].length; j++) {
                battlefield[i][j] = '-';
            }
        }

        System.out.println(" Game set:  ");
        printBattlefield();

        System.out.println();
    }

    public static void hideShip(char arr[][]) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                if (arr[i][j] != '*' && arr[i][j] != '-' && arr[i][j] != '0'
                        && arr[i][j] != 'X') {
                    System.out.print('@' + " ");
                } else {
                    System.out.print(battlefield[i][j] + " ");
                }
                System.out.println();
            }
        }
    }

    public static void printBattlefield() {
        System.out.print("  ");
        for (int i = 0; i < battlefield.length; i++) {
            System.out.print(i + 1 + " ");
        }
        for (int i = 0; i < battlefield.length; i++) {
            System.out.print("\n" + rowName[i] + " ");
            for (int j = 0; j < battlefield[i].length; j++) {
                System.out.print(battlefield[i][j] + " ");
            }

        }

    }

    public void shipInfo() {

    }

}
//todo Ship Info method
// todo второй массив для игры, где отмечать вражину
// todo игроков
// todo механизм скрытия игровых полей
//todo запрещать стрелять в стрелянную клетку
