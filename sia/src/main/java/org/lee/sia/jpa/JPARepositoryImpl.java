package org.lee.sia.jpa;

import com.vividsolutions.jts.io.WKBWriter;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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


    @Override
    @Transactional
    public void saveAoi(AOIEntity entity) {
        em.createNativeQuery("INSERT INTO aoi(name,area) VALUES(?,ST_GeomFromText(?))")
                .setParameter(1,entity.getName())
                .setParameter(2,entity.getArea())
                .executeUpdate();

    }

    @Override
    public void saveRegion(RegionEntity entity) {
        String jpql = "insert into Region(name,area) values(?,ST_GeomFromText(?))";
        em.createNativeQuery(jpql)
                .setParameter(1,entity.getName())
                .setParameter(2,entity.getArea())
                .executeUpdate();
    }
    @Override
    public String aOIFindById(Long id) {
        String jpql = "select a.name from Aoi a where a.id = ?1";
        String name = em.createQuery(jpql).
                setParameter(1,id).toString();

        return name;

    }

    @Override
    public String regionFindById(Long id) {
        String jpql = "select a.name from Aoi a where a.id = ?1";
        String name = em.createQuery(jpql).
                setParameter(1,id).toString();

        return name;

    }
}
