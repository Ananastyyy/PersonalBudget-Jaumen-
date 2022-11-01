package io.proj3ct.Jaumen.repositories;

import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Repositories {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FamilyRepository familyRepository;

    @Autowired
    private FamilyMemberRepository familyMemberRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private ChequeRepository chequeRepository;

    public UserRepository getUserRepository() {
        return userRepository;
    }

    public FamilyRepository getFamilyRepository() {
        return familyRepository;
    }

    public FamilyMemberRepository getFamilyMemberRepository() {
        return familyMemberRepository;
    }

    public CategoryRepository getCategoryRepository() {
        return categoryRepository;
    }

    public ChequeRepository getChequeRepository() {
        return chequeRepository;
    }
}
