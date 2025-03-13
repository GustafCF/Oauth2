package com.br.oauth2.controller;

import com.br.oauth2.models.RoleModel;
import com.br.oauth2.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/rl")
public class RoleController {

    @Autowired
    private RoleService service;

    @GetMapping("/list")
    public ResponseEntity<List<RoleModel>> list() {
        List<RoleModel> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<RoleModel> findById(@PathVariable Long id) {
        var obj =  service.findById(id);
        return ResponseEntity.ok().body(obj);
    }

    @PostMapping("/insert")
    public ResponseEntity<RoleModel> insert(@RequestBody RoleModel entity) {
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
    public ResponseEntity<RoleModel> update(@PathVariable Long id, @RequestBody RoleModel entity) {
        var obj = service.update(id, entity);
        return ResponseEntity.ok().body(obj);
    }
}
