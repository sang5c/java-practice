package modernjava.ch02;

import modernjava.ch02.formatter.AppleFancyFormatter;
import modernjava.ch02.formatter.AppleFormatter;
import modernjava.ch02.predicate.ApplePredicate;
import modernjava.ch02.predicate.AppleRedAndHeavyPredicate;
import modernjava.ch02.quiz.MeaningOfThis;

import java.util.ArrayList;
import java.util.List;

import static modernjava.ch02.Color.RED;

public class Main {
    public static List<Apple> filterApples(List<Apple> inventory, ApplePredicate p) {
        List<Apple> result = new ArrayList<>();
        for (Apple apple : inventory) {
            if (p.test(apple)) {
                result.add(apple);
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

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);

        // quiz 2-1
        prettyPrintApple(inventory, new AppleFancyFormatter());

        // 2.3.2 anonymous class
        List<Apple> redApples = filterApples(inventory, new ApplePredicate() {
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
        List<Apple> redApples2 = filterApples(inventory, (Apple apple) -> RED.equals(apple.getColor()));
        System.out.println(redApples2);
    }
}
