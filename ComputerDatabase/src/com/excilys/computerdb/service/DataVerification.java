package com.excilys.computerdb.service;

import com.excilys.computerdb.model.Computer;

/**
 * Contains all possible verifications of the given data
 * 
 * @author excilys
 *
 */
public class DataVerification {

	/**
	 * Verifies if the given dates are valids.
	 * 
	 * @param computer
	 *            : the computer to verify
	 * @return true if the introduction date is before the discontinuity
	 */
	public static boolean isComputerOk(Computer computer) {
		if (computer.getIntroduced().compareTo(computer.getDiscontinued()) < 0) {
			return true;
		}
		return false;
	}

}
