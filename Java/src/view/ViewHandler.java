package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Region;
import javafx.stage.Stage;
import model.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.image.Image;

public class ViewHandler
{
    // this is just a backbone of the view handler just so there is at least something
    private final Scene currentScene;
    private Stage primaryStage;
    private final ProjectManagementModel model;
    private Project currentProject;
    private Requirement currentRequirement;
    private Task currentTask;
    private TeamMember currentTeamMember;

    private TabViewController tabViewController;
    private ManageProjectViewController manageProjectViewController;
    private ManageMembersViewController manageMembersViewController;
    private ManageRequirementsViewController manageRequirementsViewController;
    private AddTeamMemberToProjectViewController addTeamMemberToProjectViewController;
    private TeamMemberDetailsViewController teamMemberDetailsViewController;
    private ManageTasksViewController manageTasksViewController;

    public ViewHandler(ProjectManagementModel model)
    {
        this.model = model;
        this.currentScene = new Scene(new Region());
        //copied form the GradeListGUI example
    }

    public void start(Stage primaryStage)
    {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        String filename = "./Java/logo.png";

        try {
            File file = new File(filename);
            FileInputStream inputStream = new FileInputStream(file);
            Image image = new Image(inputStream);
            this.primaryStage.getIcons().add(image);
        } catch (Exception e){
            e.printStackTrace();
        }

        /*
         also from the GL GUI example
         but "openView("mainMenu");" is missing
          or "openView("projectTab");"
         */
        openView("ProjectTabView");
    }

    public void openView(String id){
        Region root = null;
        switch (id){
            case "TabView":
            case "ProjectTabView":
            case "TeamMemberView":
            case "ProjectView":
            case "RequirementView":
            case "TaskView": root = loadTabView("TabView.fxml"); break;
            case "ManageProject" : root = loadManageProject("ManageProjectView.fxml",true); break;
            case "AddProject" : root = loadManageProject("ManageProjectView.fxml",false); break;
            case "ManageMember" : root = loadManageTeam("ManageMemberView.fxml",true); break;
            case "AddMember" : root = loadManageTeam("ManageMemberView.fxml",false); break;
            case "ManageRequirement" : root = loadManageRequirement("ManageRequirementsView.fxml",true); break;
            case "AddRequirement" : root = loadManageRequirement("ManageRequirementsView.fxml",false); break;
            case "AddTeamMemberToProject" : root = loadAddTeamMemberToProjectView("AddTeamMemberToProjectView.fxml",true); break;
            case "AddTeamMemberToTask" : root = loadAddTeamMemberToProjectView("AddTeamMemberToProjectView.fxml",false); break;
            case "TeamMemberDetails" : root = loadTeamMemberDetailsView("TeamMemberDetailsView.fxml"); break;
            case "ManageTask" : root = loadManageTask("ManageTaskView.fxml",true); break;
            case "AddTask" : root = loadManageTask("ManageTaskView.fxml",false); break;
        }
        currentScene.setRoot(root);
        String title = "";
        if (root.getUserData() != null)
        {
            title += root.getUserData();
        }
        primaryStage.setTitle(title);
        primaryStage.setScene(currentScene);
        primaryStage.setWidth(root.getPrefWidth());
        primaryStage.setHeight(root.getPrefHeight());
        primaryStage.show();

        switch (id){
            case "TeamMemberView": tabViewController.openTab(1);break;
            case "TabView":
            case "ProjectTabView": tabViewController.openTab(0); break;
            case "ProjectView":
            case "TaskView":
            case "RequirementView":
                tabViewController.openTab(0); tabViewController.openProjectWindow(id);break;
        }
    }

    public void closeView(){
        primaryStage.close();
    }

    private Region loadTabView(String fxmlS){
        Region root = null;
        if (tabViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                tabViewController = loader.getController();
                tabViewController.init(this,model, root);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            tabViewController.reset();
        }
        return tabViewController.getRoot();
    }

    private Region loadAddTeamMemberToProjectView(String fxmlS, boolean addToProject){
        Region root = null;
        if (addTeamMemberToProjectViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                addTeamMemberToProjectViewController = loader.getController();
                addTeamMemberToProjectViewController.init(this,model, root, addToProject);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            addTeamMemberToProjectViewController.reset(addToProject);
        }
        return addTeamMemberToProjectViewController.getRoot();
    }

    private Region loadTeamMemberDetailsView(String fxmlS){
        Region root = null;
        if (teamMemberDetailsViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                teamMemberDetailsViewController = loader.getController();
                teamMemberDetailsViewController.init(this,model, root);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            teamMemberDetailsViewController.reset();
        }
        return teamMemberDetailsViewController.getRoot();
    }

    private Region loadManageTask(String fxmlS,boolean edit){
        Region root = null;
        if (manageTasksViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                manageTasksViewController = loader.getController();
                manageTasksViewController.init(this,model, root, edit);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            manageTasksViewController.reset(edit);
        }
        return manageTasksViewController.getRoot();
    }

    private Region loadManageProject(String fxmlS,boolean edit){
        Region root = null;
        if (manageProjectViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                manageProjectViewController = loader.getController();
                manageProjectViewController.init(this,model, root, edit);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            manageProjectViewController.reset(edit);
        }
        return manageProjectViewController.getRoot();
    }

    private Region loadManageTeam(String fxmlS,boolean edit){
        Region root = null;
        if (manageMembersViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                manageMembersViewController = loader.getController();
                manageMembersViewController.init(this,model, root, edit);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            manageMembersViewController.reset(edit);
        }
        return manageMembersViewController.getRoot();
    }

    private Region loadManageRequirement(String fxmlS,boolean edit){
        Region root = null;
        if (manageRequirementsViewController == null){
            try {
                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource(fxmlS));
                root = loader.load();
                manageRequirementsViewController = loader.getController();
                manageRequirementsViewController.init(this,model, root, edit);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        } else {
            manageRequirementsViewController.reset(edit);
        }
        return manageRequirementsViewController.getRoot();
    }

    public void setCurrentProject(Project currentProject)
    {
        this.currentProject = currentProject;
    }

    public Project getCurrentProject()
    {
        return currentProject;
    }

    public void setCurrentRequirement(Requirement currentRequirement)
    {
        this.currentRequirement = currentRequirement;
    }

    public Requirement getCurrentRequirement()
    {
        return currentRequirement;
    }

    public void setCurrentTask(Task currentTask)
    {
        this.currentTask = currentTask;
    }

    public Task getCurrentTask()
    {
        return currentTask;
    }

    public void setCurrentTeamMember(TeamMember currentTeamMember)
    {
        this.currentTeamMember = currentTeamMember;
    }

    public TeamMember getCurrentTeamMember()
    {
        return currentTeamMember;
    }
}
