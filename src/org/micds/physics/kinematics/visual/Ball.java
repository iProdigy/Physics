package org.micds.physics.kinematics.visual;

import lombok.Data;
import org.micds.physics.kinematics.Entity;

@Data
public class Ball {
	private final double radius;
	private final Entity ent;

	public Ball(final double radius, final double[] position) {
		this.radius = radius;
		this.ent = new Entity(position);
	}

	// TODO

}
