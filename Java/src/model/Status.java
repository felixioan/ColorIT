  package model;

  import java.io.Serializable;

  /**
   * Holds standardised strings for the names of statuses.
   * @author Group 6 - 1Y ICT A2020
   * @version 1.0 - December 2020
   * @since 1.0
   */
  public class Status implements Serializable
  {
      public static final String NOT_STARTED  = "Not started";
      public static final String STARTED  = "Started";
      public static final String ENDED  = "Ended";
      public static final String APPROVED  = "Approved";
      public static final String REJECTED  = "Rejected";
  }
