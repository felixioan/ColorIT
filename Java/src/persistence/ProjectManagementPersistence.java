package persistence;

import model.*;

public interface ProjectManagementPersistence
{
    void saveProjectListToFile(ProjectList projectList, TeamMemberList teamMemberList);
    void saveTeamMemberListToFile(TeamMemberList teamMemberList, ProjectList projectList);
    void saveToJSON(ProjectList projectList, TeamMemberList teamMemberList);
    public TeamMemberList loadTeamMembersFromFile();
    public ProjectList loadProjectsFromFile();
}
