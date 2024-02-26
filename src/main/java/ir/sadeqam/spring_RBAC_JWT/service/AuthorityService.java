package ir.sadeqam.spring_RBAC_JWT.service;

import ir.sadeqam.spring_RBAC_JWT.repository.entities.Authority;
import ir.sadeqam.spring_RBAC_JWT.repository.repositories.AuthorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorityService {

    @Autowired
    private AuthorityRepository repository;

    public Authority insert(Authority authority){
        return repository.save(authority);
    }
}
