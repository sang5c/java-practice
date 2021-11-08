package modernjava.ch02;

import modernjava.ch02.formatter.AppleFancyFormatter;
import modernjava.ch02.formatter.AppleFormatter;
import modernjava.ch02.formatter.AppleSimpleFormatter;

import java.util.ArrayList;
import java.util.List;

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
        inventory.add(new Apple(155, Color.RED));
        inventory.add(new Apple(100, Color.RED));
        inventory.add(new Apple(150, Color.GREEN));

        List<Apple> redAndHeavyApples = filterApples(inventory, new AppleRedAndHeavyPredicate());
        System.out.println(redAndHeavyApples);

        prettyPrintApple(inventory, new AppleFancyFormatter());
    }
}
