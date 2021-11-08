package modernjava.ch02.predicate;

import modernjava.ch02.Apple;

import static modernjava.ch02.Color.GREEN;

public class AppleGreenColorPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return GREEN.equals(apple.getColor());
    }

}
