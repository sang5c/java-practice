package modernjava.ch02;

import modernjava.ch02.formatter.AppleFancyFormatter;
import modernjava.ch02.formatter.AppleFormatter;
import modernjava.ch02.predicate.Predicate;
import modernjava.ch02.predicate.AppleRedAndHeavyPredicate;
import modernjava.ch02.quiz.MeaningOfThis;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static modernjava.ch02.Color.RED;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(155, RED));
        inventory.add(new Apple(100, RED));
        inventory.add(new Apple(150, Color.GREEN));

        example(inventory);
        quiz2_1(inventory);
        example2_3_2(inventory);
        quiz2_2();
        example2_3_3(inventory);
        example2_3_4();

        example2_4_1(inventory);
        example2_4_2();
        example2_4_3();
        example2_4_4();
    }

    private static void example2_4_4() {
        // 패스
    }

    private static void example2_4_3() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.submit(() -> Thread.currentThread().getName());
    }

    private static void example2_4_2() {
        Thread t = new Thread(() -> System.out.println("Hello world!"));
        t.start();
    }

    private static void example2_4_1(List<Apple> inventory) {
        inventory.sort(Comparator.comparingInt(Apple::getWeight));
        System.out.println(inventory);
    }

    public static <T> List<T> filter(List<T> inventory, Predicate<T> p) {
        List<T> result = new ArrayList<>();
        for (T t : inventory) {
            if (p.test(t)) {
                result.add(t);
            }
        }
        return result;
    }

    public static void prettyPrintApple(List<Apple> inventory, AppleFormatter appleFormatter) {
        for (Apple apple : inventory) {
            String output = appleFormatter.accept(apple);
            System.out.println(output);
        }
    }

    private static void example2_3_4() {
        // 2.3.4 추상화
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(4);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println(evenNumbers);
    }

    private static void example2_3_3(List<Apple> inventory) {
        // 2.3.3 lambda
        List<Apple> redApples2 = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println(redApples2);
    }

    private static void quiz2_2() {
        // quiz 2-2
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();
    }

    private static void example2_3_2(List<Apple> inventory) {
        // 2.3.2 anonymous class
        List<Apple> redApples = filter(inventory, new Predicate<>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == RED;
            }
        });
        System.out.println(redApples);
    }

    private static void quiz2_1(List<Apple> inventory) {
        // quiz 2-1
        prettyPrintApple(inventory, new AppleFancyFormatter());
    }

    private static void example(List<Apple> inventory) {
        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);
    }

}
