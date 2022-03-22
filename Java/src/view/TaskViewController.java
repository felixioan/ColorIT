package view;

import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;

public class TaskViewController {
  public Label taskName;
  public Label taskID;
  public Label requirementID;
  public Label taskStatus;
  public Label taskDescription;
  public Label estimatedTime;
  public Label deadline;
  public Label totalTimeSpend;
  public TableColumn<TaskTrackViewModel, String> nameColumn;
  public TableView<TaskTrackViewModel> teamTable;
  public TableColumn<TaskTrackViewModel, String> timeColumn;
  public Label responsiblePerson;
  public TextField hoursSpend;
  public TextField minutesSpend;
  public TextField secondsSpend;
  private Region root;
  private ViewHandler viewHandler;
  private ProjectManagementModel model;
  private Task currentTask;
  private TaskTrackListViewController viewModel;
  private TeamMember[] teamMembers;

  public TaskViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    this.nameColumn.setSortable(false);
    this.timeColumn.setSortable(false);
    reset();
  }

  public void reset(){
    this.currentTask = viewHandler.getCurrentTask();
    if (currentTask == null){
      return;
    }
    this.responsiblePerson.setText(model.getName(model.getResponsibleTeamMember(currentTask)));
    this.taskName.setText(model.getName(currentTask));
    this.taskID.setText(String.valueOf(model.getTaskID(currentTask)));
    this.requirementID.setText(String.valueOf(model.getRequirementID(currentTask)));
    this.taskStatus.setText(model.getStatus(currentTask));
    this.deadline.setText(new TimeClass(model.getDeadlineTime(currentTask)).getFormattedDate());
    this.estimatedTime.setText(TimeFormat.formatSeconds(model.getEstimatedTime(currentTask)));
    this.taskDescription.setText(model.getDescription(currentTask));
    this.totalTimeSpend.setText(TimeFormat.formatSeconds(model.getTotalTime(currentTask)));

    this.viewModel = new TaskTrackListViewController(model,currentTask);
    this.teamMembers = model.getTeamMembers(currentTask);

    nameColumn.setCellValueFactory(
        cellDate -> cellDate.getValue().getNameProperty()
    );
    timeColumn.setCellValueFactory(
        cellData -> cellData.getValue().getTimeProperty()
    );

    teamTable.setItems(viewModel.getTeamList());

  }

  public Region getRoot(){
    return root;
  }

  public void openRequirements()
  {
    viewHandler.openView("RequirementView");
  }

  public void add()
  {
    viewHandler.setCurrentTask(currentTask);
    viewHandler.openView("AddTeamMemberToTask");
  }

  public void setTimeWorked()
  {
    int time = 0;
    try {
      time+= Integer.parseInt(this.hoursSpend.getText()) * 60 * 60;
      time+= Integer.parseInt(this.minutesSpend.getText()) * 60;
      time += Integer.parseInt(this.secondsSpend.getText());
    } catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("please put just numbers");
      alert.setHeaderText("You need to enter just numbers to fields hours and minutes");
      alert.show();
      this.minutesSpend.setText("");
      this.hoursSpend.setText("");
      return;
    }
    try {
      model.setTimeWorked(currentTask,teamMembers[teamTable.getSelectionModel().getFocusedIndex()], time);
      this.reset();
    } catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("something went wrong");
      alert.setHeaderText("Something went wrong please chose team member and enter his new time");
      alert.show();
    }
  }

  public void addTimeWorked()
  {
    int time = 0;
    try {
      time+= Integer.parseInt(this.hoursSpend.getText()) * 60 * 60;
      time+= Integer.parseInt(this.minutesSpend.getText()) * 60;
    } catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("please put just numbers");
      alert.setHeaderText("You need to enter just numbers to fields hours and minutes");
      alert.show();
      this.minutesSpend.setText("");
      this.hoursSpend.setText("");
      return;
    }
    try {
      model.setTimeWorked(currentTask,teamMembers[teamTable.getSelectionModel().getFocusedIndex()],
          time + model.getTimeSpendOfMember(currentTask,teamMembers[teamTable.getSelectionModel().getFocusedIndex()]));
      this.reset();
    } catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.ERROR);
      alert.setTitle("something went wrong");
      alert.setHeaderText("Something went wrong please chose team member and enter his new time");
      alert.show();
    }
  }
}
