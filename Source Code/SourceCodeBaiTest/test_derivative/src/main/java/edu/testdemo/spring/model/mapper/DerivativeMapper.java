package edu.testdemo.spring.model.mapper;

import edu.testdemo.spring.entity.Derivative;
import edu.testdemo.spring.model.dto.DerivativeDTO;

public class DerivativeMapper {

    //Mapper Deri to DeriDTO
    public static DerivativeDTO toDeriDTO(Derivative derivative){
        return DerivativeDTO.builder()
                .deriCode(derivative.getDeriCode())
                .code(derivative.getCode())
                .effectiveDate(derivative.getEffectiveDate())
                .expirationDate(derivative.getExpirationDate())
                .underlyingType(derivative.getUnderlyingType())
                .build();
    }
}
