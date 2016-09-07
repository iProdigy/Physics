package org.micds.physics.kinematics;

import lombok.Data;
import lombok.NonNull;
import org.micds.physics.vector.Vector;

import java.util.Arrays;

@Data
public class Entity {
	private final double[] position;
	private final Vector velocity;
	private final Vector acceleration;

	public Entity(final int dimensions) {
		this.position = new double[dimensions];

		Double[] zero = new Double[dimensions];
		Arrays.fill(zero, 0.0D);

		this.velocity = new Vector(zero);
		this.acceleration = new Vector(zero);
	}

	public void translate(@NonNull double... displacements) {
		if (position.length != displacements.length)
			throw new IllegalArgumentException();

		for (int i = 0; i < position.length; i++) {
			position[i] = position[i] + displacements[i];
		}
	}

}
