package com.github.iprodigy.physics.util.abstraction;

@FunctionalInterface
public interface Quantifiable<T extends Number> {
	T getMagnitude();
}
