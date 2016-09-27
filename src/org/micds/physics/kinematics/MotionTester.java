package org.micds.physics.kinematics;

import org.micds.physics.util.AngleUnit;
import org.micds.physics.vector.Vector;

import java.util.LinkedHashMap;
import java.util.Map;

public class MotionTester {

	public static void main(String[] args) {
		// TODO: Unit testing

		final Projectile ball = new Projectile(new double[]{0.0, 50.0}, new Vector(25, 0, AngleUnit.DEGREES));
		System.out.println(simulate(ball));
	}

	public static Map<String, Vector> simulate(final Entity entity) {
		return simulate(entity, 0.0001, 1, 0.0);
	}

	public static Map<String, Vector> simulate(final Entity entity, final double dt, final int limitDimension, final double min) {
		final Entity e = new Entity(entity); // make a copy instead of altering the passed entity

		if (e.getDimensions() <= limitDimension)
			return null;

		final Map<String, Vector> result = new LinkedHashMap<>();
		result.put("xi", new Vector(e.getPosition()));
		result.put("vi", e.getVelocity());
		result.put("ai", e.getAcceleration());

		double time = 0.0;

		while ((time == 0.0) || (e.getPosition()[limitDimension] > 0)) {
			e.update(dt);
			time += dt;
		}

		time -= dt;

		result.put("tf", new Vector(time));
		result.put("xf", new Vector(e.getPosition()));
		result.put("vf", e.getVelocity());
		result.put("af", e.getAcceleration());
		return result;
	}

}
