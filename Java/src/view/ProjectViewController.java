package view;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectManagementModel;

public class ProjectViewController {
  public TextField searchValue;
  @FXML private Label errorLabel;
  @FXML private ListView<String> projectListView;
  private Region root;
  private ViewHandler viewHandler;
  private ProjectManagementModel model;
  private Project[] projects;

  public ProjectViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root){
    this.viewHandler = viewHandler;
    this.model = model;
    this.root = root;

    reset();
  }

  public void reset(){
    projects = model.getAllProjects();
    projectListView.getItems().clear();
    for (Project project: projects){
      projectListView.getItems().add(project.getProjectName());
    }
    this.errorLabel.setText("");
  }

  public Region getRoot(){
    return root;
  }

  public void openRequirement()
  {
    viewHandler.openView("RequirementView");
  }

  public void openTeam()
  {
    viewHandler.openView("TeamMemberView");
  }

  public void openProject()
  {
    this.viewHandler.setCurrentProject(getProjectSelected());
    this.viewHandler.openView("RequirementView");
  }

  public Project getProjectSelected(){
    return projects[projectListView.getSelectionModel().getSelectedIndices().get(0)];
  }

  public void editProject(ActionEvent actionEvent)
  {
    this.viewHandler.setCurrentProject(getProjectSelected());
    this.viewHandler.openView("ManageProject");
  }

  public void deleteProject(ActionEvent actionEvent)
  {
    int index = projectListView.getSelectionModel().getSelectedIndices().get(0);
    try {
      model.deleteProject(projects[index].getProjectID());
      projectListView.getItems().remove(index);
    } catch (Exception e) {
      this.errorLabel.setText(e.getMessage());
  }
  }

  public void addProject()
  {
    this.viewHandler.openView("AddProject");
  }

  public void search(ActionEvent actionEvent)
  {
    projects = model.getProjectsByName(this.searchValue.getText());
    projectListView.getItems().clear();
    for (Project project: projects){
      projectListView.getItems().add(project.getProjectName());
    }
  }
}
