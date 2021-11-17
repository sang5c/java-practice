package modernjava.ch05;

import modernjava.ch04.Dish;

import java.util.Arrays;
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

        example5_2_1(menu);
        example5_2_2(menu);
        example5_2_3(menu);
        quiz5_1(menu);
        quiz5_2();
        example5_4(menu);
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
