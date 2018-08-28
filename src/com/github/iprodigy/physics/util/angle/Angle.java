package com.github.iprodigy.physics.util.angle;

import lombok.Value;
import lombok.experimental.Wither;
import org.jetbrains.annotations.NotNull;
import com.github.iprodigy.physics.util.MathUtil;
import com.github.iprodigy.physics.util.Quadrant;
import com.github.iprodigy.physics.util.abstraction.Computational;
import com.github.iprodigy.physics.util.abstraction.Quantifiable;

import static com.github.iprodigy.physics.util.angle.AngleUnit.*;

@Value
public class Angle implements Quantifiable<Double>, Comparable<Angle>, Computational<Angle> {
	public static final Angle ZERO = new Angle(0.0, RADIANS);

	@Wither
	private final double value;
	private final AngleUnit unit;

	public Angle convert(final AngleUnit to) {
		return (to == this.unit) ? this : new Angle(this.value * this.unit.convFactor(to), to);
	}

	public Angle simplified() {
		double deg = getDegrees() % 360;

		if (deg < 0)
			deg += 360;

		return new Angle(deg * AngleUnit.DEGREES.convFactor(this.unit), this.unit);
	}

	@Override
	public Angle add(final Angle other) {
		return this.withValue(this.value + other.convert(this.unit).getValue());
	}

	@Override // override to be more efficient by not creating unnecessary objects
	public Angle subtract(final Angle other) {
		return this.withValue(this.value - other.convert(this.unit).getValue());
	}

	@Override
	public Angle multiply(final double scalar) {
		return this.withValue(this.value * scalar);
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

	@Override
	public Double getMagnitude() {
		return this.getValue();
	}

	@Override
	public int compareTo(@NotNull Angle o) {
		final Angle simpl = this.simplified();
		return Double.compare(simpl.value, o.simplified().convert(simpl.unit).value);
	}

	@Override
	public boolean equals(final Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		final Angle simpl = this.simplified(),
			other = ((Angle) o).simplified().convert(simpl.unit);
		return Double.compare(simpl.value, other.value) == 0;
	}

	@Override
	public int hashCode() {
		return Double.hashCode(this.simplified().getDegrees());
	}

}
