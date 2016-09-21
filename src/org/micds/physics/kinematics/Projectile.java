package org.micds.physics.kinematics;

import org.micds.physics.vector.Vector;
import org.micds.physics.vector.Vectors;

public class Projectile extends Entity {

	public Projectile(final double[] position, final Vector vi) {
		super(position);
		this.setVelocity(vi);
		this.setAcceleration(Vectors.gravity(position.length));
	}

}
