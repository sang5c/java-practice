package modernjava.ch02.predicate;

import modernjava.ch02.Apple;

public interface Predicate<T> {
    boolean test(T t);
}
