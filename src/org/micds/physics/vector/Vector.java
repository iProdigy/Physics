package org.micds.physics.vector;

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
	private double magnitude;
	private double degree;

	// TODO: caching, make components immutable

	public Vector(@NonNull final List<Double> comps) {
		this.components = new ArrayList<>(comps);
	}

	public Vector(@NonNull final Double... comps) {
		this(Arrays.asList(comps));
	}

	public Vector(final double x, final double y, final double z) {
		if(z != 0) {
			// Yikes 3D
		} else {
			magnitude = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
			degree = Math.acos(((Math.pow(x, 2) - Math.pow(magnitude, 2) - Math.pow(y, 2)) / (-2 * magnitude * y)));
		}
		this.components = new ArrayList<>(Arrays.asList(x, y, z));
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
		this.magnitude = Math.sqrt(this.dotProduct(this));
	}

	@Override
	public Double getMagnitude() {
		return magnitude;
	}

	public Double getDegree() {
		return degree;
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

	public double degreeDiff(final Vector other) {
		return Vectors.getDegreeDiff(this, other);
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
