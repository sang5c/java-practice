package books.modernjava.ch02.predicate;

import books.modernjava.ch02.Apple;

import static books.modernjava.ch02.Color.GREEN;

public class GreenColorPredicate implements Predicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

}
