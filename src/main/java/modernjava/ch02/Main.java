package modernjava.ch02;

import modernjava.ch02.formatter.AppleFancyFormatter;
import modernjava.ch02.formatter.AppleFormatter;
import modernjava.ch02.predicate.Predicate;
import modernjava.ch02.predicate.AppleRedAndHeavyPredicate;
import modernjava.ch02.quiz.MeaningOfThis;

import java.util.ArrayList;
import java.util.List;

import static modernjava.ch02.Color.RED;

public class Main {
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

    public static void main(String[] args) {
        List<Apple> inventory = new ArrayList<>();
        inventory.add(new Apple(155, RED));
        inventory.add(new Apple(100, RED));
        inventory.add(new Apple(150, Color.GREEN));

        List<Apple> redAndHeavyApples = filter(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);

        // quiz 2-1
        prettyPrintApple(inventory, new AppleFancyFormatter());

        // 2.3.2 anonymous class
        List<Apple> redApples = filter(inventory, new Predicate<>() {
            @Override
            public boolean test(Apple apple) {
                return apple.getColor() == RED;
            }
        });
        System.out.println(redApples);

        // quiz 2-2
        MeaningOfThis m = new MeaningOfThis();
        m.doIt();

        // 2.3.3 lambda
        List<Apple> redApples2 = filter(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println(redApples2);

        // 2.3.4 추상화
        List<Integer> numbers = new ArrayList<>();
        numbers.add(1);
        numbers.add(2);
        numbers.add(4);
        List<Integer> evenNumbers = filter(numbers, (Integer i) -> i % 2 == 0);
        System.out.println(evenNumbers);
    }
}
