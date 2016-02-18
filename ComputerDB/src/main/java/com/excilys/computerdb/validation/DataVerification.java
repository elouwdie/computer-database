package com.excilys.computerdb.validation;

import java.time.LocalDate;

import com.excilys.computerdb.exceptions.DataException;
import com.excilys.computerdb.service.CompanyService;

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
	 *            the computer to verify
	 * @return true if the introduction date is before the discontinuity
	 */
	public static void areDatesOk(LocalDate intro, LocalDate discont) {
		if (intro != null && discont != null) {
			if (intro.compareTo(discont) > 0) {
				throw new DataException(
						"Discontinued date must be after introduction date. Please retry with a date after .");
			}
		}
	}

	/**
	 * Verifies if the given date is a realistic date, and is after the Monday
	 * 1st, 1970
	 * 
	 * @param date
	 *            the date to verify
	 * @return true if the date is correct, false if it isn't
	 */
	public static boolean isDateCorrect(int year, int month, int day) {
		if (year >= 1970) {
			// month with 31 days
			if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
				if (day > 0 && day <= 31) {
					return true;
				}
				// month with 30 days
			} else if (month == 4 || month == 6 || month == 9 || month == 11) {
				if (day > 0 && day <= 30) {
					return true;
				}
				// month with 28 days
			} else if (month == 28 && day > 0 && day <= 28) {
				return true;
			}
		}
		return false;
	}

	public static boolean isCompanyOk(long companyId) {
		return (CompanyService.findById(companyId) != null);
	}

}
