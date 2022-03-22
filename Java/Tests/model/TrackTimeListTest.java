package model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrackTimeListTest
{
  private TrackTimeList trackTimeList;
  @BeforeEach void setUp()
  {
    this.trackTimeList = new TrackTimeList();
  }

  @AfterEach void tearDown()
  {
    this.trackTimeList = new TrackTimeList();
  }

  @Test void setTimeWorked()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.trackTimeList.setTimeWorked(tempTeamMember, 30000);
    assertEquals(30000, this.trackTimeList.getTimeOfMember(tempTeamMember));
  }

  @Test void getTotalTime()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.trackTimeList.setTimeWorked(tempTeamMember, 30000);
    TeamMember tempTeamMember2 = new TeamMember("John", "john@doe2.com");
    this.trackTimeList.setTimeWorked(tempTeamMember2, 30000);
    assertEquals(60000, this.trackTimeList.getTotalTime());
  }

  @Test void getTrackTime()
  {
    TeamMember tempTeamMember = new TeamMember("John", "john@doe.com");
    this.trackTimeList.setTimeWorked(tempTeamMember, 30000);
    assertEquals(1, this.trackTimeList.getTrackTime().size());
  }
}