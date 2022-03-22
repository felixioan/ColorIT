package view;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import model.ProjectManagementModel;
import model.Task;

public class ProjectTabViewController {
  public ProjectViewController projectViewController;
  public RequirementViewController requirementViewController;
  public TaskViewController taskViewController;
  @FXML private StackPane stackPane;
  private Region root;
  private ViewHandler viewHandler;
  private ProjectManagementModel model;

  public ProjectTabViewController(){
    //nothing
  }

  public void init(ViewHandler viewHandler,ProjectManagementModel model, Region root){
    this.projectViewController.init(viewHandler, model, root);
    this.taskViewController.init(viewHandler, model, root);
    this.requirementViewController.init(viewHandler, model, root);

    this.root = root;
  }

  public void reset(){
    this.projectViewController.reset();
    this.taskViewController.reset();
    this.requirementViewController.reset();
  }

  public Region getRoot()
  {
    return root;
  }

  public void showView(String id){
    if (id == null){
      return;
    }
    ObservableList<Node> children = stackPane.getChildren();
    for (int i = 0; i< children.size(); i++){
      children.get(i).setVisible(false);
      if(id.equalsIgnoreCase(children.get(i).getId())){
        children.get(i).setVisible(true);
      }
    }
  }
}
