package org.lee.sia.jpa;

import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class JPARepositoryImpl implements JPARepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<AOIEntity> findAll() {
        String jpql = "select a.name from Aoi a";
        return em.createQuery(jpql).getResultList();
    }
}
