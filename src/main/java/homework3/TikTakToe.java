package homework3;
/***
 *Реализовать консольную игру крестики-нолики.
 * Использовать 2х мерный массив символов. Символы только X и O.
 *
 * На каждом шаге игры выводить на экран массив со значениями текущего состояния "клеток":
 * X O X
 * X X O
 * O X O
 *
 * У игры должно быть 3 режима:
 * 1. Компьютер против Компьютера
 * 2. Игрок против Компьютера
 * 3. Игрок против Игрока
 *
 * Дополнительно:
 *
 * В режиме "Игрок против Компьютера" Сделать 2 уровня сложности: Сложный и Легкий.
 *
 * */

import java.util.Scanner;

public class TikTakToe {
    static char game[][];
    static char mark;
    static char defVal = '.';
    static int fieldSize = 3;
    static int row, column;
    static Player players[] = new Player[2];

    public static void main(String[] args) {


        Scanner sc = new Scanner(System.in);

      //  System.out.println("Задайте размер поля");
      //  fieldSize = getNumber(sc);

        game = new char[fieldSize][fieldSize];
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                game[i][j] = defVal;
            }
        }




        System.out.println("Выберите символ для заполнения. 1 = 'X', 2 = 'O'");
        boolean isMarkNotSet = true;
        while (isMarkNotSet) {
            int marks = getNumber(sc);
            if (marks == 1) {
                mark = 'X';
                isMarkNotSet = false;

            } else if (marks == 2) {
                mark = 'O';
                isMarkNotSet = false;
            } else {
                System.out.println("1 = 'X', 2 = 'O'");
            }
        }

        System.out.println(" Поле для игры выглядит так: ");
        printArray(game);

        System.out.println();

        boolean isGameInProgress = true;

        while (isGameInProgress) {
            row = pickPositionOnField(sc, fieldSize, "ряд");
            column = pickPositionOnField(sc, fieldSize, "столбец");
            String elemTemp = String.valueOf(game[row][column]);

            if (elemTemp.equals(String.valueOf(defVal))) {
                game[row][column] = mark;

                isGameInProgress = movedFinishedGame(row,column,mark);

                printArray(game);
            } else {
                System.out.println("Место занято, выберите другие значения");
                printArray(game);
            }
            System.out.println();
        }

    }
    public static boolean movedFinishedGame(int row, int col, char mark){
        if (isRowSame(row, mark) || isColumnSame(col, mark) || isDiagonalSame(mark) ||
                isReverseDiagonalSame(mark)) {
            System.out.println("GAME OVER");
            return false;
        }

        return true;
    }


    public static boolean isReverseDiagonalSame(char mark) {
        for (int row = 0; row < game.length; row++) {
            int col = game.length - row - 1;
            if (game[row][col] != mark)
                return false;
        }
        return true;
    }

    public static boolean isDiagonalSame(char mark) {
        for (int row = 0; row < game.length; row++) {
            if (game[row][row] != mark)
                return false;
        }
        return true;
    }

    public static boolean isRowSame(int row, char mark) {
        for (int col = 0; col < game.length; col++) {
            if (game[row][col] != mark)
                return false;
        }
        return true;
    }

    public static boolean isColumnSame(int col, char mark) {
        for (int row = 0; row < game.length; row++) {
            if (game[row][col] != mark)
                return false;
        }
        return true;


    }

    public static int getNumber(Scanner sc) {
        int size = 3;
        boolean isSizeSet = true;
        while (isSizeSet) {
            if (!sc.hasNextInt()) {
                System.out.println("Вы вводите не число");
                sc.next();

            } else {
                size = sc.nextInt();
                isSizeSet = false;
            }
        }
        return size;
    }

    public static int pickPositionOnField(Scanner sc, int gameSize, String where) {
        System.out.printf(" Выберите %s для заполнения от 1 до %d\n", where, gameSize);
        int elem = 0;
        boolean isElementNotSet = true;
        while (isElementNotSet) {
            int tempElem = getNumber(sc);
            if (tempElem > gameSize) {
                System.out.println("Число должно быть меньше или равно " + gameSize);
            } else {
                elem = tempElem - 1;
                isElementNotSet = false;
            }
        }
        return elem;
    }


    public static void printArray(char arr[][]) {
        System.out.print("  ");
        for (int i = 0; i < arr.length; i++) {
            System.out.print(i + 1 + " ");
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.print("\n" + (i + 1) + " ");
            for (int j = 0; j < arr[i].length; j++) {
                System.out.print(arr[i][j] + " ");
            }

        }

    }
}

