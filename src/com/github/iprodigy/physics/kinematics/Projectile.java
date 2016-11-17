package com.github.iprodigy.physics.kinematics;

import com.github.iprodigy.physics.util.vector.Vector;
import com.github.iprodigy.physics.util.vector.Vectors;

public class Projectile extends Entity {

	public Projectile(final double[] position, final Vector vi) {
		super(position);
		this.setVelocity(vi);
		this.setAcceleration(Vectors.gravity(position.length));
	}

}
