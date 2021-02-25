# Task manager REST API

Manage your task digitally using API

## DB design and development

--- Create database taskmanagerdb and change application. properties file according to your MySQL credential. The tables will be created automatically during the project run from the code ide like Intellij Idea.

Test user: test@user.com
 
password: testuser

Test Admin user: test@admin.com
 
password: testadmin
## API endpoints for testing
For run the project 
```bash
• Create a project like (http://localhost:8888/taskmanager/projects    Use POST)
Sample JSON format such as following



	{
        "projectName": "FirstProject"
    	}

• Get all projects (http://localhost:8888/taskmanager/projects    Use GET)

• Get all projects (http://localhost:8888/taskmanager/projects    Use GET)

• Delete project (http://localhost:8888/taskmanager/projects/1    Use DELETE)

• Create task (http://localhost:8888/taskmanager/tasks    Use POST)
Sample JSON format such as following:

 {
        "taskDescription": "First Task",
        "status": "OPEN",
        "taskStartDate": "2021-02-25 05:36:57",
        "taskEndDate": "2021-02-25 05:36:57",
        "userList": null,
        "project": {
            "projectId": 1,
            "projectName": "FirstProject"
        }
    }
• Edit task (change description, status, due date). Closed tasks cannot be edited.
Create task (http://localhost:8888/taskmanager/tasks    Use PUT)
 {
        "taskId": 1,
        "taskDescription": "First Task",
        "status": "OPEN",
        "taskStartDate": "2021-02-25 05:36:57",
        "taskEndDate": "2021-02-25 05:36:57",
        "userList": null,
        "project": {
            "projectId": 1,
            "projectName": "FirstProject"
        }
    }
• Get task (http://localhost:8888/taskmanager/tasks    Use GET)
• Search tasks
◦ Get all by project (http://localhost:8888/taskmanager/tasks/all/1    Use GET)
◦ Get expired tasks (due date in the past) 
(http://localhost:8888/taskmanager/tasks/all/dueTask    Use GET)
◦ By status (http://localhost:8888/taskmanager/tasks/all?status=OPEN OR CLOSED OR IN_PROGRESS    Use GET)
ADMIN additionally should be able to:
• Get all tasks by user (http://localhost:8888/taskmanager/users/1    Use GET)
• Get all projects by user (http://localhost:8888/taskmanager/users/1    Use GET)
## Usage
Task properties:
• Description (Mandatory)
• Status (Mandatory), open/in progress/closed
• Project (Mandatory)
• Due date (Optional)
Project properties:
• Name (Mandatory)
