package com.github.iprodigy.physics.util;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;

@UtilityClass
public class MathUtil {
	public static final double EPSILON = 0.0000001;

	public static boolean floatsEqual(final double a, final double b) {
		return Math.abs(a - b) < EPSILON;
	}

	public static int fuzzyCompare(final double a, final double b) {
		return floatsEqual(a, b) ? 0 : Double.compare(a, b);
	}

	public static double round(final double value, final int decimals) {
		return new BigDecimal(value).setScale(decimals, BigDecimal.ROUND_HALF_UP).doubleValue();
	}

	public static int getQuadrant(final double degrees) {
		double deg = degrees % 360;

		if (deg < 0)
			deg += 360;

		return (int) (deg / 90) % 4 + 1;
	}

	public static double interpolate(final double now, final double then, final double percent) {
		final double delta = clamp(percent, 0.0, 1.0);
		return (1 - delta) * now + delta * then;
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

	public static double[] diff(final double[] from, final double[] remove) {
		if (from == null || remove == null || from.length != remove.length)
			throw new IllegalArgumentException("Double arrays must be of the same length");

		final double[] diff = new double[from.length];

		for (int i = 0; i < from.length; i++) {
			diff[i] = from[i] - remove[i];
		}

		return diff;
	}

	public static <T extends Comparable<T>> T clamp(final T val, final T min, final T max) {
		return (val.compareTo(min) < 0) ? min : (val.compareTo(max) > 0) ? max : val;
	}

	public static double determinant(final double[][] matrix) {
		final int n;

		if (matrix == null || (n = matrix.length) == 0)
			return Double.NaN;

		if (n == 1)
			return matrix[0].length == n ? matrix[0][0] : Double.NaN;

		if (n == 2)
			return matrix[0].length == n && matrix[1].length == n
				? matrix[0][0] * matrix[1][1] - matrix[0][1] * matrix[1][0]
				: Double.NaN;

		double det = 0;

		for (int i = 0; i < n; i++) {
			final double factor = matrix[0][i] * ((i % 2 == 0) ? 1 : -1);
			final double[][] mat2 = copy(matrix, i, 0);
			det += factor * determinant(mat2);
		}

		return det;
	}

	private double[][] copy(final double[][] source, final int skipCol, final int skipRow) {
		final int height = source.length, width = source[0].length;
		final boolean rowSkipReq = skipRow >= 0 && skipRow < height, colSkipReq = skipCol >= 0 && skipCol < width;
		final double[][] result = new double[height - (rowSkipReq ? 1 : 0)][width - (colSkipReq ? 1 : 0)];

		for (int i = 0; i < height; i++) {
			if (i == skipRow)
				continue;

			for (int j = 0; j < width; j++) {
				if (j == skipCol)
					continue;

				final int i2 = i - (rowSkipReq && i > skipRow ? 1 : 0),
					j2 = j - (colSkipReq && j > skipCol ? 1 : 0);
				result[i2][j2] = source[i][j];
			}
		}

		return result;
	}

}
