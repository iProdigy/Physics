package org.micds.physics.vector;

import com.sun.javafx.UnmodifiableArrayList;
import lombok.NonNull;
import lombok.Value;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.micds.physics.util.MathUtil.floatsEqual;
import static org.micds.physics.util.MathUtil.getQuadrant;

@Value
public class Vector implements Scalar<Double> {
	@NonNull
	private final List<Double> components;
	private Double magnitude;
	private Double degree;
	private boolean isUnit;

	public Vector(@NonNull final List<Double> comps) {
		final int n = comps.size();
		this.components = new UnmodifiableArrayList<>(comps.toArray(new Double[n]), n);
		this.magnitude = getMagnitude();
		this.degree = getDegrees();
		this.isUnit = floatsEqual(this.magnitude, 1.0);
	}

	public Vector(@NonNull final Double... comps) {
		this(Arrays.asList(comps));
	}

	// TODO: what if user wants constructor to be x, y not mag, deg
	public Vector(final double magnitude, final double degrees) {
		double deg = degrees % 360;

		if (deg < 0)
			deg += 360;

		this.degree = deg;
		double x = 0, y = 0;

		switch (getQuadrant(deg)) {
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

		this.components = new UnmodifiableArrayList<>(new Double[]{x, y}, 2);
		this.magnitude = magnitude;
		this.isUnit = floatsEqual(this.magnitude, 1.0);
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

	@Override
	public Double getMagnitude() {
		if (this.magnitude != null)
			return this.magnitude;

		return Math.sqrt(this.dotProduct(this));
	}

	public double degreeDiff(final Vector other) {
		return Vectors.getDegreeDiff(this, other);
	}

	public Double getDegrees() {
		if (this.degree != null)
			return this.degree;

		if (numComponents() == 0)
			return 0.0;

		return Math.toDegrees(Math.acos(components.get(0) / this.getMagnitude()));
	}

	public List<Double> getComponents() {
		return this.components;
	}

	public int numComponents() {
		return this.components.size();
	}

}
