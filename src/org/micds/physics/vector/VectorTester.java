package org.micds.physics.vector;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;
import org.micds.physics.util.AngleUnit;

public class VectorTester {

	@Test
	public void creation() {
		Vector[] vects = {
				new Vector(), new Vector(1337, 69, AngleUnit.DEGREES), new Vector(1337, 0.5 * Math.PI, AngleUnit.RADIANS),
				new Vector(1337, 0.5 * Math.PI, AngleUnit.GRADIANS), new Vector(1337, 0.25, AngleUnit.TURNS),
				new Vector(5.0, -10.0, 15.0)
		};

		for (Vector v : vects)
			Assert.assertNotNull(v);
	}

	@Test
	public void addition() {

	}

	@Test
	public void subtraction() {

	}

	@Test
	public void multiplication() {

	}


	@Test
	public void division() {

	}

	@Test
	public void magnitude() {

	}

	@Test
	public void angle() {

	}

	@Test
	public void angleDiff() {

	}

	public static void main(String[] args) {
		Result result = JUnitCore.runClasses(VectorTester.class);

		for (final Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}

		System.out.println(result.wasSuccessful());
	}

}
