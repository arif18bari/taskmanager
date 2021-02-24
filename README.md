# taskmanager
Task manager REST API




Create DB and REST API for task managing using JSON format for interchange.
Secure endpoints with Spring Security. There should be two roles: USER and ADMIN.
USER can only access own tasks and projects
Authentication credentials should be stored in DB
USER and ADMIN should be able to:
• Create project
• Get all projects
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
