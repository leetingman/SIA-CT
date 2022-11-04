package org.lee.sia.jpa;

import lombok.Data;
import javax.persistence.*;

@Entity(name = "Aoi")
@Data
public class AOIEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    private String name;
    @Column(columnDefinition = "Geometry", nullable = true)
//    @Type(type="org.hibernate.spatial.GeometryType")
    private String area;

}
