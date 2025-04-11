package com.manage.quanlykytucxa.service;

import java.util.List;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import com.manage.quanlykytucxa.domain.Role;
import com.manage.quanlykytucxa.repository.RoleRepository;

@Service
public class RoleService {
    private final RoleRepository roleRepository;
    

    public RoleService(RoleRepository roleRepository) {
       
        this.roleRepository = roleRepository;
    }

   

    public Role create(Role role) {
        boolean roleExist = roleRepository.existsByName(role.getName());
        if (roleExist) {
            throw new RuntimeException("Role name already exists");
        }
        
        return roleRepository.save(role);
    }

    public Role update(Role role){

    Role roleDb = roleRepository.findById(role.getId()).orElseThrow(
    () -> new RuntimeException("Không tìm thấy role : " + role.getId()));
    
    roleDb.setName(role.getName());   
   

    return roleRepository.save(roleDb);
    }

    // public Role fetchById(Long id) throws IdInvalidException {
    // return roleRepository.findById(id).orElseThrow(
    // () -> new IdInvalidException("Role not found with id: " + id));

    // }

    // public ResultPaginationDTO fetchAll(Specification<Role> spec, Pageable
    // pageable) {
    // Page<Role> roles = this.roleRepository.findAll(spec, pageable);
    // ResultPaginationDTO rs = new ResultPaginationDTO();

    // ResultPaginationDTO.Meta mt = new ResultPaginationDTO.Meta();
    // mt.setPage(roles.getNumber() + 1);
    // mt.setPageSize(roles.getSize());
    // mt.setPages(roles.getTotalPages());
    // mt.setTotal(roles.getTotalElements());
    // rs.setMeta(mt);
    // rs.setResult(roles.getContent());
    // return rs;
    // }

    // public void deleteRole(long id) {
    // this.roleRepository.deleteById(id);
    // }
}
