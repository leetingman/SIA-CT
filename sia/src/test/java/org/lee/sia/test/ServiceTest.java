package org.lee.sia.test;


import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.geom.PrecisionModel;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lee.sia.jpa.AOIEntity;
import org.lee.sia.jpa.RegionEntity;
import org.lee.sia.service.SIAService;
import org.lee.sia.vo.RequestDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class ServiceTest {


    @Autowired
    SIAService service;

    /**
     * service findAll() unit test
     * aoi 관심지역 이름 list 출력
     * @throws Exception
     * 첫번째 인덱스 북한산
     */
    @Test
    public void findAll() throws Exception{
        List<AOIEntity> result =
                service.findAll();
        assertThat(result.get(0).getName()).isEqualTo("북한산");
    }



    @Test
    public void saveAoi(){
        AOIEntity entity=mockAoiDataBuilder();
        RequestDto dto;
//        service.saveAoi(entity);
//        assertThat(service.aOIFindById(entity.getId())).isEqualTo(entity.getName());
    }

    @Test
    public void saveRegion(){

        RegionEntity entity=mockRegionDataBuilder();
//        service.saveRegion(entity);
//        assertThat(service.regionFindById(entity.getId())).isEqualTo(entity.getName());

    }


    //RegionEntity init
    public RegionEntity mockRegionDataBuilder(){


        RegionEntity entity=new RegionEntity();
        //point to Polygon
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate[] coords =new Coordinate[4];

        coords[0]=new Coordinate(127.02,37.742);
        coords[1]=new Coordinate(127.023,37.664);
        coords[2]=new Coordinate(126.945,37.692);
        coords[3]=new Coordinate(127.02,37.742);

        Polygon poly = factory.createPolygon(coords);

        entity = new RegionEntity();
        entity.setName("북한산");
//        entity.setArea(poly);
        entity.setArea(poly.toString());

        return entity;
    }


    //AOIEntity init
    public AOIEntity mockAoiDataBuilder(){
        AOIEntity entity=new AOIEntity();
        //point to Polygon
        GeometryFactory factory = new GeometryFactory(new PrecisionModel(), 4326);
        Coordinate[] coords =new Coordinate[4];

        coords[0]=new Coordinate(127.02,37.742);
        coords[1]=new Coordinate(127.023,37.664);
        coords[2]=new Coordinate(126.945,37.692);
        coords[3]=new Coordinate(127.02,37.742);

        Polygon poly = factory.createPolygon(coords);

        entity = new AOIEntity();
        entity.setName("북한산");
//        entity.setArea(poly);
        entity.setArea(poly.toString());

        return entity;
    }
}
