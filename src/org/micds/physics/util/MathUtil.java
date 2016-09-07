package org.micds.physics.util;

import lombok.experimental.*;

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

}
