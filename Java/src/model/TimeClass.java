package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Represents timestamp. Contains methods used for converting time(UNIX format).
 * @author Group 6 - 1Y ICT A2020
 * @version 1.0 - December 2020 - December 2020
 * @since 1.0
 */
public class TimeClass implements Serializable
{
  private Timestamp savedTimestamp;

  /**
   * One-argument constructor. Creating a date from milliseconds.
   * @param timeInMilSec A long containing the time in milliseconds.
   */
  public TimeClass(long timeInMilSec)
  {
    this.savedTimestamp = new Timestamp(timeInMilSec);
  }

  /**
   * One-argument constructor. Creating a date from a string of date.
   * @param dateString A String containing the date in human readable format.
   */
  public TimeClass(String dateString) {
    this.savedTimestamp = new Timestamp(this.parseString(dateString));
  }

  /**
   * Zero-argument constructor. Creating a date from the current date.
   */
  public TimeClass() {
    this.savedTimestamp = new Timestamp(System.currentTimeMillis());
  }

  /**
   * Converts to milliseconds from days.
   * @param days An Integer containing the number of days we want to convert.
   * @return An long representing the number of milliseconds in specified number of days.
   */
  private Long dayToMilliseconds(int days) {
    return (long) (days * 24 * 60 * 60 * 1000);
  }

  /**
   * Gets the formatted date.
   * @return A String representing the date and time of the saved UNIX timestamp. HH:mm:ss MM.dd.yyyy
   */
  public String getFormattedDateTime() {
    return new SimpleDateFormat("HH:mm:ss MM.dd.yyyy").format(this.savedTimestamp);
  }
  /**
   * Gets the formatted Time.
   * @return A String representing the time of the saved UNIX timestamp. HH:mm:ss
   */
  public String getFormattedTime() {
    return new SimpleDateFormat("HH:mm:ss").format(this.savedTimestamp);
  }
  /**
   * Gets the formatted date.
   * @return A String representing the date of the saved UNIX timestamp. MM.dd.yyyy
   */
  public String getFormattedDate() {
    return new SimpleDateFormat("dd.MM.yyyy").format(this.savedTimestamp);
  }

  /**
   * Gets the UNIX timestamp equivalent of the MM.dd.yyyy date format.
   * @param dateString A String containing a date in a MM.dd.yyyy format.
   * @return A long representing milliseconds from January 1, 1970, 00:00:00 GMT (UNIX timestamp).
   */
  public long parseString(String dateString) {
    try {
      SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
      Date parsedDate = dateFormat.parse(dateString);
      return parsedDate.getTime();
    } catch(Exception e) {
      e.printStackTrace();
    }
    return 0;
  }

  /**
   * Gets the staved timestamp.
   * @return A long representing milliseconds.
   */
  public long getTime() {
    return savedTimestamp.getTime();
  }

  /**
   * Returns the answer of comparison of two timestamps, whether the two are equal.
   * @param timestampToCompare Contains the timestamp we want to compare to.
   * @return True if the objects are the same. False if they are different.
   */
  public boolean equals(Timestamp timestampToCompare) {
    return timestampToCompare.getTime() == this.savedTimestamp.getTime();
  }

  /**
   * Returns the answer of comparison of two timestamps, whether the two are equal.
   * @param timestampToCompare Contains the timestamp in milliseconds we want to compare to.
   * @return True if the objects are the same. False if they are different.
   */
  public boolean equals(long timestampToCompare) {
    return timestampToCompare == this.savedTimestamp.getTime();
  }

  /**
   * Adds a specified number of days to a timestamp.
   * @param days Contains the number of days we want to add.
   * @return The timeStamp with added days.
   */
  public TimeClass addDays(int days) {
    this.savedTimestamp.setTime(this.savedTimestamp.getTime() + dayToMilliseconds(days));
    return this;
  }
}
