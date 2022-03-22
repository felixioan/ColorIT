package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import model.ProjectManagementModel;
import model.Requirement;
import model.Task;
import model.TimeClass;

public class RequirementDetailsViewController
{
  public ListView<String> taskList;
  @FXML private Label requirementName;
  @FXML private Label requirementID;
  @FXML private Label reasTeamMember;
  @FXML private Label deadline;
  @FXML private Label status;
  @FXML private Label description;

  private ViewHandler viewHandler;
  private ProjectManagementModel model;
  private Region root;
  private Requirement currentRequirement;
  private Task[] taskArray;

  public RequirementDetailsViewController() {
    //just to have own constructor
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
  }

  public void reset(){
    this.taskList.getItems().clear();
    currentRequirement = viewHandler.getCurrentRequirement();
    if (currentRequirement == null){
      return;
    }
    this.requirementName.setText(model.getName(currentRequirement));
    this.requirementID.setText(String.valueOf(model.getRequirementID(currentRequirement)));
    String [] desc = model.getDescription(currentRequirement);
    if (model.isFunctional(currentRequirement)){
      String finalDescription = "";
      finalDescription += "As a "+desc[0]+ "\n"+
          "I want to " + desc[1] + "\n"+
          "such that " + desc[2];
      this.description.setText(finalDescription);
    } else {
      this.description.setText(desc[0]);
    }
    this.reasTeamMember.setText(model.getName(model.getResponsibleTeamMember(currentRequirement)));
    this.status.setText(currentRequirement.getStatus());
    long deadline = model.getDeadlineTime(currentRequirement);
    this.deadline.setText(new TimeClass(deadline).getFormattedDate());
    //TODO add estimated time

    this.taskArray = model.getAllTasks(currentRequirement); //TODO make switch to change list
    for (Task task:
         taskArray)
    {
      this.taskList.getItems().add(task.getName());
    }
  }
  
  public Region getRoot(){
    return root;
  }

  public void addTask()
  {
    this.viewHandler.openView("AddTask");
  }

  public void openTask()
  {
    viewHandler.setCurrentTask(taskArray[taskList.getSelectionModel().getSelectedIndices().get(0)]);
    viewHandler.openView("TaskView");
  }

  public void editTask()
  {
    viewHandler.setCurrentTask(taskArray[taskList.getSelectionModel().getSelectedIndices().get(0)]);
    viewHandler.openView("ManageTask");
  }

  public void deleteTask()
  {
    int index = this.taskList.getSelectionModel().getSelectedIndex();
    try {
      model.deleteTask(currentRequirement,taskArray[index]);
      taskList.getItems().remove(index);
    } catch (Exception e) {
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("unable to delete");
      alert.setHeaderText("It was not able to remove it, please try it later.");
      alert.show();
    }
  }
}
