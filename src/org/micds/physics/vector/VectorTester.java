package org.micds.physics.vector;

public class VectorTester {

	public static void main(String[] args) {
		/*
		final Vector a = new Vector(1.0, 0.0, -3.0);
		final Vector b = new Vector(-2.0, 5.0, 1.0);
		final Vector c = new Vector(3.0, 1.0, 1.0);

		System.out.println(a.multiply(2).subtract((b.subtract(c)).multiply(3)));
		System.out.println(new Vector(0.0, 1.0).degreeDiff(new Vector(1.0, 0.0)));
		*/

		/*
		Vector v = new Vector(100, 240);
		System.out.println(v.getComponents());
		*/

		/*
		Vector a = new Vector(2, 75);
		Vector b = new Vector(4, -15);
		Vector d = a.multiply(3).add(b);
		System.out.println(d);
		*/

		Vector a = new Vector(0.800, 90);
		Vector b = new Vector(0.300, 180);
		Vector c = new Vector(0.100, 270);
		Vector d = a.add(b).add(c);
		System.out.println(d);
		System.out.println(d.getMagnitude());
		System.out.println(d.degreeDiff(new Vector(-1.0, 0.0)));
		System.out.println("X");
		Vector e = new Vector(10.0, 15.0, 0.0);
		System.out.println(e.getDegree());
	}

}
