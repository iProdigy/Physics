package org.micds.physics.vector;

import org.micds.physics.util.*;

public class VectorTester {

	// TODO: JUnit

	public static void main(String[] args) {
		/*
		final Vector a = new Vector(1.0, 0.0, -3.0);
		final Vector b = new Vector(-2.0, 5.0, 1.0);
		final Vector c = new Vector(3.0, 1.0, 1.0);

		System.out.println(a.multiply(2).subtract((b.subtract(c)).multiply(3)));
		System.out.println(new Vector(0.0, 1.0).degreeDiff(new Vector(1.0, 0.0)));
		*/

		/*
		Vector v = new Vector(100, 240, AngleUnit.DEGREES);
		System.out.println(v.getComponents());
		*/

		/*
		Vector a = new Vector(2, 75);
		Vector b = new Vector(4, -15);
		Vector d = a.multiply(3).add(b);
		System.out.println(d);
		*/

		/*
		Vector a = new Vector(0.800, 90, AngleUnit.DEGREES);
		Vector b = new Vector(0.300, 180, AngleUnit.DEGREES);
		Vector c = new Vector(0.100, 270, AngleUnit.DEGREES);
		Vector d = a.add(b).add(c);
		System.out.println(d);
		System.out.println(d.getMagnitude());
		System.out.println(d.degreeDiff(new Vector(-1.0, 0.0)));
		System.out.println("X");
		Vector e = new Vector(10.0, 15.0, 0.0);
		System.out.println(e.getDegree());
		*/

		/*
		Vector a = new Vector(3.0, 0.0);
		Vector b = new Vector(5.0, 120, AngleUnit.DEGREES);
		Vector c = a.add(b);
		System.out.println(c);
		*/

		/*
		Vector a = new Vector(1, 15.0, AngleUnit.DEGREES);
		System.out.println(a);

		Vector b = new Vector(1.0, 10.0, AngleUnit.DEGREES);
		System.out.println(b);

		Vector c = new Vector(1.0, 30 + 90, AngleUnit.DEGREES);
		System.out.println(c);
		*/

		Vector a = new Vector(75, 30, AngleUnit.DEGREES);
		Vector b = new Vector(100, 180 - 45, AngleUnit.DEGREES);
		Vector c = a.add(b);
		System.out.println(c);
	}

}
