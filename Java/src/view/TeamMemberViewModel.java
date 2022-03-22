package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.TeamMember;

public class TeamMemberViewModel
{
  private StringProperty nameProperty;
  private StringProperty emailProperty;

  public TeamMemberViewModel(TeamMember teamMember){
    nameProperty = new SimpleStringProperty(teamMember.getName());
    emailProperty = new SimpleStringProperty(teamMember.getEmail());
  }

  public StringProperty getEmailProperty()
  {
    return emailProperty;
  }

  public StringProperty getNameProperty(){
    return nameProperty;
  }
}
