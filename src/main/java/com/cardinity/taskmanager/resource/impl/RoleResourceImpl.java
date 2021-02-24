package com.cardinity.taskmanager.resource.impl;

import com.cardinity.taskmanager.exception.ApplicationException;
import com.cardinity.taskmanager.exception.ProjectNotFoundException;
import com.cardinity.taskmanager.model.Project;
import com.cardinity.taskmanager.model.Role;
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
@RequestMapping(value = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
public class RoleResourceImpl  implements Resource<Role> {

    private static Logger log = LoggerFactory.getLogger(RoleResourceImpl.class);

    @Autowired
    private IService<Role> roleService;
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<Collection<Role>> findAll() {
        log.info("RoleResourceImpl - findAll");
        if(roleService.findAll().size()==0) {
            throw new ProjectNotFoundException("Role not found");
        }
        return new ResponseEntity<>(roleService.findAll(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<Role> findById(Long id) {
        log.info("RoleResourceImpl - findById");
        Optional<Role> role = roleService.findById(id);
        if(!role.isPresent()) {
            throw new ProjectNotFoundException("Role not found");
        }
        return new ResponseEntity<>(role.get(), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<Role> save(Role role) {
        log.info("RoleResourceImpl - save");
        if(role.getId() != null) {
            throw new ApplicationException("Role ID found, ID is not required for save the data");
        }
        return new ResponseEntity<>(roleService.saveOrUpdate(role), HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<Role> update(Role role) {
        log.info("RoleResourceImpl - update");
        if(role.getId()  == null) {
            throw new ApplicationException("Role ID not found, ID is required for update the data");
        }
        return new ResponseEntity<>(roleService.saveOrUpdate(role), HttpStatus.OK);
    }
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @Override
    public ResponseEntity<String> deleteById(Long id) {
        log.info("RoleResourceImpl - deleteById");
        Optional<Role> role = roleService.findById(id);
        if(!role.isPresent()) {
            throw new ProjectNotFoundException("Role not found");
        }
        return new ResponseEntity<>(roleService.deleteById(id), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<String> invalid() {
        log.info("RoleResourceImpl - invalid");
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("message", "something is missing, please check everything before sending the request!!!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ResponseEntity<>(jsonObject.toString(), HttpStatus.OK);
    }

    @RequestMapping("/403")
    public String error403() {
        return "403";
    }
}
