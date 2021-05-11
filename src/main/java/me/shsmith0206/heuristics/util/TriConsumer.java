package me.shsmith0206.heuristics.util;

/**
 * pure evil
 */
@FunctionalInterface
public interface TriConsumer<A, B, C> {
    void accept(A a, B b, C c);
}
