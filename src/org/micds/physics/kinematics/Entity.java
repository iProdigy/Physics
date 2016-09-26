package org.micds.physics.kinematics;

import lombok.Data;
import lombok.NonNull;
import org.micds.physics.vector.Vector;

import java.util.Arrays;

@Data
public class Entity {
	private final int dimensions;
	private final double[] position;
	@NonNull
	private Vector velocity, acceleration;

	public Entity(final int dimensions) {
		this(new double[dimensions]);
	}

	public Entity(final double[] position) {
		this.dimensions = position.length;
		this.position = Arrays.copyOf(position, position.length);
		setZeroVelocityAndAcceleration();
	}

	public Entity(final Entity copy) {
		this.dimensions = copy.dimensions;
		this.position = Arrays.copyOf(copy.getPosition(), this.dimensions);
		this.velocity = copy.getVelocity();
		this.acceleration = copy.getAcceleration();
	}

	private void setZeroVelocityAndAcceleration() {
		final Double[] zero = new Double[dimensions];
		Arrays.fill(zero, 0.0D);

		this.velocity = new Vector(zero);
		this.acceleration = new Vector(zero);
	}

	public void translate(@NonNull final double... displacements) {
		if (position.length != displacements.length)
			throw new IllegalArgumentException();

		for (int i = 0; i < position.length; i++) {
			position[i] = position[i] + displacements[i];
		}
	}

	public void update(final double time) {
		for (int d = 0; d < this.dimensions; d++) {
			position[d] = position[d] + velocity.getComponent(d) * time + 0.5 * acceleration.getComponent(d) * time * time;
		}

		// update velocity based on the assumption that acceleration is unchanging over time
		this.velocity = this.velocity.add(this.acceleration.multiply(time));
	}

}
