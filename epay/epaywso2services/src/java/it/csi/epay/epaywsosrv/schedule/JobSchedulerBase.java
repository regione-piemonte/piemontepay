/*
* SPDX-FileCopyrightText: (C) Copyright 2023 Regione Piemonte
*
* SPDX-License-Identifier: EUPL-1.2 */

package it.csi.epay.epaywsosrv.schedule;

import it.csi.epay.epaywsosrv.schedule.plan.SchedulePlan;
import it.csi.epay.epaywsosrv.schedule.plan.SchedulePlanConfig;
import it.csi.epay.epaywsosrv.schedule.plan.TimeInterval;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.ScheduleExpression;
import java.util.*;

import static it.csi.epay.epaywsosrv.util.Util.APPLICATION_CODE;

public class JobSchedulerBase {
	static protected Logger log = LogManager.getLogger(APPLICATION_CODE + ".scheduler");

	static final int MONDAY = 0;
	static final int TUESDAY = 1;
	static final int WEDNESDAY = 2;
	static final int THURSDAY = 3;
	static final int FRIDAY = 4;
	static final int SATURDAY = 5;
	static final int SUNDAY = 6;

	//@formatter:off
	static final int[] daysOfWeekArray = new int[] {
		Calendar.MONDAY, 
		Calendar.TUESDAY,
		Calendar.WEDNESDAY,
		Calendar.THURSDAY,
		Calendar.FRIDAY,
		Calendar.SATURDAY,
		Calendar.SUNDAY
	};
	//@formatter:on

	static final Map<String, Integer> daysOfWeek;
	static {
		daysOfWeek = new HashMap<String, Integer>();

		daysOfWeek.put("LU", Calendar.MONDAY);
		daysOfWeek.put("MA", Calendar.TUESDAY);
		daysOfWeek.put("ME", Calendar.WEDNESDAY);
		daysOfWeek.put("GI", Calendar.THURSDAY);
		daysOfWeek.put("VE", Calendar.FRIDAY);
		daysOfWeek.put("SA", Calendar.SATURDAY);
		daysOfWeek.put("DO", Calendar.SUNDAY);
	};

	protected ScheduleExpression buildScheduleExpression(String cronExpression) throws IllegalArgumentException {
		String[] schedulePart = cronExpression.split(" ");
		if ( schedulePart.length == 0 )
			throw new IllegalArgumentException("Invalid schedule expression");

		ScheduleExpression se = new ScheduleExpression();
		//
		String secondsExpression = schedulePart[0];
		String minuteExpression = schedulePart.length > 1 ? schedulePart[1] : "*";
		String hourExpression = schedulePart.length > 2 ? schedulePart[2] : "*";
		String dayExpression = schedulePart.length > 3 ? schedulePart[3] : "*";
		String monthExpression = schedulePart.length > 4 ? schedulePart[4] : "*";
		String yearExpression = schedulePart.length > 5 ? schedulePart[5] : "*";
		//
		se.second(secondsExpression).minute(minuteExpression).hour(hourExpression).dayOfMonth(dayExpression).month(monthExpression).year(yearExpression);

		return se;
	}

