T# Task manager REST API

Manage your task digitally using API

## DB design and develop

--- Create database taskmanagerdb and change application. properties file according to your MySQL credential. The tables will be created automatically during the project run from the code ide like Intellij Idea.

• Create a project like (http://localhost:8888/taskmanager/projects    Use POST)
Sample JSON format such as following


```bash
	{
        "projectName": "FirstProject"
    	}
```
• Get all projects (http://localhost:8888/taskmanager/projects    Use GET)
• Delete project
• Create task
• Edit task (change description, status, due date). Closed tasks cannot be edited.
• Get task
• Search tasks
◦ Get all by project
◦ Get expired tasks (due date in the past)
◦ By status
ADMIN additionally should be able to:
• Get all tasks by user
• Get all projects by user
Task properties:
• Description (Mandatory)
• Status (Mandatory), open/in progress/closed
• Project (Mandatory)
• Due date (Optional)
Project properties:
• Name (Mandatory)
