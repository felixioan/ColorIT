package view;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import model.ProjectManagementModel;
import model.Requirement;
import model.TimeFormat;

public class RequirementViewModel
{
  private StringProperty nameProperty;
  private StringProperty statusProperty;
  private StringProperty reqEstiTimeProperty;
  private StringProperty reqUsedTimeProperty;

  public RequirementViewModel(Requirement requirement, ProjectManagementModel model){
    this.nameProperty = new SimpleStringProperty(model.getName(requirement));
    this.statusProperty = new SimpleStringProperty(model.getStatus(requirement));
    this.reqEstiTimeProperty = new SimpleStringProperty(TimeFormat.formatSeconds(model.getEstimatedTime(requirement)));
    this.reqUsedTimeProperty = new SimpleStringProperty(TimeFormat.formatSeconds(model.getUsedTime(requirement)));
  }

  public StringProperty getNameProperty()
  {
    return nameProperty;
  }

  public StringProperty getReqEstiTimeProperty()
  {
    return reqEstiTimeProperty;
  }

  public StringProperty getReqUsedTimeProperty()
  {
    return reqUsedTimeProperty;
  }

  public StringProperty getStatusProperty()
  {
    return statusProperty;
  }
}
