package org.micds.physics.util;

import static org.micds.physics.util.Conversions.*;

public enum AngleUnit {
	DEGREES, RADIANS, GRADIANS, TURNS;

	public double convFactor(final AngleUnit to) {
		if (this == to)
			return 1.0;

		switch (to) {
			case DEGREES:
				return (this == RADIANS) ? RAD_TO_DEG : ((this == GRADIANS) ? GRAD_TO_DEG : (this == TURNS ? TURN_TO_DEG : Double.NaN));

			case RADIANS:
				return (this == DEGREES) ? DEG_TO_RAD : ((this == GRADIANS) ? GRAD_TO_RAD : (this == TURNS) ? TURN_TO_RAD : Double.NaN);

			case GRADIANS:
				return (this == DEGREES) ? DEG_TO_GRAD : ((this == RADIANS) ? RAD_TO_GRAD : ((this == TURNS) ? TURN_TO_GRAD : Double.NaN));

			case TURNS:
				return (this == DEGREES) ? DEG_TO_TURN : ((this == RADIANS) ? RAD_TO_TURN : ((this == GRADIANS) ? GRAD_TO_TURN : Double.NaN));

			default:
				return Double.NaN;
		}
	}
}
