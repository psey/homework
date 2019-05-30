package homework3;

import java.util.*;

public class PlayersDebug {
    static Player players[] = new Player[2];
    static char mark;
    static char game[][];
    static char defVal = '.';
    static int fieldSize = 3;
    static int row, column;
    static int markCount;
    static int gameMode;
    static int complexity;
    static char elemTemp;
    static ArrayList<String> preferedMoves = new ArrayList<String>(
            Arrays.asList("1 1", "0 0", "2 0", "0 2", "2 2","0 1", "1 0", "2 1", "1 2"));


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Выбор режима игры:\n 1 - Bot vs Bot;\n 2 - Bot vs User;\n 3 - User vs User");
        boolean isModeNotSelected = true;
        while (isModeNotSelected) {
            gameMode = getNumber(sc);
            if (gameMode == 1) {
                isModeNotSelected = false;
                System.out.println("Запуск игры Bot vs Bo");

                // Init Bot
                players[0] = new Player("bot1", 'X', true);
                players[1] = new Player("bot2", 'O', true);

                arrayInit(fieldSize);

                gameImProgress(sc);

            } else if (gameMode == 2) {
                isModeNotSelected = false;
                System.out.println("Запуск игры Bot vs User");
                System.out.println("Веберите сложность игры. Сложность игры,\n 1 - Легкий,\n 2 - Сложный ");

                boolean pickMode = true;
                while (pickMode) {
                    complexity = getNumber(sc);
                    if (complexity == 1) {
                        pickMode = false;

                    } else if (complexity == 2) {
                        pickMode = false;
                    } else {
                        System.out.println("Сложность игры, 1 - Легкий, 2 - Сложный ");
                    }
                }

                // Init Bot

                players[0] = new Player("Bot 1", 'X', true);
                System.out.println("Введите имя");
                String reader = sc.next();
                players[1] = new Player(reader, 'O', false);

                if (complexity == 1) {
                    arrayInit(fieldSize);
                    gameImProgress(sc);
                } else if (complexity == 2) {
                    arrayInit(fieldSize);

                    boolean gameInProgress = true;
                    while (gameInProgress) {
                        for (int i = 0; i < players.length; i++) {
                            System.out.println("Ход игрока " + players[i].name);
                            boolean isAnswerRight = true;
                            while (isAnswerRight) {
                                char marks = players[i].mark;
                                if (players[i].isBot == true) {
                                    if (preferedMoves.size()!=0){
                                    for(int k = 0; k < preferedMoves.size(); k++){
                                        row = extractNumberFromString(preferedMoves.get(k),0);
                                        column = extractNumberFromString(preferedMoves.get(k),1);
                                        elemTemp = game[row][column];

                                        if(game[row][column] == defVal){
                                            preferedMoves.remove(k);
                                           // isAnswerRight = false;
                                            break;
                                        }

                                    }
                                    }

                                     /*   game[1][1] = marks;
                                        game[0][0];
                                        game[2][0];
                                        game[0][2];
                                        game[2][2];
                                        */

                                            //
                                            //    Random random = new Random();
                                            //    row = random.nextInt(fieldSize);
                                            //  column = random.nextInt(fieldSize);
                                            //   elemTemp = game[row][column];

                                } else {
                                    row = pickPositionOnField(sc, fieldSize, "ряд");
                                    column = pickPositionOnField(sc, fieldSize, "столбец");
                                    elemTemp = game[row][column];
                                }

                                if (elemTemp == defVal) {
                                    game[row][column] = marks;
                                    gameInProgress = movedFinishedGame(row, column, marks);
                                    printArray(game);
                                    isAnswerRight = false;
                                    markCount--;
                                    if (!gameInProgress) {
                                        System.out.println("\nПобедил " + players[i].name);
                                        System.exit(0);
                                    } else if (markCount == 0) {
                                        System.out.println("\nНичья");
                                        System.exit(0);

                                    }

                                } else {
                                    System.out.println(players[i].name + ", это место занято, выберите другие значения");
                                    printArray(game);
                                    System.out.println();
                                }
                            }
                            System.out.println();
                        }


                    }

                }


            } else if (gameMode == 3) {
                isModeNotSelected = false;
                System.out.println("Запуск игры в режиме User vs User");
                System.out.println("Игрок 1, введите имя");
                String reader = sc.next();

                players[0] = new Player(reader, 'X', false);
                System.out.println("Игрок 2, введите имя");
                String reader2 = sc.next();
                players[1] = new Player(reader2, 'O', false);

                // Field initialization
                arrayInit(fieldSize);
                gameImProgress(sc);
            } else {
                System.out.println("Доступны режимы игры:\n 1 - Bot vs Bot;\n 2 - Bot vs User;\n 3 - User vs User");

            }
        }


    }

    public static void gameImProgress(Scanner sc) {
        boolean gameInProgress = true;
        while (gameInProgress) {
            for (int i = 0; i < players.length; i++) {
                System.out.println("Ход игрока " + players[i].name);
                boolean isAnswerRight = true;
                while (isAnswerRight) {

                    char marks = players[i].mark;
                    if (players[i].isBot == true) {
                        Random random = new Random();
                        row = random.nextInt(fieldSize);
                        column = random.nextInt(fieldSize);
                        elemTemp = game[row][column];
                    } else {
                        row = pickPositionOnField(sc, fieldSize, "ряд");
                        column = pickPositionOnField(sc, fieldSize, "столбец");
                        elemTemp = game[row][column];
                    }

                    if (elemTemp == defVal) {
                        game[row][column] = marks;
                        gameInProgress = movedFinishedGame(row, column, marks);
                        printArray(game);
                        isAnswerRight = false;
                        markCount--;
                        if (!gameInProgress) {
                            System.out.println("\nПобедил " + players[i].name);
                            System.exit(0);
                        } else if (markCount == 0) {
                            System.out.println("\nНичья");
                            System.exit(0);

                        }

                    } else {
                        System.out.println(players[i].name + ", это место занято, выберите другие значения");
                        printArray(game);
                        System.out.println();
                    }
                }
                System.out.println();
            }


        }
    }

    public static boolean movedFinishedGame(int row, int col, char mark) {
        if (isRowSame(row, mark) || isColumnSame(col, mark) || isDiagonalSame(mark) ||
                isReverseDiagonalSame(mark)) {
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

    public static char markValue(Scanner sc) {
        System.out.println("Выберите символ для заполнения. 1 = 'X', 2 = 'O'");
        boolean isMarkNotSet = true;
        while (isMarkNotSet) {
            int marks = getNumber(sc);
            if (marks == 1) {
                mark = 'X';
                isMarkNotSet = false;
                return mark;

            } else if (marks == 2) {
                mark = 'O';
                isMarkNotSet = false;
                return mark;
            } else {
                System.out.println("1 = 'X', 2 = 'O'");
            }
        }
        return mark;
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
/*
    public static void initPlayer(boolean bot, Scanner sc) {
        if (bot == true) {
            for (int i = 0; i < players.length; i++) {
                players[i] = new Player();
                players[i].name = "Bot " + (i + 1);
                players[i].isBot = bot;
                if (i == 0) {
                    players[0].mark = 'X';
                }
            }
        } else {
            for (int i = 0; i < players.length; i++) {
                System.out.println("Введите имя");
                String reader = sc.next();
                players[i] = new Player();
                players[i].name = reader;
                players[i].isBot = bot;
                if (i == 0) {
                    char mark = markValue(sc);
                    players[0].mark = mark;
                }
            }

        }

    }
    */
public static int extractNumberFromString(String s, int position){
    String str = (s+" ").split(" ")[position];
    int t = Integer.parseInt(str);
    return t;
}

    public static void arrayInit(int fieldSize) {
        game = new char[fieldSize][fieldSize];
        for (int i = 0; i < game.length; i++) {
            for (int j = 0; j < game[i].length; j++) {
                game[i][j] = defVal;
            }
        }

        markCount = fieldSize * fieldSize;

        System.out.println(" Поле для игры выглядит так: ");
        printArray(game);

        System.out.println();
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

