package org.lee.sia.vo;


import com.vividsolutions.jts.geom.Coordinate;
import lombok.Data;

@Data
public class RequestDto {
    private Long id;
    private String name;
    private Coordinate[] area;
}
