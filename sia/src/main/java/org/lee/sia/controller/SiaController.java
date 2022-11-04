package org.lee.sia.controller;

import org.lee.sia.service.SIAService;
import org.lee.sia.vo.RequestDto;
import org.lee.sia.vo.ResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SiaController {
    @Autowired
    SIAService service;
    @PostMapping("/regions")
    public ResponseEntity<ResponseDto> saveRegion(@RequestBody RequestDto dto){
        ResponseDto result=service.saveRegion(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }


}
