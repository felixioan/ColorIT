package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackTimeTest
{
  private TrackTime trackTime;
  private TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
  @BeforeEach void setUp()
  {
    this.trackTime = new TrackTime(tempTeamMember, 0);
  }

  @AfterEach void tearDown()
  {
    this.trackTime = new TrackTime(tempTeamMember, 0);
  }

  @Test void setTimeWorked()
  {
    this.trackTime.setTimeWorked(30000);
    assertEquals(30000, this.trackTime.getTime());
  }

  @Test void getTeamMember()
  {
    assertEquals("john@doe.com", this.trackTime.getTeamMember().getEmail());
  }
}