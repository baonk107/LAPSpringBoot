package edu.testdemo.spring.model.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class DerivativeDTO {
    private String deriCode;
    private String code;
    private Date effectiveDate;
    private Date expirationDate;
    private String underlyingType;
}
