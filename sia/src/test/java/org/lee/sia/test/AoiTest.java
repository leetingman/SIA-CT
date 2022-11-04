package org.lee.sia.test;


import org.hibernate.service.spi.InjectService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.lee.sia.service.SIAService;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@SpringBootTest
public class AoiTest {
    @Autowired
    private SIAService service;

//    private final SIAService siaService;

//    @Autowired
//    public AoiTest(SIAService siaService) {
//        this.siaService = siaService;
//    }

    @Test
    public void findAllTest(){
        service.findAll();
    }

}
