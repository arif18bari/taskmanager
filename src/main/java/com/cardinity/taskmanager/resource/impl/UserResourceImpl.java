package com.cardinity.taskmanager.resource.impl;

import com.cardinity.taskmanager.exception.ApplicationException;
import com.cardinity.taskmanager.exception.ProjectNotFoundException;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.User;
import com.cardinity.taskmanager.resource.Resource;
import com.cardinity.taskmanager.service.IService;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users", produces = MediaType.APPLICATION_JSON_VALUE)
public class UserResourceImpl  implements Resource<User> {

    private static Logger log = LoggerFactory.getLogger(UserResourceImpl.class);

    @Autowired
    private IService<User> userService;

    @Override
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Collection<User>> findAll() {
        log.info("UserResourceImpl - findAll");
        return new ResponseEntity<>(userService.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<User> findById(Long id) {
        log.info("UserResourceImpl - findById");
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()) {
            throw new ProjectNotFoundException("User not found");
        }
        return new ResponseEntity<>(user.get(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<User> save(User user) {
        log.info("UserResourceImpl - save");
        if(user.getId() != null) {
            throw new ApplicationException("User ID found, ID is not required for save the data");
        }
        return new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<User> update(User user) {
        log.info("userResourceImpl - update");
        if(user.getId()  == null) {
            throw new ApplicationException("User ID not found, ID is required for update the data");
        }
        return new ResponseEntity<>(userService.saveOrUpdate(user), HttpStatus.OK);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")@Override
    public ResponseEntity<String> deleteById(Long id) {
        log.info("UserResourceImpl - deleteById");
        Optional<User> user = userService.findById(id);
        if(!user.isPresent()) {
            throw new ProjectNotFoundException("User not found");
        }
        return new ResponseEntity<>(userService.deleteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> invalid() {
        log.info("UserResourceImpl - invalid");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }
}
