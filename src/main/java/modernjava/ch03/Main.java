package modernjava.ch03;

public class Main {
    public static void main(String[] args) {
        example3_2_1();
    }

    private static void example3_2_1() {
        Runnable r1 = () -> System.out.println("Hello world! 1");

        Runnable r2 = new Runnable() {
            @Override
            public void run() {
                System.out.println("hello world 2");
            }
        };

        process(r1);
        process(r2);
        process(() -> System.out.println("hello world 3")); // 직접 전달된 람다 표현식으로 출력된다.
    }

    public static void process(Runnable runnable) {
        runnable.run();
    }

}
