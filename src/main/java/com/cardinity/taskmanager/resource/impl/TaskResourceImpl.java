package com.cardinity.taskmanager.resource.impl;

import com.cardinity.taskmanager.exception.ApplicationException;
import com.cardinity.taskmanager.exception.ProjectNotFoundException;
import com.cardinity.taskmanager.exception.TaskNotFoundException;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.Task;
import com.cardinity.taskmanager.model.TaskStatus;
import com.cardinity.taskmanager.repository.TaskRepository;
import com.cardinity.taskmanager.resource.Resource;
import com.cardinity.taskmanager.service.IService;
import com.cardinity.taskmanager.service.TaskmanagerService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;
import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/tasks", produces = MediaType.APPLICATION_JSON_VALUE)
public class TaskResourceImpl  implements Resource<Task> {
    private static Logger log = LoggerFactory.getLogger(TaskResourceImpl.class);
    @Autowired
    private IService<Task> taskService;
    @Autowired
    private IService<Project> projectervice;
    @Autowired
    private TaskmanagerService taskmanagerService;
   @Autowired
    private TaskRepository taskRepository;
    @Override
    public ResponseEntity<Collection<Task>> findAll() {
        log.info("TaskResourceImpl - findAll");
        return new ResponseEntity<>(taskService.findAll(), HttpStatus.OK);
    }
    @GetMapping("/all/dueTask")
    public ResponseEntity<Collection<Task>> getAllTaskByDuedate() {
        log.info("TaskResourceImpl - findAll");
        return new ResponseEntity<>(taskmanagerService.getAllTaskByDuedate(), HttpStatus.OK);
    }

    @GetMapping("/all/{projectId}")
    public ResponseEntity<Collection<Task>> findByProject(@PathVariable Long projectId) {
        log.info("TaskResourceImpl - findAll");
        Optional<Project> project =projectervice.findById(projectId) ;
        if(project.get().getProjectId()==null) {
            throw new TaskNotFoundException("No Project found");
        }else if(taskRepository.findAllByProject(project.get()).size()==0){
            throw new TaskNotFoundException("No Task found Under this Project");
        }
        return new ResponseEntity<>(taskmanagerService.getAllTaskByProject(projectId), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<Collection<Task>> findByStatus(@RequestParam String status) {
        log.info("TaskResourceImpl - findAll");
        TaskStatus   status1;
      if(status.equalsIgnoreCase(TaskStatus.CLOSED.name())){
           status1=TaskStatus.CLOSED;
      }else if(status.equalsIgnoreCase(TaskStatus.OPEN.name())){
          status1=TaskStatus.OPEN;
      }else {
          status1=TaskStatus.IN_PROGRESS;
      }

   if(taskRepository.findAllByStatus(status1).size()==0){
            throw new ApplicationException("No Task found Under this Project");
        }
        return new ResponseEntity<>(taskmanagerService.getAllTaskByStatus(status1), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> findById(Long id) {
        log.info("TaskResourceImpl - findById");
        Optional<Task> task = taskService.findById(id);
        if(!task.isPresent()) {
            throw new TaskNotFoundException("Task not found");
        }
        return new ResponseEntity<>(task.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Task> save(Task task) {
        log.info("TaskResourceImpl - save");
        if(task.getTaskId() != null) {
            throw new ApplicationException("Task ID found, ID is not required for save the data");
        }
        return new ResponseEntity<>(taskService.saveOrUpdate(task), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Task> update(Task task) {
        Optional<Task> existingTask = taskService.findById(task.getTaskId());
        log.info("TaskResourceImpl - update");
        if(task.getTaskId()  == null) {
            throw new ApplicationException("Task ID not found, ID is required for update the data");
        }else if(existingTask.get().getStatus()  == TaskStatus.CLOSED){
            throw new ApplicationException("Closed Task cannot be edited");
        }
        return new ResponseEntity<>(taskService.saveOrUpdate(task), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        log.info("TaskResourceImpl - deleteById");
        Optional<Task> task = taskService.findById(id);
        if(!task.isPresent()) {
            throw new TaskNotFoundException("Project not found");
        }
        return new ResponseEntity<>(taskService.deleteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> invalid() {
        log.info("TaskResourceImpl - invalid");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
