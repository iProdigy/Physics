package org.micds.physics.util.abstraction;

import lombok.NonNull;

@FunctionalInterface
public interface Scalar extends Computational<Scalar> {
	Double getMagnitude();

	@Override
	default Scalar add(@NonNull Scalar scalar) {
		return () -> this.getMagnitude() + scalar.getMagnitude();
	}

	@Override
	default Scalar multiply(double scalar) {
		return () -> this.getMagnitude() * scalar;
	}
}
