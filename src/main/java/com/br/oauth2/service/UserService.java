package com.br.oauth2.service;

import com.br.oauth2.models.UserModel;
import com.br.oauth2.models.enums.RoleStatus;
import com.br.oauth2.repository.RoleRepository;
import com.br.oauth2.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public List<UserModel> findAll(){
        return userRepository.findAll();
    }

    public UserModel findById(Long id){
        Optional<UserModel> obj = userRepository.findById(id);
        return obj.get();
    }

    public UserModel insert(UserModel obj){
        var roleBasic = roleRepository.findByName(RoleStatus.BASIC.name());
        UserModel entity = new UserModel();
        entity.setName(obj.getName());
        entity.setSurname(obj.getSurname());
        entity.setEmail(obj.getEmail());
        entity.setUsername(obj.getUsername());
        entity.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        entity.getRoles().add(roleBasic.get());
        return userRepository.save(obj);
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }

    public UserModel update(Long id, UserModel obj){
        var entity = userRepository.getReferenceById(id);
        updateData(entity, obj);
        return userRepository.save(entity);
    }

    private void updateData(UserModel obj, UserModel entity){
        if (entity.getName() != null && !entity.getName().isBlank()) {
            obj.setName(entity.getName());
        }
        if (entity.getSurname() != null && !entity.getSurname().isBlank()) {
            obj.setSurname(entity.getSurname());
        }
        if (entity.getEmail() != null && !entity.getEmail().isBlank()) {
            obj.setEmail(entity.getEmail());
        }
        if (entity.getUsername() != null && !entity.getUsername().isBlank()) {
            obj.setUsername(entity.getUsername());
        }
        if (entity.getPassword() != null && !entity.getPassword().isBlank()) {
            obj.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        }
    }
}
