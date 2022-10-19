package edu.testdemo.spring.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

//Object hien thi thong tin can thiet len cho client
@Data
@Builder
public class DerivativeDTO {
    private String deriCode;

    private String code;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date effectiveDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date expirationDate;
    
    private String underlyingType;
}
