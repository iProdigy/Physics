package org.micds.physics.util.abstraction;

import lombok.NonNull;

public interface Computational<T extends Computational<T>> {
	@NonNull
	T add(@NonNull T t);

	@NonNull
	T multiply(double scalar);

	@NonNull
	default T multiply(@NonNull Scalar scalar) {
		return this.multiply(scalar.getMagnitude());
	}

	@NonNull
	default T divide(double scalar) {
		return this.multiply(1 / scalar);
	}

	@NonNull
	default T divide(@NonNull Scalar scalar) {
		return this.multiply(1 / scalar.getMagnitude());
	}

	@NonNull
	default T subtract(@NonNull T t) {
		return this.add(t.multiply(-1));
	}
}
