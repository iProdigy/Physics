package org.micds.physics.util;

public enum Quadrant {
	I, II, III, IV;

	public int intValue() {
		return this.ordinal() + 1;
	}

}
