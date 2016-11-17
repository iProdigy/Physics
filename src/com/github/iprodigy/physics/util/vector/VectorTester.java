package com.github.iprodigy.physics.util.vector;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import com.github.iprodigy.physics.util.angle.*;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;

import java.util.*;

public class VectorTester {

	@Test
	public void creation() {
		Vector[] vects = {
				new Vector(), new Vector(1337, 69, AngleUnit.DEGREES), new Vector(1337, 0.5 * Math.PI, AngleUnit.RADIANS),
				new Vector(1337, 0.5 * Math.PI, AngleUnit.GRADIANS), new Vector(1337, 0.25, AngleUnit.TURNS),
				new Vector(5.0, -10.0, 15.0)
		};

		for (Vector v : vects)
			assertNotNull(v);
	}

	@Test
	public void addition() {
		assertEquals(new Vector(20.0, -50.0, 3.5).add(new Vector(-29.0, 25.0, -3.5)), new Vector(-9.0, -25.0, 0.0));
		assertEquals(new Vector(5.0 * Math.sqrt(2), 45, AngleUnit.DEGREES).add(new Vector(5.0, 5.0)), new Vector(10.0, 10.0));
	}

	@Test
	public void subtraction() {
		Assert.assertEquals(new Vector(1.0, 10.0, 100.0).subtract(new Vector(100.0, 10.0, 1.0)), new Vector(-99.0, 0.0, 99.0));
	}

	@Test
	public void multiplication() {

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

	public static void main(String[] args) {
		final Result result = JUnitCore.runClasses(VectorTester.class);
		result.getFailures().forEach(System.out::println);
		System.out.println(result.wasSuccessful() ? "we gucci" : "it broke yo");
	}

}
