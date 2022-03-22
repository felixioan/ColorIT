package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.*;

public class TaskTrackViewModel
{
  private StringProperty nameProperty;
  private StringProperty emailProperty;
  private StringProperty roleProperty;
  private StringProperty timeProperty;

  public TaskTrackViewModel(TeamMember teamMember, Task task, ProjectManagementModel model){
    nameProperty = new SimpleStringProperty(model.getName(teamMember));
    emailProperty = new SimpleStringProperty(model.getEmail(teamMember));
    timeProperty = new SimpleStringProperty(
        TimeFormat.formatSeconds(model.getTimeSpendOfMember(task, teamMember)));

    //System.out.println(model.getProductOwner(currentProject) + " .equals() " + teamMember);
    //System.out.println(model.getProductOwner(currentProject) == teamMember);
    //System.out.println(model.getProductOwner(currentProject).equals(teamMember));
    if (model.getResponsibleTeamMember(task)!=null && model.getResponsibleTeamMember(task).equals(teamMember)){
      roleProperty = new SimpleStringProperty("yes");
    } else {
      roleProperty = new SimpleStringProperty("no");
    }
  }

  public StringProperty getNameProperty()
  {
    return nameProperty;
  }

  public StringProperty getRoleProperty()
  {
    return roleProperty;
  }

  public String getEmail(){
    return emailProperty.get();
  }

  public StringProperty getTimeProperty(){
    return timeProperty;
  }
}
