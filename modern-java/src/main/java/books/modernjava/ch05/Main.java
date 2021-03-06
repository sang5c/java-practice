package books.modernjava.ch05;

import books.modernjava.ch04.Dish;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
                new Dish("seasonal fruit", true, 120, Dish.Type.OTHER),
                new Dish("prawns", false, 300, Dish.Type.FISH),
                new Dish("rice", true, 350, Dish.Type.OTHER),
                new Dish("chicken", false, 400, Dish.Type.MEAT),
                new Dish("french fries", true, 530, Dish.Type.OTHER)
        );
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        example5_2_1(menu);
        example5_2_2(menu);
        example5_2_3(menu);
        quiz5_1(menu);
        quiz5_2();
        example5_4(menu);
        example5_5_1(numbers);
        example5_5_2(numbers);
        quiz5_3(menu);

        example5_6();

        example5_7(menu);

        example5_8();
        quiz5_4();
    }

    private static void quiz5_4() {
        Stream.iterate(new int[]{0, 1}, t -> new int[]{t[1], t[0] + t[1]})
                .limit(20)
                .forEach(t -> System.out.println("(" + t[0] + ", " + t[1] + ")"));
    }

    private static void example5_8() {
        // 5.8.1
        Stream<String> stream = Stream.of("Modern ", "Java ", "In ", "Action");
        stream.map(String::toUpperCase).forEach(System.out::println);
        Stream<String> empty = Stream.empty();

        // 5.8.2 nullable stream
        String value = System.getProperty("test");
        Stream<String> valueStream = value == null ? Stream.empty() : Stream.of(value);
        Stream<String> valueStream2 = Stream.of("config", "home", "user")
                .flatMap(key -> Stream.ofNullable(System.getProperty(key)));

        // 5.8.4 of File
        long uniqueWords = 0;
        // try (Stream<String> lines = Files.lines(Paths.get("data.txt"), Charset.defaultCharset())) {
        //     uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
        //             .distinct()
        //             .count();
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }

        // 5.8.5 infinite stream
        Stream.iterate(0, n -> n + 2)
                .limit(10)
                .forEach(System.out::println);

        // with predicate
        IntStream.iterate(0, n -> n < 100, n -> n + 4)
                .forEach(System.out::println);

        // generate
        Stream.generate(Math::random)
                .limit(5)
                .forEach(System.out::println);
    }

    private static void example5_7(List<Dish> menu) {
        // boxing, unboxing ????????? ????????????.
        int calories = menu.stream()
                .map(Dish::getCalories)
                .reduce(0, Integer::sum);

        // 5.7.1
        int sum = menu.stream()
                .mapToInt(Dish::getCalories)
                .sum();
        IntStream intStream = menu.stream()
                .mapToInt(Dish::getCalories);
        Stream<Integer> boxed = intStream.boxed();

        // OptionalInt
        OptionalInt max = menu.stream()
                .mapToInt(Dish::getCalories)
                .max();
        int m = max.orElse(1);

        // 5.7.2
        // ?????? ?????? ??????
        IntStream even = IntStream.rangeClosed(1, 100)
                .filter(v -> v % 2 == 0);
        System.out.println(even.count());

        // 5.7.3
        Stream<int[]> stream = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(a -> IntStream.rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)})
                );
        stream.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

        Stream<double[]> stream2 = IntStream.rangeClosed(1, 100).boxed()
                .flatMap(
                        a -> IntStream.rangeClosed(a, 100)
                                .mapToObj(b -> new double[]{a, b, Math.sqrt(a * a + b * b)})
                                .filter(t -> t[2] % 1 == 0)
                );

        stream2.limit(5).forEach(t -> System.out.println(t[0] + ", " + t[1] + ", " + t[2]));

    }

    private static void example5_6() {
        Trader raoul = new Trader("Raoul", "Cambridge");
        Trader mario = new Trader("Mario", "Milan");
        Trader alan = new Trader("Alan", "Cambridge");
        Trader brian = new Trader("Brian", "Cambridge");

        List<Transaction> transactions = Arrays.asList(
                new Transaction(brian, 2011, 300),
                new Transaction(raoul, 2012, 1000),
                new Transaction(raoul, 2011, 400),
                new Transaction(mario, 2012, 710),
                new Transaction(mario, 2012, 700),
                new Transaction(alan, 2012, 950)
        );

        // 1. 2011?????? ????????? ?????? ??????????????? ?????? ????????????
        List<Transaction> quiz1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(quiz1);

        // 2. ???????????? ???????????? ?????? ????????? ?????? ?????? ??????
        List<String> quiz2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(quiz2);

        // 3. ?????????????????? ???????????? ?????? ???????????? ????????? ??????????????? ??????
        List<Trader> quiz3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(quiz3);

        // 4. ?????? ???????????? ????????? ?????????????????? ??????, concat
        String quiz4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s, s2) -> s + s2);
        // joining()
        System.out.println(quiz4);

        // 5. ???????????? ???????????? ???????
        boolean milan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milan);

        // 6. ?????????????????? ???????????? ???????????? ?????? ??????????????? ??????
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7. ?????? ??????????????? ?????????
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();
        System.out.println(max);

        // 8. ?????? ??????????????? ?????????
        Optional<Integer> quiz8 = transactions.stream()
                .map(Transaction::getValue)
                .reduce((v1, v2) -> v1 < v2 ? v1 : v2);
        // transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(quiz8);
    }

    // map reduce ??????
    private static void quiz5_3(List<Dish> menu) {
        Integer count = menu.stream()
                .map(x -> 1)
                .reduce(0, Integer::sum);
        System.out.println(count);

        System.out.println(menu.stream().count());
    }

    private static void example5_5_2(List<Integer> numbers) {
        Optional<Integer> max = numbers.stream()
                .reduce((x, y) -> x > y ? x : y);
        System.out.println(max);
    }

    private static void example5_5_1(List<Integer> numbers) {
        int sum = 0;
        for (int x : numbers) {
            sum += x;
        }
        System.out.println(sum);

        int reduce = numbers.stream()
                .reduce(0, (a, b) -> a + b);
        System.out.println(reduce);
    }

    private static void example5_4(List<Dish> menu) {
        // 5.4.1
        if (menu.stream().anyMatch(Dish::isVegetarian)) {
            System.out.println("the menu is (somewhat) vegetarian friendly!!");
        }

        // 5.4.2
        if (menu.stream().allMatch(dish -> dish.getCalories() < 1000)) {
            System.out.println("all match under 1000 calories");
        }

        // 5.4.2 NONE MATCH
        if (menu.stream().noneMatch(dish -> dish.getCalories() >= 1000)) {
            System.out.println(" none match!");
        }

        // 5.4.3
        Optional<Dish> dish = menu.stream()
                .filter(Dish::isVegetarian)
                .findAny();
        menu.stream()
                .filter(Dish::isVegetarian)
                .findAny()
                .ifPresent(d -> System.out.println(d.getName()));

        // 5.4.4
        Optional<Integer> firstInteger = Stream.of(1, 2, 3, 4, 5)
                .map(n -> n * n)
                .filter(n -> n % 3 == 0)
                .findFirst();
    }

    private static void quiz5_2() {
        // map
        List<Integer> numbers = List.of(1, 2, 3, 4, 5);
        List<Integer> collect = numbers.stream()
                .map(v -> v * v)
                .collect(Collectors.toList());

        System.out.println(collect);

        // flatMap
        List<Integer> numbers1 = List.of(1, 2, 3);
        List<Integer> numbers2 = List.of(3, 4);

        List<int[]> collect1 = numbers1.stream()
                .flatMap(v -> numbers2.stream()
                        .map(k -> new int[]{v, k})
                )
                .collect(Collectors.toList());

        // flatMap2
        List<int[]> collect2 = numbers1.stream()
                .flatMap(v -> numbers2.stream()
                        .filter(p -> (p + v) % 3 == 0)
                        .map(k -> new int[]{v, k})
                )
                .collect(Collectors.toList());

        System.out.println(collect1);
        System.out.println(collect2);
    }

    private static void example5_3_2() {
        List<String> words = List.of("Hello", "world");

        // String[] ???????????? ????????????.
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        // ????????? ???????????? ?????????.
        List<Stream<String>> collect1 = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        // List<String> ????????? ???????????????.
        List<String> collect2 = words.stream()
                .map(word -> word.split(""))
                .flatMap(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());
    }

    private static void quiz5_1(List<Dish> menu) {
        List<Dish> collect = menu.stream()
                .filter(dish -> Dish.Type.MEAT.equals(dish.getType()))
                .limit(2)
                .collect(Collectors.toList());
    }

    private static void example5_2_3(List<Dish> menu) {
        List<Dish> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .skip(2)
                .collect(Collectors.toList());
    }

    private static void example5_2_2(List<Dish> menu) {
        List<Dish> collect = menu.stream()
                .filter(dish -> dish.getCalories() > 300)
                .limit(3)
                .collect(Collectors.toList());
    }

    // Java 9?????? ???????????????.
    private static void example5_2_1(List<Dish> menu) {
        List<Dish> filteredMenu = menu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // TAKEWHILE. ???????????? ?????????????????? Predicate ????????? ???????????? ???????????? slice??????.
        List<Dish> collect = menu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // DROPWHILE. takeWhile??? ????????? ????????? ????????????. ???????????? ????????? ??? ???????????? ????????? ?????????.
        List<Dish> collect2 = menu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
    }

}
