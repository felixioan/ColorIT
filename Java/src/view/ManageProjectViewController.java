package view;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Region;
import model.Project;
import model.ProjectManagementModel;

public class ManageProjectViewController
{
  @FXML private Label errorLabel;
  @FXML private TextField name;
  @FXML private TextArea description;

  private Region root;
  private ProjectManagementModel model;
  private ViewHandler viewHandler;
  private Project project;
  //root model view handler

  public ManageProjectViewController() {
    //nothing
  }

  public void init(ViewHandler viewHandler, ProjectManagementModel model, Region root, boolean edit){
    this.model = model;
    this.root = root;
    this.viewHandler = viewHandler;
    reset(edit);
  }

  public void reset(boolean edit){
    if (edit){
      this.root.setUserData("Edit project");
      project = viewHandler.getCurrentProject();
      this.name.setText(project.getProjectName());
      this.description.setText(project.getProjectDescription());
    } else {
      project = null;
      this.root.setUserData("Create Project");
      this.name.setText("");
      this.name.setPromptText("Enter project name here.");
      this.description.setText("");
      this.description.setPromptText("Enter project description here.");
    }
    errorLabel.setText("");
  }

  public Region getRoot(){
    return root;
  }

  public void save()
  {
    errorLabel.setText("");
    if (project != null){
      try {
        if(!this.name.getText().equals(this.project.getProjectName())){
          model.setName(project,this.name.getText());
        }
        if(!this.description.getText().equals(this.project.getProjectDescription())){
          model.setDescription(project,this.description.getText());
        }
        viewHandler.openView("ProjectView");
      } catch (Exception e) {
        errorLabel.setText(e.getMessage());
      }
    } else {
      try {
        model.addProject(this.name.getText(),this.description.getText());
        viewHandler.openView("ProjectView");
      } catch (Exception e) {
        errorLabel.setText(e.getMessage());
      }
    }


  }

  public void cancel()
  {
    viewHandler.openView("ProjectView");
  }
}
