package modernjava.ch02.predicate;

import modernjava.ch02.Apple;

import static modernjava.ch02.Color.RED;

public class AppleRedAndHeavyPredicate implements ApplePredicate{
    @Override
    public boolean test(Apple apple) {
        return RED.equals(apple.getColor()) && apple.getWeight() > 150;
    }

}
