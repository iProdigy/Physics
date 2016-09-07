import lombok.NonNull;
import lombok.Value;
import lombok.experimental.Wither;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Value
public class Vector implements Scalar<Double> {
	@NonNull
	@Wither
	private final List<Double> components;

	// TODO: caching, make components immutable

	public Vector(@NonNull final List<Double> comps) {
		this.components = new ArrayList<>(comps);
	}

	public Vector(@NonNull final Double... comps) {
		this(Arrays.asList(comps));
	}

	// TODO: what if user wants constructor to be x, y not mag, deg
	public Vector(final double magnitude, final double degrees) {
		double deg = degrees % 360;

		if (deg < 0)
			deg += 360;

		final int quadrant = (int) (deg / 90) % 4 + 1;
		double x = 0, y = 0;

		switch (quadrant) {
			case 1:
				x = magnitude * Math.cos(Math.toRadians(deg));
				y = magnitude * Math.sin(Math.toRadians(deg));
				break;

			case 2:
				deg = 180 - deg;
				x = -magnitude * Math.cos(Math.toRadians(deg));
				y = magnitude * Math.sin(Math.toRadians(deg));
				break;

			case 3:
				deg -= 180;
				x = -magnitude * Math.cos(Math.toRadians(deg));
				y = -magnitude * Math.sin(Math.toRadians(deg));
				break;

			case 4:
				deg = 360 - deg;
				x = magnitude * Math.cos(Math.toRadians(deg));
				y = -magnitude * Math.sin(Math.toRadians(deg));
				break;
		}

		this.components = new ArrayList<>(Arrays.asList(x, y));
	}

	@Override
	public Double getMagnitude() {
		return Math.sqrt(this.dotProduct(this));
	}

	public Vector add(final Vector other) {
		final List<Double> comps = new ArrayList<>(components);

		for (int i = 0; i < other.numComponents(); i++) {
			if (comps.size() > i) {
				comps.set(i, comps.get(i) + other.getComponents().get(i));
			} else {
				comps.add(other.getComponents().get(i));
			}
		}

		return new Vector(comps);
	}

	public Vector subtract(final Vector other) {
		return this.add(other.multiply(-1));
	}

	public Vector multiply(final double scalar) {
		final List<Double> comps = new ArrayList<>();
		this.components.forEach(comp -> comps.add(comp * scalar));
		return new Vector(comps);
	}

	public Vector divide(final double scalar) {
		return this.multiply(1 / scalar);
	}

	public double dotProduct(final Vector other) {
		final int n = numComponents();
		if (n != other.numComponents())
			throw new IllegalArgumentException("Vector Dot Product cannot be computed with two Vectors of different dimensions");

		double sum = 0.0;

		for (int i = 0; i < n; i++)
			sum += components.get(i) * other.getComponents().get(i);

		return sum;
	}

	public static double dotProduct(final double magA, final double magB, final double deg) {
		return magA * magB * Math.cos(Math.toRadians(deg));
	}

	public static Vector crossProduct3D(final Vector a, final Vector b) {
		// TODO: Generalize for x dimensions
		if (a.numComponents() != b.numComponents() || a.numComponents() != 3)
			throw new IllegalArgumentException("Both Vectors must occupy three dimensions");

		final double aX = a.getComponents().get(0), aY = a.getComponents().get(1), aZ = a.getComponents().get(2);
		final double bX = b.getComponents().get(0), bY = b.getComponents().get(1), bZ = b.getComponents().get(2);

		return new Vector(aY * bZ - bY * aZ, aZ * bX - bZ * aX, aX * bY - bX * aY);
	}

	public static double getDegreeDiff(final Vector a, final Vector b) {
		return Math.toDegrees(Math.acos((a.dotProduct(b)) / (a.getMagnitude() * b.getMagnitude())));
	}

	public double degreeDiff(final Vector other) {
		return getDegreeDiff(this, other);
	}

	public boolean isUnit() {
		return this.getMagnitude() == 1.0;
	}

	public List<Double> getComponents() {
		return this.components;
	}

	public int numComponents() {
		return this.components.size();
	}

}
