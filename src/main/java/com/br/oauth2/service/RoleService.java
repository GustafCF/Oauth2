package com.br.oauth2.service;

import com.br.oauth2.models.RoleModel;
import com.br.oauth2.repository.RoleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

   public List<RoleModel> findAll(){
        return roleRepository.findAll();
   }

   public RoleModel findById(Long id){
        Optional<RoleModel> obj = roleRepository.findById(id);
        return obj.get();
   }

   public RoleModel insert(RoleModel obj){
        return  roleRepository.save(obj);
   }

   public void delete(Long id){
        roleRepository.deleteById(id);
   }

   public RoleModel update(Long id, RoleModel obj){
        var entity = roleRepository.getReferenceById(id);
        updateData(entity, obj);
        return roleRepository.save(entity);
   }

   private void updateData(RoleModel obj, RoleModel entity){
        if (entity.getName() != null && !entity.getName().isBlank()){
            entity.setName(obj.getName());
        }
        if (entity.getDescription() != null && !entity.getDescription().isBlank()){
            entity.setDescription(obj.getDescription());
        }
        if (entity.getRoleStatus() != null){
            entity.setRoleStatus(obj.getRoleStatus());
        }
   }
}
