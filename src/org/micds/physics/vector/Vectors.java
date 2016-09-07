package org.micds.physics.vector;

import lombok.NonNull;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Vectors {

	public static double dotProduct(final double magA, final double magB, final double deg) {
		return magA * magB * Math.cos(Math.toRadians(deg));
	}

	public static Vector crossProduct3D(@NonNull final Vector a, @NonNull final Vector b) {
		// TODO: Generalize for x dimensions
		if (a.numComponents() != b.numComponents() || a.numComponents() != 3)
			throw new IllegalArgumentException("Both Vectors must occupy three dimensions");

		final double aX = a.getComponents().get(0), aY = a.getComponents().get(1), aZ = a.getComponents().get(2);
		final double bX = b.getComponents().get(0), bY = b.getComponents().get(1), bZ = b.getComponents().get(2);

		return new Vector(aY * bZ - bY * aZ, aZ * bX - bZ * aX, aX * bY - bX * aY);
	}

	public static double getDegreeDiff(@NonNull final Vector a, @NonNull final Vector b) {
		if (a.getMagnitude() == 0.0 || b.getMagnitude() == 0.0)
			return 0.0;

		return Math.toDegrees(Math.acos((a.dotProduct(b)) / (a.getMagnitude() * b.getMagnitude())));
	}

}
