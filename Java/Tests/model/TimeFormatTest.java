package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TimeFormatTest
{

  @BeforeEach void setUp()
  {
  }

  @AfterEach void tearDown()
  {
  }

  @Test void timeFormat()
  {
    assertEquals("00:00:00", TimeFormat.formatSeconds(-10));
    assertEquals("00:01:00", TimeFormat.formatSeconds(60));
    assertEquals("00:01:30", TimeFormat.formatSeconds(90));
    assertEquals("02:47:40", TimeFormat.formatSeconds(10060));
  }
}