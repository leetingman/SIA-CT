package org.lee.sia.service;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.PrecisionModel;
import com.vividsolutions.jts.io.ParseException;
import com.vividsolutions.jts.io.WKTReader;
import com.vividsolutions.jts.io.WKTWriter;
import org.lee.sia.jpa.AOIEntity;
import org.lee.sia.jpa.JPARepository;
import org.lee.sia.jpa.RegionEntity;
import org.lee.sia.vo.RequestDto;
import org.lee.sia.vo.ResponseAnRDto;
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
    public ResponseDto saveAoi(RequestDto dto){
        //mapping
        ModelMapper mapper = new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        AOIEntity entity = mapper.map(dto,AOIEntity.class);
        entity.setArea(areaToWKT(dto.getArea()));
        //save
        Long id=repository.saveAoi(entity);
        //return result
        ResponseDto result =new ResponseDto();
        result.setId(id);
        return result;

    }
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

//    public String aOIFindById(Long id){return repository.aOIFindById(id);}
//    public String regionFindById(Long id){return repository.regionFindById(id);}

    //Logic Area --> WKT

    public ResponseAnRDto getInfo(Long id) {
        ResponseAnRDto dto=new ResponseAnRDto();
        //id로 지역찾기
        String area = repository.regionFindById(id);
        //
        List<Object> list = repository.findAoisByArea(area);
        AOIEntity entity;
        if(!list.isEmpty()){
            ModelMapper mapper =new ModelMapper();
            mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
            entity=mapper.map(list.get(0),AOIEntity.class);
            System.out.println(entity.getId()+entity.getName()+entity.getArea());//after fix logs

            dto=ResponseAnRDto.builder()
                    .id(entity.getId())
                    .name(entity.getName())
                    .area(wktToArea(entity.getArea()))
                    .build();
        }
        return dto;


    }

    public String areaToWKT(Coordinate[] area){
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        WKTWriter wf =new WKTWriter();
        Geometry geom=factory.createPolygon(area);
        return wf.write(geom);
    }

    public Coordinate[] wktToArea(String wkt){
        WKTReader wr =new WKTReader();
        Coordinate[] coord ;
        Geometry geom= null;
        try {
            geom = wr.read(wkt);
            coord=geom.getCoordinates();
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        return coord;
    }

    public ResponseAnRDto getAreaByDistance(Double x, Double y) {
        ResponseAnRDto dto=new ResponseAnRDto();

        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        WKTWriter wf =new WKTWriter();
        Coordinate coord = new Coordinate();
        coord.x=x;
        coord.y=y;
        Geometry geom=factory.createPoint(coord);
        String point = wf.write(geom);
        Object o=repository.getAreaByDistance(point);
        AOIEntity entity ;
        ModelMapper mapper =new ModelMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        entity=mapper.map(o,AOIEntity.class);
        System.out.println(entity.getId()+entity.getName()+entity.getArea());//after fix logs
        dto=ResponseAnRDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .area(wktToArea(entity.getArea()))
                .build();

        return dto;



    }
}
