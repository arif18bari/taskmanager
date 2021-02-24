package com.cardinity.taskmanager.service.impl;

import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.dto.ProjectDto;
import com.cardinity.taskmanager.repository.ProjectRepository;
import com.cardinity.taskmanager.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class ProjectServiceImpl  implements IService<Project> {

    @Autowired
    private ProjectRepository projectRepository;

    @Override
    public Collection<Project> findAll() {
        return  projectRepository.findAll();
    }

    @Override
    public Optional<Project> findById(Long id) {
        return projectRepository.findById(id);
    }

    @Override
    public Project saveOrUpdate(Project project) {
        return projectRepository.saveAndFlush(project);
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            projectRepository.deleteById(id);
            jsonObject.put("message", "Project deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
    }

