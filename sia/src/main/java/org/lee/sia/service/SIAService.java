package org.lee.sia.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.WKTWriter;
import org.lee.sia.jpa.AOIEntity;
import org.lee.sia.jpa.JPARepository;
import org.lee.sia.jpa.RegionEntity;
import org.lee.sia.vo.RequestDto;
import org.lee.sia.vo.ResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SIAService {
    @Autowired
    private JPARepository repository;


    public List<AOIEntity> findAll(){return repository.findAll(); }
    public void saveAoi(AOIEntity entity){repository.saveAoi(entity);}
    public ResponseDto saveRegion(RequestDto dto)
    {
        //Mapping
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        RegionEntity entity = mapper.map(dto,RegionEntity.class);
        entity.setArea(areaToWKT(dto.getArea()));
        //save
        Long id = repository.saveRegion(entity);
        //return id
        ResponseDto result =new ResponseDto();
        result.setId(id);
        return result;
    }

    public String aOIFindById(Long id){return repository.aOIFindById(id);}
    public String regionFindById(Long id){return repository.regionFindById(id);}

    //Logic Area --> WKT
    public String areaToWKT(Coordinate[] area){
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        WKTWriter wf =new WKTWriter();
        Geometry geom=factory.createPolygon(area);
        return wf.write(geom);
    }


}
