package com.br.oauth2.controller;

import com.br.oauth2.models.UserModel;
import com.br.oauth2.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/us")
public class UserController {

    @Autowired
    private UserService service;

    @GetMapping("/list")
    public ResponseEntity<List<UserModel>> list() {
        List<UserModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<UserModel> findById(@PathVariable Long id) {
        var obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/insert")
    public ResponseEntity<UserModel> insert(@RequestBody UserModel entity) {
        var obj = service.insert(entity);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).body(obj);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<UserModel> update(@PathVariable Long id, @RequestBody UserModel entity) {
        var obj = service.update(id, entity);
        return ResponseEntity.ok().body(obj);
    }
}
