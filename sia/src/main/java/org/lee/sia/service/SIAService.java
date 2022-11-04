package org.lee.sia.service;

import org.lee.sia.jpa.AOIEntity;
import org.lee.sia.jpa.JPARepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SIAService {
    @Autowired
    private JPARepository repository;


    public List<AOIEntity> findAll(){return repository.findAll(); }
    public void saveAoi(AOIEntity entity){repository.saveAoi(entity);}
    public String findById(Long id){return repository.findById(id);}
}
