package com.cardinity.taskmanager.resource;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.ws.rs.Consumes;

public interface Resource<T> {
    @GetMapping
    @Consumes(value = MediaType.APPLICATION_JSON_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<Collection<T>> findAll();

    @GetMapping("{id}")
    ResponseEntity<T> findById(@PathVariable Long id);

    @PostMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<T> save(@RequestBody T t);

    @PutMapping(consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<T> update(@RequestBody T t);

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    ResponseEntity<String> deleteById(@PathVariable Long id);

    @GetMapping("/invalid")
    ResponseEntity<String> invalid();


}
