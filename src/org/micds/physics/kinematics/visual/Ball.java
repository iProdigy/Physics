package org.micds.physics.kinematics.visual;

import javafx.scene.shape.Sphere;
import lombok.Data;
import org.micds.physics.kinematics.Entity;

@Data
public class Ball extends Sphere {
	private final Entity ent;

	public Ball(final double radius, final double[] position) {
		super(radius);
		this.ent = new Entity(position);
	}

	// TODO: Bridge gap between physics entity and javafx sphere representation

}
