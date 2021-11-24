package books.modernjava.ch02.formatter;

import books.modernjava.ch02.Apple;

public class AppleSimpleFormatter implements AppleFormatter {
    @Override
    public String accept(Apple apple) {
        return "An apple of " + apple.getWeight() + "g";
    }

}
