package lesson10;

public class ThrowException {
    static Box box;

    public static void makeSomeMagic() {
        if (box == null) {
            throw new NullPointerException("BOX не инициализирован!");
        }

    }

    public static void procA() {
        try {
            System.out.println("inside procA");
            throw new RuntimeException("demo");
        } finally {
            System.out.println("procA's finally");
        }
    }

    public static void main(String[] args) {
        procA();
    }
}

