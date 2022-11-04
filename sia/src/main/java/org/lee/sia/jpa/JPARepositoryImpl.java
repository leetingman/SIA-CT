package org.lee.sia.jpa;

import com.vividsolutions.jts.io.WKBWriter;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.math.BigInteger;
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
    public Long saveAoi(AOIEntity entity) {
//        em.createNativeQuery("INSERT INTO Aoi(name,area) VALUES(?,ST_GeomFromText(?)) returning Id")
        em.createNativeQuery("INSERT INTO Aoi(name,area) VALUES(?,ST_GeomFromText(?))")
                .setParameter(1,entity.getName())
                .setParameter(2,entity.getArea()).executeUpdate();
        long id =(Long)em.createQuery("select max(a.Id) from Aoi a").getSingleResult();


        return id;

    }

    @Override
    @Transactional
    public Long saveRegion(RegionEntity entity) {
        String jpql = "insert into Region(name,area) values(?,ST_GeomFromText(?))";

        Query q =em.createNativeQuery(jpql, Long.class)
                .setParameter(1,entity.getName())
                .setParameter(2,entity.getArea())
                ;
        q.executeUpdate();

        long id =(Long)em.createQuery("select max(r.id) from Region r").getSingleResult();

        return id;
    }


    @Override
    public String aOIFindById(Long id) {
        String jpql = "select a.name from Aoi a where a.Id = ?1";
        String name = em.createQuery(jpql).
                setParameter(1,id).toString();

        return name;

    }

    @Override
    public List<Object> findAoisByArea(String area) {
        System.out.println(area);
        List <Object> list=
                em.createNativeQuery("select a.Id as Id,a.name as name, ST_asText(a.area) as area from aoi a where ST_Within(a.area,ST_GeomFromText(?,4326)) = false ",AOIEntity.class)
                        .setParameter(1,area)
                        .getResultList();
        return list;
    }

    @Override
    public String regionFindById(Long id) {
        String jpql ="select ST_asText(r.area) from Region r where r.id = ?1";
        String area=em.createNativeQuery(jpql).
                setParameter(1,Long.valueOf(1)).getSingleResult().toString();
        return area;

    }

    @Override
    public Object getAreaByDistance(String point) {
        Object o=
        em.createNativeQuery("select a.Id as Id,a.name as name, ST_asText(a.area) as area from aoi a order by ST_Distance(a.area,ST_GeomFromText(?,4326)) limit 1",AOIEntity.class)
                .setParameter(1,point)
                .getSingleResult();
        return o;
    }
}
