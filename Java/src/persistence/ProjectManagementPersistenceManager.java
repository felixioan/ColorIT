package persistence;

import model.*;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class ProjectManagementPersistenceManager implements ProjectManagementPersistence
{
    FileHandling fileHandling = new FileHandling();

    @Override
    public void saveProjectListToFile(ProjectList projectList,
        TeamMemberList teamMemberList)
    {
        fileHandling.saveProjectListToFile(projectList);
        this.saveToJSON(projectList, teamMemberList);
    }

    @Override
    public void saveTeamMemberListToFile(TeamMemberList teamMemberList, ProjectList projectList)
    {
        fileHandling.saveTeamMemberListToFile(teamMemberList);
        this.saveToJSON(projectList, teamMemberList);
    }

    @Override
    public void saveToJSON(ProjectList projectList, TeamMemberList teamMemberList)
    {
        JSONObject jsonData = new JSONObject();
        JSONArray projectsArray = new JSONArray();
        JSONArray teamMembersArray = new JSONArray();
        for (Project project : projectList.getAllProjects())
        {
            JSONObject projectObject = new JSONObject();
            projectObject.put("id", project.getProjectID());
            projectObject.put("name", project.getProjectName());
            projectObject.put("description", project.getProjectDescription());
            JSONArray requirementsArray = new JSONArray();
            for (Requirement requirement : project.getRequirements().getAllRequirements())
            {
                JSONObject requirementObject = new JSONObject();
                requirementObject.put("id", requirement.getRequirementId());
                requirementObject.put("name", requirement.getName());
                requirementObject.put("description", requirement.getDescription());
                requirementObject.put("status", requirement.getStatus());
                requirementObject.put("deadline", requirement.getDeadlineTime());
                requirementsArray.put(requirementObject);
            }
            projectObject.put("requirements", requirementsArray);
            projectsArray.put(projectObject);
        }
        jsonData.put("projects", projectsArray);

        for (TeamMember teamMember : teamMemberList.getAllTeamMembers())
        {
            JSONObject teamMemberObject = new JSONObject();
            teamMemberObject.put("name", teamMember.getName());
            teamMemberObject.put("email", teamMember.getEmail());
            teamMembersArray.put(teamMemberObject);
        }
        jsonData.put("team", teamMembersArray);

        try
        {
            File file = new File("./data.js");
            PrintWriter out = new PrintWriter(file);
            out.println("window.projectsData = " + jsonData.toString());
            out.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }

    @Override
    public TeamMemberList loadTeamMembersFromFile()
    {
        return fileHandling.loadTeamMembersFromFile();
    }

    @Override
    public ProjectList loadProjectsFromFile()
    {
        return fileHandling.loadProjectsFromFile();
    }
}
