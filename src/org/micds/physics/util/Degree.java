package org.micds.physics.util;

import lombok.Value;

import static org.micds.physics.util.DegreeUnit.*;

@Value
public class Degree {
	private final DegreeUnit unit;
	private double value;

	public Degree convert(final DegreeUnit to) {
		return new Degree(to, this.value * this.unit.convFactor(to));
	}

	public double getDegrees() {
		return this.getValue(DEGREES);
	}

	public double getRadians() {
		return this.getValue(RADIANS);
	}

	public double getGradians() {
		return this.getValue(GRADIANS);
	}

	public double getTurns() {
		return this.getValue(TURNS);
	}

	private double getValue(final DegreeUnit du) {
		return this.convert(du).getValue();
	}

}
