package com.cardinity.taskmanager.service.impl;


import com.cardinity.taskmanager.model.Task;
import com.cardinity.taskmanager.repository.TaskRepository;
import com.cardinity.taskmanager.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class TaskServiceImpl  implements IService<Task> {
    @Autowired
    private TaskRepository taskRepository;
    @Override
    public Collection<Task> findAll() {
        return  taskRepository.findAll();
    }

    @Override
    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }

    @Override
    public Task saveOrUpdate(Task task) {
        return taskRepository.saveAndFlush(task);
    }

    @Override
    public String deleteById(Long id) {
        JSONObject jsonObject = new JSONObject();
        try {
            taskRepository.deleteById(id);
            jsonObject.put("message", "Task deleted successfully");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject.toString();
    }
}
