package com.github.iprodigy.physics.util.vector;

import lombok.NonNull;
import lombok.experimental.UtilityClass;
import com.github.iprodigy.physics.util.angle.Angle;
import com.github.iprodigy.physics.util.angle.AngleUnit;
import com.github.iprodigy.physics.util.MathUtil;

@UtilityClass
public class Vectors {
	/**
	 * Standard acceleration due to gravity (g) equalling -9.8 m/s^2
	 */
	private static final double STANDARD_GRAVITY;
	public static final Vector FORWARD, BACK, UP, DOWN, LEFT, RIGHT, ZERO, ONE, GRAVITY, GRAVITY3D, I, J, K;

	public static double dotProduct(final double magA, final double magB, final double deg) {
		return magA * magB * Math.cos(Math.toRadians(deg));
	}

	public static double tripleProduct(final Vector a, final Vector b, final Vector c) {
		if (a.numComponents() != b.numComponents() || b.numComponents() != c.numComponents())
			throw new IllegalArgumentException("Vectors must be of the same dimension");

		if (a.numComponents() == 3)
			return a.dotProduct(b.crossProduct3D(c));

		final double[][] matrix = {a.getMatrix(), b.getMatrix(), c.getMatrix()};
		return MathUtil.determinant(matrix);
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

		return new Angle(Math.acos(a.dotProduct(b) / (a.getMagnitude() * b.getMagnitude())), AngleUnit.RADIANS);
	}

	/**
	 * Linear Interpolation of 2 Vectors
	 *
	 * @param a       the starting the vector
	 * @param b       the end vector
	 * @param percent interpolation factor within [0.0, 1.0]
	 * @return the interpolated vector
	 * @see MathUtil#interpolate(double, double, double)
	 */
	public static Vector lerp(@NonNull final Vector a, @NonNull final Vector b, final double percent) {
		final double delta = MathUtil.clamp(percent, 0.0, 1.0);
		return a.multiply(1 - delta).add(b.multiply(delta));
	}

	public static Vector nlerp(@NonNull final Vector a, @NonNull final Vector b, final double percent) {
		return lerp(a, b, percent).normalized();
	}

	public static Vector gravity(final int dimensions) {
		switch (dimensions) {
			case 2:
				return GRAVITY;

			case 3:
				return GRAVITY3D;

			default:
				final double[] acceleration = new double[dimensions];

				if (dimensions >= 2)
					acceleration[1] = STANDARD_GRAVITY;

				return new Vector(acceleration);
		}
	}

	static {
		STANDARD_GRAVITY = -9.8;
		GRAVITY = new Vector(0.0, STANDARD_GRAVITY);
		GRAVITY3D = new Vector(0.0, STANDARD_GRAVITY, 0.0);

		FORWARD = new Vector(0.0, 0.0, 1.0);
		BACK = new Vector(0.0, 0.0, -1.0);
		UP = new Vector(0.0, 1.0, 0.0);
		DOWN = new Vector(0.0, -1.0, 0.0);
		LEFT = new Vector(-1.0, 0.0, 0.0);
		RIGHT = new Vector(1.0, 0.0, 0.0);
		ZERO = new Vector(0.0, 0.0, 0.0);
		ONE = new Vector(1.0, 1.0, 1.0);
		I = new Vector(1.0, 0.0, 0.0);
		J = new Vector(0.0, 1.0, 0.0);
		K = new Vector(0.0, 0.0, 1.0);
	}

}
