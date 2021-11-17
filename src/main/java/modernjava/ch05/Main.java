package modernjava.ch05;

import modernjava.ch04.Dish;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
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

        // 1. 2011년에 일어난 모든 트랜잭션을 찾아 오름차순
        List<Transaction> quiz1 = transactions.stream()
                .filter(t -> t.getYear() == 2011)
                .sorted(Comparator.comparing(Transaction::getValue))
                .collect(Collectors.toList());
        System.out.println(quiz1);

        // 2. 거래자가 근무하는 모든 도시를 중복 없이 나열
        List<String> quiz2 = transactions.stream()
                .map(Transaction::getTrader)
                .map(Trader::getCity)
                .distinct()
                .collect(Collectors.toList());
        System.out.println(quiz2);

        // 3. 케임브리지에 근무하는 모든 거래자를 찾아서 이름순으로 정렬
        List<Trader> quiz3 = transactions.stream()
                .map(Transaction::getTrader)
                .filter(t -> "Cambridge".equals(t.getCity()))
                .distinct()
                .sorted(Comparator.comparing(Trader::getName))
                .collect(Collectors.toList());
        System.out.println(quiz3);

        // 4. 모든 거래자의 이름을 알파벳순으로 정렬, concat
        String quiz4 = transactions.stream()
                .map(t -> t.getTrader().getName())
                .distinct()
                .sorted()
                .reduce("", (s, s2) -> s + s2);
        // joining()
        System.out.println(quiz4);

        // 5. 밀라노에 거래자가 있나?
        boolean milan = transactions.stream()
                .anyMatch(t -> t.getTrader().getCity().equals("Milan"));
        System.out.println(milan);

        // 6. 케임브리지에 거주하는 거래자의 모든 트랜잭션값 출력
        transactions.stream()
                .filter(t -> t.getTrader().getCity().equals("Cambridge"))
                .map(Transaction::getValue)
                .forEach(System.out::println);

        // 7. 전체 트랙잭션중 최대값
        Integer max = transactions.stream()
                .map(Transaction::getValue)
                .max(Integer::compareTo)
                .get();
        System.out.println(max);

        // 8. 전체 트랙잭션중 최소값
        Optional<Integer> quiz8 = transactions.stream()
                .map(Transaction::getValue)
                .reduce((v1, v2) ->  v1 < v2 ? v1 : v2);
        // transactions.stream().min(Comparator.comparing(Transaction::getValue));
        System.out.println(quiz8);
    }

    // map reduce 패턴
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

        // String[] 타입으로 반환된다.
        List<String[]> collect = words.stream()
                .map(word -> word.split(""))
                .distinct()
                .collect(Collectors.toList());

        // 문제가 해결되지 않았다.
        List<Stream<String>> collect1 = words.stream()
                .map(word -> word.split(""))
                .map(Arrays::stream)
                .distinct()
                .collect(Collectors.toList());

        // List<String> 형태를 반환받는다.
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

    // Java 9에서 추가되었다.
    private static void example5_2_1(List<Dish> menu) {
        List<Dish> filteredMenu = menu.stream()
                .filter(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // TAKEWHILE. 리스트가 정렬되었다면 Predicate 조건을 만났을때 스트림을 slice한다.
        List<Dish> collect = menu.stream()
                .takeWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());

        // DROPWHILE. takeWhile의 정반대 작업을 수행한다. 처음으로 거짓이 될 때까지의 요소를 버린다.
        List<Dish> collect2 = menu.stream()
                .dropWhile(dish -> dish.getCalories() < 320)
                .collect(Collectors.toList());
    }

}
