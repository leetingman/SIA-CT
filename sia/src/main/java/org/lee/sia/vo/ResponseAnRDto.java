package org.lee.sia.vo;

import com.vividsolutions.jts.geom.Coordinate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ResponseAnRDto {
    Long id;
    String name;
    Coordinate[] area;
}
