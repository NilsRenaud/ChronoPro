package org.ChronoPro.core;

public class ChronoFormat {

	private final long days;
	private final long hours;
	private final long min;
	private final long sec;
	private final long milli;

	private static final long COEFF_DAYS = 1000 * 60 * 60 * 24;
	private static final long COEFF_HOURS = 1000 * 60 * 60;
	private static final long COEFF_MIN = 1000 * 60;
	private static final long COEFF_SEC = 1000;

	private static final String DAY_CHAR = "d";
	private static final String HOUR_CHAR = "h";
	private static final String MIN_CHAR = "m";
	private static final String SEC_CHAR = "s";
	private static final String MILLI_CHAR = "ms";
	private static final String DELIMITER = " ";

	/**
	 * Instantiate the object
	 * 
	 * @param time
	 *            in millisecond (ms)
	 */
	public ChronoFormat(final long time) {
		long tmp = time;

		days = time/COEFF_DAYS;
		tmp -= days * COEFF_DAYS;
		hours = tmp/COEFF_HOURS;
		tmp -= hours * COEFF_HOURS;
		min = tmp/COEFF_MIN;
		tmp -= min * COEFF_MIN;
		sec = tmp/COEFF_SEC;
		milli = tmp - sec * COEFF_SEC;
	}

	public String getCompleteDate(){
		StringBuffer buf = new StringBuffer();
		if(days != 0)
			buf.append(days).append(DAY_CHAR).append(DELIMITER);
		if(days !=0 || hours !=0)
			buf.append(hours).append(HOUR_CHAR).append(DELIMITER);
		if(days != 0 || hours != 0 || min !=0)
			buf.append(min).append(MIN_CHAR).append(DELIMITER);
		
		buf.append(sec).append(SEC_CHAR).append(DELIMITER);
		buf.append(milli).append(MILLI_CHAR).append(DELIMITER);
			
		return buf.toString();
	}

	public long getDays() {
		return days;
	}

	public long getHours() {
		return hours;
	}

	public long getMin() {
		return min;
	}

	public long getSec() {
		return sec;
	}

	public long getMilli() {
		return milli;
	}
	
	
}
