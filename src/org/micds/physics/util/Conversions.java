package org.micds.physics.util;

import lombok.experimental.*;

@UtilityClass
public class Conversions {
	/*
	 * Angular Conversions
	 */
	public static final double RAD_TO_DEG, GRAD_TO_DEG, TURN_TO_DEG,
			DEG_TO_RAD, GRAD_TO_RAD, TURN_TO_RAD,
			DEG_TO_GRAD, RAD_TO_GRAD, TURN_TO_GRAD,
			DEG_TO_TURN, RAD_TO_TURN, GRAD_TO_TURN;

	static {
		RAD_TO_DEG = 180.0 / Math.PI;
		GRAD_TO_DEG = 9.0 / 10.0;
		TURN_TO_DEG = 360.0;

		DEG_TO_RAD = 1.0 / RAD_TO_DEG;
		GRAD_TO_RAD = Math.PI / 200.0;
		TURN_TO_RAD = 2.0 * Math.PI;

		DEG_TO_GRAD = 1.0 / GRAD_TO_DEG;
		RAD_TO_GRAD = 1.0 / GRAD_TO_RAD;
		TURN_TO_GRAD = 400.0;

		DEG_TO_TURN = 1.0 / TURN_TO_DEG;
		RAD_TO_TURN = 1.0 / TURN_TO_RAD;
		GRAD_TO_TURN = 1.0 / TURN_TO_GRAD;
	}

}
