package model;

    import java.io.Serializable;

    /**
     * Converts seconds to human readable time format
     * @author Group 6 - 1Y ICT A2020
     * @version 1.0 - December 2020
     * @since 1.0
     */
    public class TimeFormat implements Serializable
    {
      public static final String formatSeconds(int timeInSeconds) {
        if(timeInSeconds<0) {
          timeInSeconds=0;
        }
        int hours = timeInSeconds / 3600;
        int secondsLeft = timeInSeconds - hours * 3600;
        int minutes = secondsLeft / 60;
        int seconds = secondsLeft - minutes * 60;

        String formattedTime = "";
        if (hours < 10)
          formattedTime += "0";
        formattedTime += hours + ":";

        if (minutes < 10)
          formattedTime += "0";
        formattedTime += minutes + ":";

        if (seconds < 10)
          formattedTime += "0";
        formattedTime += seconds ;

        return formattedTime;
      }
    }


