package org.lee.sia.dto;

import com.vividsolutions.jts.geom.Polygon;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class AOIDto {
    private Long id;
    private String name;
    private Polygon area;
}
