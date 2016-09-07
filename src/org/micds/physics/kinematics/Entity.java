package org.micds.physics.kinematics;

import lombok.Data;
import org.micds.physics.vector.Vector;

@Data
public class Entity {
	private final double[] position;
	private final Vector velocity;
	private final Vector acceleration;

	// TODO
}
