package com.github.iprodigy.physics.util.abstraction;

import lombok.NonNull;

@FunctionalInterface
public interface Scalar extends Quantifiable<Double>, Computational<Scalar>, Comparable<Scalar> {
	@Override
	default Scalar add(@NonNull Scalar scalar) {
		return () -> this.getMagnitude() + scalar.getMagnitude();
	}

	@Override
	default Scalar multiply(double scalar) {
		return () -> this.getMagnitude() * scalar;
	}

	@Override
	default int compareTo(@NonNull Scalar o) {
		return this.getMagnitude().compareTo(o.getMagnitude());
	}
}
