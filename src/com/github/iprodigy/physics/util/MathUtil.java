package com.github.iprodigy.physics.util;

import lombok.experimental.UtilityClass;

import java.util.Arrays;

@UtilityClass
public class MathUtil {
	private static final double EPSILON = 0.0000001;

	public static boolean floatsEqual(final double a, final double b) {
		return Math.abs(a - b) < EPSILON;
	}

	public static int getQuadrant(final double degrees) {
		double deg = degrees % 360;

		if (deg < 0)
			deg += 360;

		return (int) (deg / 90) % 4 + 1;
	}

	public static double interpolate(final double now, final double then, final double percent) {
		return (1 - percent) * now + percent * then;
	}

	public static Double[] zeroArray(final int dimensions) {
		final Double[] array = new Double[dimensions];
		Arrays.fill(array, 0.0);
		return array;
	}

	public static double dist(final double[] pos1, final double[] pos2) {
		if (pos1.length != pos2.length)
			return Double.NaN;

		double sum = 0.0;

		for (int i = 0; i < pos1.length; i++) {
			sum += Math.pow(pos1[i] - pos2[i], 2);
		}

		return Math.sqrt(sum);
	}

}
