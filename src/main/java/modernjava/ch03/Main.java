package modernjava.ch03;

import modernjava.ch02.predicate.Predicate;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.file.DirectoryStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Function;

public class Main {
    public static void main(String[] args) throws IOException {
        // example3_2_1();
        // example3_3();
        // example3_4_1();
        // example3_4_2();
        example3_4_3();
    }

    private static void example3_4_3() {
        List<Integer> list = map(Arrays.asList("lambdas", "in", "action"), (String s) -> s.length());
        System.out.println(list);

        // auto boxing
        List<Integer> list2 = new ArrayList<>();
        for (int i = 300; i < 400; i++) {
            list2.add(i);
        }
        System.out.println(list2);
    }

    private static <T, R> List<R> map(List<T> list, Function<T, R>f) {
        List<R> result = new ArrayList<>();
        for (T t : list) {
            result.add(f.apply(t));
        }
        return result;
    }

    private static void example3_4_2() {
        forEach(
                Arrays.asList(1, 2, 3, 4, 5),
                (Integer i) -> System.out.println(i)
        );
    }

    public static <T> void forEach(List<T> list, Consumer<T> c) {
        for (T t : list) {
            c.accept(t);
        }
    }

    private static void example3_4_1() {
        Predicate<String> nonEmptyStringPredicate = (String s) -> !s.isEmpty();
        List<String> listOfStrings = List.of("", "1", " ");
        List<String> nonEmpty = filter(listOfStrings, nonEmptyStringPredicate);
        System.out.println(nonEmpty);
    }

    public static <T> List<T> filter(List<T> list, Predicate<T> predicate) {
        List<T> results = new ArrayList<>();
        for (T t : list) {
            if (predicate.test(t)) {
                results.add(t);
            }
        }
        return results;
    }

    private static void example3_3() throws IOException {
        String _3_3_1 = processFile();
        // 실행 어라운드 패턴
        String oneLine = processFile(BufferedReader::readLine);// 3_3_4
        String twoLine = processFile((BufferedReader br) -> br.readLine() + br.readLine());// 3_3_4
    }

    @FunctionalInterface
    public interface BufferedReaderProcessor {
        String process(BufferedReader br) throws IOException;

    }

    private static String processFile(BufferedReaderProcessor p) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return p.process(br);
        }
    }

    private static String processFile() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader("data.txt"))) {
            return br.readLine();
        }
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
