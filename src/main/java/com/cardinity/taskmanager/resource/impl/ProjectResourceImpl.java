package com.cardinity.taskmanager.resource.impl;

import java.util.Collection;
import java.util.Optional;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.exception.ApplicationException;
import com.cardinity.taskmanager.exception.ProjectNotFoundException;
import com.cardinity.taskmanager.resource.Resource;
import com.cardinity.taskmanager.service.IService;

@RestController
@RequestMapping(value = "/projects", produces = MediaType.APPLICATION_JSON_VALUE)
public class ProjectResourceImpl implements Resource<Project> {

    private static Logger log = LoggerFactory.getLogger(ProjectResourceImpl.class);

    @Autowired
    private IService<Project> projectService;

    @Override
    public ResponseEntity<Collection<Project>> findAll() {
        log.info("ProjectResourceImpl - findAll");
        return new ResponseEntity<>(projectService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Project> findById(Long id) {
        log.info("ProjectResourceImpl - findById");
        Optional<Project> project = projectService.findById(id);
        if(!project.isPresent()) {
            throw new ProjectNotFoundException("Project not found");
        }
        return new ResponseEntity<>(project.get(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Project> save(Project project) {
        log.info("ProjectResourceImpl - save");
        if(project.getProjectId() != null) {
            throw new ApplicationException("Project ID found, ID is not required for save the data");
        }
        return new ResponseEntity<>(projectService.saveOrUpdate(project), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<Project> update(Project project) {
        log.info("ProjectResourceImpl - update");
        if(project.getProjectId()  == null) {
            throw new ApplicationException("Project ID not found, ID is required for update the data");
        }
        return new ResponseEntity<>(projectService.saveOrUpdate(project), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> deleteById(Long id) {
        log.info("ProjectResourceImpl - deleteById");
        Optional<Project> project = projectService.findById(id);
        if(!project.isPresent()) {
            throw new ProjectNotFoundException("Project not found");
        }
        return new ResponseEntity<>(projectService.deleteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> invalid() {
        log.info("ProjectResourceImpl - invalid");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

}
