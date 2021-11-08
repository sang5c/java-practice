package modernjava.ch02.predicate;

import modernjava.ch02.Apple;

public class AppleHeavyWeightPredicate implements Predicate<Apple> {
    @Override
    public boolean test(Apple apple) {
        return apple.getWeight() > 150;
    }

}
