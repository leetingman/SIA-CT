package org.lee.sia.vo;

import com.vividsolutions.jts.geom.Coordinate;
import lombok.Data;

@Data
public class ResponseAnRDto {
    String id;
    String name;
    Coordinate[] area;
}
