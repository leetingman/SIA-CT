package org.lee.sia.jpa;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

//The JPAUtil class creates the EntityManagerFactory for the application,
// and provides a method to create EntityManager instances.
public interface JPARepository  {
    List<AOIEntity> findAll();
    Long saveAoi(AOIEntity entity);
    String aOIFindById(Long id);
    String regionFindById(Long id);

    Long saveRegion(RegionEntity entity);

    List<Object> findAoisByArea(String area);

    Object getAreaByDistance(String point);
}
