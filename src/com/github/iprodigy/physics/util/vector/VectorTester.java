package com.github.iprodigy.physics.util.vector;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.github.iprodigy.physics.util.MathUtil.EPSILON;
import static com.github.iprodigy.physics.util.angle.AngleUnit.*;
import static java.lang.Math.ulp;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

public class VectorTester {

	@Test
	public void creation() {
		Vector[] vects = {
			new Vector(), new Vector(1337, 69, DEGREES), new Vector(1337, 0.5 * Math.PI, RADIANS),
			new Vector(1337, 0.5 * Math.PI, GRADIANS), new Vector(1337, 0.25, TURNS),
			new Vector(5.0, -10.0, 15.0), new Vector(new double[] {1, 2, 3}, new double[] {6, 5, 4})
		};

		for (Vector v : vects)
			assertNotNull(v);
	}

	@Test
	public void immutable() {
		final List<Double> components = new ArrayList<>();
		components.add(1.0);
		components.add(2.0);
		components.add(3.0);

		final Vector v = new Vector(components);
		assertEquals(3, v.getComponents().size());

		components.add(4.0);
		assertEquals(3, v.getComponents().size());
	}

	@Test
	public void addition() {
		assertEquals(new Vector(20.0, -50.0, 3.5).add(new Vector(-29.0, 25.0, -3.5)), new Vector(-9.0, -25.0, 0.0));
		assertEquals(new Vector(5.0 * Math.sqrt(2), 45, DEGREES).add(new Vector(5.0, 5.0)), new Vector(10.0, 10.0));
	}

	@Test
	public void subtraction() {
		Assert.assertEquals(new Vector(1.0, 10.0, 100.0).subtract(new Vector(100.0, 10.0, 1.0)),
			new Vector(-99.0, 0.0, 99.0));
	}

	@Test
	public void multiplication() {
		final Vector v = new Vector(1.0, 2.0, 3.0).multiply(5.0);
		assertEquals(5.0, v.getComponent(0), 0.0);
		assertEquals(10.0, v.getComponent(1), 0.0);
		assertEquals(15.0, v.getComponent(2), 0.0);
	}

	@Test
	public void dotProduct() {
		final Vector a = new Vector(1.0, -2.0, 4.0, -8.0),
			b = new Vector(1.5, 3.0, -6.0, -12.0);
		final double dot = a.dotProduct(b);
		assertEquals(67.5, dot, EPSILON);
		assertEquals(dot, Vectors.dotProduct(a.getMagnitude(), b.getMagnitude(), a.degreeDiff(b)), EPSILON);
	}

	@Test
	public void crossProduct() {
		assertEquals(Vectors.I, Vectors.J.crossProduct3D(Vectors.K));
		assertEquals(Vectors.I, Vectors.K.multiply(-1).crossProduct3D(Vectors.J));
		assertEquals(Vectors.I.multiply(-1), Vectors.K.crossProduct3D(Vectors.J));
		assertEquals(Vectors.J, Vectors.K.crossProduct3D(Vectors.I));
		assertEquals(Vectors.K, Vectors.I.crossProduct3D(Vectors.J));
	}

	@Test
	public void tripleProduct() {

	}

	@Test
	public void division() {

	}

	@Test
	public void components() {

	}

	@Test
	public void magnitude() {

	}

	@Test
	public void unit() {

	}

	@Test
	public void angle() {

	}

	@Test
	public void angleDiff() {

	}

	@Test
	public void interpolate() {

	}

	@Test
	public void projection() {

	}

	@Test
	public void equality() {
		assertEquals(new Vector(), new Vector());
		assertNotEquals(new Vector(), new Vector(0.0));
		assertNotEquals(new Vector(), new Vector(1.0));
		assertNotEquals(new Vector(0.0), new Vector(1.0));
		assertNotEquals(new Vector(0.0).hashCode(), new Vector(1.0).hashCode());
		assertEquals(new Vector(1.0), new Vector(1.0));
		assertEquals(new Vector(0.0), new Vector(-0.0));
		assertEquals(new Vector(0.0).hashCode(), new Vector(-0.0).hashCode());
		assertEquals(new Vector(2.0, 4.0, 8.0), new Vector(2.0 - ulp(2.0), 4.0 + ulp(4), 8.0 - ulp(8.0)));
		assertEquals(new Vector(2.0, 4.0, 8.0).hashCode(),
			new Vector(2.0 - ulp(2.0), 4.0 + ulp(4.0), 8.0 - ulp(8.0)).hashCode());
		assertNotEquals(new Vector(1.0, 2.0, 3.0, 4.0, 5.0).hashCode(),
			new Vector(5.0, 4.0, 3.0, 2.0, 1.0).hashCode());
	}

}
