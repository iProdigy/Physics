package org.micds.physics.vector;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import org.micds.physics.util.Angle;
import org.micds.physics.util.AngleUnit;

@UtilityClass
public class Vectors {
	public static final Vector FORWARD, BACK, UP, DOWN, LEFT, RIGHT, ZERO, ONE;

	public static double dotProduct(final double magA, final double magB, final double deg) {
		return magA * magB * Math.cos(Math.toRadians(deg));
	}

	public static Vector crossProduct3D(@NonNull final Vector a, @NonNull final Vector b) {
		if (a.numComponents() != b.numComponents() || a.numComponents() != 3)
			throw new IllegalArgumentException("Both Vectors must occupy three dimensions");

		final double aX = a.getComponents().get(0), aY = a.getComponents().get(1), aZ = a.getComponents().get(2);
		final double bX = b.getComponents().get(0), bY = b.getComponents().get(1), bZ = b.getComponents().get(2);

		return new Vector(aY * bZ - bY * aZ, aZ * bX - bZ * aX, aX * bY - bX * aY);
	}

	public static Angle angularDifference(@NonNull final Vector a, @NonNull final Vector b) {
		if (a.getMagnitude() == 0.0 || b.getMagnitude() == 0.0)
			return Angle.ZERO;

		return new Angle(Math.acos((a.dotProduct(b)) / (a.getMagnitude() * b.getMagnitude())), AngleUnit.RADIANS);
	}

	/**
	 * Linear Interpolation of 2 Vectors
	 *
	 * @param a       the starting the vector
	 * @param b       the end vector
	 * @param percent interpolation factor within [0.0, 1.0]
	 * @return the interpolated vector
	 * @see org.micds.physics.util.MathUtil#interpolate(double, double, double)
	 */
	public static Vector lerp(@NonNull final Vector a, @NonNull final Vector b, final double percent) {
		return a.multiply(1 - percent).add(b.multiply(percent));
	}

	static {
		FORWARD = new Vector(0.0, 0.0, 1.0);
		BACK = new Vector(0.0, 0.0, -1.0);
		UP = new Vector(0.0, 1.0, 0.0);
		DOWN = new Vector(0.0, -1.0, 0.0);
		LEFT = new Vector(-1.0, 0.0, 0.0);
		RIGHT = new Vector(1.0, 0.0, 0.0);
		ZERO = new Vector(0.0, 0.0, 0.0);
		ONE = new Vector(1.0, 1.0, 1.0);
	}

}
