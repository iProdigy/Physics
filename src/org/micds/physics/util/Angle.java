package org.micds.physics.util;

import lombok.Value;
import lombok.experimental.Wither;

import static org.micds.physics.util.AngleUnit.*;

@Value
public class Angle {
	public static final Angle ZERO = new Angle(0.0, AngleUnit.RADIANS);

	@Wither
	private final double value;
	private final AngleUnit unit;

	public Angle convert(final AngleUnit to) {
		return new Angle(this.value * this.unit.convFactor(to), to);
	}

	public Angle simplified() {
		double deg = getDegrees() % 360;

		if (deg < 0)
			deg += 360;

		return new Angle(deg * DEGREES.convFactor(this.unit), this.unit);
	}

	public Quadrant getQuadrant() {
		return Quadrant.values()[MathUtil.getQuadrant(getDegrees()) - 1];
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

	private double getValue(final AngleUnit au) {
		return this.convert(au).getValue();
	}

	public double sin() {
		return Math.sin(getRadians());
	}

	public double cos() {
		return Math.cos(getRadians());
	}

	public double tan() {
		return Math.tan(getRadians());
	}

	public double csc() {
		return 1 / sin();
	}

	public double sec() {
		return 1 / cos();
	}

	public double cot() {
		return 1 / tan();
	}

}
