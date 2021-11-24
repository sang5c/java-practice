package books.modernjava.ch02.predicate;

import books.modernjava.ch02.Apple;

import static books.modernjava.ch02.Color.RED;

public class AppleRedAndHeavyPredicate implements Predicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }

}