	protected SchedulePlanConfig buildSchedulePlanConfig(String cronExpression) throws IllegalArgumentException {
		if (cronExpression == null || cronExpression.length() == 0)
			throw new IllegalArgumentException("Empty expression");

		List<SchedulePlan> scheduleRangeList = new ArrayList<SchedulePlan>();
		String[] ranges = cronExpression.split("\\/");
		for ( String range : ranges ) {
			SchedulePlan scheduleRange = null;

			if ( range.charAt ( 0 ) == '*' ) {
				// qualsiasi giorno della settimana
				scheduleRange = SchedulePlan.getAlwaysRange ();

			} else if ( range.charAt ( 0 ) == '-' ) {
				if ( range.length () == 1 ) {
					// mai
					scheduleRange = SchedulePlan.getNeverRange ();
				} else
					throw new IllegalArgumentException ( range );

			} else {
				scheduleRange = new SchedulePlan ();

				int openParenthesis = range.indexOf ( '(' );
				if ( openParenthesis == -1 )
					openParenthesis = range.length ();
				else if ( !range.endsWith ( ")" ) ) {
					// manca la parentesi chiusa
					throw new IllegalArgumentException ( "Missing right parenthesis " + range );
				}

				String daysExpression = range.substring ( 0, openParenthesis );
				int minusIndex = daysExpression.indexOf ( '-' );
				int commaIndex = daysExpression.indexOf ( ',' );

				if ( minusIndex > -1 && commaIndex > -1 ) // ci sono sia meno che virgole
					throw new IllegalArgumentException ( "Cannot use minus and commas in same expression " + range );

				Integer[] days = null;
				if ( commaIndex > -1 ) {
					String[] strDays = daysExpression.split ( "," );
					days = new Integer[strDays.length];
					for ( int j = 0; j < strDays.length; j++ ) {
						days[j] = daysOfWeek.get ( strDays[j] );
						if ( days[j] == null ) {
							// giorno non riconosciuto
							throw new IllegalArgumentException ( "Invalid day of week " + strDays[j] + " in " + daysExpression );
						}
					}
				} else if ( minusIndex > -1 ) {
					String[] extremes = daysExpression.split ( "-" );
					if ( extremes.length > 2 ) {
						// troppi segni meno
						throw new IllegalArgumentException ( "Too many minus signs " + daysExpression );
					}

					Integer lowerDOW = daysOfWeek.get ( extremes[0] );
					if ( lowerDOW == null ) {
						// giorno non riconosciuto
						throw new IllegalArgumentException ( "Invalid day of week " + extremes[0] + " in " + daysExpression );
					}

					Integer higherDOW = daysOfWeek.get ( extremes[1] );
					if ( higherDOW == null ) {
						// giorno non riconosciuto
						throw new IllegalArgumentException ( "Invalid day of week " + extremes[1] + " in " + daysExpression );
					}

					int lowerDOWIndex = getDOWArrayIndex ( lowerDOW );
					int higherDOWIndex = getDOWArrayIndex ( higherDOW );
					if ( lowerDOWIndex < higherDOWIndex ) {
						days = new Integer[higherDOWIndex - lowerDOWIndex + 1];
						for ( int j = 0; j < higherDOWIndex - lowerDOWIndex + 1; j++ ) {
							days[j] = daysOfWeekArray[j + lowerDOWIndex];
						}
					} else {
						// giorno non riconosciuto
						throw new IllegalArgumentException ( "Invalid range of days:" + daysExpression );
					}
				} else {
					Integer day = daysOfWeek.get ( daysExpression );
					if ( day == null ) {
						// giorno non riconosciuto
						throw new IllegalArgumentException ( "Invalid day of week " + daysExpression );
					}
					days = new Integer[] { day };
				}
				scheduleRange.setDays ( days );
			}
			int openParenthesis = range.indexOf ( '(' );
			if ( openParenthesis > -1 ) {
				String timeExpression = range.substring ( openParenthesis + 1, range.length () - 1 );

				String[] tokens = timeExpression.split ( "," );

				TimeInterval[] timeIntervals = new TimeInterval[tokens.length];
				for ( int t = 0; t < tokens.length; t++ ) {

					String hourExpression = tokens[t];

					if ( hourExpression.length () == 1 ) {
						if ( hourExpression.charAt ( 0 ) == '*' ) {
							timeIntervals[t] = new TimeInterval ( 0, 0, 23, 59 );
						} else if ( hourExpression.charAt ( 0 ) == '-' ) {
							timeIntervals[t] = new TimeInterval ();
							timeIntervals[t].setStartTime ( 0 );
							timeIntervals[t].setEndTime ( -1 );
						} else {
							throw new IllegalArgumentException ( "Invalid time expression " + hourExpression );
						}
						if ( tokens.length > 1 ) {
							throw new IllegalArgumentException ( "Invalid time expression, * or - must be used alone (" + hourExpression + ")" );
						}
					} else {
						if ( hourExpression.length () == 11 && hourExpression.charAt ( 2 ) == ':' && hourExpression.charAt (
										5 ) == '-' && hourExpression.charAt ( 8 ) == ':' ) {
							try {
								int startHH = Integer.parseInt ( hourExpression.substring ( 0, 2 ) );
								int startMM = Integer.parseInt ( hourExpression.substring ( 3, 5 ) );
								int endHH = Integer.parseInt ( hourExpression.substring ( 6, 8 ) );
								int endMM = Integer.parseInt ( hourExpression.substring ( 9, 11 ) );

								timeIntervals[t] = new TimeInterval ( startHH, startMM, endHH, endMM );

								if ( timeIntervals[t].getStartTime () > timeIntervals[t].getEndTime () ) {
									throw new IllegalArgumentException ( "Invalid time expression " + hourExpression + " start time is greater than end time" );
								}

							} catch ( NumberFormatException e ) {
								throw new IllegalArgumentException ( "Invalid time expression " + hourExpression );
							} catch ( IllegalArgumentException e ) {
								throw new IllegalArgumentException ( e.getMessage () + " " + hourExpression );
							}
						} else {
							throw new IllegalArgumentException ( "Invalid time expression " + hourExpression );
						}
					}
				}
				scheduleRange.setTimeIntervals ( timeIntervals );
			}
			scheduleRangeList.add ( scheduleRange );
		}

		SchedulePlanConfig planConfig = new SchedulePlanConfig();
		planConfig.setSchedulePlans(scheduleRangeList);
		return planConfig;
	}

	protected boolean isInRange(Date date, SchedulePlanConfig scheduleConfig) {
		boolean inRange = false;

		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		int dow = cal.get(Calendar.DAY_OF_WEEK);

		for (SchedulePlan range : scheduleConfig.getSchedulePlans()) {
			for (Integer dayOfWeek : range.getDays()) {
				if (dayOfWeek == dow) {
					if (range.getTimeIntervals() != null) {
						int time = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
						for (TimeInterval interval : range.getTimeIntervals())
							if (time >= interval.getStartTime() && time <= interval.getEndTime()) {
								inRange = true;
								break;
							}
					} else
						inRange = true;
				}
			}
		}

		return inRange;
	}

	private int getDOWArrayIndex(Integer calendarDOW) {
		for (int i = 0; i < daysOfWeekArray.length; i++) {
			if (daysOfWeekArray[i] == calendarDOW ) {
				return i;
			}
		}
		return -1;
	}

}
