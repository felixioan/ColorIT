package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import model.*;

import java.time.LocalDate;

public class ManageRequirementsViewController {
  @FXML private ToggleGroup descriptionType;
  @FXML private VBox statusBox;
  @FXML private ChoiceBox<String> statusChoiceBox;
  @FXML private Label actualDeadline;
  @FXML private StackPane stackPaneDescription;
  @FXML private TextField who;
  @FXML private TextField what;
  @FXML private TextField why;
  @FXML private Label actualRespTeamMem;
  @FXML private Label actualStatus;
  private ObservableList<String> teamMemberOptions;
  private TeamMember[] teamMembers;
  @FXML private ChoiceBox<String> RequirementResponsibleMember;
  @FXML private TextField requirementName;
  @FXML private TextArea requirementDescription;
  @FXML private DatePicker requirementDeadline;
  private Project currentProject;
  private Requirement currentRequirement;

  private Region root;
  private ProjectManagementModel model;
  private ViewHandler viewHandler;

  public ManageRequirementsViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model,Region root,boolean edit){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    this.statusChoiceBox.setItems(FXCollections.
        observableArrayList("Not started","Started","Approved","Rejected","Ended"));
    reset(edit);
  }

  public void reset(boolean edit){
    if (edit){
      this.currentRequirement = viewHandler.getCurrentRequirement();
    } else {
      this.currentRequirement = null;
    }
    this.descriptionType.getToggles().get(0).setSelected(true);
    this.stackPaneDescription.getChildren().get(0).setVisible(false);
    this.stackPaneDescription.getChildren().get(1).setVisible(true);
    
    this.currentProject = viewHandler.getCurrentProject();
    this.teamMembers = model.getTeamMembers(currentProject);
    this.teamMemberOptions = FXCollections.observableArrayList();
    for (TeamMember teamMember:
         teamMembers)
    {
      this.teamMemberOptions.add(model.getName(teamMember));
    }
    this.RequirementResponsibleMember.setItems(teamMemberOptions);
    requirementDeadline.setDayCellFactory(picker -> new DateCell() {
      public void updateItem(LocalDate date, boolean empty) {
        super.updateItem(date, empty);
        LocalDate today = LocalDate.now();

        setDisable(empty || date.compareTo(today) < 0 );
      }
    });
    if (edit){
      this.requirementName.setText(model.getName(currentRequirement));
      if (model.isFunctional(currentRequirement)){
        String[] desc = model.getDescription(currentRequirement);
        this.who.setText(desc[0]);
        this.what.setText(desc[1]);
        this.why.setText(desc[2]);
      } else {
        this.requirementDescription.setText(model.getDescription(currentRequirement)[0]);
      }

      /*System.out.println(new TimeClass(model.getDeadlineTime(currentRequirement)).getFormattedDate().split("."));
      Integer[] datum = Arrays.stream(new TimeClass(model.
          getDeadlineTime(currentRequirement)).getFormattedDate().split(".")).
          map(Integer::valueOf).toArray(Integer[]::new);*/
      this.actualDeadline.setTooltip(new Tooltip(new TimeClass(model.getDeadlineTime(currentRequirement)).getFormattedDate()));
      this.requirementDeadline.setValue(LocalDate.now());
      this.statusBox.setVisible(true);
      this.actualStatus.setTooltip(new Tooltip(model.getStatus(currentRequirement)));
      this.actualRespTeamMem.setTooltip( new Tooltip(model.getResponsibleTeamMember(currentRequirement).getName()));
    } else {
      this.statusBox.setVisible(false);
      this.requirementDeadline.setValue(LocalDate.now());
      this.who.setText("");
      this.what.setText("");
      this.why.setText("");
      this.requirementDescription.setText("");
      this.requirementName.setText("");
      //this.RequirementResponsibleMember.getSelectionModel().clearSelection();
    }
  }

  public Region getRoot(){
    return root;
  }

  public void confirm()
  {
    LocalDate date = this.requirementDeadline.getValue();
    String stringDate = date.getDayOfMonth() + "." + date.getMonthValue() + "." + date.getYear();
    System.out.println(stringDate);
    System.out.println(new TimeClass(new TimeClass(stringDate).getTime()).getFormattedDate());
    String stringDate2 = "12.16";
    TeamMember newTeamMember = teamMembers[this.RequirementResponsibleMember.getSelectionModel().getSelectedIndex()];
    //TODO implement functional and non functional version
    if (viewHandler.getCurrentRequirement() == null){
      if (this.descriptionType.getToggles().get(0).isSelected()){
        model.addRequirement(currentProject,
            this.requirementName.getText(),
            new String[]{this.who.getText(),this.what.getText(),this.why.getText()},
            new TimeClass(stringDate).getTime(),
            newTeamMember);
      } else {
        model.addRequirement(
            currentProject,
            this.requirementName.getText(),
            this.requirementDescription.getText(),
            new TimeClass(stringDate).getTime(),
            newTeamMember);
      }

      viewHandler.openView("RequirementView");
    } else {
      if (!this.requirementName.getText().equals(model.getName(currentRequirement))){
        model.setName(currentRequirement,this.requirementName.getText());
      }
      model.setDeadlineTime(currentRequirement,new TimeClass(stringDate).getTime());
      model.setResponsibleTeamMember(currentRequirement,newTeamMember);
      switch (statusChoiceBox.getSelectionModel().getSelectedIndex()){
        case 0:model.setStatus(currentRequirement,Status.NOT_STARTED); break;
        case 1:model.setStatus(currentRequirement,Status.STARTED);break;
        case 2:model.setStatus(currentRequirement,Status.APPROVED);break;
        case 3:model.setStatus(currentRequirement,Status.REJECTED);break;
        case 4:model.setStatus(currentRequirement,Status.ENDED);break;
      }

      if (this.descriptionType.getToggles().get(0).isSelected()){
        model.setDescription(currentRequirement,this.who.getText(),this.what.getText(),this.why.getText());
      } else {
        model.setDescription(currentRequirement,this.requirementDescription.getText());
      }
      viewHandler.openView("RequirementView");
    }
  }

  public void openRequirementView()
  {
    this.viewHandler.openView("RequirementView");
  }

  public void functionalDesc()
  {
    this.stackPaneDescription.getChildren().get(0).setVisible(false);
    this.stackPaneDescription.getChildren().get(1).setVisible(true);
  }

  public void onFunctionalDesc()
  {
    this.stackPaneDescription.getChildren().get(1).setVisible(false);
    this.stackPaneDescription.getChildren().get(0).setVisible(true);
  }
}
