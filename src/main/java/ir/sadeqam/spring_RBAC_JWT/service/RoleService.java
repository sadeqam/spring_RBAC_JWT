package ir.sadeqam.spring_RBAC_JWT.service;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Role;
import ir.sadeqam.spring_RBAC_JWT.repository.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    @Autowired
    private RoleRepository repository;

    public Role insert(Role role){
        return repository.save(role);
    }
}
