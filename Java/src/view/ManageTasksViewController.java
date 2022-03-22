package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import model.*;

import java.time.LocalDate;

public class ManageTasksViewController {
  public TextField taskName;
  public TextArea taskDescription;
  public DatePicker deadline;
  public ChoiceBox<String> choiceBoxTeam;
  public TextField estimatedHour;
  public TextField estimatedMinutes;
  public TextField estimatedSeconds;
  public ChoiceBox<String> choiceBoxStatus;

  private ProjectManagementModel model;
  private Region root;
  private ViewHandler viewHandler;
  private Task currentTask;
  private Requirement currentRequirement;
  private ObservableList<String> teamMemberOptions;
  private TeamMember[] teamMembers;

  public ManageTasksViewController(){
    //nothing
  }


  public void init(ViewHandler viewHandler,ProjectManagementModel model, Region root, boolean edit)
  {
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;
    reset(edit);
  }

  public void reset(boolean edit){
    currentRequirement = viewHandler.getCurrentRequirement();
    currentTask = viewHandler.getCurrentTask();
    choiceBoxStatus.setItems(FXCollections.
        observableArrayList("Not started","Started","Approved","Rejected","Ended"));

    this.teamMemberOptions = FXCollections.observableArrayList();
    this.teamMembers = model.getAllTeamMembers(); //NOTE maybe change this later
    this.choiceBoxTeam.getItems().clear();
    for (TeamMember teamMember: teamMembers){
      teamMemberOptions.add(teamMember.getName());
    }
    this.choiceBoxTeam.getItems().addAll(teamMemberOptions);
    deadline.setDayCellFactory(picker -> new DateCell() {
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();

        setDisable(empty || date.compareTo(today) < 0 );
      }
    });

    if (currentRequirement == null){
      return;
    }
    if (edit){
      this.taskName.setText(model.getName(currentTask));
      this.taskDescription.setText(model.getDescription(currentTask));
      this.deadline.setTooltip(new Tooltip(new TimeClass(model.getDeadlineTime(currentTask)).getFormattedDate()));
      int time = model.getEstimatedTime(currentTask);
      this.estimatedHour.setText(String.valueOf(time/3600));
      this.estimatedMinutes.setText(String.valueOf((time/60)%60));
      this.estimatedSeconds.setText(String.valueOf(time%60));
      this.choiceBoxTeam.setTooltip(new Tooltip(model.getResponsibleTeamMember(currentTask).getName()));
      this.choiceBoxStatus.setTooltip(new Tooltip(model.getStatus(currentTask)));
      this.choiceBoxStatus.setVisible(true);
    } else {
      currentTask = null;
      this.taskName.setText("");
      this.taskDescription.setText("");
      this.deadline.setValue(LocalDate.now());
      this.estimatedHour.setText("");
      this.estimatedMinutes.setText("");
      this.estimatedSeconds.setText("");
      this.choiceBoxStatus.setVisible(false);
    }
    this.choiceBoxTeam.getSelectionModel().clearSelection();
  }

  public Region getRoot()
  {
    return root;
  }

  public void confirmTask()
  {
    int time = 0;
    try {
      time += Integer.parseInt(this.estimatedHour.getText()) * 60 * 60;
      time += Integer.parseInt(this.estimatedMinutes.getText()) * 60;
      time += Integer.parseInt(this.estimatedSeconds.getText());
    } catch (Exception e){
      Alert alert = new Alert(Alert.AlertType.INFORMATION);
      alert.setTitle("select a team member");
      alert.setHeaderText("You need to select team member that you want to edit first.");
      alert.show();
      return;
    }
    if (currentTask == null){
      if (this.choiceBoxTeam.getValue() == null){
        model.addTask(currentRequirement,this.taskName.getText(),model.getRequirementID(currentRequirement),
            time, this.taskDescription.getText(),
            new TimeClass(""+this.deadline.getValue().getDayOfMonth()+"."+
                this.deadline.getValue().getMonthValue()+"." +
                this.deadline.getValue().getYear()).getTime());//TODO check this
      } else {
        model.addTask(currentRequirement,this.taskName.getText(),model.getRequirementID(currentRequirement),
            time, this.taskDescription.getText(),
            new TimeClass(""+this.deadline.getValue().getDayOfMonth()+"."+
                this.deadline.getValue().getMonthValue()+"." +
                this.deadline.getValue().getYear()).getTime(),
            teamMembers[this.choiceBoxTeam.getSelectionModel().getSelectedIndex()]);
      }
      this.cancel();
    } else {
      if (this.choiceBoxTeam.getValue() == null){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("select a team member");
        alert.setHeaderText("You need to select responsible team member.");
        alert.show();
      } else {
        switch (choiceBoxStatus.getSelectionModel().getSelectedIndex()){
          case 0:model.setStatus(currentTask,Status.NOT_STARTED); break;
          case 1:model.setStatus(currentTask,Status.STARTED);break;
          case 2:model.setStatus(currentTask,Status.APPROVED);break;
          case 3:model.setStatus(currentTask,Status.REJECTED);break;
          case 4:model.setStatus(currentTask,Status.ENDED);break;
        }
        model.changeTask(this.currentTask,this.taskName.getText(),time,
            this.taskDescription.getText(),
            new TimeClass(""+this.deadline.getValue().getDayOfMonth()+"."+
                this.deadline.getValue().getMonthValue()+"." +
                this.deadline.getValue().getYear()).getTime(),
            teamMembers[this.choiceBoxTeam.getSelectionModel().getSelectedIndex()]);
        this.cancel();
      }

    }
  }

  public void cancel()
  {
    viewHandler.openView("RequirementView");
  }
}
